import time
from code.game.props.bullet import *
from code.game.props.hook import Hook
from code.game.utils import *


class GraphicalPlayer(pg.sprite.Sprite):
    def __init__(self, x, y, id):
        super().__init__()
        # Player attributes
        pos = (x, y)
        self.id = id
        self.image_list = [
            load_sprite(f"character_{id}", folder="").convert_alpha(),
        ]
        self.image = self.image_list[0]
        self.rect = self.image.get_rect(topleft=pos)
        self.facing_right = True

        # Animation states load images
        self.animation_data = {
            "idle": {
                "images": [
                    load_sprite(f"idle_{id}_{i}", folder="idle").convert_alpha()
                    for i in range(1, 15)
                ],
                "speed": 0.15,
            },
            "run": {
                "images": [
                    load_sprite(f"run_{id}_{i}", folder="run").convert_alpha()
                    for i in range(1, 9)
                ],
                "speed": 0.07,
            },
            "jump": {
                "images": [
                    load_sprite(f"jump_{id}_{i}", folder="jump").convert_alpha()
                    for i in range(1, 9)
                ],
                "speed": 0.07,
            },
        }

        self.animation_frame = 0
        self.last_animation_time = time.time()
        self.previous_state = None

        # Player stats
        self.kill_count = 0
        self.death_count = 0
        self.health = 4

        self.true_x, self.true_y = self.rect.x, self.rect.y

        # Gun attributes
        self.shooting = False
        self.gun_image = pg.transform.scale(
            load_sprite("gun", folder=""), (128, 128)
        ).convert_alpha()
        self.gun_angle = 0

        self.bullets = pg.sprite.Group()
        self.particles_effects = pg.sprite.Group()

        # Nothing to see here
        # self.hook = Hook()
        # self.rope_image = load_sprite("rope", "").convert_alpha()
        # self.hook_rope_rect = self.rope_image.get_rect()
        # self.hook_image = load_sprite("hook", "").convert_alpha()

        self.dx = 0
        self.dy = 0

        self.sent_particles = ""

    def draw_gun(self, window, dx=0, dy=0):
        gun_position = self.rect.center
        angle = math.degrees(self.gun_angle)
        flip = not (-90 <= angle <= 90)
        gun = pg.transform.flip(self.gun_image, False, flip)
        rotated_gun = pg.transform.rotate(gun, -angle)
        rotated_rect = rotated_gun.get_rect(center=gun_position)
        window.blit(rotated_gun, (rotated_rect.x + dx, rotated_rect.y + dy))

    def hook_display(self):
        angle = math.degrees(self.hook.angle)
        flip = not (-90 <= angle <= 90)
        print(flip)
        rope_image = pg.transform.flip(self.rope_image, False, flip)
        hook_image = pg.transform.flip(self.hook_image, False, flip)
        rect = rope_image.get_rect()
        rect.width = self.hook.current_length
        cropped_rope_image = self.rope_image.subsurface(rect)
        rotated_rope = pg.transform.rotate(cropped_rope_image, -angle)
        rope_rect = rotated_rope.get_rect()
        rotated_hook = pg.transform.rotate(hook_image, -angle + 90)
        return rotated_rope, rotated_hook, rope_rect

    def draw_hook(self, window):
        rope, hook, rope_rect = self.hook_display()
        if self.hook.current_length > self.hook.min_length:
            end_position = (
                rope_rect.midleft
                if self.hook.angle < math.pi / 2
                else rope_rect.midright
            )
            window.blit(rope, self.rect.center)
            window.blit(hook, end_position)

    def shoot(self):
        if self.shooting:
            bullet = Bullet(
                self.rect.centerx, self.rect.centery, self.gun_angle, self.id
            )
            self.bullets.add(bullet)
            bullet.sound.play()

    def flip_image(self):
        self.image = pg.transform.flip(
            self.image, not self.facing_right, False
        ).convert_alpha()

    def draw(self, window):
        self.shoot()
        self.bullets.update()
        self.bullets.draw(window)
        self.draw_gun(window)
        self.update_animation()

        animation_state_data = self.animation_data.get(self.state, None)
        if animation_state_data:
            animation_images = animation_state_data["images"]

            self.image = animation_images[self.animation_frame]

        if not self.facing_right:
            self.flip_image()

        if self.sent_particles == "jump":
            particle_effect = ParticleEffect(
                (self.rect.midbottom[0], self.rect.midbottom[1] + 20), "jump"
            )
            self.particles_effects.add(particle_effect)

        window.blit(self.image, (self.rect.x, self.rect.y))
        self.particles_effects.draw(window)

    def receive_data(self, data):
        (
            self.true_x,
            self.true_y,
            self.facing_right,
            self.shooting,
            self.gun_angle,
            self.health,
            self.death_count,
            self.state,
            self.sent_particles,
        ) = data

    def bullet_collision(self, bullets):
        for sprite in bullets:
            if pg.sprite.collide_mask(self, sprite):
                sprite.hit_sound.play()
                sprite.kill()
                return True
        return False

    def update_animation(self):
        current_time = time.time()
        animation_state_data = self.animation_data.get(self.state, None)

        if animation_state_data:
            if self.state != self.previous_state:
                self.previous_state = self.state
                self.animation_frame = 0
            animation_images = animation_state_data["images"]
            animation_speed = animation_state_data["speed"]

            if current_time - self.last_animation_time > animation_speed:
                self.last_animation_time = current_time
                self.animation_frame = (self.animation_frame + 1) % len(
                    animation_images
                )

    def update(self, dx, dy):
        self.particles_effects.update()
        self.rect.x = self.true_x + dx
        self.rect.y = self.true_y + dy

    def powerup_collision(self, powerups):
        for powerup in powerups:
            if pg.sprite.collide_rect(self, powerup):
                print(f"ID: {powerup.id}")
                powerup.kill()


