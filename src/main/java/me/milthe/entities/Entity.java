package me.milthe.entities;

import javafx.scene.image.Image;
import me.milthe.core.Game;
import me.milthe.ui.Gui;

public abstract class Entity {
    protected Game game;
    protected int xPos, yPos, width, height, speed, spriteWidth, spriteHeight;
    protected double xDirection, yDirection;

    public abstract void update();

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

    public abstract Image getSprite();

    public void setGame(Game game) {
        this.game = game;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    //+200px Margin of Error danach wir Objekt gelöscht
    protected boolean isObjectOutOfBounce(Entity entity) {
        if (entity.getxPos() >= (Gui.width + 200) || entity.getxPos() <= -200 || entity.getyPos() >= (Gui
                .height + 200) || entity.getyPos() <= -200) {
            return true;
        } else {
            return false;
        }
    }
}
