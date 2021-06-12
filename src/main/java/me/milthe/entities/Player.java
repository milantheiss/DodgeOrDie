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

    public Player(int hitpoints) {
        Player.hitpoints = hitpoints;
        xPos = Gui.width / 2 - 25;
        yPos = Gui.height / 2 - 25;
        width = 53;
        height = 50;
        speed = 16;
        dashanimation = new GIF(5, "/sprites/entities/player/dashanimation/", 45);

    }

    @Override
    public void update() {
        move();
    }

    public void move() { //Movement wird in UpdateController.java aufgerufen
        xVelocity = 0;
        yVelocity = 0;

        if (Game.input.isPressed(KeyCode.W)) {
            if ((yPos - speed) >= 0) {
                yVelocity--;

                if (Game.input.isPressed(KeyCode.SPACE)) {

                    dash();
                }
            }
        }
        if (Game.input.isPressed(KeyCode.S)) {
            if ((yPos + speed) <= Gui.height - height) {
                yVelocity++;
                if (Game.input.isPressed(KeyCode.SPACE)) {
                    dash();
                }
            }
        }
        if (Game.input.isPressed(KeyCode.A)) {
            if ((xPos - speed) >= 0) {
                xVelocity--;
                if (Game.input.isPressed(KeyCode.SPACE)) {
                    dash();
                }
            }
        }
        if (Game.input.isPressed(KeyCode.D)) {
            if ((xPos + speed) <= Gui.width - width) {
                xVelocity++;
                if (Game.input.isPressed(KeyCode.SPACE)) {
                    dash();
                }
            }
        }

        xPos += SpeedNormalizer.calculateNormalizedSpeed(speed, SpeedNormalizer.normalizeXVelocity(xVelocity, yVelocity));
        yPos += SpeedNormalizer.calculateNormalizedSpeed(speed, SpeedNormalizer.normalizeYVelocity(xVelocity, yVelocity));
    }

    public void dash() { //Dash in Richtung in die sich der Spieler bewegt (
        if ((System.currentTimeMillis() - dashCooldown) > lastDash) {
            dashanimation.playGIF(System.currentTimeMillis(), xPos-(dashanimation.getWidth()/2-width/2), yPos-(dashanimation.getHeight()/2-height/2));
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

    @Override
    public int getSpriteWidth() {
        return 53;
    }

    @Override
    public int getSpriteHeight() {
        return 50;
    }
}
