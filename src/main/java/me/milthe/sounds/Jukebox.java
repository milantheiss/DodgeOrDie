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

/**
 * Ermöglicht es Ton wie Soundeffekte oder Musik abzuspielen. Die Sounddatei muss vom Type .wav sein
 */
public class Jukebox {
    Clip soundeffect;
    Clip ingamemusic;

    private final List<String> titles = new ArrayList<>();
    private int lastTitle;

    private boolean playMusic;

    /**
     * Erstellt beim Erstellen einer neuen Jukebox einen BufferedReader, welcher aus playlist.txt die Ingame Music Playlist ausliest
     *
     * @throws IOException
     */
    public Jukebox() throws IOException {
        playMusic = false;
        String tempString = "";
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/sound/music/playlist.txt"))))) {
            while ((tempString = bufferedReader.readLine()) != null) {
                titles.add(tempString);
            }
        }
    }

    /**
     * Spielt definierten .wav Soundclip ab. Muss sich in sound/effects/ befinden
     *
     * @param filename Name des Soundeffekts
     */
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

    /**
     * Spielt aus vordefinierter Playlist (playlist.txt) einen zufälligen Soundclip ab. Wenn Soundclip zu Ende ist ruft die Methode sich selbst auf
     */
    public void playInGameMusic() {
        playMusic = true;
        int randomTitle;
        do {
            randomTitle = (int) (Math.random() * titles.size());
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

    /**
     * Startet Ingame Musik wieder nachdem sie gestoppt wurde wurde
     */
    public void resumeInGameMusic() {
        try {
            ingamemusic.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stopt Ingame Musik
     */
    public void stopInGameMusic() {
        try {
            ingamemusic.stop();
            playMusic = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
