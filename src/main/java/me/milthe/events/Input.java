package me.milthe.events;

import javafx.scene.input.KeyCode;

/**
 * Handelt Benutzerinputs und speichert ab welche Tasten gedrückt sind
 */
public class Input {
    public boolean[] pressed;

    public Input() {
        pressed = new boolean[KeyCode.values().length];
    }

    /**
     * Gibt zurück ob Taste gedrückt ist
     * @param code interger repräsentativ für Taste
     * @return true wenn Taste gedrückt ist
     */
    public boolean isPressed(KeyCode code) { //return ob Taste gedrückt wird
        return pressed[code.getCode()];
    }
}
