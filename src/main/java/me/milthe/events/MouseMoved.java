package me.milthe.events;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseMoved implements EventHandler<MouseEvent> { //Updatet die Position des Cursors. Wir Stand V0.0.2 nur für MouseDash benötigt
    public static int x, y;

    @Override
    public void handle(MouseEvent mouseEvent) {
        x = (int) mouseEvent.getX();
        y = (int) mouseEvent.getY();
    }
}
