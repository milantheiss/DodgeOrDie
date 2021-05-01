package me.milthe.draw;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import me.milthe.Main;
import me.milthe.clocks.CircleSpawn;
import me.milthe.entities.Entity;
import me.milthe.entities.Player;
import me.milthe.events.Collision;
import me.milthe.gui.Gui;


public class DrawMain { //JavaFX Draw um Grafik anzuzeigen Wird in ClockMain.java render() aufgerufen
    public static int score = 0;

    public void draw(GraphicsContext g, Main main) {
        g.setFill(new Color(4. / 255., 0. / 255., 17. / 255., 1));
        g.fillRect(0, 0, Gui.width, Gui.height);

//        System.out.println(main.getEntities().size());
        /*for (Entity e : Main.entities){
            System.out.println(""+e.getSprite()+ e.getxPos() + e.getyPos() + e.getWidth() + e.getHeight());
        }*/
       // Main.entities.forEach(entity -> System.out.println("" /*+ entity.getSprite()*/ + entity.getxPos() + entity.getyPos() + entity.getWidth() + entity.getHeight()));
        System.out.println(main.entities.get(0).getSprite());
        main.entities.forEach(entity -> g.drawImage(entity.getSprite(), entity.getxPos(), entity.getyPos(), entity.getWidth(), entity.getHeight()));

        g.setFill(Color.WHITE);
        g.setFont(new Font(50));
        g.fillText(String.valueOf(score), 30, 60);
    }
}
