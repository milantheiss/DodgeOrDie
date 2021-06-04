package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.milthe.extras.Highscore;
import me.milthe.ui.UiButton;

public class DrawHighscore {
    private static UiButton zurueck;

    public DrawHighscore() {
        zurueck = new UiButton("zurueck", getClass().getResourceAsStream("/sprites/buttons/zurueck.png"));
        zurueck.setX((Gui.width - zurueck.getWidth()) / 2);
        zurueck.setY((Gui.height - zurueck.getHeight()) / 2 + 140);
    }

    public void render(GraphicsContext g) {
        g.setFill(Color.WHITE);
        g.setFont(Font.loadFont(getClass().getResourceAsStream("/font/DodgeFont.ttf"), 50));
        g.fillText("Highscores", (Gui.width - 400) / 2, (Gui.height - 350) / 2);
        g.setFont(Font.loadFont(getClass().getResourceAsStream("/font/DodgeFont.ttf"), 40));
        g.fillText("Zeit  überlebt:  " + Highscore.getSurviedtime(), (Gui.width - 470) / 2, (Gui.height - 182) / 2);
        g.fillText("Gegner  überlebt: " + Highscore.getSurviedenemies(), (Gui.width - 560) / 2, (Gui.height - 43) / 2);
        g.fillText("Höhste Anzahl an Leben:   " + Highscore.getHighestnumberhealth(), (Gui.width - 730) / 2, (Gui.height + 96) / 2);
        g.drawImage(zurueck.getSprite(), zurueck.getX(), zurueck.getY(), zurueck.getWidth(), zurueck.getHeight());
    }

    public static UiButton getZurueck() {
        return zurueck;
    }
}
