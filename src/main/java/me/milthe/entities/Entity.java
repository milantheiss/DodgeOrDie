package me.milthe.entities;

import javafx.scene.image.Image;
import me.milthe.core.Game;

public abstract class Entity {
    protected Game game;
    //TODO Private machen
    public int xPos, yPos, width, height, speed;
    protected double xDirection, yDirection;
    private int listIndex;

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
