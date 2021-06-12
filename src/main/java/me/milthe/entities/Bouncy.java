package me.milthe.entities;

import javafx.scene.image.Image;
import me.milthe.gamemode.Endless;
import me.milthe.ui.Gui;

import java.util.Objects;

public class Bouncy extends Entity {
    private final Image SPRITE_IDLE = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/bouncy/bouncy_idle.png")));
    private int bounces;

    public Bouncy() {
        width = 60;
        speed = 30;
        bounces = 3;
        Endless.totalEnemiesSpawned++;
        generateStartingPoint();
    }

    @Override
    public void update() {
        move();
        if (isBouncyHittingWall() && bounces > 0) {
            if ((getxPos() <= 0 || getxPos() >= Gui.width) && !(getyPos() <= 0 || getyPos() >= Gui.height)) {
                xVelocity *= -1;
                bounces--;
            } else if (!(getxPos() <= 0 || getxPos() >= Gui.width) && (getyPos() <= 0 || getyPos() >= Gui.height)) {
                yVelocity *= -1;
                bounces--;
            } else {
                xVelocity *= -1;
                yVelocity *= -1;
                bounces--;
            }
        }
        if (isObjectOutOfBounce(this)) {
            game.removeEntity(this);
        }
    }

    @Override
    public void move() {
        xPos += (int) Math.round(xVelocity * speed);
        yPos += (int) Math.round(yVelocity * speed);
    }

    public void generateStartingPoint() {
        int startedge = (int) (Math.random() * 4);

        if (startedge == 0) {
            //start von Oben -> Y = 0
            xPos = (int) (Math.random() * Gui.width);
            yPos = 0;
            xVelocity = (Math.random() < 0.5) ? Math.random() : Math.random() * -1;
            do {
                yVelocity = Math.random();
            } while (xVelocity == 0 && yVelocity == 0);
        } else if (startedge == 1) {
            //start von Rechts -> X = Screen width
            xPos = Gui.width;
            yPos = (int) (Math.random() * Gui.height);
            do {
                xVelocity = Math.random() * -1;
            } while (xVelocity == 0 && yVelocity == 0);
            yVelocity = (Math.random() < 0.5) ? Math.random() : Math.random() * -1;
        } else if (startedge == 2) {
            //start von Unten -> Y = Screen height
            xPos = (int) (Math.random() * Gui.width);
            yPos = Gui.height;
            xVelocity = (Math.random() < 0.5) ? Math.random() : Math.random() * -1;
            do {
                yVelocity = Math.random() * -1;
            } while (xVelocity == 0 && yVelocity == 0);
        } else if (startedge == 3) {
            //start von Links -> X = 0
            xPos = 0;
            yPos = (int) (Math.random() * Gui.height);
            do {
                xVelocity = Math.random();
            } while (xVelocity == 0 && yVelocity == 0);
            yVelocity = (Math.random() < 0.5) ? Math.random() : Math.random() * -1;
        }
    }

    @Override
    public Image getSprite() {
        return SPRITE_IDLE;
    }

    @Override
    public int getSpriteWidth() {
        return (int) getSprite().getWidth();
    }

    @Override
    public int getSpriteHeight() {
        return (int) getSprite().getHeight();
    }

    private boolean isBouncyHittingWall() {
        return getxPos() <= 0 || getxPos() >= Gui.width || getyPos() <= 0 || getyPos() >= Gui.height;
    }
}
