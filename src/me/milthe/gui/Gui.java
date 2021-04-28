package me.milthe.gui;

import com.sun.javafx.collections.ElementObservableListDecorator;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import me.milthe.draw.DrawMain;
import me.milthe.events.*;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Gui {
    public static DrawMain dm;

    public static int width, height;
    public static GraphicsContext gc_main;

    public Gui() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        width = gd.getDisplayMode().getWidth();
        height = gd.getDisplayMode().getHeight();
    }

    public void init() {
        dm = new DrawMain();
    }

    public void create(Stage stage) {
        Canvas canvas_main;
        StackPane root = new StackPane();
        int cWidth = width - 10, cHeight = height - 10;

        canvas_main = new Canvas(width, height);
        gc_main = canvas_main.getGraphicsContext2D();
        dm.draw(gc_main);

        root.getChildren().add(canvas_main);
        Scene scene = new Scene(root, cWidth, cHeight);

        scene.setOnMouseMoved(new MouseMoved());
        scene.setOnKeyPressed(new KeyPressed());
        scene.setOnKeyReleased(new KeyReleased());


        stage.setTitle("Dodge or Die");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
}
