package me.milthe.events;

import javafx.scene.input.KeyCode;

public class Input {
    public boolean[] pressed;

    public Input(){
        pressed = new boolean[KeyCode.values().length];
    }

    public boolean isPressed(KeyCode code) { //return ob Taste gedrückt wird
        return pressed[code.getCode()];
    }
}
