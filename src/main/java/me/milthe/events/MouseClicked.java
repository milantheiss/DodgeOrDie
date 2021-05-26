package me.milthe.events;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseClicked implements EventHandler<MouseEvent> {
    public static int x, y;
    public static boolean clickHandeled;

    @Override
    public void handle(MouseEvent mouseEvent) {
        x = (int) mouseEvent.getX();
        y = (int) mouseEvent.getY();
        clickHandeled = false;
    }
}
