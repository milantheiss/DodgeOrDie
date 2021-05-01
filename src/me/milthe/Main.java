package me.milthe;

import javafx.application.Application;
import javafx.stage.Stage;
import me.milthe.clocks.*;
import me.milthe.entities.Entity;
import me.milthe.entities.Player;
import me.milthe.gui.Gui;

import java.util.ArrayList;

public class Main extends Application {
    Gui g = new Gui(this);
    public ArrayList<Entity> entities;
    CircleSpawn circleSpawn;

    //Launcht Application
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        //Erstellt neue Entity Objekte
        entities = new ArrayList<>();
        entities.add(new Player());

        g.init();
        g.create(stage);

        circleSpawn = new CircleSpawn(this);
        circleSpawn.start();

        //Startet Clock
        new Thread(new ClockMain(this)).start();
    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }
}
