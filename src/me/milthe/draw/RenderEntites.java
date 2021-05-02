package me.milthe.draw;

import javafx.scene.canvas.GraphicsContext;
import me.milthe.clocks.CircleSpawn;
import me.milthe.entities.Player;
import me.milthe.gui.Gui;

public class RenderEntites {
    public void render(GraphicsContext g){
        Gui.entitylist.getEntities().forEach(entity -> g.drawImage(entity.getSprite(), entity.getxPos(), entity.getyPos(), entity.getWidth(), entity.getHeight()));
    }
}
