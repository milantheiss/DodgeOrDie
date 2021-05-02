package me.milthe.clocks;

import me.milthe.entities.CircleEnemy;
import me.milthe.gui.Gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class CircleSpawn {
    public List<CircleEnemy> circles = new CopyOnWriteArrayList<CircleEnemy>();
    public static Timer timer;
    static int spawnDelay = 5000; //SpawnDelay nach aufrufen der Methode in Millisekunden(1000ms = 1s)
    static int spawnInterval = 500; //Zeit zwischen Enemyspawn in Millisekunden

    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                newCircle(new CircleEnemy());
            }
        }, spawnDelay, spawnInterval);
    }

    public void removeCircle(int index){
        circles.remove(index);
        circles.forEach(circle -> circle.setCircleIndex(circles.indexOf(circle)));
    }

    public void newCircle(CircleEnemy circle){
        circles.add(circle);
        circle.setCircleIndex(circles.indexOf(circle));
        Gui.entitylist.setEntity(circle);
        circle.setListIndex(Gui.entitylist.entities.indexOf(circle));
    }

    public List<CircleEnemy> getCircles() {
        return circles;
    }
}
