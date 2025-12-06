from code.game.platform import *
from code.game.utils import load_sprite


class Level:
    def __init__(self, level_data, screen):
        terrain_layout = import_csv_layout(level_data)
        self.terrain_sprites = self.create_tile_group(terrain_layout, "terrain")
        self.screen = screen
        self.backgrounds = [
            load_sprite("clouds", "levels"),
            load_sprite("last_chance_bg", "levels"),
        ]
        self.background = self.backgrounds[0]
        self.background_x = 0

    @staticmethod
    def create_tile_group(layout, type):
        sprite_group = pg.sprite.Group()

        for row_index, row in enumerate(layout):
            for col_index, val in enumerate(row):
                if val != "-1":
                    x = col_index * tile_size
                    y = row_index * tile_size

                    if type == "terrain":
                        terrain_tile_list = import_cut_graphics(
                            "terrain_tiles", "terrain"
                        )
                        tile_surface = terrain_tile_list[int(val)]
                        sprite = StaticTile(tile_size, x, y, tile_surface)
                    sprite_group.add(sprite)
        return sprite_group

    def change_background(self, backgound_id):
        self.background = self.backgrounds[backgound_id]

    def update(self, dx, dy):
        self.terrain_sprites.update(dx, dy)

    def draw(self):
        self.background_x = self.background_x - 3
        if self.background_x < -2732:
            self.background_x = 0

        self.screen.blit(self.background, (self.background_x, 0))
        self.terrain_sprites.draw(self.screen)
