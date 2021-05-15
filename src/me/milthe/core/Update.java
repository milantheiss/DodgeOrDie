package me.milthe.core;

import javafx.scene.input.KeyCode;
import me.milthe.entities.Player;
import me.milthe.gamemode.Gamemode;
import me.milthe.gamemode.Gamemodes;
import me.milthe.gamemode.Infinite;
import me.milthe.graphic.DrawEndscreen;
import me.milthe.ui.ButtonUi;
import me.milthe.events.MouseClicked;
import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Entity;
import me.milthe.entities.actions.Collision;
import me.milthe.graphic.DrawIngameUi;
import me.milthe.graphic.DrawTutorial;
import me.milthe.graphic.Gui;

public class Update {
    private final Game game;
    Collision col;


    public Update(Game game) {
        this.game = game;
        col = new Collision(game);
    }

    public void runUpdate() {
        updateGamestate();
        if (Gamestate.state == Gamestates.ingame) {
            checkIfWindowIsFocused();
            entitiesUpdate();
            collisionPlayerCircle();
            game.circleEnemyList.forEach(this::checkForCircleOutOfBounce);
        }
    }

    public void entitiesUpdate() { //Updatet alle Methoden von Entities die wiederholt geupdatet werden müssen --> Hauptsächlich bewegung
        game.entities.forEach(Entity::move);
    }

    public void collisionPlayerCircle() {//Kollisionsdetektion für Gegner zu Spieler
        for (int i = 0; i < game.circleEnemyList.size(); i++) {
            if (col.collisionPlayerCircle(game.circleEnemyList.get(i))) {
                game.removeCircleEnemy(game.circleEnemyList.get(i).listIndex, game.circleEnemyList.get(i).circleIndex);
                if ((Player.hitpoints-1)==0){
                    Player.hitpoints--;
                    Scoring.totalEnemiesSpawned--;
                    game.infinite.stopInfinite();
                    Gamestate.state = Gamestates.endscreen;
                }else {
                    Player.hitpoints--;
                    Scoring.totalEnemiesSpawned--;
                }
            }
        }
    }

    public void checkForCircleOutOfBounce(CircleEnemy circleEnemy) {
        if (circleEnemy.getxPos() >= (Gui.width + 100) || circleEnemy.getxPos() <= -100 || circleEnemy.getyPos() >= (Gui
                .height + 100) || circleEnemy.getyPos() <= -100) {
            game.removeCircleEnemy(circleEnemy.listIndex, circleEnemy.circleIndex);
        }
    }

