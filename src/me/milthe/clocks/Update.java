package me.milthe.clocks;

import me.milthe.draw.DrawMain;
import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Entity;
import me.milthe.entities.Entitylist;
import me.milthe.entities.Player;
import me.milthe.events.Collision;
import me.milthe.gui.Gui;

public class Update {
    Collision col = new Collision();

    public void runUpdate(){
        entitiesUpdate();
        collisionPlayerCircle();
        Gui.circleSpawn.getCircles().forEach(this::checkForCircleOutOfBounce);
    }

    public void entitiesUpdate(){ //Updatet alle Methoden von Entities die wiederholt geupdatet werden müssen --> Hauptsächlich bewegung
        Gui.entitylist.getEntities().forEach(Entity::move);
    }
    public void collisionPlayerCircle(){//Kollisionsdetektion für Gegner zu Spieler
        for (int i = 0; i < Gui.circleSpawn.circles.size(); i++) {
            if (col.collisionPlayerCircle(Gui.circleSpawn.circles.get(i))) {
                Gui.entitylist.removeCircleEntity(Gui.circleSpawn.circles.get(i).listIndex, Gui.circleSpawn.circles.get(i).circleIndex);
                DrawMain.score++;
            }
        }
    }
    public void checkForCircleOutOfBounce(CircleEnemy circleEnemy){
        if (circleEnemy.getxPos() >= (Gui.width + 100) || circleEnemy.getxPos() <= -100 || circleEnemy.getyPos() >= (Gui.height + 100) || circleEnemy.getyPos() <= -100) {
            Gui.entitylist.removeCircleEntity(circleEnemy.listIndex, circleEnemy.circleIndex);
        }
    }
}
