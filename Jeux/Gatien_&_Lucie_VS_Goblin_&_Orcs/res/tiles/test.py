from PIL import Image

# Chargement de l'image du tileset
tileset = Image.open("C:/Users/bapti/Downloads/kenney_roguelike-caves-dungeons/Spritesheet/roguelikeDungeon_transparent.png")

# Taille des tuiles (à adapter à ton tileset)
tile_width = 17
tile_height = 17

# Dimensions de l'image
tileset_width, tileset_height = tileset.size
columns = tileset_width // tile_width
rows = tileset_height // tile_height

# Découpage
index = 0
for y in range(rows):
    for x in range(columns):
        box = (x * tile_width, y * tile_height,
               (x + 1) * tile_width, (y + 1) * tile_height)
        tile = tileset.crop(box)
        tile.save(f"tile_{index}.png")
        index += 1
