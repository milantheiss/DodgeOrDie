package me.milthe.draw;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.milthe.entities.Player;
import me.milthe.gui.Gui;
import me.milthe.gui.ImageLoader;

public class DrawMain {
    public void draw(GraphicsContext g) {
        g.setFill(new Color(4. / 255., 0. / 255., 17. / 255., 1));
        g.fillRect(0, 0, Gui.width, Gui.height);

        g.drawImage(ImageLoader.imagePlayer, Player.xPos, Player.yPos, Player.width, Player.height);
        g.drawImage(ImageLoader.imageEnemyRound, 200, 200, 100, 100);
    }
}
