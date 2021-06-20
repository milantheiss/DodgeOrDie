package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.milthe.core.Time;
import me.milthe.entities.Player;
import me.milthe.ui.Gui;

import java.util.Objects;

/**
 * Draw Klasse für das Ingame UI
 */
public class DrawIngameUi {
    private final Image heart;

    public DrawIngameUi(GraphicsContext g) {
        heart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/heart.png")));

    }

    /**
     * Redert alle Komponenten des Ingame UIs
     * @param g Grafische Oberfläche auf, die das Bild gemalt werden soll
     */
    public void render(GraphicsContext g) {
        g.setFill(Color.WHITE);
        g.setFont(Font.loadFont(getClass().getResourceAsStream("/font/DodgeFont.ttf"), 40));

        g.fillText(Time.getTimeString(Time.getTimeInSeconds()), Gui.WIDTH - 140 - (30 * (Time.getTimeString(Time.getTimeInSeconds()).length() - 3)), 57);

        g.drawImage(heart, 30, 25, heart.getWidth(), heart.getHeight());
        g.fillText(String.valueOf(Player.getHitpoints()), 109, 57);
    }
}