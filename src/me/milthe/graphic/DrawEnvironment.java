package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.milthe.core.Game;
import me.milthe.core.Gamestate;
import me.milthe.core.GamestateEnum;


public class DrawEnvironment {
    public static int score; //JavaFX Draw um Grafik anzuzeigen Wird in GameLoop.java render() aufgerufen

    public void draw(GraphicsContext g) {
        //Hintergrund
        g.setFill(new Color(4. / 255., 0. / 255., 17. / 255., 1));
        g.fillRect(0, 0, Gui.width, Gui.height);

        //Collision Score
        if (Gamestate.state == GamestateEnum.ingame || Gamestate.state == GamestateEnum.pause) {
            g.setFill(Color.WHITE);
            g.setFont(Font.loadFont("file:rsc/font/DodgeFont.ttf", 50));
            g.fillText(String.valueOf(score), 30, 60);
        }
    }
}
