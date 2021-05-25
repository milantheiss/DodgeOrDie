package me.milthe.core;

import javafx.scene.input.KeyCode;
import me.milthe.core.updates.UpdateEndless;
import me.milthe.core.updates.UpdateMenu;
import me.milthe.gamemode.Gamemodes;
import me.milthe.graphic.DrawEndscreenEndless;
import me.milthe.events.MouseClicked;
import me.milthe.graphic.DrawTutorial;
import me.milthe.ui.UiCompontent;

public class UpdateController {
    private final Game game;

    private final UpdateMenu updateMenu;
    private final UpdateEndless updateEndless;

    public UpdateController(Game game) {
        this.game = game;

        updateMenu = new UpdateMenu(this, game);
        updateEndless = new UpdateEndless(this, game);
    }

    public void runUpdate() {
        if (Game.state == Gamestates.INGAME && Game.mode == Gamemodes.ENDLESS) {
            updateEndless.runUpdate();
        } else if (Game.state == Gamestates.MENU || Game.state == Gamestates.PAUSE) {
            updateMenu.runUpdate();
        } else if (Game.state == Gamestates.ENDSCREEN) {
            endscreenEndlessController();
        } else if (Game.state == Gamestates.TUTORIAL) {
            tutorialController();
        }
    }

    //TODO Muss noch ausgebettet werden
    private void endscreenEndlessController() {
        if (Game.input.isPressed(KeyCode.ESCAPE) || (isComponentClicked(DrawEndscreenEndless.getZurueck()) && !MouseClicked.clickHandeled)) {
            MouseClicked.clickHandeled = true;
            Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
            if (Game.mode == Gamemodes.ENDLESS) {
                game.endless.terminateEndless();
            }
            Game.state = Gamestates.MENU;
        }
    }

    private void tutorialController() {
        //todo tutorial neue tutorial erstellen
        if (Game.input.isPressed(KeyCode.ESCAPE)) {
            Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
            Game.state = Gamestates.MENU;
        }
        if (isComponentClicked(DrawTutorial.zurueckButton) && !MouseClicked.clickHandeled) {
            MouseClicked.clickHandeled = true;
            Game.state = Gamestates.MENU;
        }
    }

    public boolean isComponentClicked(UiCompontent uiCompontent) {
        return MouseClicked.x > uiCompontent.getX() && MouseClicked.x < (uiCompontent.getX() + uiCompontent.getWidth()) && MouseClicked.y > uiCompontent.getY() && MouseClicked.y < (uiCompontent.getY() + uiCompontent.getHeight());
    }


}
