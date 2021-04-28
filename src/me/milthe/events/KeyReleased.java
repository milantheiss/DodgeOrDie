package me.milthe.events;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import me.milthe.entities.Player;

public class KeyReleased implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W) {
            Player.setYVelocity(0);
        } else if (keyEvent.getCode() == KeyCode.S) {
            Player.setYVelocity(0);
        } else if (keyEvent.getCode() == KeyCode.A) {
            Player.setXVelocity(0);
        } else if (keyEvent.getCode() == KeyCode.D) {
            Player.setXVelocity(0);
        }
    }
}
