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

/**
 * Restliche Methoden, welche geupdated werden müssen
 */
public class UpdateRest {
    private final UpdateController updateController;

    /**
     * Erstellt neuen UpdateRest
     * @param updateController Das Hauptobjekt von UpdateController
     */
    public UpdateRest(UpdateController updateController) {
        this.updateController = updateController;
    }

    /**
     * Führt Updates aus
     */
    public void runUpdate() {
        if (Game.getGamestate() == Gamestates.ENDSCREEN || Game.getGamestate() == Gamestates.HIGHSCORE) {
            simpleController();
        } else if (Game.getGamestate() == Gamestates.TUTORIAL) {
            tutorialController();
        }
    }

    /**
     * Wickelt Benutzereingaben im Endscreen und Highscore ab und definiert Events, die auf Benutzereingaben folgen
     */
    private void simpleController() {
        if (Game.getInput().isPressed(KeyCode.ESCAPE) || ((updateController.isComponentClicked(DrawEndscreenEndless.getZurueck()) || updateController.isComponentClicked(DrawHighscore.getZurueck())) && !MouseClicked.clickHandled)) {
            MouseClicked.clickHandled = true;
            Game.getInput().pressed[KeyCode.ESCAPE.getCode()] = false;
            if (Game.getGamemode() == Gamemodes.ENDLESS && Game.getGamestate() == Gamestates.ENDSCREEN) {
                Game.getEndless().terminateEndless();
            }
            Game.setGamestate(Gamestates.MENU);
        }
    }

    /**
     * Wickelt Benutzereingaben im Tutorial ab und definiert Events, die auf Benutzereingaben folgen
     */
    private void tutorialController() {
        if (Game.getInput().isPressed(KeyCode.ESCAPE)) {
            Game.getInput().pressed[KeyCode.ESCAPE.getCode()] = false;
            Game.setGamestate(Gamestates.MENU);
        }
        if (updateController.isComponentClicked(DrawTutorial.tutorial.getZURUECK_BUTTON()) && !MouseClicked.clickHandled) {
            MouseClicked.clickHandled = true;
            Game.setGamestate(Gamestates.MENU);
        }
        if (updateController.isComponentClicked(DrawTutorial.tutorial.getLEFT_BUTTON()) && !MouseClicked.clickHandled) {
            MouseClicked.clickHandled = true;
            DrawTutorial.tutorial.setIndex(-1);
        }
        if (updateController.isComponentClicked(DrawTutorial.tutorial.getRIGHT_BUTTON()) && !MouseClicked.clickHandled) {
            MouseClicked.clickHandled = true;
            DrawTutorial.tutorial.setIndex(1);
        }
    }
}
