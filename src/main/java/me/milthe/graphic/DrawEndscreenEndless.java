package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.milthe.core.Time;
import me.milthe.gamemode.Endless;
import me.milthe.ui.UiButton;

public class DrawEndscreenEndless {
    private static UiButton zurueck;

    public DrawEndscreenEndless() {
        zurueck = new UiButton("zurueck", "file:rsc/sprites/buttons/zurueck.png");
        zurueck.setX((Gui.width - zurueck.getWidth()) / 2);
        zurueck.setY((Gui.height - zurueck.getHeight()) / 2 + 140);
    }

    public void render(GraphicsContext g) {
        g.setFill(new Color(45. / 255., 45. / 255., 45. / 255., 0.5));
        g.fillRect(0, 0, Gui.width, Gui.height);
        g.setFill(Color.WHITE);
        g.setFont(Font.loadFont("file:rsc/font/DodgeFont.ttf", 40));
        g.fillText("Gegner  überlebt: " + Endless.totalEnemiesSpawned, (Gui.width - 560) / 2, (Gui.height - 182) / 2);
        g.fillText("Zeit  überlebt:  " + Time.getTime(), (Gui.width - 590) / 2, (Gui.height - 43) / 2);
        g.fillText("Höhste Anzahl an Leben:   " + Endless.highestAmountOfHealth, (Gui.width - 730) / 2, (Gui.height + 96) / 2);
        g.drawImage(zurueck.getSprite(), zurueck.getX(), zurueck.getY(), zurueck.getWidth(), zurueck.getHeight());
        //TODO add Button zurück & neustart + Überlebt Gegner anzeige Count
    }

    public static UiButton getZurueck() {
        return zurueck;
    }
}
