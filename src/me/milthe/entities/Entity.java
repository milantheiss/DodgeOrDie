package me.milthe.entities;

import javafx.scene.image.Image;

public abstract class Entity {
    protected int xPos, yPos,width, height, xVel, yVel, speed;
    public abstract void move();
    public abstract Image getSprite();

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

    public int getxVel() {
        return xVel;
    }

    public int getyVel() {
        return yVel;
    }

    public int getSpeed() {
        return speed;
    }
}
