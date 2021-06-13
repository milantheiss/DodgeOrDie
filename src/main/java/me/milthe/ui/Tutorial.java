package me.milthe.ui;

import javafx.scene.image.Image;
import me.milthe.graphic.DrawTutorial;

/**
 * Ermöglicht das Tutorial anzuzeigen
 */
public class Tutorial {
    private final Image[] tutorialImages = new Image[5];
    private final UiButton ZURUECK_BUTTON = new UiButton("zurueck", DrawTutorial.class.getResourceAsStream("/sprites/buttons/zurueck.png"));
    private final UiButton LEFT_BUTTON = new UiButton("left", DrawTutorial.class.getResourceAsStream("/sprites/buttons/left.png"));
    private final UiButton RIGHT_BUTTON = new UiButton("right", DrawTutorial.class.getResourceAsStream("/sprites/buttons/right.png"));
    private int index;

    public Tutorial() {
        index = 0;
        tutorialImages[0] = new Image(DrawTutorial.class.getResourceAsStream("/sprites/tutorial/spieler.png"));
        tutorialImages[1] = new Image(DrawTutorial.class.getResourceAsStream("/sprites/tutorial/endless.png"));
        tutorialImages[2] = new Image(DrawTutorial.class.getResourceAsStream("/sprites/tutorial/circleenemy.png"));
        tutorialImages[3] = new Image(DrawTutorial.class.getResourceAsStream("/sprites/tutorial/bouncy.png"));
        tutorialImages[4] = new Image(DrawTutorial.class.getResourceAsStream("/sprites/tutorial/friend.png"));
        ZURUECK_BUTTON.setX((Gui.WIDTH - ZURUECK_BUTTON.getWidth()) / 2);
        LEFT_BUTTON.setX((Gui.WIDTH - ZURUECK_BUTTON.getWidth()) / 2 - (LEFT_BUTTON.getWidth() * 2));
        RIGHT_BUTTON.setX((Gui.WIDTH - ZURUECK_BUTTON.getWidth()) / 2 + (ZURUECK_BUTTON.getWidth() + RIGHT_BUTTON.getWidth()));
    }

    /**
     * Gibt Index des momentan angezeigten Bilds in Array zurück
     * @return Index des momentan angezeigten Bilds
     */
    public int getIndex() {
        return index;
    }

    /**
     * Rotiert durch Index, dass Array nicht OutOfBounce geht
     * @param index Erwartet entweder +1 oder -1
     */
    public void setIndex(int index) {
        if ((this.index + index) < 0) {
            this.index = 4;
        } else if ((this.index + index) > 4) {
            this.index = 0;
        } else {
            this.index += index;
        }
    }

    /**
     * Gibt Bild in Array an der Stelle index zurück
     * @param index Index des erwarteten Bilds
     * @return Bild in Array an der Stelle index
     */
    public Image getTutorialImage(int index) {
        return tutorialImages[index];
    }

    /**
     * Gibt zurück Button zurück
     * @return zurück Button
     */
    public UiButton getZURUECK_BUTTON() {
        return ZURUECK_BUTTON;
    }

    /**
     * Gibt linker Button zurück
     * @return linker Button
     */
    public UiButton getLEFT_BUTTON() {
        return LEFT_BUTTON;
    }

    /**
     * Gibt rechter Button zurück
     * @return rechter Button
     */
    public UiButton getRIGHT_BUTTON() {
        return RIGHT_BUTTON;
    }
}
