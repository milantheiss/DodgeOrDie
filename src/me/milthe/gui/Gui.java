package me.milthe.gui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import me.milthe.draw.Draw;
import me.milthe.events.*;

import java.awt.*;

public class Gui {
    public static Draw dm;

    public static int width, height;
    public static GraphicsContext gc_main;

    public static Input in;

    public Gui() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(); //Setzt den Bildschirm auf dem das Programm laufen soll
        width = gd.getDisplayMode().getWidth(); //--> GetScreensize
        height = gd.getDisplayMode().getHeight();
        in = new Input();
    }

    public void init() {
        dm = new Draw();
    }

    public void create(Stage stage) { //JavaFX Setup
        Canvas canvas_main;
        StackPane root = new StackPane();
        int cWidth = width - 10, cHeight = height - 10;

        canvas_main = new Canvas(width, height);
        gc_main = canvas_main.getGraphicsContext2D();
        dm.draw(gc_main);

        root.getChildren().add(canvas_main);
        Scene scene = new Scene(root, cWidth, cHeight);

        //EventListeners
        scene.setOnMouseMoved(new MouseMoved());
        scene.setOnKeyPressed(new KeyPressed());
        scene.setOnKeyReleased(new KeyReleased());


        stage.setTitle("Dodge or Die");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
