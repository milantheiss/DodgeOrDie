package me.milthe.clocks;

import me.milthe.entities.CircleEnemy;
import me.milthe.events.Collision;
import me.milthe.gui.Gui;

import java.util.Timer;
import java.util.TimerTask;

public class MainUpdater {
    Timer timer;

    public MainUpdater() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Gui.gc_main.clearRect(0, 0, Gui.width, Gui.height);
                    Gui.dm.draw(Gui.gc_main);
                } catch (Exception e) {
                    System.out.println("Main Timer Error");
                }
            }
        }, 0, 7);
    }
}
