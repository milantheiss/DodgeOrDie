package me.milthe.core.updates;

import javafx.scene.input.KeyCode;
import me.milthe.core.Game;
import me.milthe.core.Gamestates;
import me.milthe.entities.Entity;
import me.milthe.entities.actions.Collision;
import me.milthe.graphic.Gui;
import me.milthe.graphic.Menustates;

public abstract class UpdateIngame {
    protected final Collision col;
    protected final Game game;

    public UpdateIngame(Game game){
        this.game = game;
        col = new Collision(game);

    }

    public abstract void runUpdate();

    protected void entitiesUpdate() { //Updatet alle Methoden von Entities die wiederholt geupdatet werden müssen --> Hauptsächlich bewegung
        Game.entities.forEach(Entity::move);
    }

    //TODO Wenn neue Enemy --> für diese umschreiben
    protected boolean checkForCircleOutOfBounce(Entity entity) {
        if (entity.getxPos() >= (Gui.width + 100) || entity.getxPos() <= -100 || entity.getyPos() >= (Gui
                .height + 100) || entity.getyPos() <= -100) {
            return true;
        }else {
            return false;
        }
    }

    protected abstract void collisionAction();

    protected void ingamePauseListener() {
        if (Game.input.isPressed(KeyCode.ESCAPE)) {
            Game.state = Gamestates.PAUSE;
            Gui.menustate = Menustates.PAUSE;
            Game.input.pressed[KeyCode.ESCAPE.getCode()] = false;
            System.out.println("Ingame");
        }
    }

    protected void checkIfWindowIsFocused() {
        if (!Gui.stage.isFocused()) {
            Game.state = Gamestates.PAUSE;
        }
    }
}
