package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import me.milthe.core.Game;
import me.milthe.entities.Player;

public class DrawEntities {
    public void render(GraphicsContext g) {
        Game.entities.forEach(entity -> g.drawImage(entity.getSprite(), entity.getxPos(), entity.getyPos(), entity.getSpriteWidth(), entity.getSpriteHeight()));
        if (Player.dashanimation.isPlaying()){
            Player.dashanimation.updateGIF();
            g.drawImage(Player.dashanimation.getFrame(Player.dashanimation.getCurrentFrameIndex()), Player.dashanimation.getxPos(), Player.dashanimation.getyPos(), Player.dashanimation.getWidth(), Player.dashanimation.getHeight());
        }
    }
}
