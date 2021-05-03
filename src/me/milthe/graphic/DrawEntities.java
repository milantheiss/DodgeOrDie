package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import me.milthe.core.Game;

public class DrawEntities {
    public void render(GraphicsContext g, Game game){
        game.entities.forEach(entity -> g.drawImage(entity.getSprite(), entity.getxPos(), entity.getyPos(), entity.getWidth(), entity.getHeight()));
    }
}
