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

public class Endless {
    public static int totalEnemiesSpawned;
    private final Game game;
    private Timer timer;
    private static int spawnDelay = 2000;
    public static int highestAmountOfHealth;

    public Endless(Game game) {
        this.game = game;
    }

    public void startEndless() {
        timer = new Timer();
        Game.state = Gamestates.INGAME;
        Game.mode = Gamemodes.ENDLESS;

        Game.player = new Player(4);

        totalEnemiesSpawned = 0;
        highestAmountOfHealth = 4;

        game.addEntity(Game.player);

        Time.startTimer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (Game.state == Gamestates.INGAME) {
                    double secondtotal = 0;
                    double treshold = Math.random() * 1.1;
                    while (secondtotal < treshold) {
                        secondtotal += 0.1;
                        if (secondtotal > treshold) {
                            game.addEntity(new Friend());
                            break;
                        }
                        secondtotal += 0.2;
                        if (secondtotal > treshold) {
                            game.addEntity(new Bouncy());
                            break;
                        }
                        secondtotal += 0.4;
                        if (secondtotal > treshold) {
                            game.addEntity(new CircleEnemy());
                            break;
                        }
                    }
                }
            }
            // }, spawnDelay, (int) (Math.random() * 1000-(Time.getTimeInSeconds()/10)) + 500);
        }, spawnDelay, 700);
    }

    public void stopEndless() throws IOException {
        Time.stopTimer();
        Highscore.isHighscoreBigger(Time.getTimeInSeconds(), totalEnemiesSpawned, highestAmountOfHealth);
        timer.cancel();
        Game.state = Gamestates.PAUSE;
        System.out.println(Time.getTimeString(Time.getTimeInSeconds()));
    }

    public void terminateEndless() {
        Game.entities.clear();
    }
}
