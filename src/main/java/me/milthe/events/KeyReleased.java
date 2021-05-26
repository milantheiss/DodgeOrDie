package me.milthe.events;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import me.milthe.core.Game;

public class KeyReleased implements EventHandler<KeyEvent> {//Wenn Taste losgelassen wird, wird die Taste an Input.java geschickt in Zahl umgewandelt und assoziierter Array Spot auf false gesetzt

    @Override
    public void handle(KeyEvent keyEvent) {
        Game.input.pressed[keyEvent.getCode().getCode()] = false;
    }
}
