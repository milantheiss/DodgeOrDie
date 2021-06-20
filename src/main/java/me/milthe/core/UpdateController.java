package me.milthe.core;

import me.milthe.core.updates.UpdateEndless;
import me.milthe.core.updates.UpdateMenu;
import me.milthe.core.updates.UpdateRest;
import me.milthe.gamemode.Gamemodes;
import me.milthe.events.MouseClicked;
import me.milthe.ui.UiComponent;

/**
 * Steuert Updates
 */
public class UpdateController {
    private final UpdateMenu updateMenu;
    private final UpdateEndless updateEndless;
    private final UpdateRest updateRest;

    /**
     * Erstellt einen neuen UpdateController mit dem spezifizierten Game
     */
    public UpdateController() {
        updateMenu = new UpdateMenu(this);
        updateEndless = new UpdateEndless();
        updateRest = new UpdateRest(this);
    }

    /**
     * Führt Updates aus
     */
    public void runUpdate() {
        if (Game.getGamestate() == Gamestates.INGAME && Game.getGamemode() == Gamemodes.ENDLESS) {
            updateEndless.runUpdate();
        } else if (Game.getGamestate() == Gamestates.MENU || Game.getGamestate() == Gamestates.PAUSE) {
            updateMenu.runUpdate();
        } else if (Game.getGamestate() == Gamestates.ENDSCREEN || Game.getGamestate() == Gamestates.HIGHSCORE || Game.getGamestate() == Gamestates.TUTORIAL) {
            updateRest.runUpdate();
        }
    }

    /**
     * Testet ob mit Cursor auf UiComponent gedrückt wurde
     * @param uiComponent Das UiComponent was gecheckt werden soll
     * @return true wenn auf UiComponent
     */
    public boolean isComponentClicked(UiComponent uiComponent) {
        if(MouseClicked.x > uiComponent.getX() && MouseClicked.x < (uiComponent.getX() + uiComponent.getWidth()) && MouseClicked.y > uiComponent.getY() && MouseClicked.y < (uiComponent.getY() + uiComponent.getHeight())){
            if (!MouseClicked.clickHandled) Game.getJukebox().playSoundEffect("sfx_click");
            return true;
        }else {
            return false;
        }
    }
}
