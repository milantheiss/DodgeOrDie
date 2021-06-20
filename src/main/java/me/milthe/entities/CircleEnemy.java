package me.milthe.entities;

import javafx.scene.image.Image;
import me.milthe.core.Game;
import me.milthe.gamemode.Endless;
import me.milthe.ui.Gui;
import java.util.Objects;

/**
 * Gegnerklasse CircleEnemy: Suchen sich beim spawnen die Position des Spielers und fliegen über diesen Punkt Out of Bounce und despawnen
 */
public class CircleEnemy extends Entity {
    private final Image SPRITE_IDLE = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_idle.png")));
    private final Image SPRITE_TOP = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_top.png")));
    private final Image SPRITE_TOP_RIGHT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_topright.png")));
    private final Image SPRITE_RIGHT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_right.png")));
    private final Image SPRITE_BOTTOM_RIGHT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_bottomright.png")));
    private final Image SPRITE_BOTTOM = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_bottom.png")));
    private final Image SPRITE_BOTTOM_LEFT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_bottomleft.png")));
    private final Image SPRITE_LEFT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_left.png")));
    private final Image SPRITE_TOP_LEFT = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/enemies/circleEnemy/circle_enemy_topleft.png")));

    /**
     * Erstellt neuen CircleEnemy und setzt Parameter für width = 100, height = 100, speed = random zwischen 20 - 30
     */
    public CircleEnemy() {
        width = 100;
        height = 100;
        speed = (int) (Math.random() * 30) + 20; //Desto höher desto langsamer
        Endless.setTotalEnemiesSurvived(1);
        setPath();
    }

    /**
     * Überschreibt update() aus superclass und führt Aktionen aus, die pro Tick für CircleEnemy ausgeführt werden müssen. Wie zum Beispiel move()
     */
    @Override
    public void update() {
        move();
        if (isObjectOutOfBounce()) {
            Game.removeEntity(this);
        }
    }

    /**
     * Überschreibt move() aus superclass und verändert xPosition und yPosition um die berechnete Geschwindigkeit
     */
    public void move() {
        xPos += xVelocity * speed;
        yPos += yVelocity * speed;
    }

    /**
     * Setzt Weg auf dem sich der CircleEnemy bewegt. Läuft immer über den Punkt auf dem sich der Spieler befindet, wenn der CircleEnemy spawnt
     */
    public void setPath() {
        //Gibt an welcher Bildschirmkante der Circle spawnt
        int startingedge = (int) (Math.random() * 4);

        //Der Circle bewegt sich auf einer Graden, die den Punkt berührt an dem der Spieler stand als der Circle gespawnt ist
        int targetX = Game.getPlayer().getxPos();
        int targetY = Game.getPlayer().getyPos();

        if (startingedge == 0) {
            //start von Oben -> Y = 0
            xPos = (int) (Math.random() * Gui.WIDTH);
            yPos = 0;

        } else if (startingedge == 1) {
            //start von Rechts -> X = Screen width
            xPos = Gui.WIDTH;
            yPos = (int) (Math.random() * Gui.HEIGHT);
        } else if (startingedge == 2) {
            //start von Unten -> Y = Screen height
            xPos = (int) (Math.random() * Gui.WIDTH);
            yPos = Gui.HEIGHT;
        } else if (startingedge == 3) {
            //start von Links -> X = 0
            xPos = 0;
            yPos = (int) (Math.random() * Gui.HEIGHT);
        }

        double m = ((double) targetY - (double) yPos) / ((double) targetX - (double) xPos);

        if (m < 1 && m > -1) {
            xVelocity = xPos < targetX ? 1 : -1;
            yVelocity = yPos < targetY ? Math.abs(m) : (Math.abs(m)) * -1;
        } else {
            xVelocity = xPos < targetX ? Math.abs(1 / m) : (Math.abs(1 / m)) * -1;
            yVelocity = yPos < targetY ? 1 : -1;
        }
    }

    /**
     * Gibt Sprite von CircleEnemy zurück relative zur Bewegungsrichtung
     * @return Sprite von CircleEnemy. Variiert je nach Bewegungsrichtung
     */
    @Override
    public Image getSprite() {
        if (xVelocity > -0.5 && xVelocity < 0.5 && yVelocity == 1) {
            return SPRITE_BOTTOM;
        } else if ((xVelocity < 1 && xVelocity >= 0.5 && yVelocity == 1) || (xVelocity == 1 && yVelocity < 1 && yVelocity >= 0.5)) {
            return SPRITE_BOTTOM_RIGHT;
        } else if (xVelocity == 1 && yVelocity > -0.5 && yVelocity < 0.5) {
            return SPRITE_RIGHT;
        } else if ((xVelocity < 1 && xVelocity >= 0.5 && yVelocity == -1) || (xVelocity == 1 && yVelocity > -1 && yVelocity <= -0.5)) {
            return SPRITE_TOP_RIGHT;
        } else if (xVelocity > -0.5 && xVelocity < 0.5 && yVelocity == -1) {
            return SPRITE_TOP;
        } else if ((xVelocity > -1 && xVelocity <= -0.5 && yVelocity == -1) || (xVelocity == -1 && yVelocity > -1 && yVelocity <= -0.5)) {
            return SPRITE_TOP_LEFT;
        } else if (xVelocity == -1 && yVelocity > -0.5 && yVelocity < 0.5) {
            return SPRITE_LEFT;
        } else if ((xVelocity > -1 && xVelocity <= -0.5 && yVelocity == 1) || (xVelocity == -1 && yVelocity < 1 && yVelocity >= 0.5)) {
            return SPRITE_BOTTOM_LEFT;
        } else {
            return SPRITE_IDLE;
        }
    }
}
