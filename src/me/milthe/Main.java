package me.milthe;

import javafx.application.Application;
import javafx.stage.Stage;
import me.milthe.clocks.*;
import me.milthe.entities.Player;
import me.milthe.gui.Gui;

public class Main extends Application {
    Gui g = new Gui();

    //Launcht Application
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        g.init();
        g.create(stage);

        //Startet Clock
        new Thread(new ClockMain()).start();
    }
}
