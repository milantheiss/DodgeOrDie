package me.milthe.events;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import me.milthe.core.Game;

/**
 * Registiert wenn eine Taste auf der Tastatur gedrückt wurde
 */
public class KeyPressed implements EventHandler<KeyEvent> {

    /**
     * Setzt boolean assoziiert mit Taste auf true
     * @param keyEvent Benötigtes KeyEvent wird von EventHandler erstellt
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        try {
            Game.getInput().pressed[keyEvent.getCode().getCode()] = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}