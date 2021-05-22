package me.milthe.core;

import javafx.scene.input.KeyCode;
import me.milthe.entities.Friend;
import me.milthe.entities.Player;
import me.milthe.gamemode.Gamemodes;
import me.milthe.graphic.DrawEndscreen;
import me.milthe.graphic.Menustates;
import me.milthe.events.MouseClicked;
import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Entity;
import me.milthe.entities.actions.Collision;
import me.milthe.graphic.DrawTutorial;
import me.milthe.graphic.Gui;
import me.milthe.ui.UiCompontent;

public class Update {
    private final Game game;
    private final Collision col;


    public Update(Game game) {
        this.game = game;
        col = new Collision(game);
    }

    public void runUpdate() {
        if (Game.state == Gamestates.INGAME) {
            ingameEscapeListener();
            checkIfWindowIsFocused();
            entitiesUpdate();
            collisionAction();
            Game.entities.forEach(entity -> {
                if (!(entity instanceof Player)){
                    if (checkForCircleOutOfBounce(entity)) game.removeEntity(entity);
                }
            });
        } else if (Game.state == Gamestates.MENU || Game.state == Gamestates.PAUSE) {
            if (Gui.menustate == Menustates.MAIN) {
                mainMenuController();
            } else if (Gui.menustate == Menustates.SPIELMODI) {
                spielmodiMenuController();
            } else if (Gui.menustate == Menustates.SPIELMODI_INFINITE) {
                spielmodiInfiniteMenuController();
            } else if (Gui.menustate == Menustates.SPIELMODI_CUSTOM) {
                spielmodiCustomMenuController();
            } else if (Gui.menustate == Menustates.SPIELMODI_CUSTOM_SELECT) {
                spielmodiCustomSelectMenuController();
            } else if (Gui.menustate == Menustates.PAUSE) {
                pauseMenuController();
            }
        } else if (Game.state == Gamestates.ENDSCREEN) {
            endscreenController();
        } else if (Game.state == Gamestates.TUTORIAL) {
            tutorialController();
        }
    }

    private void entitiesUpdate() { //Updatet alle Methoden von Entities die wiederholt geupdatet werden müssen --> Hauptsächlich bewegung
        Game.entities.forEach(Entity::move);
    }

    private void collisionAction() {//Kollisionsdetektion für Gegner zu Spieler
        Game.entities.forEach(entity -> {
            if (col.collisionRectangleCircle(Game.getPlayer(), entity) && !(entity instanceof Player)){
                if (entity instanceof CircleEnemy){
                    game.removeEntity(entity);
                    if ((Player.hitpoints - 1) == 0) {
                        Player.hitpoints--;
                        Scoring.totalEnemiesSpawned--;
                        game.infinite.stopInfinite();
                        Game.state = Gamestates.ENDSCREEN;
                    } else {
                        Player.hitpoints--;
                        Scoring.totalEnemiesSpawned--;
                    }
                }else if (entity instanceof Friend){
                    game.removeEntity(entity);
                    if (Player.hitpoints < 4){
                        Player.hitpoints++;
                    }
                }
            }
        });
    }

    //TODO Wenn neue Enemy --> für diese umschreiben
    private boolean checkForCircleOutOfBounce(Entity entity) {
        if (entity.getxPos() >= (Gui.width + 100) || entity.getxPos() <= -100 || entity.getyPos() >= (Gui
                .height + 100) || entity.getyPos() <= -100) {
            return true;
        }else {
            return false;
        }
    }

    private void ingameEscapeListener() {
        if (Game.input.isPressed(KeyCode.ESCAPE)) {
            Game.state = Gamestates.PAUSE;
            Gui.menustate = Menustates.PAUSE;
            Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
            System.out.println("Ingame");
        }
    }

