package me.milthe.entities;

import javafx.scene.image.Image;
import me.milthe.core.Game;
import me.milthe.gamemode.Endless;
import me.milthe.ui.Gui;
import java.util.Objects;

/**
 * Gegnerklasse Bouncy: Springt von Wänden ab
 */
public class Bouncy extends Entity {
    private int bounces;

    /**
     * Erstellt neuen Bouncy und setzt Parameter für width = 60, speed = 30, bounces = 3
     */
    public Bouncy() {
        SPRITE_IDLE = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/bouncy/bouncy_idle.png")));
        SPRITE_BOTTOM = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/bouncy/bouncy_bottom.png")));
        SPRITE_BOTTOM_RIGHT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/bouncy/bouncy_bottomright.png")));
        SPRITE_RIGHT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/bouncy/bouncy_right.png")));
        SPRITE_TOP_RIGHT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/bouncy/bouncy_topright.png")));
        SPRITE_TOP = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/bouncy/bouncy_top.png")));
        SPRITE_TOP_LEFT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/bouncy/bouncy_topleft.png")));
        SPRITE_LEFT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/bouncy/bouncy_left.png")));
        SPRITE_BOTTOM_LEFT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/bouncy/bouncy_bottomleft.png")));

        width = 60;
        speed = 30;
        bounces = 3;
        Endless.setTotalEnemiesSurvived(1);
        generateStartingPoint();
    }

    /**
     * Überschreibt update() aus superclass und führt Aktionen aus, die pro Tick für Friend ausgeführt werden müssen. Wie zum Beispiel move()
     */
    @Override
    public void update() {
        move();
        if (isBouncyHittingWall() && bounces > 0) {
            if ((getxPos() <= 0 || getxPos() >= Gui.WIDTH) && !(getyPos() <= 0 || getyPos() >= Gui.HEIGHT)) {
                xVelocity *= -1;
                bounces--;
            } else if (!(getxPos() <= 0 || getxPos() >= Gui.WIDTH) && (getyPos() <= 0 || getyPos() >= Gui.HEIGHT)) {
                yVelocity *= -1;
                bounces--;
            } else {
                xVelocity *= -1;
                yVelocity *= -1;
                bounces--;
            }
        }
        if (isObjectOutOfBounce()) {
            Game.removeEntity(this);
        }
    }

    /**
     * Überschreibt move() aus superclass und verändert xPosition und yPosition um die berechnete Geschwindigkeit
     */
    @Override
    public void move() {
        xPos += (int) Math.round(xVelocity * speed);
        yPos += (int) Math.round(yVelocity * speed);
    }

    /**
     * Gibt die Breite des momentanen Sprites zurück
     * @return Breite des momentanen Sprites
     */
    @Override
    public int getSpriteWidth() {
        return (int) Math.round(getSprite().getWidth());
    }

    /**
     * Gibt die Höhe des momentanen Sprites zurück
     * @return Höhe des momentanen Sprites
     */
    @Override
    public int getSpriteHeight() {
        return (int) Math.round(getSprite().getHeight());
    }

    /**
     * Generiert einen Startpunkt für Bouncy, wenn es gespawnt wird und gibt Bouncy eine Zufällige Velocity
     */
    public void generateStartingPoint() {
        int startingedge = (int) (Math.random() * 4);

        if (startingedge == 0) {
            //start von Oben -> Y = 0
            xPos = (int) (Math.random() * Gui.WIDTH);
            yPos = 0;
            xVelocity = (Math.random() < 0.5) ? Math.random() : Math.random() * -1;
            do {
                yVelocity = Math.random();
            } while (xVelocity == 0 && yVelocity == 0);
        } else if (startingedge == 1) {
            //start von Rechts -> X = Screen width
            xPos = Gui.WIDTH;
            yPos = (int) (Math.random() * Gui.HEIGHT);
            do {
                xVelocity = Math.random() * -1;
            } while (xVelocity == 0 && yVelocity == 0);
            yVelocity = (Math.random() < 0.5) ? Math.random() : Math.random() * -1;
        } else if (startingedge == 2) {
            //start von Unten -> Y = Screen height
            xPos = (int) (Math.random() * Gui.WIDTH);
            yPos = Gui.HEIGHT;
            xVelocity = (Math.random() < 0.5) ? Math.random() : Math.random() * -1;
            do {
                yVelocity = Math.random() * -1;
            } while (xVelocity == 0 && yVelocity == 0);
        } else if (startingedge == 3) {
            //start von Links -> X = 0
            xPos = 0;
            yPos = (int) (Math.random() * Gui.HEIGHT);
            do {
                xVelocity = Math.random();
            } while (xVelocity == 0 && yVelocity == 0);
            yVelocity = (Math.random() < 0.5) ? Math.random() : Math.random() * -1;
        }
    }

    /**
     * Gibt zurück ob Bouncy eine Wand berührt
     * @return true wenn Bouncy eine Wand berührt
     */
    private boolean isBouncyHittingWall() {
        return getxPos() <= 0 || getxPos() >= Gui.WIDTH || getyPos() <= 0 || getyPos() >= Gui.HEIGHT;
    }
}
