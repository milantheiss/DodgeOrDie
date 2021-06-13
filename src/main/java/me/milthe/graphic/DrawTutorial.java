package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import me.milthe.ui.Gui;
import me.milthe.ui.Tutorial;

/**
 * Draw Klasse für den Tutorial Screen
 */
public class DrawTutorial {
    public static Tutorial tutorial;
    public DrawTutorial(){
        tutorial = new Tutorial();
    }

    /**
     * Redert alle Komponenten des Tutorial Screens
     * @param g Grafische Oberfläche auf, die das Bild gemalt werden soll
     */
    public void render(GraphicsContext g) {
        g.drawImage(tutorial.getTutorialImage(tutorial.getIndex()), (Gui.WIDTH - tutorial.getTutorialImage(tutorial.getIndex()).getWidth()) / 2, (Gui.HEIGHT - tutorial.getTutorialImage(tutorial.getIndex()).getHeight()) / 2, tutorial.getTutorialImage(tutorial.getIndex()).getWidth(), tutorial.getTutorialImage(tutorial.getIndex()).getHeight());
        tutorial.getZURUECK_BUTTON().setYToBeSum((int) (((Gui.HEIGHT - tutorial.getTutorialImage(0).getHeight()) / 2) + tutorial.getTutorialImage(0).getHeight()), 50);
        tutorial.getLEFT_BUTTON().setY(tutorial.getZURUECK_BUTTON().getY() + 3);
        tutorial.getRIGHT_BUTTON().setY(tutorial.getZURUECK_BUTTON().getY() + 1);
        g.drawImage(tutorial.getZURUECK_BUTTON().getSprite(), tutorial.getZURUECK_BUTTON().getX(), tutorial.getZURUECK_BUTTON().getY(), tutorial.getZURUECK_BUTTON().getWidth(), tutorial.getZURUECK_BUTTON().getHeight());
        g.drawImage(tutorial.getLEFT_BUTTON().getSprite(), tutorial.getLEFT_BUTTON().getX(), tutorial.getLEFT_BUTTON().getY(), tutorial.getLEFT_BUTTON().getWidth(), tutorial.getLEFT_BUTTON().getHeight());
        g.drawImage(tutorial.getRIGHT_BUTTON().getSprite(), tutorial.getRIGHT_BUTTON().getX(), tutorial.getRIGHT_BUTTON().getY(), tutorial.getRIGHT_BUTTON().getWidth(), tutorial.getRIGHT_BUTTON().getHeight());
    }
}
