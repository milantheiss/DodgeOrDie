package me.milthe.events;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import me.milthe.entities.Player;
import me.milthe.gui.Gui;

public class KeyPressed implements EventHandler<KeyEvent> {//Wenn Taste gedrückt wird, wird die Taste an Input.java geschickt in Zahl umgewandelt und assoziierter Array Spot auf true gesetzt
    @Override
    public void handle(KeyEvent keyEvent) {
        Gui.in.pressed[Gui.in.getKeyCode(keyEvent.getCode())] = true;
    }
}