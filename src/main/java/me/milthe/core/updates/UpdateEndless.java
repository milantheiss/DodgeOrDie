package me.milthe.core.updates;

import me.milthe.core.Game;
import me.milthe.core.Gamestates;
import me.milthe.entities.*;
import me.milthe.gamemode.Endless;
import java.io.IOException;

/**
 * Wickelt Ereignisse und Aktionen für den Endlos Modus ab
 */
public class UpdateEndless extends UpdateGamemodes {

    /**
     * Führt Updates aus
     */
    @Override
    public void runUpdate() {
        Game.getEntities().forEach(Entity::update);
        ingamePauseListener();
        checkIfWindowIsFocused();
        collisionAction();
    }

    /**
     * Definiert, was bei einer Kollision zwischen dem Spieler und einer anderen Entity passiert.
     */
    @Override
    protected void collisionAction() {
        Game.getEntities().forEach(entity -> {
            if (col.collisionRectangleCircle(Game.getPlayer(), entity) && !(entity instanceof Player)) {
                if (entity instanceof CircleEnemy || entity instanceof Bouncy) {
                    Game.removeEntity(entity);
                    Game.getJukebox().playSoundEffect("sfx_damage");
                    if ((Player.getHitpoints() - 1) == 0) {
                        Player.setHitpoints(-1);
                        Endless.setTotalEnemiesSurvied(-1);
                        try {
                            Game.setGamestate(Gamestates.ENDSCREEN);
                            Game.getEndless().stopEndless();
                            Game.setGamestate(Gamestates.ENDSCREEN);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Player.setHitpoints(-1);
                        Endless.setTotalEnemiesSurvied(-1);
                    }
                } else if (entity instanceof Friend) {
                    Game.getJukebox().playSoundEffect("sfx_good");
                    Game.removeEntity(entity);
                    Player.setHitpoints(1);
                    Endless.setHighestAmountOfHealth(Math.max(Endless.getHighestAmountOfHealth(), Player.getHitpoints()));
                }
            }
        });
    }
}
