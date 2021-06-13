package me.milthe.core.updates;

import javafx.scene.input.KeyCode;
import me.milthe.core.Game;
import me.milthe.core.Gamestates;
import me.milthe.entities.actions.Collision;
import me.milthe.ui.Gui;
import me.milthe.ui.Menustates;

/**
 * Standard Klasse für Gamemode Updates
 */
public abstract class UpdateGamemodes {
    protected final Collision col;

    /**
     * Erstellt eine neuen abstrakten UpdateGamemode
     */
    public UpdateGamemodes() {
        col = new Collision();
    }

    /**
     * Führt, speziell für den Gamemode definierte Updates aus. Muss für jeden neuen Gamemode einzeln angepasst werden
     */
    public abstract void runUpdate();

    /**
     * Definiert, speziell für den Gamemode, was bei einer Kollision zwischen dem Spieler und einer anderen Entity passiert. Muss für jeden neuen Gamemode einzeln angepasst werden
     */
    protected abstract void collisionAction();

    /**
     * Wartet auf Benutzereingabe: Wenn ESCAPE gedrückt wird, wird das Spiel pausiert
     */
    protected void ingamePauseListener() {
        if (Game.getInput().isPressed(KeyCode.ESCAPE)) {
            Game.setGamestate(Gamestates.PAUSE);
            Gui.menustate = Menustates.PAUSE;
            Game.getInput().pressed[KeyCode.ESCAPE.getCode()] = false;
            Game.getJukebox().stopInGameMusic();
        }
    }

    /**
     * Testet ob Spiel fokusiert ist. Wenn nicht wird das Spiel pausiert
     */
    protected void checkIfWindowIsFocused() {
        if (!Gui.stage.isFocused()) {
            Game.setGamestate(Gamestates.PAUSE);
            Gui.menustate = Menustates.PAUSE;
            Game.getJukebox().stopInGameMusic();
        }
    }
}
