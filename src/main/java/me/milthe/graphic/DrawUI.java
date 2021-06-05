package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.milthe.ui.UiCompontent;
import me.milthe.ui.UiContainer;

public class DrawUI {
    public void render(GraphicsContext g, UiContainer container) {
        for (UiCompontent uiCompontent : container.components) {
            g.drawImage(uiCompontent.getSprite(), uiCompontent.getX(), uiCompontent.getY(), uiCompontent.getWidth(), uiCompontent.getHeight());
        }
    }
}

