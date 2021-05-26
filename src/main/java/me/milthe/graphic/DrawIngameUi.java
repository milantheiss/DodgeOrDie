package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.milthe.core.Time;
import me.milthe.entities.Player;


public class DrawIngameUi {
    private Image heart = new Image("file:rsc/sprites/heart.png");

    public void render(GraphicsContext g) {
        g.setFill(Color.WHITE);
        g.setFont(Font.loadFont("file:rsc/font/DodgeFont.ttf", 40));

        g.fillText(Time.getTime(), Gui.width-140-(30*(Time.getTime().length()-3)), 57);

        g.drawImage(heart,30, 25, heart.getWidth(), heart.getHeight());
        g.fillText(String.valueOf(Player.hitpoints), 109, 57);
    }
}