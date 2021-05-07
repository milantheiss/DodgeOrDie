package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import me.milthe.UI.ButtonUi;
import me.milthe.UI.UiContainer;
import me.milthe.core.Gamestate;
import me.milthe.core.GamestateEnum;

public class DrawUI {
    public void render(GraphicsContext g){
        if (Gamestate.state == GamestateEnum.menu){
            for (UiContainer container : Gui.uiComponents){
                for (ButtonUi buttonUI : container.components){
                    g.drawImage(buttonUI.sprite, buttonUI.getX(), buttonUI.getY(), buttonUI.getWidth(), buttonUI.getHeight());
                }
            }
        }
    }
}
