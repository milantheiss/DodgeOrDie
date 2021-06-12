package me.milthe.core;

import me.milthe.core.updates.UpdateEndless;
import me.milthe.core.updates.UpdateMenu;
import me.milthe.core.updates.UpdateRest;
import me.milthe.gamemode.Gamemodes;
import me.milthe.events.MouseClicked;
import me.milthe.ui.UiCompontent;

/**
 * Steuert Updates
 */
public class UpdateController {
    private final UpdateMenu updateMenu;
    private final UpdateEndless updateEndless;
    private final UpdateRest updateRest;

    /**
     * Erstellt einen neuen UpdateController mit dem spezifizierten Game
     * @param game Das Hauptobjekt von Game.java des Projekts
     */
    public UpdateController(Game game) {
        updateMenu = new UpdateMenu(this, game);
        updateEndless = new UpdateEndless(game);
        updateRest = new UpdateRest(this, game);
    }

    public void runUpdate() {
        if (Game.state == Gamestates.INGAME && Game.mode == Gamemodes.ENDLESS) {
            updateEndless.runUpdate();
        } else if (Game.state == Gamestates.MENU || Game.state == Gamestates.PAUSE) {
            updateMenu.runUpdate();
        } else if (Game.state == Gamestates.ENDSCREEN || Game.state == Gamestates.HIGHSCORE || Game.state == Gamestates.TUTORIAL) {
            updateRest.runUpdate();
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
}