class LocalPlayer(GraphicalPlayer):
    def __init__(self, x, y, id, keybinds):
        super().__init__(x, y, id)
        self.keybinds = keybinds
        # collision handling
        self.horizontal_tolerance = 10
        # player states
        self.stuck_right = False
        self.stuck_left = False
        self.state = "idle"
        self.on_ground = False
        self.on_jump = False
        # player movement
        self.velocity = np.array([0, 0])
        self.speed = 10
        self.jump_key_released = True
        self.jump_count = 0
        self.gravity = 1.2
        self.jump_speed = -22
        self.jump_count = 0
        self.jump_key_released = True
        self.sound_jump = pg.mixer.Sound("./assets/audio_game/sound_jump.mp3")
        self.sound_dj = pg.mixer.Sound("./assets/audio_game/sound_dj.mp3")
        self.sound_jump.set_volume(0.3)
        self.sound_dj.set_volume(0.3)

        self.active_powerups = []

        self.invulnerable = False

        # gun
        self.last_shoot = 0.0
        self.gun_cooldown = 0.3  # in seconds
        # oh le grappling est parti avec wesh

    def get_gun_angle(self):
        gun_position = self.rect.center
        mouse_x, mouse_y = pg.mouse.get_pos()
        self.gun_angle = math.atan2(
            mouse_y - gun_position[1], mouse_x - gun_position[0]
        )

    def vertical_collision(self, terrain_sprites):
        for sprite in terrain_sprites:
            if pg.sprite.collide_rect(self, sprite):
                if self.velocity[1] > 0:
                    self.rect.bottom = sprite.rect.top
                    self.jump_count = 0
                    self.velocity[1] = 0
                    self.on_ground = True
                    self.on_jump = False
                elif self.velocity[1] < 0:
                    self.rect.top = sprite.rect.bottom
                    self.velocity[
                        1
                    ] *= (
                        -1
                    )  # non-elastic collision - non on arrête les conneries, c'est nul et chiant

    def horizontal_collision(self, terrain_sprites, sign):
        self.rect.x += sign * self.horizontal_tolerance

        self.speed /= 2
        self.force_move(sign * self.speed, 0)
        would_collide = False
        self.stuck_right = False
        self.stuck_left = False
        for sprite in terrain_sprites:
            if pg.sprite.collide_rect(self, sprite):
                would_collide = True
                if sign == 1:
                    self.stuck_right = True
                else:
                    self.stuck_left = True
                break
        self.force_move(-sign * self.speed, 0)
        self.speed *= 2
        self.rect.x -= sign * self.horizontal_tolerance
        return would_collide

    def powerup_collision(self, powerups, current_time):
        eaten = -1
        for powerup in powerups:
            if pg.sprite.collide_rect(self, powerup):
                powerup.handle_player_collision()
                powerup.timer = current_time
                eaten = powerup.id
                stacked = False
                for i, (kind, timer) in enumerate(self.active_powerups):
                    if kind == powerup.kind:
                        timer += powerup.duration
                        self.active_powerups[i] = (kind, timer)
                        stacked = True
                        break
                if not stacked:
                    self.active_powerups.append(
                        (powerup.kind, current_time + powerup.duration)
                    )
        return eaten

    def get_inputs(self, terrain, current_time):
        keys = pg.key.get_pressed()
        mouse_buttons = pg.mouse.get_pressed()
        mouse_x = pg.mouse.get_pos()[0]

        if mouse_buttons[0] and current_time - self.last_shoot > self.gun_cooldown:
            self.shooting = True
            self.last_shoot = current_time
        else:
            self.shooting = False

        if mouse_x >= self.rect.centerx:
            self.facing_right = True
        else:
            self.facing_right = False

        would_collide = self.horizontal_collision(
            terrain, 1
        ) or self.horizontal_collision(terrain, -1)
        # self.on_jump = not would_collide
        if keys[self.keybinds["right"]] and (not would_collide or self.stuck_left):
            self.velocity[0] = self.speed
            self.facing_right = True
        elif keys[self.keybinds["left"]] and (not would_collide or self.stuck_right):
            self.velocity[0] = -self.speed
            self.facing_right = False

        else:
            self.velocity[0] = 0

        if keys[self.keybinds["jump"]]:
            if self.jump_key_released:
                self.jump()
                self.jump_key_released = False
                self.on_ground = False
        else:
            self.jump_key_released = True

        if keys[self.keybinds["down"]] and not would_collide:
            if self.velocity[1] < 3.5:
                self.gravity += 1
        else:
            self.gravity = 1.1

    def apply_forces(self):
        if self.velocity[1] < 0:
            self.velocity[1] += self.gravity / 1.5  # gradually decrease upward velocity
        else:
            self.velocity[1] += self.gravity

    def apply_powerups(self, current_time):
        for i, (kind, timer) in enumerate(self.active_powerups):
            if current_time > timer:
                if kind == 1:
                    self.speed = 10
                    # self.jump_speed = -22
                elif kind == 2:
                    self.invulnerable = False
                del self.active_powerups[i]
            else:
                if kind == 0:
                    self.health = min(self.health + 1, 4)
                elif kind == 1:
                    self.speed = 20
                    # self.jump_speed = -30
                else:
                    self.invulnerable = True

    def jump(self):
        if self.jump_count < 2:
            self.velocity[1] = self.jump_speed
            self.jump_count += 1
            if self.jump_count == 1:
                self.sound_jump.play()
            if self.jump_count == 2:
                self.sound_dj.play()
                particle_effect = ParticleEffect(
                    (self.rect.midbottom[0], self.rect.midbottom[1] + 20), "jump"
                )
                self.particles_effects.add(particle_effect)
                self.sent_particles = "jump"

    def move(self):
        self.rect.x += self.velocity[0]
        self.rect.y += self.velocity[1]

    def force_move(self, dx, dy):
        self.rect.x += dx
        self.rect.y += dy

    def force_position(self, x, y):
        self.rect.x = x
        self.rect.y = y

    def hook_movement(self):
        pass

    def update(self, window, terrain, powerups, current_time):
        self.sent_particles = ""
        self.get_inputs(terrain, current_time)
        self.apply_forces()
        self.move()
        self.vertical_collision(terrain)
        eaten = self.powerup_collision(powerups, current_time)
        self.apply_powerups(current_time)

        self.bullets.update()
        self.get_gun_angle()
        self.shoot()

        self.particles_effects.update()

        if self.velocity[0] == 0 and self.velocity[1] == 0 and self.on_ground:
            self.state = "idle"
        elif self.velocity[0] != 0 and self.on_ground:
            self.state = "run"
        elif self.velocity[1] < 0:
            self.on_jump = True
            self.state = "jump"

        if self.state != getattr(self, "previous_state", None):
            self.previous_state = self.state
            self.animation_frame = 0

        self.true_x += self.rect.x - window.get_width() // 2
        self.true_y += self.rect.y - window.get_height() // 2
        self.rect.x = window.get_width() // 2
        self.rect.y = window.get_height() // 2

        self.dx = -self.true_x + self.rect.x
        self.dy = -self.true_y + self.rect.y

        return eaten

    def health_update(self, other_player):
        if other_player is not None and not self.invulnerable:
            self.health -= int(self.bullet_collision(other_player.bullets))

        if self.true_y > 2000 or self.health <= 0:
            self.health = 4
            self.death_count += 1
            self.true_y = -20
            self.velocity[1] = 0

    def draw(self, window):
        self.bullets.draw(window)
        self.draw_gun(window)

        self.update_animation()

        animation_state_data = self.animation_data.get(self.state, None)
        if animation_state_data:
            animation_images = animation_state_data["images"]

            self.image = animation_images[self.animation_frame]

        if not self.facing_right:
            self.flip_image()
        window.blit(self.image, self.rect.topleft)
        self.particles_effects.draw(window)
