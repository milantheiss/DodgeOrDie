package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.milthe.core.Time;
import me.milthe.gamemode.Endless;
import me.milthe.scoring.Highscore;
import me.milthe.ui.Gui;
import me.milthe.ui.UiButton;

import java.util.Objects;

/**
 * Draw Klasse für den Endscreen des Endlos Modus
 */
public class DrawEndscreenEndless {
    private static UiButton zurueck;
    private final Image highscoreImage;

    /**
     * Erstellt zurueck UiButton, setzt X und Y von zurueck UiButton und inizalisiert highscoreImage
     */
    public DrawEndscreenEndless() {
        zurueck = new UiButton("zurueck", getClass().getResourceAsStream("/sprites/buttons/zurueck.png"));
        zurueck.setX((Gui.width - zurueck.getWidth()) / 2);
        zurueck.setY((Gui.height - zurueck.getHeight()) / 2 + 140);
        highscoreImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/newhighscore.png")));
    }

    /**
     * Redert alle Komponenten des Endscreens
     * @param g Grafische Oberfläche auf, die das Bild gemalt werden soll
     */
    public void render(GraphicsContext g) {
        g.setFill(new Color(45. / 255., 45. / 255., 45. / 255., 0.5));
        g.fillRect(0, 0, Gui.width, Gui.height);
        if (Highscore.isNewHighscore()) g.drawImage(highscoreImage, (Gui.width - highscoreImage.getWidth()) / 2, (Gui.height - 450) / 2, highscoreImage.getWidth(), highscoreImage.getHeight());
        g.setFill(Color.WHITE);
        g.setFont(Font.loadFont(getClass().getResourceAsStream("/font/DodgeFont.ttf"), 40));
        g.fillText("Zeit  überlebt:  " + Time.getTimeString(Time.getTimeInSeconds()), (Gui.width - 590) / 2, (Gui.height - 182) / 2);
        g.fillText("Gegner  überlebt: " + Endless.totalEnemiesSpawned, (Gui.width - 560) / 2, (Gui.height - 43) / 2);
        g.fillText("Höhste Anzahl an Leben:   " + Endless.highestAmountOfHealth, (Gui.width - 730) / 2, (Gui.height + 96) / 2);
        g.drawImage(zurueck.getSprite(), zurueck.getX(), zurueck.getY(), zurueck.getWidth(), zurueck.getHeight());
    }

    /**
     * Gibt zurueck UiButton zurück
     * @return zurueck UiButton. Wir in Konstruktor erstellt
     */
    public static UiButton getZurueck() {
        return zurueck;
    }
}
