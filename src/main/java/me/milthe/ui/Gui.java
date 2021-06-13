package me.milthe.ui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import me.milthe.events.KeyPressed;
import me.milthe.events.KeyReleased;
import me.milthe.events.MouseClicked;
import me.milthe.graphic.*;

import java.util.Objects;

/**
 * Stellt JavaFX Fester ein und enthält integrale Parameter
 */
public class Gui {
    public static DrawEnvironment drawEnvironment;
    public static DrawEntities drawEntities;
    public static DrawUI drawUI;
    public static DrawIngameUi drawIngameUi;
    public static DrawTutorial drawTutorial;
    public static DrawEndscreenEndless drawEndscreenEndless;
    public static DrawHighscore drawHighscore;

    public static GraphicsContext gc_main;
    public final static int WIDTH = (int) Screen.getPrimary().getBounds().getWidth(), HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
    public static Scene scene;
    public static Stage stage;

    public static Menus menus;
    public static Menustates menustate;

    public Gui() {
        drawEnvironment = new DrawEnvironment();
        drawEntities = new DrawEntities();
        drawUI = new DrawUI();
        drawTutorial = new DrawTutorial();
        drawEndscreenEndless = new DrawEndscreenEndless();
        drawHighscore = new DrawHighscore();
        menus = new Menus();
    }

    /**
     * Erstellt neues JavaFX Fenster
     * @param stage Primäre Stage
     */
    public void create(Stage stage) { //JavaFX Setup
        Gui.stage = stage;
        Canvas canvas_main;
        StackPane root = new StackPane();

        int cWidth = WIDTH - 10, cHeight = HEIGHT - 10;
        canvas_main = new Canvas(WIDTH, HEIGHT);
        gc_main = canvas_main.getGraphicsContext2D();

        drawIngameUi = new DrawIngameUi(Gui.gc_main);

        root.getChildren().add(canvas_main);
        scene = new Scene(root, cWidth, cHeight);

        //EventListeners
        scene.setOnMouseClicked(new MouseClicked());
        scene.setOnKeyPressed(new KeyPressed());
        scene.setOnKeyReleased(new KeyReleased());

        //Setzt die Window Properties
        stage.setTitle("Dodge or Die");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/app_icon.png"))));
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

    /**
     * Schließt die JavaFX Anwendung
     */
    public static void close() {
        Platform.exit();
        System.exit(0);
        stage.close();
    }
}
