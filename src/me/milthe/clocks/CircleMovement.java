package me.milthe.clocks;

import java.util.Timer;
import java.util.TimerTask;

public class CircleMovement {
    Timer timer;

    public CircleMovement() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < CircleSpawn.circles.size(); i++) {
                        CircleSpawn.circles.get(i).move();
                    }
                } catch (Exception e) {
                    System.out.println("Circle Movement Timer Error");
                }
            }
        }, 20, 20);
    }
}
