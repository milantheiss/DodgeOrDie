package me.milthe.core;

import javafx.scene.input.KeyCode;
import me.milthe.UI.ButtonUi;
import me.milthe.UI.UiContainer;
import me.milthe.events.MouseClicked;
import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Entity;
import me.milthe.entities.actions.Collision;
import me.milthe.graphic.DrawIngameUi;
import me.milthe.graphic.DrawTutorial;
import me.milthe.graphic.Gui;

import java.security.Key;

public class Update {
    private final Game game;
    Collision col;


    public Update(Game game) {
        this.game = game;
        col = new Collision(game);
    }

    public void runUpdate() {
        updateGamestate();
        if (Gamestate.state == GamestateEnum.ingame) {
            entitiesUpdate();
            collisionPlayerCircle();
            game.circleSpawn.getCircles().forEach(this::checkForCircleOutOfBounce);
        }
    }

    public void entitiesUpdate() { //Updatet alle Methoden von Entities die wiederholt geupdatet werden müssen --> Hauptsächlich bewegung
        game.entities.forEach(Entity::move);
    }

    public void collisionPlayerCircle() {//Kollisionsdetektion für Gegner zu Spieler
        for (int i = 0; i < game.circleSpawn.circles.size(); i++) {
            if (col.collisionPlayerCircle(game.circleSpawn.circles.get(i))) {
                game.removeCircleEntity(game.circleSpawn.circles.get(i).listIndex, game.circleSpawn.circles.get(i).circleIndex);
                DrawIngameUi.score++;
            }
        }
    }

    public void checkForCircleOutOfBounce(CircleEnemy circleEnemy) {
        if (circleEnemy.getxPos() >= (Gui.width + 100) || circleEnemy.getxPos() <= -100 || circleEnemy.getyPos() >= (Gui
                .height + 100) || circleEnemy.getyPos() <= -100) {
            game.removeCircleEntity(circleEnemy.listIndex, circleEnemy.circleIndex);
        }
    }

    public void updateGamestate() {
        if (Gamestate.state == GamestateEnum.ingame) {
            if (Game.input.isPressed(KeyCode.P)) {
                Gamestate.state = GamestateEnum.pause;
                Game.input.pressed[Game.input.getKeyCode(KeyCode.P)] = false;
                System.out.println("Ingame");
            }
        }
        if (Gamestate.state == GamestateEnum.pause) {
            if (Game.input.isPressed(KeyCode.P)) {
                Gamestate.state = GamestateEnum.ingame;
                Game.input.pressed[Game.input.getKeyCode(KeyCode.P)] = false;
                System.out.println("Ingame");
            }
            Gui.pauseContainer.components.forEach(buttonUi -> {
                if (clickOnButton(buttonUi) && !MouseClicked.clickHandeled){
                    if (buttonUi.getButtonName().equals("weiter")){
                        MouseClicked.clickHandeled = true;
                        Gamestate.state = GamestateEnum.ingame;
                        Game.input.pressed[Game.input.getKeyCode(KeyCode.P)] = false;
                        System.out.println("Ingame");
                    }
                    if (buttonUi.getButtonName().equals("neustart")){
                        System.out.println("Neustart");
                        MouseClicked.clickHandeled = true;
                        Game.input.pressed[Game.input.getKeyCode(KeyCode.P)] = false;
                        game.queueInitSpawn();
                        Gamestate.state = GamestateEnum.ingame;
                        System.out.println("Ingame");
                    }
                    if (buttonUi.getButtonName().equals("verlassen")){
                        MouseClicked.clickHandeled = true;
                        Gamestate.state = GamestateEnum.menu;
                        System.out.println("Menu");
                        Game.input.pressed[Game.input.getKeyCode(KeyCode.P)] = false;
                    }
                }
            });
        }
        if (Gamestate.state == GamestateEnum.menu) {
            Gui.menuContainer.components.forEach(buttonUi -> {
                if (clickOnButton(buttonUi) && !MouseClicked.clickHandeled) {
                    if (buttonUi.getButtonName().equals("start")) {
                        MouseClicked.clickHandeled = true;
                        game.queueInitSpawn();
                        Gamestate.state = GamestateEnum.ingame;
                        System.out.println("Ingame");
                    }
                    if (buttonUi.getButtonName().equals("steuerung")) {
                        MouseClicked.clickHandeled = true;
                        Gamestate.state = GamestateEnum.tutorial;
                        System.out.println("Tutorial");
                    }
                    if (buttonUi.getButtonName().equals("verlassen")) {
                        MouseClicked.clickHandeled = true;
                        Gui.close();
                    }
                }
            });
        }
        if (Gamestate.state == GamestateEnum.tutorial) {
            if (Game.input.isPressed(KeyCode.P)) {
                Game.input.pressed[Game.input.getKeyCode(KeyCode.P)] = false;
                Gui.close();
            }
            if (clickOnButton(DrawTutorial.zurueckButton) && !MouseClicked.clickHandeled){
                MouseClicked.clickHandeled = true;
                Gamestate.state = GamestateEnum.menu;
            }
        }
    }

    private boolean clickOnButton(ButtonUi buttonUi) {
        return MouseClicked.x > buttonUi.getX() && MouseClicked.x < (buttonUi.getX() + buttonUi.getWidth()) && MouseClicked.y > buttonUi.getY() && MouseClicked.y < (buttonUi.getY() + buttonUi.getHeight());
    }
}
