package me.milthe.entities;

import javafx.scene.image.Image;
import me.milthe.graphic.Gui;

import java.util.Objects;

public class Friend extends Entity {
    private final Image SPRITE_IDLE = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/friend/friend_idle.png")));

    public Friend() {
        width = 100;
        height = 100;
        setPath();
        speed = (int) (Math.random() * 15) + 10;
    }

    @Override
    public void update() {
        move();
        if (isObjectOutOfBounce(this)) {
            game.removeEntity(this);
        }
    }

    @Override
    public void move() {
        xPos += (int) Math.round(xDirection * speed);
        yPos += (int) Math.round(yDirection * speed);
    }

    @Override
    public Image getSprite() {
        return SPRITE_IDLE;
    }

    public void setPath() {
        //Gibt an auf welcher Seite der Friend spawnt
        int startingSite = (int) (Math.random() * 4);

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

        if ((Gui.width - xPos) < xPos) {
            xDirection = Math.random() * (-1);
        } else {
            xDirection = Math.random();
        }

        if ((Gui.height - yPos) < yPos) {
            yDirection = Math.random() * (-1);
        } else {
            yDirection = Math.random();
        }
    }

    @Override
    public int getSpriteWidth() {
        return (int) getSprite().getWidth();
    }

    @Override
    public int getSpriteHeight() {
        return (int) getSprite().getHeight();
    }
}
