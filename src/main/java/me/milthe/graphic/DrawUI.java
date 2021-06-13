package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import me.milthe.ui.UiComponent;
import me.milthe.ui.UiContainer;

/**
 * Draw Klasse für das UI
 */
public class DrawUI {
    /**
     * Redert alle Komponenten des Ui
     * @param g Grafische Oberfläche auf, die das Bild gemalt werden soll
     */
    public void render(GraphicsContext g, UiContainer container) {
        for (UiComponent uiComponent : container.getComponents()) {
            g.drawImage(uiComponent.getSprite(), uiComponent.getX(), uiComponent.getY(), uiComponent.getWidth(), uiComponent.getHeight());
        }
    }
}

