import math
import pygame as pg
from code.game.utils import load_sprite


class Bullet(pg.sprite.Sprite):
    def __init__(self, x, y, angle, id, gun_size=64):
        pg.sprite.Sprite.__init__(self)
        bullet_image = pg.transform.scale_by(
            load_sprite(f"bullet_{id}", folder=""), 0.3
        ).convert_alpha()
        deg_angle = math.degrees(angle)
        self.image = pg.transform.rotate(bullet_image, -deg_angle)
        self.speed_bullet = 50

        self.ca = math.cos(angle)
        self.sa = math.sin(angle)
        x += gun_size * self.ca
        y += gun_size * self.sa
        self.rect = self.image.get_rect()
        self.rect.center = (x, y)
        self.true_x, self.true_y = self.rect.x, self.rect.y
        self.mask = pg.mask.from_surface(self.image)
        self.image = pg.transform.rotate(
            pg.transform.scale_by(
                load_sprite(f"bullet_{id}_visible", folder=""), 0.3
            ).convert_alpha(),
            -deg_angle,
        )
        self.sound = pg.mixer.Sound("./assets/audio_game/sound_shoot.wav")
        self.hit_sound = pg.mixer.Sound("./assets/audio_game/sound_hit.wav")
        self.sound.set_volume(0.2)

    def update(self, dx=0, dy=0):
        self.true_x += self.ca * self.speed_bullet
        self.true_y += self.sa * self.speed_bullet
        self.rect.x = self.true_x + dx
        self.rect.y = self.true_y + dy
