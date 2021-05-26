package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import me.milthe.ui.UiButton;

public class DrawTutorial {
    Image keybinds = new Image("file:rsc/sprites/keybinds.png");
    public static UiButton zurueckButton = new UiButton("zurueck", DrawTutorial.class.getResourceAsStream("/sprites/buttons/zurueck.png"));

    public DrawTutorial() {
        zurueckButton.setX((Gui.width - zurueckButton.getWidth()) / 2);
        System.out.println();
        zurueckButton.setYToBeSum((int) (((Gui.height - keybinds.getHeight()) / 2) + keybinds.getHeight()), 50);
    }

    public void render(GraphicsContext g) {
        g.drawImage(keybinds, (Gui.width - keybinds.getWidth()) / 2, (Gui.height - keybinds.getHeight()) / 2, keybinds.getWidth(), keybinds.getHeight());

        g.drawImage(zurueckButton.getSprite(), zurueckButton.getX(), zurueckButton.getY(), zurueckButton.getWidth(), zurueckButton.getHeight());
    }
}