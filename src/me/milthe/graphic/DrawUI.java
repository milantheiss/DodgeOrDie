package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import me.milthe.UI.ButtonUi;
import me.milthe.UI.UiContainer;

public class DrawUI {
    UiContainer uiContainer = new UiContainer();

    public void render(GraphicsContext g) {
        for (ButtonUi buttonUI : UiContainer.components) {
            g.drawImage(buttonUI.sprite, buttonUI.getX(), buttonUI.getY(), buttonUI.getWidth(), buttonUI.getHeight());
        }
    }
}
