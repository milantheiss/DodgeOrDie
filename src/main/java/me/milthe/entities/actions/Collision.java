package me.milthe.entities.actions;

import me.milthe.core.Game;
import me.milthe.entities.Bouncy;
import me.milthe.entities.Entity;

public class Collision {
    Game game;

    public Collision(Game game) {
        this.game = game;
    }

    public boolean collisionRectangleCircle(Entity rectangle, Entity circle) {
        int xCenterCircle = circle.getxPos() + (circle.getWidth() / 2);
        int yCenterCircle;
        if (circle instanceof Bouncy) {
            yCenterCircle = circle.getyPos() + (circle.getWidth() / 2) + 13;
        } else {
            yCenterCircle = circle.getyPos() + (circle.getWidth() / 2);
        }

        int[] xCornerRectangle = new int[4];
        int[] yCornerRectangle = new int[4];

        for (int i = 0; i < 4; i++) {
            xCornerRectangle[i] = (i == 0 || i == 2) ? rectangle.getxPos() : (rectangle.getxPos() + rectangle.getWidth() - 12);
            yCornerRectangle[i] = (i == 0 || i == 1) ? rectangle.getyPos() : (rectangle.getyPos() + rectangle.getWidth() - 12);
        }

        double[] a = new double[4];
        double[] b = new double[4];
        double[] c = new double[4];

        for (int i = 0; i < 4; i++) {
            a[i] = xCornerRectangle[i] - xCenterCircle;
            b[i] = yCornerRectangle[i] - yCenterCircle;
            c[i] = Math.sqrt((a[i] * a[i]) + (b[i] * b[i]));
        }

        return !(c[0] > (circle.getWidth() / 2)) || !(c[1] > (circle.getWidth() / 2)) || !(c[2] > (circle.getWidth() / 2)) || !(c[3] > (circle.getWidth() / 2));
    }
}
