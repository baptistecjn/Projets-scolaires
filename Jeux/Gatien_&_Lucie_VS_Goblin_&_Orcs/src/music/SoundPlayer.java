package music;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {

    private Clip clip;

    public SoundPlayer(String resourcePath) {
        try {
            // Charger le fichier son depuis le classpath (ex: "/sounds/music.wav")
            URL soundURL = getClass().getResource(resourcePath);
            if (soundURL == null) {
                System.err.println("Le fichier son " + resourcePath + " n'a pas été trouvé !");
                return;
            }
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundURL);

            clip = AudioSystem.getClip();
            clip.open(audioInput);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip == null) return;
        clip.start();
    }

    public void loop() {
        if (clip == null) return;
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (clip == null) return;
        clip.stop();
    }
}