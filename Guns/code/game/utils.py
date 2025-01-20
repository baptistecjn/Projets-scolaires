import os
from csv import reader

import numpy as np
import pygame as pg

tile_size = 64


path = os.getcwd()


def load_sprite(id, folder=""):
    try:
        return pg.image.load(path + "/assets/graphics/{folder}/{id}.jpg")
    except FileNotFoundError:
        return pg.image.load(path + f"/assets/graphics/{folder}/{id}.png")


def import_csv_layout(path):
    terrain_map = []
    with open(path) as map:
        level = reader(map, delimiter=",")
        for row in level:
            terrain_map.append(list(row))
        return terrain_map


def import_cut_graphics(id, folder):
    surface = load_sprite(id, folder)
    tile_num_x = int(surface.get_size()[0] / tile_size)
    tile_num_y = int(surface.get_size()[1] / tile_size)

    cut_tiles = []
    for row in range(tile_num_y):
        for col in range(tile_num_x):
            x = col * tile_size
            y = row * tile_size
            new_surf = pg.Surface((tile_size, tile_size), flags=pg.SRCALPHA)
            new_surf.blit(surface, (0, 0), pg.Rect(x, y, tile_size, tile_size))
            cut_tiles.append(new_surf)

    return cut_tiles


def make_playlist():
    files = os.listdir("./assets/tracks/")
    np.random.shuffle(files)
    return files


class Button(pg.sprite.Sprite):
    def __init__(self, x, y, id, folder="", size=None, sound=None):
        pg.sprite.Sprite.__init__(self)
        self.id = id
        self.folder = folder
        self.img_list = [load_sprite(id, folder), load_sprite(id + "_h", folder)]
        img = self.img_list[0]

        if size is not None:
            for i in range(len(self.img_list)):
                self.img_list[i] = pg.transform.scale(
                    self.img_list[i],
                    (
                        int(img.get_width() * size),
                        int(img.get_height() * size),
                    ),
                )

        self.img = self.img_list[0]
        self.rect = self.img.get_rect()
        self.rect.x = x
        self.rect.y = y
        self.sound = sound
        self.sound_has_played = False

    def reload_sprite(self):
        self.img = load_sprite(self.id, self.folder)

    def blit(self, window):
        window.blit(self.img, (self.rect.x, self.rect.y))

    def is_clicked(self, pos):
        return self.rect.collidepoint(pos)

    def handle_hover(self, is_hovered):
        if is_hovered:
            if self.sound is not None and not self.sound_has_played:
                self.sound.play()
                self.sound_has_played = True
            self.img = self.img_list[1]
        else:
            self.img = self.img_list[0]
            self.sound_has_played = False


class Volume_Button(pg.sprite.Sprite):
    muted = False

    def __init__(self, x, y, id, folder="", size=None, sound=None):
        pg.sprite.Sprite.__init__(self)
        self.id = id
        self.folder = folder
        self.img_list = [load_sprite(id, folder), load_sprite(id + "_mute", folder)]
        img = self.img_list[0]
        self.active = Volume_Button.muted

        if size is not None:
            for i in range(len(self.img_list)):
                self.img_list[i] = pg.transform.scale(
                    self.img_list[i],
                    (
                        int(img.get_width() * size),
                        int(img.get_height() * size),
                    ),
                )

        self.img = self.img_list[1] if self.active else self.img_list[0]
        self.rect = self.img.get_rect()
        self.rect.x = x
        self.rect.y = y
        self.sound = sound
        self.sound_has_played = False

    def reload_sprite(self):
        self.img = load_sprite(self.id, self.folder)

    def blit(self, window):
        window.blit(self.img, (self.rect.x, self.rect.y))

    def is_clicked(self, pos):
        return self.rect.collidepoint(pos)

    def is_active(self):
        return self.active

    def toggle(self):
        Volume_Button.muted = not Volume_Button.muted
        self.active = Volume_Button.muted
        self.img = self.img_list[1] if self.active else self.img_list[0]


class KeyBindingText(pg.sprite.Sprite):
    def __init__(self, x, y, action, default_key, font_size=24, color=(0, 0, 0)):
        pg.sprite.Sprite.__init__(self)
        self.action = action
        self.key = default_key
        self.font_size = font_size
        self.color = color
        self.font = pg.font.Font(None, self.font_size)
        self.update_text()
        self.rect = self.image.get_rect(topleft=(x, y))

    def update_text(self):
        self.image = self.font.render(
            f"{self.action}: {pg.key.name(self.key)}", True, self.color
        )

    def blit(self, window):
        window.blit(self.image, self.rect.topleft)

    def is_clicked(self, pos):
        return self.rect.collidepoint(pos)


class ParticleEffect(pg.sprite.Sprite):
    def __init__(self, pos, type):
        super().__init__()
        self.frame_index = 0
        self.animation_speed = 0.5
        if type == "jump":
            particle_type = "jump"
            folder = f"../../assets/graphics/character/dust_particles/{particle_type}"
            image_files = [f"{particle_type}_{i}" for i in range(1, 7)]
            self.frames = [load_sprite(file, folder) for file in image_files]
        if type == "land":
            particle_type = "land"
            folder = f"../../assets/graphics/character/dust_particles/{particle_type}"
            image_files = [f"{particle_type}_{i}" for i in range(1, 6)]
            self.frames = [load_sprite(file, folder) for file in image_files]

        self.frames = [pg.transform.flip(frame, False, True) for frame in self.frames]
        self.image = self.frames[self.frame_index]
        self.rect = self.image.get_rect(center=pos)
        self.true_x, self.true_y = self.rect.x, self.rect.y

    def animate(self):
        self.frame_index += self.animation_speed
        if self.frame_index >= len(self.frames):
            self.kill()
        else:
            self.image = self.frames[int(self.frame_index)]

    def update(self, dx=0, dy=0):
        self.rect.x = self.true_x + dx
        self.rect.y = self.true_y + dy
        self.animate()
