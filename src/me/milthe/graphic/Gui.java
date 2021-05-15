package me.milthe.graphic;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import me.milthe.ui.UiContainer;

import java.awt.*;

public class Gui {
    public static DrawEnvironment drawEnvironment;
    public static DrawEntities drawEntities;
    public static DrawUI drawUI;
    public static DrawIngameUi drawIngameUi;
    public static DrawTutorial drawTutorial;
    public static DrawEndscreen drawEndscreen;

    public static GraphicsContext gc_main;
    public static int width, height;
    public static Scene scene;
    public static Stage stage;

    public static UiContainer menuContainer = new UiContainer();
    public static UiContainer pauseContainer = new UiContainer();

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
        drawEndscreen = new DrawEndscreen();

        menuContainer.addComponent("start", "file:rsc/sprites/start.png");
        menuContainer.addComponent("steuerung", "file:rsc/sprites/steuerung.png");
        menuContainer.addComponent("verlassen", "file:rsc/sprites/verlassen.png");
        menuContainer.components.get(1).setMarginTop(menuContainer.components.get(1).getHeight()/2);
        menuContainer.components.get(2).setMarginTop(menuContainer.components.get(1).getHeight()/2);

        menuContainer.centerContainerToScreen();

        pauseContainer.addComponent("weiter", "file:rsc/sprites/weiter.png");
        pauseContainer.addComponent("neustart", "file:rsc/sprites/neustart.png");
        pauseContainer.addComponent("verlassen", "file:rsc/sprites/verlassen.png");
        pauseContainer.components.get(1).setMarginTop(pauseContainer.components.get(1).getHeight()/2);
        pauseContainer.components.get(2).setMarginTop(pauseContainer.components.get(1).getHeight()/2);
        pauseContainer.centerContainerToScreen();
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
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
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
        stage.close();
    }
}
