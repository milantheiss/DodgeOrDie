package me.milthe.core.updates;

import javafx.scene.input.KeyCode;
import me.milthe.core.Game;
import me.milthe.core.Gamestates;
import me.milthe.core.UpdateController;
import me.milthe.events.MouseClicked;
import me.milthe.gamemode.Gamemodes;
import me.milthe.graphic.Gui;
import me.milthe.graphic.Menustates;

import java.io.IOException;

public class UpdateMenu {
    private final UpdateController updateController;
    private final Game game;

    public UpdateMenu(UpdateController updateController, Game game) {
        this.updateController = updateController;
        this.game = game;
    }

    public void runUpdate() {
        if (Gui.menustate == Menustates.MAIN) {
            mainMenuController();
        } else if (Gui.menustate == Menustates.SPIELMODI) {
            spielmodiMenuController();
        } else if (Gui.menustate == Menustates.SPIELMODI_ENDLESS) {
            spielmodiInfiniteMenuController();
        } else if (Gui.menustate == Menustates.SPIELMODI_CUSTOM) {
            spielmodiCustomMenuController();
        } else if (Gui.menustate == Menustates.SPIELMODI_CUSTOM_SELECT) {
            spielmodiCustomSelectMenuController();
        } else if (Gui.menustate == Menustates.PAUSE) {
            pauseMenuController();
        }
    }

    private void mainMenuController() {
        Gui.menuSetup.MAIN_MENU_CONTAINER.uiButtons.forEach(buttonUi -> {
            if (updateController.isComponentClicked(buttonUi) && !MouseClicked.clickHandeled) {
                if (buttonUi.getComponentName().equals("spielmodi")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.SPIELMODI;
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

    private void spielmodiMenuController() {
        Gui.menuSetup.SPIELMODI_MENU_CONTAINER.uiButtons.forEach(buttonUi -> {
            if (updateController.isComponentClicked(buttonUi) && !MouseClicked.clickHandeled) {
                if (buttonUi.getComponentName().equals("endless")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.SPIELMODI_ENDLESS;
                }
                if (buttonUi.getComponentName().equals("custom")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.SPIELMODI_CUSTOM;
                }
                if (buttonUi.getComponentName().equals("zurueck")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.MAIN;
                }
            }
        });
    }

    private void spielmodiInfiniteMenuController() {
        Gui.menuSetup.SPIELMODI_INFINITE_MENU_CONTAINER.uiButtons.forEach(buttonUi -> {
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
                    Gui.menustate = Menustates.SPIELMODI;
                }
            }
        });
    }

    private void spielmodiCustomMenuController() {
        Gui.menuSetup.SPIELMODI_CUSTOM_MENU_CONTAINER.uiButtons.forEach(buttonUi -> {
            if (updateController.isComponentClicked(buttonUi) && !MouseClicked.clickHandeled) {
                if (buttonUi.getComponentName().equals("auswaehlen")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.SPIELMODI_CUSTOM_SELECT;
                }
                if (buttonUi.getComponentName().equals("tutorial")) {
                    MouseClicked.clickHandeled = true;
                    //TODO Mit neuem Tutorial verbinden --> für Spielmodus Custom
                    Game.state = Gamestates.TUTORIAL;
                }
                if (buttonUi.getComponentName().equals("zurueck")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.SPIELMODI;
                }
            }
        });
    }

    private void spielmodiCustomSelectMenuController() {
        Gui.menuSetup.SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.components.forEach(uiCompontent -> {
            if (updateController.isComponentClicked(uiCompontent) && !MouseClicked.clickHandeled) {
                if (uiCompontent.getComponentName().equals("lvlname")) {
                    //TODO Textfield funktion mit aufnahme von Text einfügen
                    MouseClicked.clickHandeled = true;
                    uiCompontent.setVisible(false);
                    Gui.menuSetup.SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.uiTextFields.forEach(uiTextField -> {
                        if (uiTextField.getComponentName().equals("lvlnameTextField")) {
                            uiTextField.setVisible(true);
                            uiTextField.setRequestingInput(true);
                        }
                    });
                }
                if (uiCompontent.getComponentName().equals("start")) {
                    MouseClicked.clickHandeled = true;
                    //TODO Custom starten
                }
                if (uiCompontent.getComponentName().equals("zurueck")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.SPIELMODI_CUSTOM;
                }
            }
        });
    }

    private void pauseMenuController() {
        if (Game.input.isPressed(KeyCode.ESCAPE)) {
            Game.state = Gamestates.INGAME;
            Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
            Game.jukebox.resumeInGameMusic();
        }
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
                    } else if (Game.mode == Gamemodes.CUSTOM) {
                        //todo Start Stop für Custom hinzufügen
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
                    } else if (Game.mode == Gamemodes.CUSTOM) {
                        //todo Custom stoppen
                    }
                    Game.state = Gamestates.MENU;
                    Gui.menustate = Menustates.MAIN;
                    Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
                }
            }
        });

    }
}
