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
        zurueck.setX((Gui.WIDTH - zurueck.getWidth()) / 2);
        zurueck.setY((Gui.HEIGHT - zurueck.getHeight()) / 2 + 140);
        highscoreImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/newhighscore.png")));
    }

    /**
     * Redert alle Komponenten des Endscreens
     * @param g Grafische Oberfläche auf, die das Bild gemalt werden soll
     */
    public void render(GraphicsContext g) {
        g.setFill(new Color(45. / 255., 45. / 255., 45. / 255., 0.5));
        g.fillRect(0, 0, Gui.WIDTH, Gui.HEIGHT);
        if (Highscore.isNewHighscore()) g.drawImage(highscoreImage, (Gui.WIDTH - highscoreImage.getWidth()) / 2, (Gui.HEIGHT - 450) / 2, highscoreImage.getWidth(), highscoreImage.getHeight());
        g.setFill(Color.WHITE);
        g.setFont(Font.loadFont(getClass().getResourceAsStream("/font/DodgeFont.ttf"), 40));
        g.fillText("Zeit  überlebt:  " + Time.getTimeString(Time.getTimeInSeconds()), (Gui.WIDTH - 590) / 2, (Gui.HEIGHT - 182) / 2);
        g.fillText("Gegner  überlebt: " + Endless.getTotalEnemiesSurvied(), (Gui.WIDTH - 560) / 2, (Gui.HEIGHT - 43) / 2);
        g.fillText("Höhste Anzahl an Leben:   " + Endless.getHighestAmountOfHealth(), (Gui.WIDTH - 730) / 2, (Gui.HEIGHT + 96) / 2);
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
