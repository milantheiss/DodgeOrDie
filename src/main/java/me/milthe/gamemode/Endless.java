package me.milthe.gamemode;

import me.milthe.core.*;
import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Friend;
import me.milthe.entities.Player;

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
                double typeNumber = Math.random();
                //double typeNumber = 1;
                if (Game.state == Gamestates.INGAME) {
                    if (typeNumber <= 0.2) {
                        game.addFriend(new Friend());
                    } else {
                        game.addCircleEnemy(new CircleEnemy());
                    }
                }
            }
        }, spawnDelay, (int) (Math.random() * 1000) + 500);
    }

    public void stopEndless() {
        Time.stopTimer();
        timer.cancel();
        Game.state = Gamestates.PAUSE;
        System.out.println(Time.getTime());
    }

    public void terminateEndless() {
        Game.entities.clear();
    }
}
