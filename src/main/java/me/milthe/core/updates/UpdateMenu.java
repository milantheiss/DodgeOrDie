package me.milthe.core.updates;

import javafx.scene.input.KeyCode;
import me.milthe.core.Game;
import me.milthe.core.Gamestates;
import me.milthe.core.UpdateController;
import me.milthe.events.MouseClicked;
import me.milthe.gamemode.Gamemodes;
import me.milthe.ui.Gui;
import me.milthe.ui.Menustates;
import java.io.IOException;

/**
 * Wickelt Ereignisse und Aktionen für alle Menüs ab
 */
public class UpdateMenu {
    private final UpdateController updateController;

    /**
     * @param updateController Steuert alle Updates die während des Spiels und im Menü ausgeführt werden müssen
     */
    public UpdateMenu(UpdateController updateController) {
        this.updateController = updateController;
    }

    /**
     * Führt Updates aus
     */
    public void runUpdate() {
        if (Gui.menustate == Menustates.MAIN) {
            mainMenuController();
        } else if (Gui.menustate == Menustates.ENDLESS) {
            EndlessMenuController();
        } else if (Gui.menustate == Menustates.PAUSE) {
            pauseMenuController();
        }
    }

    /**
     * Wickelt Benutzereingaben im Hauptmenü ab und definiert Events, die auf Benutzereingaben folgen
     */
    private void mainMenuController() {
        Gui.menus.MAIN_MENU_CONTAINER.getUiButtons().forEach(buttonUi -> {
            if (updateController.isComponentClicked(buttonUi) && !MouseClicked.clickHandled) {
                if (buttonUi.getComponentName().equals("spielen")) {
                    MouseClicked.clickHandled = true;
                    Gui.menustate = Menustates.ENDLESS;
                }
                if (buttonUi.getComponentName().equals("tutorial")) {
                    MouseClicked.clickHandled = true;
                    Game.setGamestate(Gamestates.TUTORIAL);
                }
                if (buttonUi.getComponentName().equals("verlassen")) {
                    MouseClicked.clickHandled = true;
                    Gui.close();
                }
            }
        });
    }

    /**
     * Wickelt Benutzereingaben im Endlos Modus Menü ab und definiert Events, die auf Benutzereingaben folgen
     */
    private void EndlessMenuController() {
        Gui.menus.ENDLESS_MENU_CONTAINER.getUiButtons().forEach(buttonUi -> {
            if (updateController.isComponentClicked(buttonUi) && !MouseClicked.clickHandled) {
                if (buttonUi.getComponentName().equals("start")) {
                    MouseClicked.clickHandled = true;
                    Game.getEndless().startEndless();
                    Game.setGamestate(Gamestates.INGAME);
                }
                if (buttonUi.getComponentName().equals("highscore")) {
                    MouseClicked.clickHandled = true;
                    Game.setGamestate(Gamestates.HIGHSCORE);
                }
                if (buttonUi.getComponentName().equals("zurueck")) {
                    MouseClicked.clickHandled = true;
                    Gui.menustate = Menustates.MAIN;
                }
            }
        });
    }

    /**
     * Wickelt Benutzereingaben im Pausenmenü ab und definiert Events, die auf Benutzereingaben folgen
     */
    private void pauseMenuController() {
        //Beendet das Menü wenn Escape gedrückt wird
        if (Game.getInput().isPressed(KeyCode.ESCAPE)) {
            Game.setGamestate(Gamestates.INGAME);
            Game.getInput().pressed[KeyCode.ESCAPE.getCode()] = false;
            Game.getJukebox().resumeInGameMusic();
        }
        //Handlet die Buttons im Menü
        Gui.menus.PAUSE_MENU_CONTAINER.getUiButtons().forEach(buttonUi -> {
            if (updateController.isComponentClicked(buttonUi) && !MouseClicked.clickHandled) {
                if (buttonUi.getComponentName().equals("weiter")) {
                    MouseClicked.clickHandled = true;
                    Game.setGamestate(Gamestates.INGAME);
                    Game.getInput().pressed[KeyCode.ESCAPE.getCode()] = false;
                    Game.getJukebox().resumeInGameMusic();
                }
                if (buttonUi.getComponentName().equals("neustart")) {
                    MouseClicked.clickHandled = true;
                    Game.getInput().pressed[KeyCode.ESCAPE.getCode()] = false;
                    if (Game.getGamemode() == Gamemodes.ENDLESS) {
                        try {
                            Game.getEndless().stopEndless();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Game.getEndless().terminateEndless();
                        Game.getEndless().startEndless();
                    }
                }
                if (buttonUi.getComponentName().equals("verlassen")) {
                    MouseClicked.clickHandled = true;
                    if (Game.getGamemode() == Gamemodes.ENDLESS) {
                        try {
                            Game.getEndless().stopEndless();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Game.getEndless().terminateEndless();
                    }
                    Game.setGamestate(Gamestates.MENU);
                    Gui.menustate = Menustates.MAIN;
                    Game.getInput().pressed[KeyCode.ESCAPE.getCode()] = false;
                }
            }
        });
    }
}
