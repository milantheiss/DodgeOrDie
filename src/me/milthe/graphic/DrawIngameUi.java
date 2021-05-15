package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.milthe.core.Time;
import me.milthe.entities.Player;


public class DrawIngameUi {
    public static int score; //JavaFX Draw um Grafik anzuzeigen Wird in GameLoop.java render() aufgerufen
    private Image[] health = new Image[5];

    public DrawIngameUi() {
        health[0] = new Image("file:rsc/sprites/heart0.png");
        health[1] = new Image("file:rsc/sprites/heart1.png");
        health[2] = new Image("file:rsc/sprites/heart2.png");
        health[3] = new Image("file:rsc/sprites/heart3.png");
        health[4] = new Image("file:rsc/sprites/heart4.png");
    }

    public void render(GraphicsContext g) {
        g.setFill(Color.WHITE);
        g.setFont(Font.loadFont("file:rsc/font/DodgeFont.ttf", 40));
        g.fillText(Time.getTime(), 30, 57);
        //Todo leben anzeige hinzufügen

        g.drawImage(health[Player.hitpoints], (Gui.width - health[Player.hitpoints].getWidth() - 30), 25, 241, 40);
    }
}
//(Gui.width-190)