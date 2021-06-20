package me.milthe.entities;

import javafx.scene.image.Image;
import me.milthe.core.Game;
import me.milthe.ui.Gui;
import java.util.Objects;

/**
 * Freundklasse Friend: Fliegt zufällig über Bildschirm und gibt wenn er mit dem Spieler kollidiert +1 Leben wieder
 */
public class Friend extends Entity {
    private final Image SPRITE_IDLE = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/friend/friend_idle.png")));

    /**
     * Erstellt neuen Friend und setzt Parameter für width = 100, height = 100, speed = zufällig zwischen 10 - 15
     */
    public Friend() {
        width = 100;
        height = 100;
        setPath();
        speed = (int) (Math.random() * 15) + 10;
    }

    /**
     * Überschreibt update() aus superclass und führt Aktionen aus, die pro Tick für Friend ausgeführt werden müssen. Wie zum Beispiel move()
     */
    @Override
    public void update() {
        move();
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
     * Überschreibt getSprite aus superclass und gibt Sprite zurück
     * @return momentaner Sprite
     */
    @Override
    public Image getSprite() {
        return SPRITE_IDLE;
    }

    /**
     * Berechnet Weg auf dem sich die Entity nach dem Spawn bewegt
     */
    public void setPath() {
        //Gibt an auf welcher Seite der Friend spawnt
        int startingSite = (int) (Math.random() * 4);

        if (startingSite == 0) {
            //start von Oben -> Y = 0
            xPos = (int) (Math.random() * Gui.WIDTH);
            yPos = 0;
        } else if (startingSite == 1) {
            //start von Rechts -> X = Screen width
            xPos = Gui.WIDTH;
            yPos = (int) (Math.random() * Gui.HEIGHT);
        } else if (startingSite == 2) {
            //start von Unten -> Y = Screen height
            xPos = (int) (Math.random() * Gui.WIDTH);
            yPos = Gui.HEIGHT;
        } else if (startingSite == 3) {
            //start von Links -> X = 0
            xPos = 0;
            yPos = (int) (Math.random() * Gui.HEIGHT);
        }

        if ((Gui.WIDTH - xPos) < xPos) {
            xVelocity = Math.random() * (-1);
        } else {
            xVelocity = Math.random();
        }

        if ((Gui.HEIGHT - yPos) < yPos) {
            yVelocity = Math.random() * (-1);
        } else {
            yVelocity = Math.random();
        }
    }
}
