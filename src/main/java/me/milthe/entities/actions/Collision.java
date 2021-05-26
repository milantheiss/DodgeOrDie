package me.milthe.entities.actions;

import me.milthe.core.Game;
import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Entity;

public class Collision {
    Game game;

    public Collision(Game game){
        this.game = game;
    }

    public boolean collisionRectangleCircle(Entity rectangle, Entity circle) {
        int xCenterCircle = circle.getxPos() + (circle.width/2);
        int yCenterCircle = circle.getyPos() + (circle.height/2);
        int[] xCornerRectangle = new int[4];
        int[] yCornerRectangle = new int[4];

        for (int i = 0; i < 4; i++) {
            xCornerRectangle[i] = (i == 0 || i == 2) ? rectangle.getxPos() : (rectangle.getxPos() + rectangle.width - 12);
            yCornerRectangle[i] = (i == 0 || i == 1) ? rectangle.getyPos() : (rectangle.getyPos() + rectangle.height - 12);
        }

        double[] a = new double[4];
        double[] b = new double[4];
        double[] c = new double[4];

        for (int i = 0; i < 4; i++) {
            a[i] = xCornerRectangle[i] - xCenterCircle;
            b[i] = yCornerRectangle[i] - yCenterCircle;
            c[i] = Math.sqrt((a[i] * a[i]) + (b[i] * b[i]));
        }

        return !(c[0] > 50) || !(c[1] > 50) || !(c[2] > 50) || !(c[3] > 50);
    }
}
