package me.milthe.core.updates;

import me.milthe.core.Game;
import me.milthe.core.Gamestates;
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
                    Game.jukebox.playSoundEffect("sfx_damage");
                    if ((Player.hitpoints - 1) == 0) {
                        Player.hitpoints--;
                        Endless.totalEnemiesSpawned--;
                        try {
                            Game.state = Gamestates.ENDSCREEN;
                            game.endless.stopEndless();
                            Game.state = Gamestates.ENDSCREEN;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Player.hitpoints--;
                        Endless.totalEnemiesSpawned--;
                    }
                } else if (entity instanceof Friend) {
                    Game.jukebox.playSoundEffect("sfx_good");
                    game.removeEntity(entity);
                    Player.hitpoints++;
                    Endless.highestAmountOfHealth = Math.max(Endless.highestAmountOfHealth, Player.hitpoints);
                }
            }
        });
    }
}
