package me.milthe.core;

import me.milthe.entities.*;
import me.milthe.events.*;
import me.milthe.gamemode.Gamemodes;
import me.milthe.gamemode.Endless;
import me.milthe.graphic.Gui;
import me.milthe.graphic.Menustates;
import me.milthe.scoring.Highscore;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Game {
    public Highscore highscore;
    public Endless endless;

    public static Gamestates state;
    public static Gamemodes mode;

    public static File GAMEDATA_DIRECTORY;

    public static Input input;

    public static Player player;
    public static List<Entity> entities;

    public Levelloader levelloader = new Levelloader(this);

    public Game() throws IOException, URISyntaxException {
        GAMEDATA_DIRECTORY = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "dodgeordie");
        GAMEDATA_DIRECTORY.mkdir();

        input = new Input();

        state = Gamestates.MENU;
        Gui.menustate = Menustates.MAIN;

        highscore = new Highscore();
        endless = new Endless(this);

        entities = new CopyOnWriteArrayList<>();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        entity.setGame(this);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public static Player getPlayer() {
        return player;
    }
}
