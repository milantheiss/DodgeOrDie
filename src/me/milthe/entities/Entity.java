package me.milthe.entities;

import javafx.scene.image.Image;

public abstract class Entity {
    protected int xPos, yPos, width, height, xVel, yVel, speed;

    public abstract void move();
    public abstract Image getSprite();

}
