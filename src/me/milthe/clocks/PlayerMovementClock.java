package me.milthe.clocks;

import me.milthe.entities.Player;
import me.milthe.gui.Gui;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerMovementClock {
    Timer timer;

    public PlayerMovementClock() {
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
        }, 3, 3);
    }
}
