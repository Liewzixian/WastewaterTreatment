package com.example.demo1;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Public class SoundEffect is used add sound effects and BGM to the program
 *
 * @author Group 2A
 * @version 1.0
 * @since 21/04/2022
 */
public class SoundEffect {
    /**
     * Clip for bgm to be started and stopped
     */
    Clip BGMClip;

    /**
     * This method plays the sound effect in the program.
     * @param soundName sound effect to be played
     */
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

    /**
     * This method plays the BGM in the program.
     * @param soundName BGM to be played
     */
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

    /**
     * This method plays a clicking sound.
     */
    public static void clicksound()
    {
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
    }

    /**
     * This method plays an error sound.
     */
    public static void errorsound()
    {
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/error.wav");
    }

    /**
     * This method plays a success sound.
     */
    public static void success()
    {
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/short-success.wav");
    }
}
