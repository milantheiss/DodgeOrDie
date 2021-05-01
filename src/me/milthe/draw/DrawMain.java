package me.milthe.draw;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.milthe.clocks.CircleSpawn;
import me.milthe.entities.Player;
import me.milthe.events.Collision;
import me.milthe.gui.Gui;


public class DrawMain {
    public static int score; //JavaFX Draw um Grafik anzuzeigen Wird in ClockMain.java render() aufgerufen
    Collision col = new Collision();

    public void draw(GraphicsContext g) {
        g.setFill(new Color(4. / 255., 0. / 255., 17. / 255., 1));
        g.fillRect(0, 0, Gui.width, Gui.height);

        g.drawImage(Player.getSprite(), Player.xPos, Player.yPos, Player.width, Player.height);
        for (int i = 0; i < CircleSpawn.circles.size(); i++) {
            g.drawImage(CircleSpawn.circles.get(i).getSprite(), CircleSpawn.circles.get(i).getxPos(), CircleSpawn.circles.get(i).getyPos(), CircleSpawn.circles.get(i).getWidth(), CircleSpawn.circles.get(i).getHeight());
        }

        g.setFill(Color.WHITE);
        g.setFont(new Font(50));
        g.fillText(String.valueOf(score), 30, 60);
    }
}
