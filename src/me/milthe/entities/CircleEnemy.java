package me.milthe.entities;

public class CircleEnemy {
    public int xPos, yPos, width = 100, height = 100, xVel, yVel, startingSite, speed;

    public CircleEnemy() {
        startingSite = (int) (Math.random() * 4);
        if (startingSite == 0) {
            //start von Oben -> Y = 0
        } else if (startingSite == 1) {
            //start von Rechts -> X = Screen width
        } else if (startingSite == 2) {
            //start von Unten -> Y = Screen height
        } else if (startingSite == 3) {
            //start von Links -> X = 0
        }
    }

    public void setxVel(int xVel) {
        this.xVel = xVel;
    }

    public void setyVel(int yVel) {
        this.yVel = yVel;
    }
}
