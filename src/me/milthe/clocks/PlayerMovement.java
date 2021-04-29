package me.milthe.clocks;

import me.milthe.entities.Player;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerMovement {
    Timer timer;

    public PlayerMovement() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Player.move();
                } catch (Exception e) {
                    System.out.println("Player Movement Timer Error");
                }
            }
        }, 0, 7);
    }
}
