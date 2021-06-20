package me.milthe.gamemode;

import me.milthe.core.*;
import me.milthe.entities.Bouncy;
import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Friend;
import me.milthe.entities.Player;
import me.milthe.scoring.Highscore;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Definiert Parameter und Methoden für den Endlos Modus
 */
public class Endless {
    private static int totalEnemiesSurvived;
    private Timer timer;
    private static int highestAmountOfHealth;

    /**
     * Startet den Endlos Modus. Dass heißt alles wird auf Null gesetzt und der Spawn Timer wird gestartet
     */
    public void startEndless() {
        timer = new Timer();
        Game.setGamestate(Gamestates.INGAME);
        Game.setGamemode(Gamemodes.ENDLESS);

        Game.setPlayer(new Player(2000));

        totalEnemiesSurvived = 0;
        highestAmountOfHealth = 4;

        Game.addEntity(Game.getPlayer());

        Time.startTimer();

        Game.getJukebox().playInGameMusic();

        int spawnDelay = 2000;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (Game.getGamestate() == Gamestates.INGAME) {
                    double secondtotal = 0;
                    double treshold = Math.random() * 1.1;
                    while (secondtotal < treshold) {
                        secondtotal += 0.1;
                        if (secondtotal > treshold) {
                            Game.addEntity(new Friend());
                            break;
                        }
                        secondtotal += 0.2;
                        if (secondtotal > treshold) {
                            Game.addEntity(new Bouncy());
                            break;
                        }
                        secondtotal += 0.4;
                        if (secondtotal > treshold) {
                            Game.addEntity(new CircleEnemy());
                            break;
                        }
                    }
                }
            }
            // }, spawnDelay, (int) (Math.random() * 1000-(Time.getTimeInSeconds()/10)) + 500);
        }, spawnDelay, 700);
    }

    /**
     * Stoppt alle Prozesse des Endlos Modus und checkt ob es einen neuen Highscore gibt
     * @throws IOException
     */
    public void stopEndless() throws IOException {
        Time.stopTimer();
        Game.getJukebox().stopInGameMusic();
        Highscore.setNewHighscore(Highscore.isHighscoreBigger(Time.getTimeInSeconds(), totalEnemiesSurvived, highestAmountOfHealth));
        timer.cancel();
        Game.setGamestate(Gamestates.PAUSE);
    }

    /**
     * Beendet den Endlos Modus endgültigt und löscht Entity Liste
     */
    public void terminateEndless() {
        Game.getEntities().clear();
    }

    /**
     * Gibt Anzahl der überlebten Gegner im Run zurück
     * @return Anzahl der überlebten Gegner
     */
    public static int getTotalEnemiesSurvived() {
        return totalEnemiesSurvived;
    }

    /**
     * Erhöhte oder verringert überlebte Gegner um factor
     * @param factor Um wie viel totalEnemiesSurvied erhöht oder verringert werden soll
     */
    public static void setTotalEnemiesSurvived(int factor) {
        Endless.totalEnemiesSurvived += factor;
    }

    /**
     * Gibt höchste Nummer an Leben im Run zurück
     * @return Höchste Nummer an Leben
     */
    public static int getHighestAmountOfHealth() {
        return highestAmountOfHealth;
    }

    /**
     * Setzt Höchsten Nummer an Leben neu
     * @param highestAmountOfHealth neue Anzahl
     */
    public static void setHighestAmountOfHealth(int highestAmountOfHealth) {
        Endless.highestAmountOfHealth = highestAmountOfHealth;
    }
}
