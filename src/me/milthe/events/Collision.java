package me.milthe.events;

import me.milthe.Main;
import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Player;

//TODO Collision Klasse aufräumen

public class Collision {
    Main main;

    public Collision(Main main){
        this.main = main;
    }
    
    public boolean collisionPlayerCircle(CircleEnemy ce) {
        int xCenter = ce.getxPos() + 50; //Mittelpunkt von Kreis --> X
        int yCenter = ce.getyPos() + 50; //Mittelpunkt von Kreis --> Y
        int[] x = new int[4];
        int[] y = new int[4];

        x[0] = main.getEntities().get(0).getxPos();
        y[0] = main.getEntities().get(0).getyPos();
        x[1] = main.getEntities().get(0).getxPos() + 50;
        x[2] = main.getEntities().get(0).getxPos();
        x[3] = main.getEntities().get(0).getxPos() + 50;
        y[1] = main.getEntities().get(0).getyPos() + 50;
        y[2] = main.getEntities().get(0).getyPos();
        y[3] = main.getEntities().get(0).getyPos() + 50;

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
