package me.milthe;

import javafx.application.Application;
import javafx.stage.Stage;
import me.milthe.clocks.CircleMovement;
import me.milthe.clocks.CircleSpawn;
import me.milthe.clocks.ClockMain;
import me.milthe.clocks.PlayerMovement;
import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Player;
import me.milthe.gui.Gui;

public class Main extends Application{
    Gui g = new Gui();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        g.init();
        g.create(stage);

        new Player();

        new ClockMain();

        new CircleMovement();
        CircleSpawn.start();
        new PlayerMovement();
    }
}
