package me.milthe.graphic;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import me.milthe.UI.UiContainer;
import me.milthe.core.GameLoop;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Gui {
    public static DrawEnvironment drawEnvironment;
    public static DrawEntities drawEntities;
    public static DrawUI drawUI;
    public static DrawIngameUi drawIngameUi;
    public static DrawTutorial drawTutorial;
    public static GraphicsContext gc_main;
    public static int width, height;
    public static Scene scene;
    public static List<UiContainer> uiComponents = new ArrayList<>();
    public static Stage stage;

    public Gui() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(); //Setzt den Bildschirm auf dem das Programm laufen soll
        width = gd.getDisplayMode().getWidth(); //--> GetScreensize
        height = gd.getDisplayMode().getHeight();
    }

    public void init() {
        drawEnvironment = new DrawEnvironment();
        drawEntities = new DrawEntities();
        drawUI = new DrawUI();
        drawIngameUi = new DrawIngameUi();
        drawTutorial = new DrawTutorial();

        uiComponents.add(new UiContainer());
    }

    public void create(Stage stage) { //JavaFX Setup
        Gui.stage = stage;
        Canvas canvas_main;
        StackPane root = new StackPane();

        int cWidth = width - 10, cHeight = height - 10;
        canvas_main = new Canvas(width, height);
        gc_main = canvas_main.getGraphicsContext2D();

        root.getChildren().add(canvas_main);
        scene = new Scene(root, cWidth, cHeight);

        //Setzt die Window Properties
        stage.setTitle("Dodge or Die");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        //Close Settings
        stage.setOnCloseRequest(windowEvent -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public static void close(){
        System.out.println("close");
        Platform.exit();
        System.exit(0);
    }
}
