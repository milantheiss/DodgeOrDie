package me.milthe.events;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import me.milthe.core.Game;
import me.milthe.graphic.Gui;

import java.util.Locale;

public class KeyPressed implements EventHandler<KeyEvent> {//Wenn Taste gedrückt wird, wird die Taste an Input.java geschickt in Zahl umgewandelt und assoziierter Array Spot auf true gesetzt

    @Override
    public void handle(KeyEvent keyEvent) {

        Gui.menuSetup.SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.uiTextFields.forEach(uiTextField -> {
            if (uiTextField.isRequestingInput()) {
                if (keyEvent.getCode() == KeyCode.ESCAPE) {
                    uiTextField.userInputString = "";
                    Gui.menuSetup.SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.components.get(0).setVisible(true);
                    uiTextField.setVisible(false);
                    uiTextField.setRequestingInput(false);
                } else if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
                    if (uiTextField.userInputString.length() > 0)
                        uiTextField.userInputString = uiTextField.userInputString.substring(0, uiTextField.userInputString.length() - 1);
                } else {
                    //TODO Nur Buchstaben und Zahlen zulassen
                    if (keyEvent.getCode().isLetterKey() || keyEvent.getCode().isDigitKey()) {
                        if (keyEvent.isShiftDown()) {
                            uiTextField.setUserinputString(keyEvent.getText().toUpperCase());
                        } else {
                            uiTextField.setUserinputString(keyEvent.getText());
                        }
                    }
                }
            } else {
                try {
                    Game.input.pressed[keyEvent.getCode().getCode()] = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}