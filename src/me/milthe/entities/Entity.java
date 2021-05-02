package me.milthe.entities;

import javafx.scene.image.Image;

public abstract class Entity {
    Image sprite;
    public int xPos, yPos, width, height, xVel, yVel, speed, listIndex;


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

    public Image getSprite() {
        return sprite;
    }
}