    public void updateGamestate() {
        if (Gamestate.state == Gamestates.ingame) {
            if (Game.input.isPressed(KeyCode.ESCAPE)) {
                Gamestate.state = Gamestates.pause;
                Game.input.pressed[Game.input.getKeyCode(KeyCode.ESCAPE)] = false;
                System.out.println("Ingame");
            }
        }
        if (Gamestate.state == Gamestates.pause) {
            if (Game.input.isPressed(KeyCode.ESCAPE)) {
                Gamestate.state = Gamestates.ingame;
                Game.input.pressed[Game.input.getKeyCode(KeyCode.ESCAPE)] = false;
                System.out.println("Ingame");
            }
            Gui.pauseContainer.components.forEach(buttonUi -> {
                if (clickOnButton(buttonUi) && !MouseClicked.clickHandeled) {
                    if (buttonUi.getButtonName().equals("weiter")) {
                        MouseClicked.clickHandeled = true;
                        Gamestate.state = Gamestates.ingame;
                        Game.input.pressed[Game.input.getKeyCode(KeyCode.ESCAPE)] = false;
                        System.out.println("Ingame");
                    }
                    if (buttonUi.getButtonName().equals("neustart")) {
                        System.out.println("Neustart");
                        MouseClicked.clickHandeled = true;
                        Game.input.pressed[Game.input.getKeyCode(KeyCode.ESCAPE)] = false;
                        if (Gamemode.mode == Gamemodes.infinite) {
                            game.infinite.stopInfinite();
                            game.infinite.terminateInfinite();
                            game.infinite.startInfinite();
                        } else if (Gamemode.mode == Gamemodes.custom) {
                            //todo Start Stop für Custom hinzufügen
                        }
                        System.out.println("Ingame");
                    }
                    if (buttonUi.getButtonName().equals("verlassen")) {
                        MouseClicked.clickHandeled = true;
                        if (Gamemode.mode == Gamemodes.infinite) {
                            game.infinite.stopInfinite();
                            game.infinite.terminateInfinite();
                        } else if (Gamemode.mode == Gamemodes.custom) {
                            //todo Custom stoppen
                        }
                        Gamestate.state = Gamestates.menu;
                        System.out.println("Menu");
                        Game.input.pressed[Game.input.getKeyCode(KeyCode.ESCAPE)] = false;
                    }
                }
            });
        }
        //todo menu neu ordnen so dass Spielmodi neues UI öffnet wo spieler den modus wählen können
        if (Gamestate.state == Gamestates.menu) {
            Gui.menuContainer.components.forEach(buttonUi -> {
                if (clickOnButton(buttonUi) && !MouseClicked.clickHandeled) {
                    if (buttonUi.getButtonName().equals("start")) {
                        MouseClicked.clickHandeled = true;
                        game.infinite.startInfinite();
                        //todo button funktion an neue modi anpassen
                        Gamestate.state = Gamestates.ingame;
                        System.out.println("Ingame");
                    }
                    if (buttonUi.getButtonName().equals("steuerung")) {
                        MouseClicked.clickHandeled = true;
                        Gamestate.state = Gamestates.tutorial;
                        System.out.println("Tutorial");
                    }
                    if (buttonUi.getButtonName().equals("verlassen")) {
                        MouseClicked.clickHandeled = true;
                        Gui.close();
                    }
                }
            });
            if (Game.input.isPressed(KeyCode.P)) {
                //todo funktion neu definieren mit neuen spielmodus klassen
                Gamestate.state = Gamestates.ingame;
                Gamemode.mode = Gamemodes.custom;
                //todo Custom start
                Game.input.pressed[Game.input.getKeyCode(KeyCode.P)] = false;
                System.out.println("level");
            }
        }
        if (Gamestate.state == Gamestates.tutorial) {
            //todo tutorial neue tutorial erstellen
            if (Game.input.isPressed(KeyCode.ESCAPE)) {
                Game.input.pressed[Game.input.getKeyCode(KeyCode.ESCAPE)] = false;
                Gamestate.state = Gamestates.menu;
            }
            if (clickOnButton(DrawTutorial.zurueckButton) && !MouseClicked.clickHandeled) {
                MouseClicked.clickHandeled = true;
                Gamestate.state = Gamestates.menu;
            }
        }

        if(Gamestate.state == Gamestates.endscreen){
            if (Game.input.isPressed(KeyCode.ESCAPE) || (clickOnButton(DrawEndscreen.getZurueck()) && !MouseClicked.clickHandeled)) {
                MouseClicked.clickHandeled = true;
                Game.input.pressed[Game.input.getKeyCode(KeyCode.ESCAPE)] = false;
                if (Gamemode.mode == Gamemodes.infinite){
                    game.infinite.terminateInfinite();
                }
                Gamestate.state = Gamestates.menu;
            }
        }
    }

    private boolean clickOnButton(ButtonUi buttonUi) {
        return MouseClicked.x > buttonUi.getX() && MouseClicked.x < (buttonUi.getX() + buttonUi.getWidth()) && MouseClicked.y > buttonUi.getY() && MouseClicked.y < (buttonUi.getY() + buttonUi.getHeight());
    }

    private void checkIfWindowIsFocused() {
        if (!Gui.stage.isFocused()){
            Gamestate.state = Gamestates.pause;
        }
    }
}
