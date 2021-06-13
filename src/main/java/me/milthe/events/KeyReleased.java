package me.milthe.events;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import me.milthe.core.Game;

/**
 * Registriert wenn eine Taste auf der Tastatur nicht mehr gedrückt wird
 */
public class KeyReleased implements EventHandler<KeyEvent> {//Wenn Taste losgelassen wird, wird die Taste an Input.java geschickt in Zahl umgewandelt und assoziierter Array Spot auf false gesetzt

    /**
     * Setzt boolean assoziiert mit Taste auf false
     * @param keyEvent Benötigtes KeyEvent wird von EventHandler erstellt
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        try {
            Game.getInput().pressed[keyEvent.getCode().getCode()] = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
