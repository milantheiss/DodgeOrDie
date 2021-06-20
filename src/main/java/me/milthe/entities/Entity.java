package me.milthe.entities;

import javafx.scene.image.Image;
import me.milthe.ui.Gui;

/**
 * Abstrakte Entity Klasse. Gibt Variablen und Methoden vor.
 */
public abstract class Entity {
    protected int xPos, yPos, width, height, speed, spriteWidth, spriteHeight;
    protected double xVelocity, yVelocity;

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
     * @return xPos der Entity
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Gibt yPosition von Entity zurück
     * @return yPos der Entity
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * Gibt Breite von Entity zurück
     * @return Breite der Entity
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gibt Höhe von Entity zurück
     * @return Höhe der Entity
     */
    public int getHeight() {
        return height;
    }

    /**
     * Abstract: Soll Sprites von Entity zurück geben
     * @return Sprites der Entity
     */
    public abstract Image getSprite();

    /**
     * Gibt die Breite des momentanen Sprites der Entity zurück. Kann von Hitbox abweichen
     * @return Breite des momentanen Sprites
     */
    public int getSpriteWidth() {
        return (int) Math.round(getSprite().getWidth());
    }

    /**
     * Gibt die Höhe des momentanen Sprites der Entity zurück. Kann von Hitbox abweichen
     * @return Breite Höhe des momentanen Sprites
     */
    public int getSpriteHeight() {
        return (int) Math.round(getSprite().getHeight());
    }

    /**
     * Überprüft ob Entity Out of Bounce ist
     * @return true wenn Entity +200px bzw. -200px von Bildschirmgrenze entfernt ist
     */
    protected boolean isObjectOutOfBounce() {
        //+200px Margin of Error danach wir Objekt gelöscht
        return getxPos() >= (Gui.WIDTH + 200) || getxPos() <= -200 || getyPos() >= (Gui
                .HEIGHT + 200) || getyPos() <= -200;
    }
}
