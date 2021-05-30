package me.milthe;

import javafx.application.Application;
import javafx.stage.Stage;
import me.milthe.core.Game;
import me.milthe.core.GameLoop;
import me.milthe.graphic.Gui;

import java.io.IOException;
import java.net.URISyntaxException;

public class App extends Application {
    Gui g = new Gui();

    //Launcht Application
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        g.init();
        g.create(stage);

        //Startet Clock
        new Thread(new GameLoop(new Game())).start();
    }
}
