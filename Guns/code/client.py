from code.game.game import *
from code.game.utils import *
from random import *


class Client:
    def __init__(self, width=1366, height=768, fullscreen=False, volume=0.03):
        self.screen = pg.display.set_mode((width, height), fullscreen)
        self.width = width
        self.height = height
        self.state = "menu"
        self.fps = 120
        self.playlist = None
        self.music_paused = False
        self.key_sound = pg.mixer.Sound("./assets/sounds/sound_key.wav")
        self.hov_sound = pg.mixer.Sound("./assets/sounds/button_hover.mp3")
        self.volume = volume
        pg.mixer.music.set_volume(self.volume)
        self.key_sound.set_volume(2 * self.volume)
        self.hov_sound.set_volume(2 * self.volume)

        self.game = None
        self.keybinds = {
            "jump": pg.K_z,
            "right": pg.K_d,
            "left": pg.K_q,
            "down": pg.K_s,
        }

    def new_playlist(self):
        if not pg.mixer.music.get_busy():
            if not self.music_paused:
                self.playlist = make_playlist()
                while len(self.playlist) > 0:
                    track = self.playlist.pop()
                    pg.mixer.music.load(f"./assets/tracks/{track}")
                    pg.mixer.music.queue(f"./assets/tracks/{track}")
                pg.mixer.music.play()

    def menu(self):
        run = True
        clock = pg.time.Clock()

        title = load_sprite("title", "menus")
        frame_count = 0
        pm = 1
        static_background = load_sprite("background", "menus")
        background = load_sprite("clouds", "menus")
        background_x = 0
        menu_buttons = [
            Button(515, 450, "play", "menus", size=0.5, sound=self.hov_sound),
            Button(515, 550, "settings", "menus", size=0.5, sound=self.hov_sound),
            Button(515, 650, "exit", "menus", size=0.5, sound=self.hov_sound),
        ]

        while run:
            clock.tick(self.fps)
            clicking = False

            self.screen.fill((255, 255, 255))

            for event in pg.event.get():
                if event.type == pg.QUIT:
                    pg.quit()
                    run = False
                if event.type == pg.MOUSEBUTTONDOWN:
                    clicking = True

            self.new_playlist()

            pos = pg.mouse.get_pos()
            for button in menu_buttons:
                if button.is_clicked(pos):
                    if clicking:
                        run = False
                        bid = button.id
                        print(bid)
                        if bid == "play":
                            self.new_game()
                        elif bid == "settings":
                            self.settings_menu()
                        else:
                            pg.quit()
                    else:
                        button.handle_hover(True)
                else:
                    button.handle_hover(False)

            background_x = background_x - 3
            if background_x < -3518:
                background_x = 0
            self.screen.blit(background, (background_x, 0))
            self.screen.blit(static_background, (0, 0))
            for button in menu_buttons:
                button.blit(self.screen)
            frame_count = (frame_count + 1) % 30
            if frame_count == 0:
                pm = -pm
            title_size = 1 + 0.08 * (frame_count - 15) * pm / 30
            blitted_title = pg.transform.scale_by(title, title_size)
            title_rect = blitted_title.get_rect()
            title_rect.center = (1366 // 2, 768 // 2)
            self.screen.blit(blitted_title, title_rect)
            pg.display.update()

    def settings_menu(self, exit_callback=None):
        run = True
        clock = pg.time.Clock()

        back_button = Button(20, 700, "back", "menus", size=0.3, sound=self.hov_sound)
        mute_button = Volume_Button(
            1225, 625, "volume", "menus", size=0.2, sound=self.hov_sound
        )

        jump_text = KeyBindingText(600, 200, "Jump", self.keybinds["jump"], 50)
        right_text = KeyBindingText(600, 275, "Right", self.keybinds["right"], 50)
        left_text = KeyBindingText(600, 350, "Left", self.keybinds["left"], 50)
        down_text = KeyBindingText(600, 425, "Down", self.keybinds["down"], 50)

        key_texts = [jump_text, right_text, left_text, down_text]
        while run:
            clock.tick(self.fps)
            clicking = False

            self.screen.fill((255, 255, 255))

            for event in pg.event.get():
                if event.type == pg.QUIT:
                    pg.quit()
                    run = False
                if event.type == pg.MOUSEBUTTONDOWN:
                    clicking = True
                    if mute_button.is_clicked(pg.mouse.get_pos()):
                        if clicking:
                            mute_button.toggle()
                            if mute_button.is_active():
                                pg.mixer.music.pause()
                                self.music_paused = True
                            else:
                                pg.mixer.music.unpause()
                                self.music_paused = False
                    for key_text in key_texts:
                        if key_text.is_clicked(pg.mouse.get_pos()):
                            # Ecran de selection de touche
                            self.screen.fill((255, 255, 255))
                            selection_text = pg.font.Font(None, 36).render(
                                f"Press a key for {key_text.action}", True, (0, 0, 0)
                            )
                            self.screen.blit(
                                selection_text,
                                (
                                    self.width // 2 - selection_text.get_width() // 2,
                                    300,
                                ),
                            )
                            pg.display.update()

                            new_key = None
                            while new_key is None:
                                for event in pg.event.get():
                                    if event.type == pg.KEYDOWN:
                                        new_key = event.key
                            self.keybinds[
                                key_text.action.lower()
                            ] = new_key  # actually storing the binding
                            key_text.key = new_key  # graphical
                            key_text.update_text()

            pos = pg.mouse.get_pos()

            if back_button.is_clicked(pos) and clicking:
                run = False
                if exit_callback:
                    exit_callback()

            back_button.handle_hover(back_button.is_clicked(pos))

            back_button.blit(self.screen)
            mute_button.blit(self.screen)

            for key_text in key_texts:
                key_text.blit(self.screen)
            pg.display.update()

        if exit_callback:
            print("FDP DE TA GRAND MERE LA REINE DES PUTES")
            print(exit_callback)
            exit_callback()
        else:
            self.menu()

    def show_settings_menu(self):
        self.settings_menu(exit_callback=self.return_to_game)

    def return_to_game(self):
        self.game.run(music_paused=self.music_paused)

    def new_game(self):
        try:
            self.game = Game(self.screen, True, self.keybinds, self.show_settings_menu)
        except:
            self.game = Game(self.screen, False, self.keybinds, self.show_settings_menu)
        d, k = self.game.run()
        if d > k:
            self.lose_screen()
        else:
            self.win_screen()

    def win_screen(self):
        pg.mixer.music.stop()
        run = True
        clock = pg.time.Clock()
        background = load_sprite("win_screen", "menus")

        display_duration = 3  # 3 seconds of display
        start_time = pg.time.get_ticks()

        while run:
            clock.tick(self.fps)

            for event in pg.event.get():
                if event.type == pg.QUIT:
                    pg.quit()
                    run = False
                if event.type == pg.MOUSEBUTTONDOWN:
                    pass

            self.screen.blit(background, (0, 0))
            pg.display.flip()
            current_time = pg.time.get_ticks()
            if (current_time - start_time) >= display_duration * 1000:
                run = False

        self.menu()

    def lose_screen(self):
        pg.mixer.music.stop()
        run = True
        clock = pg.time.Clock()
        background = load_sprite("lose_screen", "menus")

        display_duration = 3  # 3 seconds of display
        start_time = pg.time.get_ticks()

        while run:
            clock.tick(self.fps)

            for event in pg.event.get():
                if event.type == pg.QUIT:
                    pg.quit()
                    run = False
                if event.type == pg.MOUSEBUTTONDOWN:
                    pass

            self.screen.blit(background, (0, 0))
            pg.display.flip()
            current_time = pg.time.get_ticks()
            if (current_time - start_time) >= display_duration * 1000:
                run = False

        self.menu()
