package me.milthe.events;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import me.milthe.core.Game;
import me.milthe.graphic.Gui;

public class KeyReleased implements EventHandler<KeyEvent> {//Wenn Taste losgelassen wird, wird die Taste an Input.java geschickt in Zahl umgewandelt und assoziierter Array Spot auf false gesetzt

    @Override
    public void handle(KeyEvent keyEvent) {
        Gui.menuSetup.SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.uiTextFields.forEach(uiTextField -> {
            if (!uiTextField.isRequestingInput()) {
                try {
                    Game.input.pressed[keyEvent.getCode().getCode()] = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
