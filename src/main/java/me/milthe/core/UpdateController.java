package me.milthe.core;

import javafx.scene.input.KeyCode;
import me.milthe.core.updates.UpdateEndless;
import me.milthe.core.updates.UpdateMenu;
import me.milthe.gamemode.Gamemodes;
import me.milthe.graphic.DrawEndscreenEndless;
import me.milthe.events.MouseClicked;
import me.milthe.graphic.DrawTutorial;
import me.milthe.sounds.Jukebox;
import me.milthe.ui.UiCompontent;

public class UpdateController {
    private final Game game;

    private final UpdateMenu updateMenu;
    private final UpdateEndless updateEndless;

    public UpdateController(Game game) {
        this.game = game;

        updateMenu = new UpdateMenu(this, game);
        updateEndless = new UpdateEndless(game);
    }

    public void runUpdate() {
        if (Game.state == Gamestates.INGAME && Game.mode == Gamemodes.ENDLESS) {
            updateEndless.runUpdate();
        } else if (Game.state == Gamestates.MENU || Game.state == Gamestates.PAUSE) {
            updateMenu.runUpdate();
        } else if (Game.state == Gamestates.ENDSCREEN || Game.state == Gamestates.HIGHSCORE) {
            simpleController();
        } else if (Game.state == Gamestates.TUTORIAL) {
            tutorialController();
        }
        checkForThomasMode();
    }

    private void simpleController() {
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
        //todo an neues tutorial anpassen
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
        if(MouseClicked.x > uiCompontent.getX() && MouseClicked.x < (uiCompontent.getX() + uiCompontent.getWidth()) && MouseClicked.y > uiCompontent.getY() && MouseClicked.y < (uiCompontent.getY() + uiCompontent.getHeight())){
            if (!MouseClicked.clickHandeled) Game.jukebox.playSoundEffect("sfx_click");
            return true;
        }else {
            return false;
        }
    }

    public void checkForThomasMode(){
        if (Game.input.isPressed(KeyCode.T) && Game.input.isPressed(KeyCode.M)){
            Jukebox.activateThomasMode();
        }
    }
}
