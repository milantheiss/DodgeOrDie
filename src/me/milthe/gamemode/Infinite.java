package me.milthe.gamemode;

import me.milthe.core.*;
import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Player;
import me.milthe.graphic.DrawIngameUi;

import java.util.Timer;
import java.util.TimerTask;

public class Infinite {
    private final Game game;
    private Timer timer;
    private static int spawnDelay = 2000;
    private static int spawnInterval;

    //todo add scoring system

    public Infinite(Game game){
        this.game = game;
    }

    public void startInfinite(){
        timer = new Timer();
        Gamestate.state = Gamestates.ingame;
        Gamemode.mode = Gamemodes.infinite;

        Game.player = new Player(4);

        Scoring.totalEnemiesSpawned = 0;

        game.addEntity(Game.player);

        Time.startTimer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //int typeNumber = (int) (Math.random() * 1);
                if (Gamestate.state == Gamestates.ingame) {
                    // if (typeNumber == 0){
                    game.addCircleEnemy(new CircleEnemy());
                    // }
                }
            }
        }, spawnDelay, (int)(Math.random()*1000)+500);
    }

    public void stopInfinite(){
        Time.stopTimer();
        timer.cancel();
        Gamestate.state = Gamestates.pause;
        System.out.println(Time.getTime());
    }

    public void terminateInfinite(){
        Game.circleEnemyList.clear();
        Game.entities.clear();
    }
}
