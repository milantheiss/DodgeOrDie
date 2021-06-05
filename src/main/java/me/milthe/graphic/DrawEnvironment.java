package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawEnvironment {
    public void draw(GraphicsContext g) {
        //Hintergrund
        g.setFill(new Color(4. / 255., 0. / 255., 17. / 255., 1));
        g.fillRect(0, 0, Gui.width, Gui.height);
    }
}
