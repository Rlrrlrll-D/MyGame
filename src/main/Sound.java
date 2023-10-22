package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL =new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/sound/epic .wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/unlock.wav");
        soundURL[3] = getClass().getResource("/sound/close.wav");
        soundURL[4] = getClass().getResource("/sound/grub.wav");
        soundURL[5] = getClass().getResource("/sound/win.wav");
    }

    public void setFile(int count){
        try{
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL[count]);
            clip = AudioSystem.getClip();
            clip.open(audio);
        }catch (Exception ignored){

        }
    }
    public void play(){
        clip.start();

    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void stop(){
        clip.stop();

    }
}
