package me.milthe.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DrawTutorial {
    Image wasd = new Image("file:rsc/sprites/wasdcontrol.png");
    Image pause = new Image("file:rsc/sprites/pause.png");
    Image dashen = new Image("file:rsc/sprites/dashen.png");
    public void render(GraphicsContext g) {
        g.drawImage(wasd, 956, 654, 648, 131);
        //g.drawImage(pause, (Gui.width-wasd.getWidth())/2, 1, 1,1);
    }
}
