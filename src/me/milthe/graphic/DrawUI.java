package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import me.milthe.ui.ButtonUi;
import me.milthe.ui.UiContainer;

public class DrawUI {
    UiContainer uiContainer = new UiContainer();

    public void render(GraphicsContext g, UiContainer container) {
        for (ButtonUi buttonUI : container.components) {
            g.drawImage(buttonUI.sprite, buttonUI.getX(), buttonUI.getY(), buttonUI.getWidth(), buttonUI.getHeight());
        }
    }
}
