package me.milthe.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import me.milthe.calculations.SpeedNormalizer;
import me.milthe.core.Game;
import me.milthe.ui.GIF;
import me.milthe.ui.Gui;
import java.util.Objects;


public class Player extends Entity {
    public int dashCooldown = 500, dashRange = 250;
    private static long lastDash = 0;
    private final Image SPRITE_LEFT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/player/left.png")));
    private final Image SPRITE_CENTER = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/player/center.png")));
    private final Image SPRITE_RIGHT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/player/right.png")));
    public static int hitpoints;
    public static GIF dashanimation;

    /**
     * Erstellt neuen Player und setzt Parameter für xPos, yPos, width = 53, height = 50, speed = 16
     * @param hitpoints Lebenspunkte des Spielers
     */
    public Player(int hitpoints) {
        Player.hitpoints = hitpoints;
        xPos = Gui.width / 2 - 25;
        yPos = Gui.height / 2 - 25;
        width = 53;
        height = 50;
        speed = 16;
        dashanimation = new GIF(5, "/sprites/entities/player/dashanimation/", 45);

    }

    /**
     * Überschreibt update() aus superclass und führt Aktionen aus, die pro Tick für Player ausgeführt werden müssen. Wie zum Beispiel move()
     */
    @Override
    public void update() {
        move();
    }

    /**
     *  Überschreibt move() aus superclass und verändert xPosition und yPosition um die berechnete Geschwindigkeit
     */
    @Override
    public void move() {
        xVelocity = 0;
        yVelocity = 0;

        if (Game.getInput().isPressed(KeyCode.W)) {
            if ((yPos - speed) >= 0) {
                yVelocity--;

                if (Game.getInput().isPressed(KeyCode.SPACE)) {

                    dash();
                }
            }
        }
        if (Game.getInput().isPressed(KeyCode.S)) {
            if ((yPos + speed) <= Gui.height - height) {
                yVelocity++;
                if (Game.getInput().isPressed(KeyCode.SPACE)) {
                    dash();
                }
            }
        }
        if (Game.getInput().isPressed(KeyCode.A)) {
            if ((xPos - speed) >= 0) {
                xVelocity--;
                if (Game.getInput().isPressed(KeyCode.SPACE)) {
                    dash();
                }
            }
        }
        if (Game.getInput().isPressed(KeyCode.D)) {
            if ((xPos + speed) <= Gui.width - width) {
                xVelocity++;
                if (Game.getInput().isPressed(KeyCode.SPACE)) {
                    dash();
                }
            }
        }

        xPos += SpeedNormalizer.calculateNormalizedSpeed(speed, SpeedNormalizer.normalizeXVelocity(xVelocity, yVelocity));
        yPos += SpeedNormalizer.calculateNormalizedSpeed(speed, SpeedNormalizer.normalizeYVelocity(xVelocity, yVelocity));
    }

    /**
     * Ermöglicht es dem Spieler zu dash um die Dashrange
     */
    public void dash() { //Dash in Richtung in die sich der Spieler bewegt (
        if ((System.currentTimeMillis() - dashCooldown) > lastDash) {
            dashanimation.playGIF(xPos-(dashanimation.getWidth()/2-width/2), yPos-(dashanimation.getHeight()/2-height/2));
            if (xVelocity == 1) {
                int tempX = xPos + dashRange;
                if (tempX >= 0 && tempX <= Gui.width - width) {
                    xPos = tempX;
                }
                lastDash = System.currentTimeMillis();
            } else if (xVelocity == -1) {
                int tempX = xPos - dashRange;
                if (tempX >= 0 && tempX <= Gui.width - width) {
                    xPos = tempX;
                }
                lastDash = System.currentTimeMillis();
            }

            if (yVelocity == 1) {
                int tempY = yPos + dashRange;
                if (tempY >= 0 && tempY <= Gui.height - height) {
                    yPos = tempY;
                }
                lastDash = System.currentTimeMillis();
            } else if (yVelocity == -1) {
                int tempY = yPos - dashRange;
                if (tempY >= 0 && tempY <= Gui.height - height) {
                    yPos = tempY;
                }
                lastDash = System.currentTimeMillis();
            }
        }
    }

    /**
     * Gibt den Spieler Sprite relative zur momentanen Bewegungsrichtung zurück
     * @return Sprite für + oder - X Richtung
     */
    public Image getSprite() {
        if (xVelocity == 1) {
            return SPRITE_RIGHT;
        } else if (xVelocity == -1) {
            return SPRITE_LEFT;
        } else {
            return SPRITE_CENTER;
        }
    }

    /**
     * Überschreibt getSpriteWidth und setzt 53 als festen Wert
     * @return 53
     */
    @Override
    public int getSpriteWidth() {
        return 53;
    }

    /**
     * Überschreibt getSpriteHeight und setzt 50 als festen Wert
     * @return 50
     */
    @Override
    public int getSpriteHeight() {
        return 50;
    }
}
