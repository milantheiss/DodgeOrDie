package me.milthe.clocks;

import me.milthe.entities.CircleEnemy;
import me.milthe.events.Collision;
import me.milthe.gui.Gui;

import java.util.Timer;
import java.util.TimerTask;

public class ClockMain implements Runnable{

    private boolean running;
    private final double updateRate = 1.0d/60d;

    Update update;

    public ClockMain() {
        this.update = new Update();
    }


    @Override //MainClock fürs Spiel
    public void run() {
        running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();

        while(running) {
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            if(accumulator >= updateRate) {
                while(accumulator >= updateRate) {
                    update();
                    accumulator -= updateRate;
                }
                render();
            }
        }
    }

    private void update() {
        update.entitiesUpdate();
    }

    private void render() {
        Gui.gc_main.clearRect(0, 0, Gui.width, Gui.height);
        Gui.dm.draw(Gui.gc_main);
    }
}
