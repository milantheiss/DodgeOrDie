package me.milthe.clocks;

import me.milthe.draw.DrawMain;
import me.milthe.entities.Player;
import me.milthe.events.Collision;

public class Update {
    Collision col = new Collision();



    public void entitiesUpdate(){ //Updatet alle Methoden von Entities die wiederholt geupdatet werden müssen --> Hauptsächlich bewegung
        Player.move();
        for (int i = 0; i < CircleSpawn.circles.size(); i++) {
            CircleSpawn.circles.get(i).move();
        }
    }
    public void collisionPlayerCircle(){//Kollisionsdetektion für Gegner zu Spieler
        for (int i = 0; i < CircleSpawn.circles.size(); i++) {
            if (col.collisionPlayerCircle(CircleSpawn.circles.get(i))) {
                CircleSpawn.circles.remove(i);
                DrawMain.score++;
            }
        }
    }
}
