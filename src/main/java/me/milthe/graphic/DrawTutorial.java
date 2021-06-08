package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import me.milthe.ui.Gui;
import me.milthe.ui.UiButton;

public class DrawTutorial {
    private Image[] tutorialImages = new Image[5];
    public static UiButton zurueckButton = new UiButton("zurueck", DrawTutorial.class.getResourceAsStream("/sprites/buttons/zurueck.png"));
    public static UiButton leftButton = new UiButton("left", DrawTutorial.class.getResourceAsStream("/sprites/buttons/left.png"));
    public static UiButton rightButton = new UiButton("right", DrawTutorial.class.getResourceAsStream("/sprites/buttons/right.png"));
    private static int index;

    public DrawTutorial() {
        index = 0;
        tutorialImages[0] = new Image(DrawTutorial.class.getResourceAsStream("/sprites/tutorial/spieler.png"));
        tutorialImages[1] = new Image(DrawTutorial.class.getResourceAsStream("/sprites/tutorial/endless.png"));
        tutorialImages[2] = new Image(DrawTutorial.class.getResourceAsStream("/sprites/tutorial/circleenemy.png"));
        tutorialImages[3] = new Image(DrawTutorial.class.getResourceAsStream("/sprites/tutorial/bouncy.png"));
        tutorialImages[4] = new Image(DrawTutorial.class.getResourceAsStream("/sprites/tutorial/friend.png"));
        zurueckButton.setX((Gui.width - zurueckButton.getWidth()) / 2);
        leftButton.setX((Gui.width - zurueckButton.getWidth()) / 2 - (leftButton.getWidth() * 2));
        rightButton.setX((Gui.width - zurueckButton.getWidth()) / 2 + (zurueckButton.getWidth() + rightButton.getWidth()));
    }

    public void render(GraphicsContext g) {
        g.drawImage(tutorialImages[index], (Gui.width - tutorialImages[index].getWidth()) / 2, (Gui.height - tutorialImages[index].getHeight()) / 2, tutorialImages[index].getWidth(), tutorialImages[index].getHeight());
        zurueckButton.setYToBeSum((int) (((Gui.height - tutorialImages[0].getHeight()) / 2) + tutorialImages[0].getHeight()), 50);
        leftButton.setY(zurueckButton.getY() + 3);
        rightButton.setY(zurueckButton.getY() + 1);
        g.drawImage(zurueckButton.getSprite(), zurueckButton.getX(), zurueckButton.getY(), zurueckButton.getWidth(), zurueckButton.getHeight());
        g.drawImage(leftButton.getSprite(), leftButton.getX(), leftButton.getY(), leftButton.getWidth(), leftButton.getHeight());
        g.drawImage(rightButton.getSprite(), rightButton.getX(), rightButton.getY(), rightButton.getWidth(), rightButton.getHeight());
    }

    public static void setIndex(int index) {
        if ((DrawTutorial.index + index) < 0) {
            DrawTutorial.index = 4;
        } else if ((DrawTutorial.index + index) > 4) {
            DrawTutorial.index = 0;
        } else {
            DrawTutorial.index += index;
        }
    }
}
