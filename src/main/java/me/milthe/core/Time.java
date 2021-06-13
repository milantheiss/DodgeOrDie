package me.milthe.core;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Wickelt Ingame Zeitmessung ab. Die Zeit wird bei jedem Ingame Neustart auf null zurück gesetzt
 */
public class Time {
    private static long timeInSeconds;
    private static Timer timer;

    /**
     * Startet Zeitmessung
     */
    public static void startTimer() {
        timer = new Timer();
        timeInSeconds = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (Game.getGamestate() == Gamestates.INGAME) timeInSeconds++;
            }
        }, 1000, 1000);
    }

    /**
     * Stoppt Zeitmessung
     */
    public static void stopTimer() {
        timer.cancel();
    }

    /**
     * Gibt Zeit im formatierten Format MM:SS zurück
     * @param timeInSeconds Vergangene Zeit in Sekunden seit Ingame Spielstart
     * @return Zeit formatiert als String und in Form MM:SS
     */
    public static String getTimeString(long timeInSeconds) {
        String minutes = ((timeInSeconds / 60) < 10) ? "0" + (timeInSeconds / 60) : String.valueOf(timeInSeconds / 60);
        String seconds = ((timeInSeconds % 60) < 10) ? "0" + (timeInSeconds % 60) : String.valueOf(timeInSeconds % 60);
        return minutes + ":" + seconds;
    }

    /**
     * Gibt vergangene Zeit in Sekunden seit Ingame Spielstart zurück
     * @return Zeit in Sekunden als Integer
     */
    public static long getTimeInSeconds() {
        return timeInSeconds;
    }
}
