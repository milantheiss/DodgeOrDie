package me.milthe.draw;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.milthe.clocks.CircleSpawn;
import me.milthe.entities.Player;
import me.milthe.gui.Gui;

public class DrawMain {
    public void draw(GraphicsContext g) {
        g.setFill(new Color(4. / 255., 0. / 255., 17. / 255., 1));
        g.fillRect(0, 0, Gui.width, Gui.height);

        g.drawImage(ImageLoader.imagePlayer, Player.xPos, Player.yPos, Player.width, Player.height);
        for (int i = 0; i < CircleSpawn.circles.size(); i++) {
            g.drawImage(ImageLoader.imageCircleEnemy, CircleSpawn.circles.get(i).getxPos(), CircleSpawn.circles.get(i).getyPos(), CircleSpawn.circles.get(i).getWidth(), CircleSpawn.circles.get(i).getHeight());
        }
    }
}
