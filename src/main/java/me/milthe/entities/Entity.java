package me.milthe.entities;

import javafx.scene.image.Image;
import me.milthe.ui.Gui;

/**
 * Abstrakte Entity Klasse. Gibt Variablen und Methoden vor.
 */
public abstract class Entity {
    protected int xPos, yPos, width, height, speed;
    protected double xVelocity, yVelocity;

    protected Image SPRITE_IDLE;
    protected Image SPRITE_BOTTOM;
    protected Image SPRITE_BOTTOM_RIGHT;
    protected Image SPRITE_RIGHT;
    protected Image SPRITE_TOP_RIGHT;
    protected Image SPRITE_TOP;
    protected Image SPRITE_TOP_LEFT;
    protected Image SPRITE_LEFT;
    protected Image SPRITE_BOTTOM_LEFT;

    /**
     * Abstract: Soll Aktionen ausführen, die pro Tick ausgeführt werden müssen. Wie zum Beispiel move()
     */
    public abstract void update();

    /**
     * Abstract: Soll xPosition und yPosition um die berechnete Geschwindigkeit verändern
     */
    public abstract void move();

    /**
     * Gibt xPosition von Entity zurück
     *
     * @return xPos der Entity
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Gibt yPosition von Entity zurück
     *
     * @return yPos der Entity
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * Gibt Breite von Entity zurück
     *
     * @return Breite der Entity
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gibt Höhe von Entity zurück
     *
     * @return Höhe der Entity
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gibt Richtung spezifisches Sprite zurück
     *
     * @return Sprites der Entity
     */
    public Image getSprite() {
        if ((xVelocity < 1 && xVelocity >= 0.05 && yVelocity >= 0) || (xVelocity >= 0 && yVelocity < 1 && yVelocity >= 0.05)) {
            return SPRITE_BOTTOM_RIGHT;
        } else if ((xVelocity > -1 && xVelocity < -0.05 && yVelocity >= 0) || (xVelocity <= 0 && yVelocity < 1 && yVelocity >= 0.05)) {
            return SPRITE_BOTTOM_LEFT;
        } else if ((xVelocity < 1 && xVelocity >= 0.05 && yVelocity <= 0) || (xVelocity >= 0 && yVelocity > -1 && yVelocity <= -0.05)) {
            return SPRITE_TOP_RIGHT;
        } else if ((xVelocity > -1 && xVelocity <= -0.05 && yVelocity <= 0) || (xVelocity <= 0 && yVelocity > -1 && yVelocity <= -0.05)) {
            return SPRITE_TOP_LEFT;
        } else if (xVelocity > -0.055 && xVelocity < 0.05 && yVelocity >= 0) {
            return SPRITE_BOTTOM;
        } else if (xVelocity >= 0 && yVelocity > -0.05 && yVelocity < 0.05) {
            return SPRITE_RIGHT;
        } else if (xVelocity > -0.05 && xVelocity < 0.05 && yVelocity <= 0) {
            return SPRITE_TOP;
        } else if (xVelocity <= 0 && yVelocity > -0.05 && yVelocity < 0.05) {
            return SPRITE_LEFT;
        } else {
            return SPRITE_IDLE;
        }
    }

    /**
     * Gibt die Breite des momentanen Sprites der Entity zurück. Kann von Hitbox abweichen
     *
     * @return Breite des momentanen Sprites
     */
    public abstract int getSpriteWidth();

    /**
     * Gibt die Höhe des momentanen Sprites der Entity zurück. Kann von Hitbox abweichen
     *
     * @return Breite Höhe des momentanen Sprites
     */
    public abstract int getSpriteHeight();

    /**
     * Überprüft ob Entity Out of Bounce ist
     *
     * @return true wenn Entity +200px bzw. -200px von Bildschirmgrenze entfernt ist
     */
    protected boolean isObjectOutOfBounce() {
        //+200px Margin of Error danach wir Objekt gelöscht
        return getxPos() >= (Gui.WIDTH + 200) || getxPos() <= -200 || getyPos() >= (Gui
                .HEIGHT + 200) || getyPos() <= -200;
    }
}
