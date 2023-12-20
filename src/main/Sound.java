package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Sound {

    Clip clip;
    File[] sounds = new File[20];
    FloatControl floatControl;
    int volumeScale = 1;
    float volume;
    // URL[] soundURL =new URL[30];

    public Sound() {


        sounds[0] = new File("src/res/sound/epic.wav");
        sounds[1] = new File("src/res/sound/coin.wav");
        sounds[2] = new File("src/res/sound/unlock.wav");
        sounds[3] = new File("src/res/sound/close.wav");
        sounds[4] = new File("src/res/sound/grub.wav");
        sounds[5] = new File("src/res/sound/win.wav");
        sounds[6] = new File("src/res/sound/ouch.wav");
        sounds[7] = new File("src/res/sound/hit-monster.wav");
        sounds[8] = new File("src/res/sound/receive-damage.wav");
        sounds[9] = new File("src/res/sound/swing-weapon.wav");
        sounds[10] = new File("src/res/sound/health.wav");
        sounds[11] = new File("src/res/sound/leve-lup.wav");
        sounds[12] = new File("src/res/sound/cursor.wav");
        sounds[13] = new File("src/res/sound/yahoo.wav");
        sounds[14] = new File("src/res/sound/burning.wav");
        sounds[15] = new File("src/res/sound/cut-tree.wav");
//        soundURL[0] = getClass().getResource("/res.sound/epic .wav");
//        soundURL[1] = getClass().getResource("/res.sound/coin.wav");
//        soundURL[2] = getClass().getResource("/res.sound/unlock.wav");
//        soundURL[3] = getClass().getResource("/res.sound/close.wav");
//        soundURL[4] = getClass().getResource("/res.sound/grub.wav");
//        soundURL[5] = getClass().getResource("/res.sound/win.wav");


    }

    public void setFile(int count) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(sounds[count]);
            clip = AudioSystem.getClip();
            clip.open(audio);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            chkVolume();
        } catch (Exception ignored) {

        }
    }

    public void play() {
        clip.start();

    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void stop() {
        clip.stop();

    }

    public void chkVolume() {
        switch (volumeScale) {
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -20f;
                break;
            case 2:
                volume = -12f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5:
                volume = 6f;
                break;
        }
        floatControl.setValue(volume);
    }
}
