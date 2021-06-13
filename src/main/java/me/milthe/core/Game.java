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
    private static Endless endless;

    private static Gamestates state;
    private static Gamemodes mode;

    private static File GAME_DIRECTORY;

    private static Input input;
    private static Jukebox jukebox;

    private static Player player;
    private static List<Entity> entities;

    /**
     * Erstellt, falls nicht vorhanden, beim erstellen eines neuen Game Objektes ein lokales Game Directory
     * @throws IOException
     * @throws URISyntaxException
     */
    public Game() throws IOException, URISyntaxException {
        GAME_DIRECTORY = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "dodgeordie");
        GAME_DIRECTORY.mkdir();

        input = new Input();
        jukebox = new Jukebox();

        state = Gamestates.MENU;
        Gui.menustate = Menustates.MAIN;

        new Highscore();
        endless = new Endless();

        entities = new CopyOnWriteArrayList<>();
    }

    /**
     * Gibt entities Liste zurück
     * @return entitie List
     */
    public static List<Entity> getEntities() {
        return entities;
    }

    /**
     * Fügt gegebene Entity zu entities Liste hinzu
     * @param entity Objekt, das in entities Liste hinzugefügt werden soll
     */
    public static void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Entfernt gegebene Entity von entities Liste
     * @param entity Objekt, das aus entities Liste entfernt werden soll
     */
    public static void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    /**
     * Gibt Spieler zurück
     * @return Haupt Player Objekt
     */
    public static Player getPlayer() {
        return player;
    }

    /**
     * Setzt Player
     * @param player Das Player Objekt, das das neue haupt Player Objekt sein soll
     */
    public static void setPlayer(Player player) {
        Game.player = player;
    }

    /**
     * Gibt Input zurück, der die Benutzereingaben sammelt
     * @return Haupt Input des Projektes
     */
    public static Input getInput() {
        return input;
    }

    /**
     * Gibt Jukebox zurück, die für die Audioausgabe Ingame verantwortlich ist
     * @return Haupt Jukebox des Projektes
     */
    public static Jukebox getJukebox() {
        return jukebox;
    }

    /**
     * Gibt Game Directory assoziiert mit dem Spiel zurück
     * @return Game Directory, das beim Erstellen eines neuen Game.java Objekts erstellt wird
     */
    public static File getGameDirectory() {
        return GAME_DIRECTORY;
    }

    /**
     * Gibt momentanen Gamestate zurück
     * @return momentaner Gamestate
     */
    public static Gamestates getGamestate() {
        return state;
    }

    /**
     * Setzt momentanen Gamestate
     * @param state Neuer momentanen Gamestate
     */
    public static void setGamestate(Gamestates state) {
        Game.state = state;
    }

    /**
     * Gibt das Endless.java Objekt des Projekt zurück
     * @return Haupt Endless Objekt des Projektes
     */
    public static Endless getEndless() {
        return endless;
    }

    /**
     * Gibt momentanen Gamemode zurück
     * @return momentaner Gamemode
     */
    public static Gamemodes getGamemode() {
        return mode;
    }

    /**
     * Setzt momentanen Gamemode
     * @param mode Neuer momentanen Gamemode
     */
    public static void setGamemode(Gamemodes mode) {
        Game.mode = mode;
    }
}
