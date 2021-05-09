package me.milthe.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import me.milthe.calculations.DiagonalSpeedNormalizer;
import me.milthe.core.Game;
import me.milthe.graphic.Gui;


public class Player extends Entity{
    public int dashCooldown = 500, dashRange = 250;
    private static long lastDash = 0;

    public Player() {
        xPos = Gui.width / 2 - 25;
        yPos = Gui.height / 2 - 25;
        width = 50;
        height = 50;
        speed = 16;
        sprite = new Image("file:rsc/sprites/player.png");
    }

    public void move() { //Movement wird in Update.java aufgerufen
        xDirection = 0;
        yDirection = 0;

        if (Game.input.isPressed(KeyCode.W)) {
            if ((yPos - speed) >= 0) {
                yDirection--;
                if (Game.input.isPressed(KeyCode.SPACE)) {
                    dash();
                }
            }
        }
        if (Game.input.isPressed(KeyCode.S)) {
            if ((yPos + speed) <= Gui.height - height) {
                yDirection++;
                if (Game.input.isPressed(KeyCode.SPACE)) {
                    dash();
                }
            }
        }
        if (Game.input.isPressed(KeyCode.A)) {
            if ((xPos - speed) >= 0) {
                xDirection--;
                if (Game.input.isPressed(KeyCode.SPACE)) {
                    dash();
                }
            }
        }
        if (Game.input.isPressed(KeyCode.D)) {
            if ((xPos + speed) <= Gui.width - width) {
                xDirection++;
                if (Game.input.isPressed(KeyCode.SPACE)) {
                    dash();
                }
            }
        }

        xPos += DiagonalSpeedNormalizer.applySpeed(speed, DiagonalSpeedNormalizer.normalizeX(xDirection, yDirection));
        yPos += DiagonalSpeedNormalizer.applySpeed(speed, DiagonalSpeedNormalizer.normalizeY(xDirection, yDirection));
    }

    public void dash() { //Dash in Richtung in die sich der Spieler bewegt (
        if ((System.currentTimeMillis() - dashCooldown) > lastDash) {
            if (xDirection == 1) {
                int tempX = xPos + dashRange;
                if (tempX >= 0 && tempX <= Gui.width - width) {
                    xPos = tempX;
                }
                lastDash = System.currentTimeMillis();
            } else if (xDirection == -1) {
                int tempX = xPos - dashRange;
                if (tempX >= 0 && tempX <= Gui.width - width) {
                    xPos = tempX;
                }
                lastDash = System.currentTimeMillis();
            }

            if (yDirection == 1) {
                int tempY = yPos + dashRange;
                if (tempY >= 0 && tempY <= Gui.height - height) {
                    yPos = tempY;
                }
                lastDash = System.currentTimeMillis();
            } else if (yDirection == -1) {
                int tempY = yPos - dashRange;
                if (tempY >= 0 && tempY <= Gui.height - height) {
                    yPos = tempY;
                }
                lastDash = System.currentTimeMillis();
            }
        }
    }

    /*public void mouseDash() {
        System.out.println("xPos " + xPos);
        System.out.println("yPos " + yPos);
        double xMouse = MouseMoved.x;
        System.out.println("xMouse " + xMouse);
        double yMouse = MouseMoved.y;
        System.out.println("yMouse " + yMouse);
        double m = (yMouse - yPos) / (xMouse - xPos);
        System.out.println("M " + m);

        double yTemp = m * xPos;
        System.out.println("yTemp " + yTemp);

        double n = yPos - yTemp;
        System.out.println("N " + n);

        double a = 250 * (Math.sin(Math.atan(m)));
        System.out.println("A " + a);
        double yTarget;
        double offset = (xMouse - xPos);
        System.out.println("Offset " + offset);
        if (offset < 0) {
            yTarget = yPos - a;
            System.out.println("Hier");
        } else {
            yTarget = yPos + a;
        }
        System.out.println("yTarget " + yTarget);
        double xTarget = (yTarget - n) / m;
        System.out.println("xTarget " + xTarget);
        xPos = (int) xTarget;
        yPos = (int) yTarget;
    } //Dash zur Maus wird nicht genutzt und ist seit V0.0.1 nicht geupdatet --> Wahrscheinlich seit V0.0.2 nicht mehr kompatibel*/
}
