package me.milthe.core.updates;

import me.milthe.core.Game;
import me.milthe.core.Gamestates;
import me.milthe.core.UpdateController;
import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Friend;
import me.milthe.entities.Player;
import me.milthe.gamemode.Endless;

public class UpdateEndless extends UpdateIngame{
    private final UpdateController updateController;
    private final Game game;

    public UpdateEndless(UpdateController updateController, Game game) {
        super(game);
        this.updateController = updateController;
        this.game = game;
    }

    @Override
    public void runUpdate() {
        ingamePauseListener();
        checkIfWindowIsFocused();
        entitiesUpdate();
        collisionAction();
        Game.entities.forEach(entity -> {
            if (!(entity instanceof Player)){
                if (checkForCircleOutOfBounce(entity)) game.removeEntity(entity);
            }
        });
    }

    @Override
    protected void collisionAction() {
        Game.entities.forEach(entity -> {
            if (col.collisionRectangleCircle(Game.getPlayer(), entity) && !(entity instanceof Player)){
                if (entity instanceof CircleEnemy){
                    game.removeEntity(entity);
                    if ((Player.hitpoints - 1) == 0) {
                        Player.hitpoints--;
                        Endless.totalEnemiesSpawned--;
                        game.endless.stopEndless();
                        Game.state = Gamestates.ENDSCREEN;
                    } else {
                        Player.hitpoints--;
                        Endless.totalEnemiesSpawned--;
                    }
                }else if (entity instanceof Friend){
                    game.removeEntity(entity);
                    Player.hitpoints++;
                    Endless.highestAmountOfHealth = Math.max(Endless.highestAmountOfHealth, Player.hitpoints);
                }
            }
        });
    }
}
