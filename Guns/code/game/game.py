from code.game.levels import Level
from code.game.player import *
from code.game.props.heart import *
from code.network import Network
from code.game.utils import *
from code.game.props.powerups import *
import numpy as np


class NetworkPlaceholder:
    def __init__(self):
        self.id = 0


class Game:
    def __init__(self, screen, online, keybinds, escape_callback=None):
        self.escape_callback = escape_callback
        self.net = Network() if online else NetworkPlaceholder()
        self.screen = screen

        self.online = online
        self.level = Level("./code/data/level_0.csv", self.screen)
        self.powerups = pg.sprite.Group()
        self.last_chance_on = False
        id = int(self.net.id)
        x = 600 if id == 0 else 1600
        self.player = LocalPlayer(x, 50, id=id, keybinds=keybinds)
        if online:
            self.other_player = GraphicalPlayer(0, 0, (id + 1) % 2)
        else:
            self.other_player = None

        self.waiting_screen = load_sprite("waiting", "menus")

        self.lives = 5

        self.score_bar = ScoreBar()

        pg.mixer.music.load(f"./assets/audio_game/track1.mp3")

    def last_chance(self):
        if not self.last_chance_on:
            if self.player.death_count == 4 or (
                self.other_player is not None and self.other_player.death_count == 4
            ):
                self.last_chance_on = True
                background_id = 1
                pg.mixer.music.stop()
                pg.mixer.music.load("./assets/audio_game/last_chance.wav")
                # pg.mixer.music.set_volume(0.3)
                pg.mixer.music.play()
            else:
                background_id = 0
                self.last_chance_on = False
            self.level.change_background(backgound_id=background_id)

        if self.player.death_count != 4 and (
            self.other_player is None or self.other_player.death_count != 4
        ):
            self.last_chance_on = False

    def generator_powerup(self):
        gen = np.random.rand()
        if gen < 0.003:
            powerup = generate_powerup(
                self.level.terrain_sprites.sprites(), self.player.dx, self.player.dy
            )
        else:
            powerup = None
        return powerup

    def run(self, music_paused=False):
        self.music_paused = music_paused
        self.waiting()

        clock = pg.time.Clock()
        keep_running = True

        while keep_running:
            clock.tick(120)
            current_time = time.time()

            if not pg.mixer.music.get_busy() and not self.music_paused:
                pg.mixer.music.play(-1)

            for event in pg.event.get():
                if event.type == pg.QUIT:
                    keep_running = False

                if event.type == pg.KEYDOWN and event.key == pg.K_ESCAPE:
                    if self.escape_callback:
                        self.escape_callback()

            self.player.health_update(self.other_player)
            if self.online:
                self.other_player.bullet_collision(self.player.bullets)

            # local update
            if int(self.net.id) == 0:
                powerup = self.generator_powerup()
            else:
                powerup = None
            if powerup is not None:
                self.powerups.add(powerup)
            eaten = self.player.update(
                self.screen,
                self.level.terrain_sprites.sprites(),
                self.powerups,
                current_time,
            )
            self.level.update(self.player.dx, self.player.dy)
            self.last_chance()
            self.level.draw()
            self.powerups.update(
                self.player,
                self.player.dx,
                self.player.dy,
                eaten,
            )
            if eaten != -1:
                # print("Powerup mangé ", eaten)
                pass
            # print(clock.get_fps())
            # network
            if self.online:
                update_data = self.parse_data(self.send_data(powerup, eaten))
                # update powerups
                if update_data[-4] != -123456:
                    self.powerups.add(
                        Powerup(
                            update_data[-4],
                            update_data[-3],
                            update_data[-2],
                        )
                    )
                self.other_player.powerup_collision(self.powerups)
                # update other player's data
                self.other_player.receive_data(update_data[:-4])
                # update other players' bullets and positions on screen
                self.other_player.update(self.player.dx, self.player.dy)
                self.other_player.draw(self.screen)
                # score bar update
                health_1 = self.player.health
                health_2 = self.other_player.health
                score_1 = self.other_player.death_count
                score_2 = self.player.death_count
                if self.player.id == 0:
                    self.score_bar.update(health_1, health_2, score_1, score_2)
                else:
                    self.score_bar.update(health_2, health_1, score_2, score_1)
                keep_running = (
                    self.player.death_count < self.lives
                    and self.other_player.death_count < self.lives
                )

            self.powerups.draw(self.screen)
            # local player is always on top
            self.player.draw(self.screen)
            if self.online:
                self.score_bar.draw(self.screen)

            pg.display.update()
            self.last_chance()

        return self.player.death_count, self.other_player.death_count

    def waiting(self):
        if self.online:
            clock = pg.time.Clock()
            self.screen.blit(self.waiting_screen, (0, 0))
            pg.display.update()

            px = -123456

            while px == -123456:
                px = self.parse_data(self.send_data())[0]
                clock.tick(5)

    def send_data(self, powerup=None, eaten=-1):
        """
        Send infos to server
        :return: None
        """
        pre_data = [
            self.player.true_x,
            self.player.true_y,
            self.player.facing_right,
            self.player.shooting,
            self.player.gun_angle,
            self.player.health,
            self.player.death_count,
            self.player.state,
            self.player.sent_particles,
        ]
        if powerup is not None:
            pre_data.append(powerup.true_x)
            pre_data.append(powerup.true_y)
            pre_data.append(powerup.kind)
        else:
            pre_data.append(-123456)
            pre_data.append(-123456)
            pre_data.append(0)
        pre_data.append(eaten)
        data = str(self.net.id) + ":"  # id
        data += str(pre_data[0]) + ","  # x
        data += str(pre_data[1]) + ","  # y
        data += str(int(pre_data[2])) + ","  # facing_right
        data += str(int(pre_data[3])) + ","  # shooting
        data += str(pre_data[4]) + ","  # gun_angle
        data += str(pre_data[5]) + ","  # health
        data += str(pre_data[6]) + ","  # death_count
        data += str(pre_data[7]) + ","  # state
        data += pre_data[8] + ","  # sent_particles
        data += str(pre_data[9]) + ","  # powerup x
        data += str(pre_data[10]) + ","  # powerup y
        data += str(pre_data[11]) + ","  # powerup kind
        data += str(pre_data[12])  # powerup eaten

        reply = self.net.send(data)
        return reply

    @staticmethod
    def parse_data(data):
        try:
            d = data.split(":")[1].split(",")
            return (
                int(d[0]),  # player y
                int(d[1]),  # player x
                bool(int(d[2])),  # facing right
                bool(int(d[3])),  # shooting
                float(d[4]),  # gun angle
                int(d[5]),  # health
                int(d[6]),  # death count
                str(d[7]),  # state
                str(d[8]),  # sent_particles
                int(d[9]),  # powerup x
                int(d[10]),  # powerup y
                int(d[11]),  # powerup kind
                float(d[12]),  # powerup eaten
            )
        except:
            return [
                -123456,  # player x
                0,  # player y
                False,  # facing right
                False,  # shooting
                0.0,  # gun angle
                4,  # health
                0,  # death count
                "",  # state
                "",  # sent_particles
                -123456,  # powerup x
                -123456,  # powerup y
                0,  # powerup kind
                -1.0,  # powerup eaten
            ]
