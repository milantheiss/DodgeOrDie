package me.milthe.clocks;

import me.milthe.Main;
import me.milthe.draw.DrawMain;
import me.milthe.entities.Entity;
import me.milthe.events.Collision;

public class Update {
    Main main;
    Collision col;

    public Update(Main main){
        this.main = main;
        col = new Collision(main);
    }

    public void entitiesMovement(){ //Updatet alle Methoden von Entities die wiederholt geupdatet werden müssen --> Hauptsächlich bewegung
        main.getEntities().forEach(Entity::move);
    }

    public void collisionPlayerCircle(){
        for (int i = 0; i < CircleSpawn.circles.size(); i++) {
            if (col.collisionPlayerCircle(CircleSpawn.circles.get(i))) { //Kollisionsdetektion für Gegner zu Spieler --> Muss noch ausgelagert werden
                CircleSpawn.circles.remove(i);
                DrawMain.score++;
            }
        }
    }
}
