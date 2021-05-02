package me.milthe.events;

import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Player;
import me.milthe.gui.Gui;

//TODO Collision Klasse aufräumen

public class Collision {
    public boolean collisionPlayerCircle(CircleEnemy ce) {
        int xCenter = ce.getxPos() + 50; //Mittelpunkt von Kreis --> X
        int yCenter = ce.getyPos() + 50; //Mittelpunkt von Kreis --> Y
        int[] x = new int[4];
        int[] y = new int[4];

        //Muss hart gecodet werden ansonsten wird Kollision zu langsam berechnet
        x[0] = Gui.entitylist.getEntities().get(0).getxPos();
        y[0] = Gui.entitylist.getEntities().get(0).getyPos();
        x[1] = Gui.entitylist.getEntities().get(0).getxPos() + 50;
        x[2] = Gui.entitylist.getEntities().get(0).getxPos();
        x[3] = Gui.entitylist.getEntities().get(0).getxPos() + 50;
        y[1] = Gui.entitylist.getEntities().get(0).getyPos() + 50;
        y[2] = Gui.entitylist.getEntities().get(0).getyPos();
        y[3] = Gui.entitylist.getEntities().get(0).getyPos() + 50;

        double[] a = new double[4];
        double[] b = new double[4];
        double[] c = new double[4];

        for (int i = 0; i < 4; i++) {
            a[i] = x[i] - xCenter;
            b[i] = y[i] - yCenter;
            c[i] = Math.sqrt((a[i] * a[i]) + (b[i] * b[i]));
        }

        return !(c[0] > 50) || !(c[1] > 50) || !(c[2] > 50) || !(c[3] > 50);
    }
}
