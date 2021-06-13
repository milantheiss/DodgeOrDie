package me.milthe;

import javafx.application.Application;
import javafx.stage.Stage;
import me.milthe.core.Game;
import me.milthe.core.GameLoop;
import me.milthe.ui.Gui;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Launcht JavaFX Application
 */
public class App extends Application {
    Gui g = new Gui();

    /**
     * Wird nach Programmstart aufgerufen und startet JavaFx Application
     * @param args Gegeben von Main
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start Klasse der JavaFX Application
     * @param stage Erstellt von JavaFX Application
     * @throws IOException
     * @throws URISyntaxException
     */
    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        g.create(stage);

        new Game();
        new Thread(new GameLoop()).start();
    }
}
