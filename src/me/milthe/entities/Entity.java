package me.milthe.entities;

import javafx.scene.image.Image;
import me.milthe.core.Game;

public abstract class Entity {
    Game game;
    public int xPos, yPos, width, height, xVel, yVel, speed, listIndex, xDirection, yDirection;
    Image sprite;

    public abstract void move();

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getListIndex() {
        return listIndex;
    }

    public void setListIndex(int index){
        listIndex = index;
    }

    public abstract Image getSprite();

    public void setGame(Game game) {
        this.game = game;
    }
}
