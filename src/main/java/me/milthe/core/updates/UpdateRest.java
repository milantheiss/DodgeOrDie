package me.milthe.core.updates;

import javafx.scene.input.KeyCode;
import me.milthe.core.Game;
import me.milthe.core.Gamestates;
import me.milthe.core.UpdateController;
import me.milthe.events.MouseClicked;
import me.milthe.gamemode.Gamemodes;
import me.milthe.graphic.DrawEndscreenEndless;
import me.milthe.graphic.DrawHighscore;
import me.milthe.graphic.DrawTutorial;
import me.milthe.ui.Gui;
import me.milthe.ui.Menustates;

/**
 * Restliche Methoden, welche geupdated werden müssen
 */
public class UpdateRest {
    private final UpdateController updateController;
    private final Game game;

    /**
     * Erstellt neuen UpdateRest
     * @param updateController Das Hauptobjekt von UpdateController
     * @param game Das Hauptobjekt von Game.java des Projekts
     */
    public UpdateRest(UpdateController updateController, Game game) {
        this.updateController = updateController;
        this.game = game;
    }

    /**
     * Führt Updates aus
     */
    public void runUpdate() {
        if (Game.state == Gamestates.ENDSCREEN || Game.state == Gamestates.HIGHSCORE) {
            simpleController();
        } else if (Game.state == Gamestates.TUTORIAL) {
            tutorialController();
        }
    }

    /**
     * Wickelt Benutzereingaben im Endscreen und Highscore ab und definiert Events, die auf Benutzereingaben folgen
     */
    private void simpleController() {
        if (Game.input.isPressed(KeyCode.ESCAPE) || ((updateController.isComponentClicked(DrawEndscreenEndless.getZurueck()) || updateController.isComponentClicked(DrawHighscore.getZurueck())) && !MouseClicked.clickHandeled)) {
            MouseClicked.clickHandeled = true;
            Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
            if (Game.mode == Gamemodes.ENDLESS && Game.state == Gamestates.ENDSCREEN) {
                game.endless.terminateEndless();
            }
            Game.state = Gamestates.MENU;
        }
    }

    /**
     * Wickelt Benutzereingaben im Tutorial ab und definiert Events, die auf Benutzereingaben folgen
     */
    private void tutorialController() {
        if (Game.input.isPressed(KeyCode.ESCAPE)) {
            Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
            Game.state = Gamestates.MENU;
        }
        if (updateController.isComponentClicked(DrawTutorial.zurueckButton) && !MouseClicked.clickHandeled) {
            MouseClicked.clickHandeled = true;
            Game.state = Gamestates.MENU;
        }
        if (updateController.isComponentClicked(DrawTutorial.leftButton) && !MouseClicked.clickHandeled) {
            MouseClicked.clickHandeled = true;
            DrawTutorial.setIndex(-1);
        }
        if (updateController.isComponentClicked(DrawTutorial.rightButton) && !MouseClicked.clickHandeled) {
            MouseClicked.clickHandeled = true;
            DrawTutorial.setIndex(1);
        }
    }
}
