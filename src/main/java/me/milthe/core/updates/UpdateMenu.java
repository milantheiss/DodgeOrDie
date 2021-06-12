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
    private final Game game;

    /**
     * @param updateController Steuert alle Updates die während des Spiels und im Menü ausgeführt werden müssen
     * @param game Das Hauptobjekt von Game.java des Spiels
     */
    public UpdateMenu(UpdateController updateController, Game game) {
        this.updateController = updateController;
        this.game = game;
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
        Gui.menuSetup.MAIN_MENU_CONTAINER.uiButtons.forEach(buttonUi -> {
            if (updateController.isComponentClicked(buttonUi) && !MouseClicked.clickHandeled) {
                if (buttonUi.getComponentName().equals("spielen")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.ENDLESS;
                }
                if (buttonUi.getComponentName().equals("tutorial")) {
                    MouseClicked.clickHandeled = true;
                    Game.state = Gamestates.TUTORIAL;
                }
                if (buttonUi.getComponentName().equals("verlassen")) {
                    MouseClicked.clickHandeled = true;
                    Gui.close();
                }
            }
        });
    }

    /**
     * Wickelt Benutzereingaben im Endlos Modus Menü ab und definiert Events, die auf Benutzereingaben folgen
     */
    private void EndlessMenuController() {
        Gui.menuSetup.ENDLESS_MENU_CONTAINER.uiButtons.forEach(buttonUi -> {
            if (updateController.isComponentClicked(buttonUi) && !MouseClicked.clickHandeled) {
                if (buttonUi.getComponentName().equals("start")) {
                    MouseClicked.clickHandeled = true;
                    game.endless.startEndless();
                    Game.state = Gamestates.INGAME;
                }
                if (buttonUi.getComponentName().equals("highscore")) {
                    MouseClicked.clickHandeled = true;
                    Game.state = Gamestates.HIGHSCORE;
                }
                if (buttonUi.getComponentName().equals("zurueck")) {
                    MouseClicked.clickHandeled = true;
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
        if (Game.input.isPressed(KeyCode.ESCAPE)) {
            Game.state = Gamestates.INGAME;
            Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
            Game.jukebox.resumeInGameMusic();
        }
        //Handlet die Buttons im Menü
        Gui.menuSetup.PAUSE_MENU_CONTAINER.uiButtons.forEach(buttonUi -> {
            if (updateController.isComponentClicked(buttonUi) && !MouseClicked.clickHandeled) {
                if (buttonUi.getComponentName().equals("weiter")) {
                    MouseClicked.clickHandeled = true;
                    Game.state = Gamestates.INGAME;
                    Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
                    Game.jukebox.resumeInGameMusic();
                }
                if (buttonUi.getComponentName().equals("neustart")) {
                    MouseClicked.clickHandeled = true;
                    Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
                    if (Game.mode == Gamemodes.ENDLESS) {
                        try {
                            game.endless.stopEndless();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        game.endless.terminateEndless();
                        game.endless.startEndless();
                    }
                }
                if (buttonUi.getComponentName().equals("verlassen")) {
                    MouseClicked.clickHandeled = true;
                    if (Game.mode == Gamemodes.ENDLESS) {
                        try {
                            game.endless.stopEndless();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        game.endless.terminateEndless();
                    }
                    Game.state = Gamestates.MENU;
                    Gui.menustate = Menustates.MAIN;
                    Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
                }
            }
        });
    }
}
