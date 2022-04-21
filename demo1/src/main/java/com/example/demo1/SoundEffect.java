package com.example.demo1;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {
    Clip BGMClip;
    public void playSound(String soundName) {

        try {
            //input audio
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start( );
        }
        catch (UnsupportedAudioFileException e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }
        catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playBGM(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            BGMClip = AudioSystem.getClip( );
            BGMClip.open(audioInputStream);
            BGMClip.loop(Clip.LOOP_CONTINUOUSLY);
            BGMClip.start();
        }
        catch (UnsupportedAudioFileException e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }
        catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void clicksound()
    {
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
    }

    public static void errorsound()
    {
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/error.wav");
    }

    public static void success()
    {
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/short-success.wav");
    }
}
