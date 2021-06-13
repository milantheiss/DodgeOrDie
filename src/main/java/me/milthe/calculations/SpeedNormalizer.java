package me.milthe.calculations;

/**
 * Normalisiert die Velocity einer Entity auf diagonalen Strecken und berechnet die normalisierte Geschwindigkeit der Entity
 */
public class SpeedNormalizer {
    /**
     * Berechnet diagonale Strecke mit Pythagoras
     * @return Gibt berechnete diagonale Strecke zurück
     */
    public static double length(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Normalisiert X Velocity
     * @param xVelo Velocity in X Richtung. Wert befindet sich standardmäßig zwischen -1 und 1
     * @param yVelo Velocity in Y Richtung. Wert befindet sich standardmäßig zwischen -1 und 1
     * @return Gibt normalisiert X Velocity zurück
     */
    public static double normalizeXVelocity(double xVelo, double yVelo) {
        double length = length(xVelo, yVelo);
        xVelo = (xVelo == 0) ? 0 : xVelo / length;
        return xVelo;
    }

    /**
     * Normalisiert Y Velocity
     * @param xVelo Velocity in X Richtung. Wert befindet sich standardmäßig zwischen -1 und 1
     * @param yVelo Velocity in Y Richtung. Wert befindet sich standardmäßig zwischen -1 und 1
     * @return Gibt normalisiert Y Velocity zurück
     */
    public static double normalizeYVelocity(double xVelo, double yVelo) {
        double length = length(xVelo, yVelo);
        yVelo = (yVelo == 0) ? 0 : yVelo / length;
        return yVelo;
    }

    /**
     * Berechnet normalisierte Geschwindigkeit und gibt diese zurück
     * @param speed Maximale Geschwindigkeit, die Entity erreichen kann
     * @param velocity Velocity, die auf die Entity wirkt
     * @return Gibt normalisierte Geschwindigkeit zurück
     */
    public static int calculateNormalizedSpeed(int speed, double velocity) {
        return (int) Math.round(speed * velocity);
    }
}
