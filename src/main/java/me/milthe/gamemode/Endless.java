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
    public static int totalEnemiesSpawned;
    private Timer timer;
    private static int spawnDelay = 2000;
    public static int highestAmountOfHealth;

    /**
     * Startet den Endlos Modus. Dass heißt alles wird auf Null gesetzt und der Spawn Timer wird gestartet
     */
    public void startEndless() {
        timer = new Timer();
        Game.setGamestate(Gamestates.INGAME);
        Game.setGamemode(Gamemodes.ENDLESS);

        Game.setPlayer(new Player(20000000));

        totalEnemiesSpawned = 0;
        highestAmountOfHealth = 4;

        Game.addEntity(Game.getPlayer());

        Time.startTimer();

        Game.getJukebox().playInGameMusic();

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
        Highscore.setNewHighscore(Highscore.isHighscoreBigger(Time.getTimeInSeconds(), totalEnemiesSpawned, highestAmountOfHealth));
        timer.cancel();
        Game.setGamestate(Gamestates.PAUSE);
    }

    /**
     * Beendet den Endlos Modus endgültigt und löscht Entity Liste
     */
    public void terminateEndless() {
        Game.getEntities().clear();
    }
}
