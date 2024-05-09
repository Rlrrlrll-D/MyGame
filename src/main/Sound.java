package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[40];
    FloatControl floatControl;
    public int volumeScale = 2;
    float volume;

    public Sound() {

        soundURL[0] = getClass().getResource("/res/sound/Joshua McLean - Mountain Trials.wav");
        soundURL[1] = getClass().getResource("/res/sound/coin.wav");
        soundURL[2] = getClass().getResource("/res/sound/unlock.wav");
        soundURL[3] = getClass().getResource("/res/sound/close.wav");
        soundURL[4] = getClass().getResource("/res/sound/grub.wav");
        soundURL[5] = getClass().getResource("/res/sound/win.wav");
        soundURL[6] = getClass().getResource("/res/sound/ouch.wav");
        soundURL[7] = getClass().getResource("/res/sound/hit-monster.wav");
        soundURL[8] = getClass().getResource("/res/sound/receive-damage.wav");
        soundURL[9] = getClass().getResource("/res/sound/swing-weapon.wav");
        soundURL[10] = getClass().getResource("/res/sound/health.wav");
        soundURL[11] = getClass().getResource("/res/sound/leve-lup.wav");
        soundURL[12] = getClass().getResource("/res/sound/cursor.wav");
        soundURL[13] = getClass().getResource("/res/sound/yahoo.wav");
        soundURL[14] = getClass().getResource("/res/sound/burning.wav");
        soundURL[15] = getClass().getResource("/res/sound/cut-tree.wav");
        soundURL[16] = getClass().getResource("/res/sound/game-over.wav");
        soundURL[17] = getClass().getResource("/res/sound/sleep.wav");
        soundURL[18] = getClass().getResource("/res/sound/blocked.wav");
        soundURL[19] = getClass().getResource("/res/sound/parry.wav");
        soundURL[20] = getClass().getResource("/res/sound/speak.wav");
        soundURL[21] = getClass().getResource("/res/sound/Kevin MacLeod - 8bit Dungeon Level.wav");
        soundURL[22] = getClass().getResource("/res/sound/merchant.wav");
        soundURL[23] = getClass().getResource("/res/sound/chip-wall.wav");
        soundURL[24] = getClass().getResource("/res/sound/door-open.wav");
        soundURL[25] = getClass().getResource("/res/sound/FinalBattle.wav");
        soundURL[26] = getClass().getResource("/res/sound/fanfare.wav");
        soundURL[27] = getClass().getResource("/res/sound/slime.wav");

    }

    public void setFile(int count) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL[count]);
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
