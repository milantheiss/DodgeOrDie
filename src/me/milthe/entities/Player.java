package me.milthe.entities;

import javafx.scene.input.KeyCode;
import me.milthe.events.MouseMoved;
import me.milthe.gui.Gui;

public class Player {
    public static int xPos, yPos, width = 50, height = 50, xVel, yVel, speed = 16, dashCooldown = 500, dashRange = 250;
    private static long lastDash = 0;

    public Player() {
        xPos = Gui.width / 2 - 25;
        yPos = Gui.height / 2 - 25;
    }

    public static void move() { //Movement wird in Update.java aufgerufen
        xVel = 0;
        yVel = 0;
        if (Gui.in.isPressed(KeyCode.W)) {
            if ((yPos - speed) >= 0) {
                yVel = (-speed);
                if (Gui.in.isPressed(KeyCode.SPACE)) {
                    Player.dash();
                }
            }
        } else if (Gui.in.isPressed(KeyCode.S)) {
            if ((yPos + speed) <= Gui.height - height) {
                yVel = speed;
                if (Gui.in.isPressed(KeyCode.SPACE)) {
                    Player.dash();
                }
            }
        } else if (Gui.in.isPressed(KeyCode.A)) {
            if ((xPos - speed) >= 0) {
                xVel = (-speed);
                if (Gui.in.isPressed(KeyCode.SPACE)) {
                    Player.dash();
                }
            }
        } else if (Gui.in.isPressed(KeyCode.D)) {
            if ((xPos + speed) <= Gui.width - width) {
                xVel = speed;
                if (Gui.in.isPressed(KeyCode.SPACE)) {
                    Player.dash();
                }
            }
        }

        xPos += xVel;
        yPos += yVel;
    }

    public static void dash() { //Dash in Richtung in die sich der Spieler bewegt (
        if ((System.currentTimeMillis() - dashCooldown) > lastDash) {
            if (xVel > 0) {
                int tempX = xPos + dashRange;
                if (tempX >= 0 && tempX <= Gui.width - width) {
                    xPos = tempX;
                }
                lastDash = System.currentTimeMillis();
            } else if (xVel < 0) {
                int tempX = xPos - dashRange;
                if (tempX >= 0 && tempX <= Gui.width - width) {
                    xPos = tempX;
                }
                lastDash = System.currentTimeMillis();
            }

            if (yVel > 0) {
                int tempY = yPos + dashRange;
                if (tempY >= 0 && tempY <= Gui.height - height) {
                    yPos = tempY;
                }
                lastDash = System.currentTimeMillis();
            } else if (yVel < 0) {
                int tempY = yPos - dashRange;
                if (tempY >= 0 && tempY <= Gui.height - height) {
                    yPos = tempY;
                }
                lastDash = System.currentTimeMillis();
            }
        }
    }

    public static void mouseDash() {
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
    } //Dash zur Maus wird nicht genutzt und ist seit V0.0.1 nicht geupdatet --> Wahrscheinlich seit V0.0.2 nicht mehr kompatibel
}
