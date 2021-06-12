package me.milthe.core;

import me.milthe.entities.*;
import me.milthe.events.*;
import me.milthe.gamemode.Gamemodes;
import me.milthe.gamemode.Endless;
import me.milthe.ui.Gui;
import me.milthe.ui.Menustates;
import me.milthe.scoring.Highscore;
import me.milthe.sounds.Jukebox;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Zentrale Game Klasse. Ruft integrale Klassen auf
 */
public class Game {
    public static Game game;

    public Highscore highscore;
    public Endless endless;

    public static Gamestates state;
    public static Gamemodes mode;

    public static File GAMEDATA_DIRECTORY;

    public static Input input;
    public static Jukebox jukebox;

    public static Player player;
    public static List<Entity> entities;

    /**
     * Erstellt, falls nicht vorhanden, beim erstellen eines neuen Game Objektes ein lokales Game Directory
     * @throws IOException
     * @throws URISyntaxException
     */
    public Game() throws IOException, URISyntaxException {
        game = this;

        GAMEDATA_DIRECTORY = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "dodgeordie");
        GAMEDATA_DIRECTORY.mkdir();

        input = new Input();
        jukebox = new Jukebox();

        state = Gamestates.MENU;
        Gui.menustate = Menustates.MAIN;

        highscore = new Highscore();
        endless = new Endless(this);

        entities = new CopyOnWriteArrayList<>();
    }

    /**
     * Gibt entities Liste zurück
     * @return entitie List
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Fügt gegebene Entity zu entities Liste hinzu
     * @param entity Objekt, das in entities Liste hinzugefügt werden soll
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
        entity.setGame(this);
    }

    /**
     * Entfernt gegebene Entity von entities Liste
     * @param entity Objekt, das aus entities Liste entfernt werden soll
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    /**
     * Gibt Spieler zurück
     * @return Spieler
     */
    public static Player getPlayer() {
        return player;
    }
}
