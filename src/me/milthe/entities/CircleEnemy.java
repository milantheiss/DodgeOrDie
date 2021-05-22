package me.milthe.entities;

import javafx.scene.image.Image;
import me.milthe.core.Game;
import me.milthe.graphic.Gui;

public class CircleEnemy extends Entity {
    private int circleIndex;
    private final Image SPRITE_IDLE = new Image("file:rsc/sprites/entities/enemies/circleEnemy/circle_enemy_idle.png");
    private final Image SPRITE_TOP = new Image("file:rsc/sprites/entities/enemies/circleEnemy/circle_enemy_top.png");
    private final Image SPRITE_TOP_RIGHT = new Image("file:rsc/sprites/entities/enemies/circleEnemy/circle_enemy_topright.png");
    private final Image SPRITE_RIGHT = new Image("file:rsc/sprites/entities/enemies/circleEnemy/circle_enemy_right.png");
    private final Image SPRITE_BOTTOM_RIGHT = new Image("file:rsc/sprites/entities/enemies/circleEnemy/circle_enemy_bottomright.png");
    private final Image SPRITE_BOTTOM = new Image("file:rsc/sprite/entities/enemies/circleEnemy/circle_enemy_bottom.png");
    private final Image SPRITE_BOTTOM_LEFT = new Image("file:rsc/sprites/entities/enemies/circleEnemy/circle_enemy_bottomleft.png");
    private final Image SPRITE_LEFT = new Image("file:rsc/sprite/entities/enemies/circleEnemy/circle_enemy_left.png");
    private final Image SPRITE_TOP_LEFT = new Image("file:rsc/sprites/entities/enemies/circleEnemy/circle_enemy_topleft.png");

    public CircleEnemy() {
        width = 100;
        height = 100;
        speed = 30; //Desto höher desto langsamer
        setPath();
    }

    public void move() {
        xPos += xVel;
        yPos += yVel;
    }

    public void setPath() { //Setzt den Path auf dem der Circle sich bewegt
        //Gibt an auf welcher Seite der Circle spawnt
        int startingSite = (int) (Math.random() * 4);

        //Circle bewegt sich immer auf einem Weg, der über den Punkt läuft auf dem sich der Player befindet wenn der Circle gespawnt wird
        int targetX = Game.getPlayer().getxPos();
        int targetY = Game.getPlayer().getyPos();

        if (startingSite == 0) {
            //start von Oben -> Y = 0
            xPos = (int) (Math.random() * Gui.width);
            yPos = 0;
        } else if (startingSite == 1) {
            //start von Rechts -> X = Screen width
            xPos = Gui.width;
            yPos = (int) (Math.random() * Gui.height);
        } else if (startingSite == 2) {
            //start von Unten -> Y = Screen height
            xPos = (int) (Math.random() * Gui.width);
            yPos = Gui.height;
        } else if (startingSite == 3) {
            //start von Links -> X = 0
            xPos = 0;
            yPos = (int) (Math.random() * Gui.height);
        }
        int xOffset = targetX - xPos;
        int yOffset = targetY - yPos;

        //Je höher speed ist desto langsamer bewegt sich der Circle
        xVel = xOffset / speed;
        xDirection = (xVel > 0) ? 1 : -1;
        xDirection = (xVel == 0) ? 0 : xDirection;
        yVel = yOffset / speed;
        yDirection = (yVel > 0) ? 1 : -1;
        yDirection = (xVel == 0) ? 0 : yDirection;
    }

    public void setCircleIndex(int index) {
        circleIndex = index;
    }

    public int getCircleIndex(){
        return circleIndex;
    }

    @Override
    public Image getSprite() {
        if (xDirection == 0 && yDirection == 1) {
            return SPRITE_BOTTOM;
        } else if (xDirection == 1 && yDirection == 1) {
            return SPRITE_BOTTOM_RIGHT;
        } else if (xDirection == 1 && yDirection == 0) {
            return SPRITE_RIGHT;
        } else if (xDirection == 1 && yDirection == -1) {
            return SPRITE_TOP_RIGHT;
        } else if (xDirection == 0 && yDirection == -1) {
            return SPRITE_TOP;
        } else if (xDirection == -1 && yDirection == -1) {
            return SPRITE_TOP_LEFT;
        } else if (xDirection == -1 && yDirection == 0) {
            return SPRITE_LEFT;
        } else if (xDirection == -1 && yDirection == 1) {
            return SPRITE_BOTTOM_LEFT;
        } else {
            return SPRITE_IDLE;
        }
    }
}
