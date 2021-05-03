package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class DrawEnvironment {
    public static int score; //JavaFX Draw um Grafik anzuzeigen Wird in GameLoop.java render() aufgerufen

    public void draw(GraphicsContext g) {
        //Hintergrund
        g.setFill(new Color(4. / 255., 0. / 255., 17. / 255., 1));
        g.fillRect(0, 0, Gui.width, Gui.height);

        //Collision Score
        g.setFill(Color.WHITE);
        g.setFont(new Font(50));
        g.fillText(String.valueOf(score), 30, 60);
    }
}
