package me.milthe;

import javafx.application.Application;
import javafx.stage.Stage;
import me.milthe.clocks.CircleUpdater;
import me.milthe.clocks.CircleSpawn;
import me.milthe.clocks.MainUpdater;
import me.milthe.clocks.PlayerUpdater;
import me.milthe.entities.Player;
import me.milthe.events.Input;
import me.milthe.gui.Gui;

public class Main extends Application {
    Gui g = new Gui();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        g.init();
        g.create(stage);

        new Player();

        new Input();

        new MainUpdater();

        new PlayerUpdater(Gui.gc_main);
        CircleSpawn.start();
        new CircleUpdater(Gui.gc_main);
    }
}
