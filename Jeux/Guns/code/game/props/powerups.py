import pygame as pg
from code.game.utils import load_sprite
import random


class Powerup(pg.sprite.Sprite):
    last_id = 0

    def __init__(self, x, y, kind=0, timer=0):
        super().__init__()
        Powerup.last_id += 1
        self.id = Powerup.last_id
        self.kind = kind  # 0 = health, 1 = speed, 2 = invulnerability

        colors = ["keur", "haile", "boulette"]
        self.image = pg.transform.scale_by(load_sprite(colors[kind], "powerups"), 0.4)
        self.rect = self.image.get_rect()
        self.rect.x = x
        self.rect.y = y

        self.true_x, self.true_y = x, y

        self.timer = timer
        durations = [
            0.0,
            3.0,
            4.0,
        ]  # Health: No duration, Speed: 3s, Invulnerability: 4s
        self.duration = durations[self.kind]
        sounds = [None, None, None]
        self.sound = sounds[self.kind]

    def update(self, player, dx, dy, eaten):
        self.update_position(dx, dy)
        # self.check_player_collision(player)
        if eaten == self.id:
            self.kill()

    def update_position(self, dx, dy):
        self.rect.x = self.true_x + dx
        self.rect.y = self.true_y + dy

    def check_player_collision(self, player):
        if pg.sprite.collide_rect(self, player):
            self.handle_player_collision()

    def handle_player_collision(self):
        # self.true_x, self.true_y = -100000, -100000
        self.image.fill((0, 0, 0))
        # play sound

    def draw(self, screen):
        screen.blit(self.image, self.rect)


def generate_powerup(terrain_sprites, dx, dy):
    x = random.randint(0, 2750 - 30)
    y = random.randint(0, 1200 - 30)
    i = 2 * int(random.random()) - 1
    j = 2 * int(random.random()) - 1

    powerup = Powerup(x, y)
    powerup.update_position(dx, dy)
    for sprite in terrain_sprites:
        if pg.sprite.collide_rect(powerup, sprite):
            x += i * sprite.rect.width
            y += j * sprite.rect.height

    kind_probabilities = [1, 5, 2]  # Health: 12.5%, Speed: 62.5%, Invulnerability: 25%
    kind = random.choices(range(3), weights=kind_probabilities)[0]
    powerup = Powerup(x, y, kind)
    print(f"Generated a P.O. of kind {powerup.kind}")
    return powerup
