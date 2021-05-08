package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DrawIngameUi {
    public static int score; //JavaFX Draw um Grafik anzuzeigen Wird in GameLoop.java render() aufgerufen

    public void render(GraphicsContext g) {
        g.setFill(Color.WHITE);
        g.setFont(Font.loadFont("file:rsc/font/DodgeFont.ttf", 50));
        g.fillText(String.valueOf(score), 30, 60);
    }
}
