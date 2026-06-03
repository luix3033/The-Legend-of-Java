package com.treszerotresstudios.main;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {

    private Clip clip;
    
    public static final Sound musicBackground = new Sound("/hyrule.wav");
    public static final Sound gameoverMusic = new Sound("/gameover.wav");
    public static final Sound menuBackground = new Sound("/title.wav");
    public static final Sound hurtEffect = new Sound("/hurt.wav");
    public static final Sound arrowThrow = new Sound("/swing.wav");
    public static final Sound menuChange = new Sound("/interface1.wav");
    public static final Sound menuSelect = new Sound("/interface3.wav");
    public static final Sound picBow = new Sound("/bowpic.wav");
    public static final Sound goblinHit = new Sound("/ogre5.wav");
    public static final Sound goblinDeath = new Sound("/ogre3.wav");
    public static final Sound lowLife = new Sound("/lowlife.wav");
    public static final Sound getLife = new Sound("/life.wav");

    public Sound(String name) {
    	

        try {

            URL url = Sound.class.getResource(name);

            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(url);

            clip = AudioSystem.getClip();
            clip.open(audio);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {

        if (clip == null) return;

        clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {

        if (clip == null) return;

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {

        if (clip != null) {
            clip.stop();
        }
    }
    public void setVolumePercent(float percent) {
        if (clip == null) return;

        FloatControl gainControl =
            (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        float min = gainControl.getMinimum();
        float max = gainControl.getMaximum();

        float gain = min + (max - min) * (percent / 100f);

        gainControl.setValue(gain);
    }
}