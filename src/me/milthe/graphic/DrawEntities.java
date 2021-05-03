package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import me.milthe.core.Game;
import me.milthe.core.Gamestate;
import me.milthe.core.GamestateEnum;

public class DrawEntities {
    public void render(GraphicsContext g, Game game){
        if (Gamestate.state == GamestateEnum.ingame || Gamestate.state == GamestateEnum.pause)
        game.entities.forEach(entity -> g.drawImage(entity.getSprite(), entity.getxPos(), entity.getyPos(), entity.getWidth(), entity.getHeight()));
    }
}
