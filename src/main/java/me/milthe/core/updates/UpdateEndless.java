package me.milthe.core.updates;

import me.milthe.core.Game;
import me.milthe.core.Gamestates;
import me.milthe.core.UpdateController;
import me.milthe.entities.*;
import me.milthe.gamemode.Endless;

import java.io.IOException;

public class UpdateEndless extends UpdateIngame {
    private final Game game;

    public UpdateEndless(Game game) {
        super(game);
        this.game = game;
    }

    @Override
    public void runUpdate() {
        Game.entities.forEach(Entity::update);
        ingamePauseListener();
        checkIfWindowIsFocused();
        collisionAction();
    }

    @Override
    protected void collisionAction() {
        Game.entities.forEach(entity -> {
            if (col.collisionRectangleCircle(Game.getPlayer(), entity) && !(entity instanceof Player)) {
                if (entity instanceof CircleEnemy || entity instanceof Bouncy) {
                    game.removeEntity(entity);
                    if ((Player.hitpoints - 1) == 0) {
                        Player.hitpoints--;
                        Endless.totalEnemiesSpawned--;
                        try {
                            game.endless.stopEndless();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Game.state = Gamestates.ENDSCREEN;
                    } else {
                        Player.hitpoints--;
                        Endless.totalEnemiesSpawned--;
                    }
                } else if (entity instanceof Friend) {
                    game.removeEntity(entity);
                    Player.hitpoints++;
                    Endless.highestAmountOfHealth = Math.max(Endless.highestAmountOfHealth, Player.hitpoints);
                }
            }
        });
    }
}
