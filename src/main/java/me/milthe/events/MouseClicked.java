package me.milthe.events;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Registriert wenn die linke oder rechte Maustaste gedrückt wurde
 */
public class MouseClicked implements EventHandler<MouseEvent> {
    public static int x, y;
    public static boolean clickHandeled;

    /**
     * Setzt bei Maus Klick die Position der Maus um und setzt clickHandeled auf false
     * @param mouseEvent Benötigtes MouseEvent wird von EventHandler erstellt
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        x = (int) mouseEvent.getX();
        y = (int) mouseEvent.getY();
        clickHandeled = false;
    }
}
