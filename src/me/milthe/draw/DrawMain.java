package me.milthe.draw;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.milthe.clocks.CircleSpawn;
import me.milthe.entities.Player;
import me.milthe.events.Collision;
import me.milthe.gui.Gui;


public class DrawMain {
    Collision col = new Collision();
    int score = 0;

    public void draw(GraphicsContext g) {
        g.setFill(new Color(4. / 255., 0. / 255., 17. / 255., 1));
        g.fillRect(0, 0, Gui.width, Gui.height);

        g.drawImage(ImageLoader.imagePlayer, Player.xPos, Player.yPos, Player.width, Player.height);
        for (int i = 0; i < CircleSpawn.circles.size(); i++) {
            if (col.collisionPlayerCircle(CircleSpawn.circles.get(i))){
                CircleSpawn.circles.remove(i);
                score++;
            }else {
                g.drawImage(ImageLoader.imageCircleEnemy, CircleSpawn.circles.get(i).getxPos(), CircleSpawn.circles.get(i).getyPos(), CircleSpawn.circles.get(i).getWidth(), CircleSpawn.circles.get(i).getHeight());
            }
        }
        g.setFill(Color.WHITE);
        g.setFont(new Font(50));
        g.fillText(String.valueOf(score), 30, 60);


        /*for (CircleEnemy c : CircleSpawn.circles){
            if (col.collisionPlayerCircle(c)){
                c
            }else {
                g.drawImage(ImageLoader.imageCircleEnemy, c.getxPos(), c.getyPos(), c.getWidth(), c.getHeight());
            }
        }*/
    }
}
