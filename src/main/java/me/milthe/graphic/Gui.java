package me.milthe.graphic;

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
import me.milthe.events.MouseMoved;
import me.milthe.ui.MenuSetup;

import java.util.Objects;

public class Gui {
    public static DrawEnvironment drawEnvironment;
    public static DrawEntities drawEntities;
    public static DrawUI drawUI;
    public static DrawIngameUi drawIngameUi;
    public static DrawTutorial drawTutorial;
    public static DrawEndscreenEndless drawEndscreenEndless;

    public static GraphicsContext gc_main;
    public static int width, height;
    public static Scene scene;
    public static Stage stage;

    public static MenuSetup menuSetup;
    public static Menustates menustate;

    public Gui() {
        width = (int) Screen.getPrimary().getBounds().getWidth();
        height = (int) Screen.getPrimary().getBounds().getHeight();
    }

    public void init() {
        drawEnvironment = new DrawEnvironment();
        drawEntities = new DrawEntities();
        drawUI = new DrawUI();

        drawTutorial = new DrawTutorial();
        drawEndscreenEndless = new DrawEndscreenEndless();

        menuSetup = new MenuSetup();
    }

    public void create(Stage stage) { //JavaFX Setup
        Gui.stage = stage;
        Canvas canvas_main;
        StackPane root = new StackPane();

        int cWidth = width - 10, cHeight = height - 10;
        canvas_main = new Canvas(width, height);
        gc_main = canvas_main.getGraphicsContext2D();

        drawIngameUi = new DrawIngameUi(Gui.gc_main);

        root.getChildren().add(canvas_main);
        scene = new Scene(root, cWidth, cHeight);

        //EventListeners
        scene.setOnMouseClicked(new MouseClicked());
        scene.setOnMouseMoved(new MouseMoved());
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

    public static void close(){
        System.out.println("close");
        Platform.exit();
        System.exit(0);
        stage.close();
    }
}
