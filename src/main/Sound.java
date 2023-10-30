package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

public class Sound {

    Clip clip;
    File[] sounds = new File[6];
   // URL[] soundURL =new URL[30];

    public Sound(){


        sounds[0] = new File("src/sound/epic.wav");
        sounds[1] = new File("src/sound/coin.wav");
        sounds[2] = new File("src/sound/unlock.wav");
        sounds[3] = new File("src/sound/close.wav");
        sounds[4] = new File("src/sound/grub.wav");
        sounds[5] = new File("src/sound/win.wav");
//        soundURL[0] = getClass().getResource("/sound/epic .wav");
//        soundURL[1] = getClass().getResource("/sound/coin.wav");
//        soundURL[2] = getClass().getResource("/sound/unlock.wav");
//        soundURL[3] = getClass().getResource("/sound/close.wav");
//        soundURL[4] = getClass().getResource("/sound/grub.wav");
//        soundURL[5] = getClass().getResource("/sound/win.wav");




    }

    public void setFile(int count){
        try{
            AudioInputStream audio = AudioSystem.getAudioInputStream(sounds[count]);
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
