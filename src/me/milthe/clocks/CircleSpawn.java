package me.milthe.clocks;

import me.milthe.entities.CircleEnemy;
import me.milthe.events.Collision;
import me.milthe.gui.Gui;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CircleSpawn {
    public static ArrayList<CircleEnemy> circles = new ArrayList<>();
    public static Timer timer;
    static int spawnTimer = 5000; //SpawnTimer in Millisekunden

    public static void start() {
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < circles.size(); i++) {
                    if (circles.get(i).getxPos() >= (Gui.width + 100) || circles.get(i).getxPos() <= -100 || circles.get(i).getyPos() >= (Gui.height + 100) || circles.get(i).getyPos() <= -100) {
                        circles.remove(i);
                    }
                }
                circles.add(new CircleEnemy());
            }
        }, spawnTimer, 500);
    }
}
