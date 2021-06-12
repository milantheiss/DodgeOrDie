package me.milthe.entities;

import javafx.scene.image.Image;
import me.milthe.core.Game;
import me.milthe.gamemode.Endless;
import me.milthe.ui.Gui;
import java.util.Objects;

public class CircleEnemy extends Entity {
    private final Image SPRITE_IDLE = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_idle.png")));
    private final Image SPRITE_TOP = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_top.png")));
    private final Image SPRITE_TOP_RIGHT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_topright.png")));
    private final Image SPRITE_RIGHT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_right.png")));
    private final Image SPRITE_BOTTOM_RIGHT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_bottomright.png")));
    private final Image SPRITE_BOTTOM = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_bottom.png")));
    private final Image SPRITE_BOTTOM_LEFT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_bottomleft.png")));
    private final Image SPRITE_LEFT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_left.png")));
    private final Image SPRITE_TOP_LEFT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_topleft.png")));

    public CircleEnemy() {
        width = 100;
        height = 100;
        speed = (int) (Math.random() * 30) + 20; //Desto höher desto langsamer
        Endless.totalEnemiesSpawned++;
        setPath();
    }

    @Override
    public void update() {
        move();
        if (isObjectOutOfBounce(this)) {
            game.removeEntity(this);
        }
    }

    public void move() {
        xPos += xVelocity * speed;
        yPos += yVelocity * speed;
    }

    //Setzt den Path auf dem der Circle sich bewegt
    public void setPath() {
        //Gibt an welcher Bildschirmkante der Circle spawnt
        int startedge = (int) (Math.random() * 4);

        //Der Circle bewegt sich auf einer Graden, die den Punkt berührt an dem der Spieler stand als der Circle gespawnt ist
        int targetX = Game.getPlayer().getxPos();
        int targetY = Game.getPlayer().getyPos();

        if (startedge == 0) {
            //start von Oben -> Y = 0
            xPos = (int) (Math.random() * Gui.width);
            yPos = 0;

        } else if (startedge == 1) {
            //start von Rechts -> X = Screen width
            xPos = Gui.width;
            yPos = (int) (Math.random() * Gui.height);
        } else if (startedge == 2) {
            //start von Unten -> Y = Screen height
            xPos = (int) (Math.random() * Gui.width);
            yPos = Gui.height;
        } else if (startedge == 3) {
            //start von Links -> X = 0
            xPos = 0;
            yPos = (int) (Math.random() * Gui.height);
        }

        double m = ((double) targetY - (double) yPos) / ((double) targetX - (double) xPos);

        if (m < 1 && m > -1) {
            xVelocity = xPos < targetX ? 1 : -1;
            yVelocity = yPos < targetY ? Math.abs(m) : (Math.abs(m)) * -1;
        } else {
            xVelocity = xPos < targetX ? Math.abs(1 / m) : (Math.abs(1 / m)) * -1;
            yVelocity = yPos < targetY ? 1 : -1;
        }
    }

    @Override
    public Image getSprite() {
        if (xVelocity > -0.5 && xVelocity < 0.5 && yVelocity == 1) {
            return SPRITE_BOTTOM;
        } else if ((xVelocity < 1 && xVelocity >= 0.5 && yVelocity == 1) || (xVelocity == 1 && yVelocity < 1 && yVelocity >= 0.5)) {
            return SPRITE_BOTTOM_RIGHT;
        } else if (xVelocity == 1 && yVelocity > -0.5 && yVelocity < 0.5) {
            return SPRITE_RIGHT;
        } else if ((xVelocity < 1 && xVelocity >= 0.5 && yVelocity == -1) || (xVelocity == 1 && yVelocity > -1 && yVelocity <= -0.5)) {
            return SPRITE_TOP_RIGHT;
        } else if (xVelocity > -0.5 && xVelocity < 0.5 && yVelocity == -1) {
            return SPRITE_TOP;
        } else if ((xVelocity > -1 && xVelocity <= -0.5 && yVelocity == -1) || (xVelocity == -1 && yVelocity > -1 && yVelocity <= -0.5)) {
            return SPRITE_TOP_LEFT;
        } else if (xVelocity == -1 && yVelocity > -0.5 && yVelocity < 0.5) {
            return SPRITE_LEFT;
        } else if ((xVelocity > -1 && xVelocity <= -0.5 && yVelocity == 1) || (xVelocity == -1 && yVelocity < 1 && yVelocity >= 0.5)) {
            return SPRITE_BOTTOM_LEFT;
        } else {
            return SPRITE_IDLE;
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
