import pygame as pg

from code.game.utils import load_sprite

hearts_positions = [(152, 24), (112, 24), (72, 24), (32, 24)]
hearts_positions.reverse()

enemy_hearts_positions = [(1366 - 32 - p[0], p[1]) for p in hearts_positions]


class Heart(pg.sprite.Sprite):
    def __init__(self, player, x, y):
        super().__init__()
        if player > 0:
            prefix = "enemy_"
        else:
            prefix = ""

        self.counter = 0
        self.images = [
            load_sprite(prefix + "heart", ""),
            load_sprite("heart_empty", ""),
        ]
        self.image = self.images[0]

        self.x = x
        self.y = y

    def grey(self):
        self.image = self.images[self.counter]

    def draw(self, window):
        window.blit(self.image, (self.x, self.y))


class ScoreBar:
    def __init__(self):
        positions = [hearts_positions, enemy_hearts_positions]
        self.hearts_1 = []
        self.hearts_2 = []
        for x, y in positions[0]:
            self.hearts_1.append(Heart(0, x, y))
        for x, y in positions[1]:
            self.hearts_2.append(Heart(1, x, y))

        self.health_1 = 4
        self.health_2 = 4
        self.score_1 = 0
        self.score_2 = 0

        pg.font.init()
        font_path = "./assets/fonts/grapple.ttf"
        font_size = 64
        self.font = pg.font.Font(font_path, font_size)
        self.score_1 = self.font.render("0", 1, (225, 32, 55))
        self.score_2 = self.font.render("0", 1, (108, 217, 25))

    def update(self, health_1, health_2, score_1, score_2):
        self.health_1, self.health_2 = health_1, health_2
        for i, heart in enumerate(self.hearts_1):
            heart.image = heart.images[1] if i >= self.health_1 else (heart.images)[0]
        for i, heart in enumerate(self.hearts_2):
            heart.image = heart.images[1] if i >= self.health_2 else (heart.images)[0]
        self.score_1 = self.font.render(str(score_1), 1, (225, 32, 55))
        self.score_2 = self.font.render(str(score_2), 1, (108, 217, 25))

    def draw(self, window):
        for heart in self.hearts_1:
            heart.draw(window)
        for heart in self.hearts_2:
            heart.draw(window)
        window.blit(self.score_1, (583, 32))
        window.blit(self.score_2, (783, 32))
