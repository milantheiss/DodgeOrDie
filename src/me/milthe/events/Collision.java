package me.milthe.events;

import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Player;

//TODO Collision Klasse aufräumen

public class Collision {
    public boolean collisionPlayerCircle(CircleEnemy ce) {
        int xCenter = ce.getxPos() + 50; //Mittelpunkt von Kreis --> X
        int yCenter = ce.getyPos() + 50; //Mittelpunkt von Kreis --> Y
        int[] x = new int[4];
        int[] y = new int[4];

        x[0] = Player.xPos;
        y[0] = Player.yPos;
        x[1] = Player.xPos + 50;
        x[2] = Player.xPos;
        x[3] = Player.xPos + 50;
        y[1] = Player.yPos + 50;
        y[2] = Player.yPos;
        y[3] = Player.yPos + 50;

        double[] a = new double[4];
        double[] b = new double[4];
        double[] c = new double[4];
        for (int i = 0; i < 4; i++) {
            /*if (i != 0) {
                x[i] = (i == 1 || i == 3) ? (x[0] + 50) : x[0];
                y[i] = (i == 2 || i == 3) ? (y[0] - 50) : y[0];
            }*/
            a[i] = x[i] - xCenter;
            b[i] = y[i] - yCenter;
            c[i] = Math.sqrt((a[i] * a[i]) + (b[i] * b[i]));
        }

        return !(c[0] > 50) || !(c[1] > 50) || !(c[2] > 50) || !(c[3] > 50);
        /*if (c[0] < 50 && c[1] < 50 && c[2] < 50 && c[3] < 50) {
            System.out.println("Collision");
        }*/

    }
}
