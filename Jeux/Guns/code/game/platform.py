from code.game.utils import *


class Tile(pg.sprite.Sprite):
    def __init__(self, size, x, y):
        super().__init__()
        self.image = pg.Surface((size, size))
        self.rect = self.image.get_rect(topleft=(x, y))
        self.true_x, self.true_y = self.rect.x, self.rect.y
        self.mask = pg.mask.from_surface(self.image)

    def update(self, dx, dy):
        self.rect.x = self.true_x + dx
        self.rect.y = self.true_y + dy


class StaticTile(Tile):
    def __init__(self, size, x, y, surface):
        super().__init__(size, x, y)
        self.image = surface
        self.mask = pg.mask.from_surface(self.image)