    private void mainMenuController() {
        Gui.menuSetup.MAIN_MENU_CONTAINER.uiButtons.forEach(buttonUi -> {
            if (isComponentClicked(buttonUi) && !MouseClicked.clickHandeled) {
                if (buttonUi.getComponentName().equals("spielmodi")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.SPIELMODI;
                    System.out.println("Spielmodi");
                }
                if (buttonUi.getComponentName().equals("tutorial")) {
                    MouseClicked.clickHandeled = true;
                    //TODO Mit neuem Tutorial verbinden
                    Game.state = Gamestates.TUTORIAL;
                    System.out.println("Tutorial");
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
            if (isComponentClicked(buttonUi) && !MouseClicked.clickHandeled) {
                if (buttonUi.getComponentName().equals("infinite")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.SPIELMODI_INFINITE;
                    System.out.println("Infinite");
                }
                if (buttonUi.getComponentName().equals("custom")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.SPIELMODI_CUSTOM;
                    System.out.println("Custom");
                }
                if (buttonUi.getComponentName().equals("zurueck")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.MAIN;
                    System.out.println("Main Menu");
                }
            }
        });
    }

    private void spielmodiInfiniteMenuController() {
        Gui.menuSetup.SPIELMODI_INFINITE_MENU_CONTAINER.uiButtons.forEach(buttonUi -> {
            if (isComponentClicked(buttonUi) && !MouseClicked.clickHandeled) {
                if (buttonUi.getComponentName().equals("start")) {
                    MouseClicked.clickHandeled = true;
                    game.infinite.startInfinite();
                    Game.state = Gamestates.INGAME;
                    System.out.println("Ingame Infinite");
                }
                if (buttonUi.getComponentName().equals("highscore")) {
                    MouseClicked.clickHandeled = true;
                    //TODO Add Highscore Page
                    System.out.println("Highscore");
                }
                if (buttonUi.getComponentName().equals("zurueck")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.SPIELMODI;
                    System.out.println("Spielmodi");
                }
            }
        });
    }

    private void spielmodiCustomMenuController() {
        Gui.menuSetup.SPIELMODI_CUSTOM_MENU_CONTAINER.uiButtons.forEach(buttonUi -> {
            if (isComponentClicked(buttonUi) && !MouseClicked.clickHandeled) {
                if (buttonUi.getComponentName().equals("auswaehlen")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.SPIELMODI_CUSTOM_SELECT;
                    System.out.println("Select");
                }
                if (buttonUi.getComponentName().equals("tutorial")) {
                    MouseClicked.clickHandeled = true;
                    //TODO Mit neuem Tutorial verbinden --> für Spielmodus Custom
                    Game.state = Gamestates.TUTORIAL;
                    System.out.println("Tutorial");
                }
                if (buttonUi.getComponentName().equals("zurueck")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.SPIELMODI;
                    System.out.println("Spielmodi");
                }
            }
        });
    }

    private void spielmodiCustomSelectMenuController() {
        Gui.menuSetup.SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.components.forEach(uiCompontent -> {
            if (isComponentClicked(uiCompontent) && !MouseClicked.clickHandeled) {
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
                    System.out.println("Type");
                }
                if (uiCompontent.getComponentName().equals("start")) {
                    MouseClicked.clickHandeled = true;
                    //TODO Custom starten
                    System.out.println("Custom start");
                }
                if (uiCompontent.getComponentName().equals("zurueck")) {
                    MouseClicked.clickHandeled = true;
                    Gui.menustate = Menustates.SPIELMODI_CUSTOM;
                    System.out.println("Spielmodi");
                }
            }
        });
    }

    private void pauseMenuController() {
        if (Game.input.isPressed(KeyCode.ESCAPE)) {
            Game.state = Gamestates.INGAME;
            Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
            System.out.println("Ingame");
        }
        Gui.menuSetup.PAUSE_MENU_CONTAINER.uiButtons.forEach(buttonUi -> {
            if (isComponentClicked(buttonUi) && !MouseClicked.clickHandeled) {
                if (buttonUi.getComponentName().equals("weiter")) {
                    MouseClicked.clickHandeled = true;
                    Game.state = Gamestates.INGAME;
                    Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
                    System.out.println("Ingame");
                }
                if (buttonUi.getComponentName().equals("neustart")) {
                    System.out.println("Neustart");
                    MouseClicked.clickHandeled = true;
                    Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
                    if (Game.mode == Gamemodes.INFINITE) {
                        game.infinite.stopInfinite();
                        game.infinite.terminateInfinite();
                        game.infinite.startInfinite();
                    } else if (Game.mode == Gamemodes.CUSTOM) {
                        //todo Start Stop für Custom hinzufügen
                    }
                    System.out.println("Ingame");
                }
                if (buttonUi.getComponentName().equals("verlassen")) {
                    MouseClicked.clickHandeled = true;
                    if (Game.mode == Gamemodes.INFINITE) {
                        game.infinite.stopInfinite();
                        game.infinite.terminateInfinite();
                    } else if (Game.mode == Gamemodes.CUSTOM) {
                        //todo Custom stoppen
                    }
                    Game.state = Gamestates.MENU;
                    Gui.menustate = Menustates.MAIN;
                    System.out.println("Menu");
                    Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
                }
            }
        });

    }

    private void endscreenController() {
        if (Game.input.isPressed(KeyCode.ESCAPE) || (isComponentClicked(DrawEndscreen.getZurueck()) && !MouseClicked.clickHandeled)) {
            MouseClicked.clickHandeled = true;
            Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
            if (Game.mode == Gamemodes.INFINITE) {
                game.infinite.terminateInfinite();
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

    private boolean isComponentClicked(UiCompontent uiCompontent) {
        return MouseClicked.x > uiCompontent.getX() && MouseClicked.x < (uiCompontent.getX() + uiCompontent.getWidth()) && MouseClicked.y > uiCompontent.getY() && MouseClicked.y < (uiCompontent.getY() + uiCompontent.getHeight());
    }

    private void checkIfWindowIsFocused() {
        if (!Gui.stage.isFocused()) {
            Game.state = Gamestates.PAUSE;
        }
    }
}
