package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Sound {

    Clip clip;
    File[] sounds = new File[40];
    FloatControl floatControl;
    public int volumeScale = 2;
    float volume;

    public Sound() {

        sounds[0] = new File("src/res/sound/Joshua McLean - Mountain Trials.wav");
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
        sounds[16] = new File("src/res/sound/game-over.wav");
        sounds[17] = new File("src/res/sound/sleep.wav");
        sounds[18] = new File("src/res/sound/blocked.wav");
        sounds[19] = new File("src/res/sound/parry.wav");
        sounds[20] = new File("src/res/sound/speak.wav");
        sounds[21] = new File("src/res/sound/Kevin MacLeod - 8bit Dungeon Level.wav");
        sounds[22] = new File("src/res/sound/merchant.wav");
        sounds[23] = new File("src/res/sound/chip-wall.wav");
        sounds[24] = new File("src/res/sound/door-open.wav");
        sounds[25] = new File("src/res/sound/FinalBattle.wav");
        sounds[26] = new File("src/res/sound/fanfare.wav");
        sounds[27] = new File("src/res/sound/slime.wav");

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
    float[] volumeLevels = {-80f, -20f, -12f, -5f, 1f, 6f};
    if (volumeScale >= 0 && volumeScale < volumeLevels.length) {
        volume = volumeLevels[volumeScale];
    }
    floatControl.setValue(volume);
}
}
