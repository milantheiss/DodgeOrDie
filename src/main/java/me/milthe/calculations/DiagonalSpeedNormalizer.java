package me.milthe.calculations;

public class DiagonalSpeedNormalizer {

    private static double x = 0;
    private static double y = 0;


    public static double length() {
        return Math.sqrt(x * x + y * y);
    }

    public static double normalizeX(double xDir, double yDir) {
        x = xDir;
        y = yDir;
        double length = length();
        x = (x == 0) ? 0 : x / length;
        return x;
    }

    public static double normalizeY(double xDir, double yDir) {
        x = xDir;
        y = yDir;
        double length = length();
        y = (y == 0) ? 0 : y / length;
        return y;
    }

    public static int applySpeed(int speed, double direction) {
        return (int) Math.round(speed * direction);
    }
}
