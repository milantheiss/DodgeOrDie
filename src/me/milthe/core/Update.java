package me.milthe.core;

import javafx.scene.input.KeyCode;
import me.milthe.UI.ButtonUi;
import me.milthe.UI.UiContainer;
import me.milthe.events.MouseClicked;
import me.milthe.graphic.DrawEnvironment;
import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Entity;
import me.milthe.entities.actions.Collision;
import me.milthe.graphic.Gui;

public class Update {
    private final Game game ;
    Collision col;
    
    public Update(Game game){
        this.game = game;
        col = new Collision(game);
    }

    public void runUpdate(){
        updateGamestate();
        if (Gamestate.state == GamestateEnum.ingame) {
            entitiesUpdate();
            collisionPlayerCircle();
            game.circleSpawn.getCircles().forEach(this::checkForCircleOutOfBounce);
        }
    }

    //Comment

    public void entitiesUpdate(){ //Updatet alle Methoden von Entities die wiederholt geupdatet werden müssen --> Hauptsächlich bewegung
        game.entities.forEach(Entity::move);
    }

    public void collisionPlayerCircle(){//Kollisionsdetektion für Gegner zu Spieler
        for (int i = 0; i < game.circleSpawn.circles.size(); i++) {
            if (col.collisionPlayerCircle(game.circleSpawn.circles.get(i))) {
                game.removeCircleEntity(game.circleSpawn.circles.get(i).listIndex, game.circleSpawn.circles.get(i).circleIndex);
                DrawEnvironment.score++;
            }
        }
    }

    public void checkForCircleOutOfBounce(CircleEnemy circleEnemy){
        if (circleEnemy.getxPos() >= (Gui.width + 100) || circleEnemy.getxPos() <= -100 || circleEnemy.getyPos() >= (Gui
                .height + 100) || circleEnemy.getyPos() <= -100) {
            game.removeCircleEntity(circleEnemy.listIndex, circleEnemy.circleIndex);
        }
    }

    public void updateGamestate(){
        if (Gamestate.state == GamestateEnum.ingame){
            if (Game.input.isPressed(KeyCode.H)){
                Gamestate.state = GamestateEnum.pause;
                System.out.println("Pause");
                Game.input.pressed[Game.input.getKeyCode(KeyCode.H)] = false;
            }
        }
        if (Gamestate.state == GamestateEnum.pause){
            if (Game.input.isPressed(KeyCode.H)){
                Gamestate.state = GamestateEnum.ingame;
                System.out.println("Ingame");
                Game.input.pressed[Game.input.getKeyCode(KeyCode.H)] = false;
            }
        }
        if (Gamestate.state == GamestateEnum.menu){
            for (UiContainer uiContainer : Gui.uiComponents){
                for (ButtonUi buttonUi : uiContainer.components){
                    if (clickOnButton(buttonUi)){
                        if (buttonUi.getButtonName().equals("start")){
                            Gamestate.state = GamestateEnum.ingame;
                            System.out.println("Ingame");
                        }
                    }
                }
            }
        }

    }

    private boolean clickOnButton(ButtonUi buttonUi){
        if (MouseClicked.x > buttonUi.getX() && MouseClicked.x < (buttonUi.getX()+buttonUi.getWidth()) && MouseClicked.y > buttonUi.getY() && MouseClicked.y < (buttonUi.getY()+buttonUi.getHeight())){
            return true;
        }
        return false;
    }
}
