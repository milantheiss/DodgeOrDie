package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.milthe.core.Scoring;
import me.milthe.core.Time;
import me.milthe.ui.ButtonUi;

public class DrawEndscreen {
    private static ButtonUi zurueck;

    public DrawEndscreen() {
        zurueck = new ButtonUi("zurueck", "file:rsc/sprites/zurueck.png");
        zurueck.setX((Gui.width - zurueck.getWidth()) / 2);
        zurueck.setY((Gui.height - zurueck.getHeight()) / 2 + 85);
    }

    public void render(GraphicsContext g) {
        g.setFill(new Color(45. / 255., 45. / 255., 45. / 255., 0.5));
        g.fillRect(0, 0, Gui.width, Gui.height);
        g.setFill(Color.WHITE);
        g.setFont(Font.loadFont("file:rsc/font/DodgeFont.ttf", 40));
        g.fillText("Gegner  überlebt: " + String.valueOf(Scoring.totalEnemiesSpawned), (Gui.width - 560) / 2, (Gui.height - 182) / 2);
        g.fillText("Zeit  überlebt:  " + Time.getTime(), (Gui.width - 590) / 2, (Gui.height - 43) / 2);
        g.drawImage(zurueck.sprite, zurueck.getX(), zurueck.getY(), zurueck.getWidth(), zurueck.getHeight());
        //TODO add Button zurück & neustart + Überlebt Gegner anzeige Count
    }

    public static ButtonUi getZurueck() {
        return zurueck;
    }
}
