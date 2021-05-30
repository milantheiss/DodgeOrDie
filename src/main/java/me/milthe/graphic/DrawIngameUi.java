package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.milthe.core.Time;
import me.milthe.entities.Player;

import java.util.Objects;


public class DrawIngameUi {
    private Image heart;

    public DrawIngameUi(GraphicsContext g) {
        heart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/heart.png")));

    }

    public void render(GraphicsContext g) {
        g.setFill(Color.WHITE);
        g.setFont(Font.loadFont(getClass().getResourceAsStream("/font/DodgeFont.ttf"), 40));

        g.fillText(Time.getTimeString(Time.getTimeInSeconds()), Gui.width - 140 - (30 * (Time.getTimeString(Time.getTimeInSeconds()).length() - 3)), 57);

        g.drawImage(heart, 30, 25, heart.getWidth(), heart.getHeight());
        g.fillText(String.valueOf(Player.hitpoints), 109, 57);
    }
}