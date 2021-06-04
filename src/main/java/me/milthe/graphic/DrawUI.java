package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.milthe.ui.UiCompontent;
import me.milthe.ui.UiContainer;

public class DrawUI {

    public void render(GraphicsContext g, UiContainer container) {
        for (UiCompontent uiCompontent : container.components) {
            if (uiCompontent.isVisible()) {
                g.drawImage(uiCompontent.getSprite(), uiCompontent.getX(), uiCompontent.getY(), uiCompontent.getWidth(), uiCompontent.getHeight());
            }
            if (Gui.menustate == Menustates.SPIELMODI_CUSTOM_SELECT) {
                g.setFill(Color.WHITE);
                g.setFont(Font.font("Courier New", 30));
                g.fillText(Gui.menuSetup.SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.uiTextFields.get(0).userInputString, Gui.menuSetup.SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.uiTextFields.get(0).getX() + 10, Gui.menuSetup.SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.uiTextFields.get(0).getY() + 40);
            }
        }
    }
}

