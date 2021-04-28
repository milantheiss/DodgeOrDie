package me.milthe.entities;

import me.milthe.gui.Gui;

public class Player {
    public static int xPos, yPos, width = 50, height = 50, xVel, yVel, speed = 7;

    public Player() {
        xPos = Gui.width / 2 - 25;
        yPos = Gui.height / 2 - 25;
    }

    public static void move() {
        int tempX = xPos + xVel;
        int tempY = yPos + yVel;
        if (tempX >= 0 && tempX <= Gui.width - width) {
            xPos = tempX;
        }
        if (tempY >= 0 && tempY <= Gui.height - height) {
            yPos = tempY;
        }

    }

    public static void setXVelocity(int xVelocity) {
        xVel = xVelocity;
    }

    public static void setYVelocity(int yVelocity) {
        yVel = yVelocity;
    }

    public static void dash() {
        if (xVel > 0) {
            int tempX = xPos + 250;
            if (tempX >= 0 && tempX <= Gui.width - width) {
                xPos = tempX;
            }
        } else if (xVel < 0) {
            int tempX = xPos - 250;
            if (tempX >= 0 && tempX <= Gui.width - width) {
                xPos = tempX;
            }
        }

        if (yVel > 0) {
            int tempY = yPos + 250;
            if (tempY >= 0 && tempY <= Gui.height - height) {
                yPos = tempY;
            }
        } else if (yVel < 0) {
            int tempY = yPos - 250;
            if (tempY >= 0 && tempY <= Gui.height - height) {
                yPos = tempY;
            }
        }
    }
}
