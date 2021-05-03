package me.milthe.core;

import me.milthe.graphic.DrawEnvironment;
import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Entity;
import me.milthe.events.Collision;
import me.milthe.graphic.Gui;

public class Update {
    private final Game game ;
    Collision col;
    
    public Update(Game game){
        this.game = game;
        col = new Collision(game);
    }

    public void runUpdate(){
        entitiesUpdate();
        collisionPlayerCircle();
        game.circleSpawn.getCircles().forEach(this::checkForCircleOutOfBounce);
    }

    public void entitiesUpdate(){ //Updatet alle Methoden von Entities die wiederholt geupdatet werden müssen --> Hauptsächlich bewegung
        game.entities.forEach(Entity::move);
    }

    public void collisionPlayerCircle(){//Kollisionsdetektion für Gegner zu Spieler
        for (int i = 0; i < game.circleSpawn.circles.size(); i++) {
            if (col.collisionPlayerCircle(game.circleSpawn.circles.get(i))) {
                game.removeCircleEntity(game.circleSpawn.circles.get(i).listIndex, game.circleSpawn.circles.get(i).circleIndex);
                DrawEnvironment.score++;
            }
        }
    }

    public void checkForCircleOutOfBounce(CircleEnemy circleEnemy){
        if (circleEnemy.getxPos() >= (Gui.width + 100) || circleEnemy.getxPos() <= -100 || circleEnemy.getyPos() >= (Gui
                .height + 100) || circleEnemy.getyPos() <= -100) {
            game.removeCircleEntity(circleEnemy.listIndex, circleEnemy.circleIndex);
        }
    }
}
