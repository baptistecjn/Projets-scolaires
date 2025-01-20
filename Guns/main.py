from code.client import *

width = 1366
height = 768
fullscreen = False

if __name__ == "__main__":
    pg.init()
    pg.font.init()
    pg.mixer.pre_init(44100, 16, 2, 4096)
    icon = pg.image.load("./assets/graphics/icon.png")
    pg.display.set_icon(icon)
    client = Client(width, height, fullscreen)
    pg.display.set_caption("Guns Game")
    client.menu()
