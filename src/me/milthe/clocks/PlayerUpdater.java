package me.milthe.clocks;

import javafx.scene.canvas.GraphicsContext;
import me.milthe.draw.ImageLoader;
import me.milthe.entities.Player;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerUpdater {
    Timer timer;

    public PlayerUpdater(GraphicsContext g) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {

                    g.drawImage(ImageLoader.imagePlayer, Player.xPos, Player.yPos, Player.width, Player.height);
                    Player.move();
                } catch (Exception e) {
                    System.out.println("Player Movement Timer Error");
                }
            }
        }, 0, 7);
    }
}
