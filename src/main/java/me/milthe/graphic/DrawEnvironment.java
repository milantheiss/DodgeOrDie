package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.milthe.ui.Gui;

/**
 * Draw Klasse für den Hintergrund
 */
public class DrawEnvironment {
    /**
     * Redert den Hintergrund
     * @param g Grafische Oberfläche auf, die das Bild gemalt werden soll
     */
    public void render(GraphicsContext g) {
        //Hintergrund
        g.setFill(new Color(4. / 255., 0. / 255., 17. / 255., 1));
        g.fillRect(0, 0, Gui.WIDTH, Gui.HEIGHT);
    }
}
