package me.milthe.sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Jukebox {
    Clip soundeffect;
    Clip ingamemusic;

    private final List<String> titles = new ArrayList<>();
    private int lastTitle;

    private boolean playMusic;

    public Jukebox() throws IOException {
        playMusic = false;
        String tempString = "";
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/sound/music/music.txt"))))) {
            while ((tempString = bufferedReader.readLine()) != null) {
                titles.add(tempString);
            }
        }
    }

    public void playSoundEffect(String filename) {
        try {
            soundeffect = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResource("/sound/effects/" + filename + ".wav")));
            soundeffect.open(inputStream);
            soundeffect.loop(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void playInGameMusic() {
        playMusic = true;
        int randomTitle;
        do {
            randomTitle = (int) Math.round(Math.random() * titles.size());
        } while (randomTitle == lastTitle);
        lastTitle = randomTitle;
        try {
            ingamemusic = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResource("/sound/music/" + titles.get(randomTitle) + ".wav")));
            ingamemusic.open(inputStream);
            ingamemusic.loop(0);
            ingamemusic.start();
            ingamemusic.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP && playMusic) {
                    playInGameMusic();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseInGameMusic() {
        try {
            ingamemusic.stop();
            playMusic = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resumeInGameMusic() {
        try {
            ingamemusic.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopInGameMusic() {
        try {
            ingamemusic.stop();
            playMusic = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
