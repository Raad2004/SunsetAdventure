package my2dGame;

import java.net.URL;
import javax.sound.sampled.*;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/GameMusic.wav");
        soundURL[1] = getClass().getResource("/sound/Coin.wav");
        soundURL[2] = getClass().getResource("/sound/BootsSound.wav");
        soundURL[3] = getClass().getResource("/sound/OpenDoor.wav");
        soundURL[4] = getClass().getResource("/sound/chest.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

            // Reduce volume using FloatControl
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f); // Reduce volume by 10 decibels (adjust this value as needed)

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
