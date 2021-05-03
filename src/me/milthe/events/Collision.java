package me.milthe.events;

import me.milthe.core.Game;
import me.milthe.entities.CircleEnemy;

public class Collision {
    Game game;

    public Collision(Game game){
        this.game = game;
    }

    public boolean collisionPlayerCircle(CircleEnemy ce) {
        int xCenter = ce.getxPos() + 50; //Mittelpunkt von Kreis --> X
        int yCenter = ce.getyPos() + 50; //Mittelpunkt von Kreis --> Y
        int[] x = new int[4];
        int[] y = new int[4];

        //Muss hart gecodet werden ansonsten wird Kollision zu langsam berechnet
        x[0] = Game.getPlayer().getxPos();
        y[0] = Game.getPlayer().getyPos();
        x[1] = Game.getPlayer().getxPos() + 50;
        x[2] = Game.getPlayer().getxPos();
        x[3] = Game.getPlayer().getxPos() + 50;
        y[1] = Game.getPlayer().getyPos() + 50;
        y[2] = Game.getPlayer().getyPos();
        y[3] = Game.getPlayer().getyPos() + 50;

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
