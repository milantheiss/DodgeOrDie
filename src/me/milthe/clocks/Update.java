package me.milthe.clocks;

import me.milthe.entities.Player;

public class Update {
    public void entitiesUpdate(){ //Updatet alle Methoden von Entities die wiederholt geupdatet werden müssen --> Hauptsächlich bewegung
        Player.move();
        for (int i = 0; i < CircleSpawn.circles.size(); i++) {
            CircleSpawn.circles.get(i).move();
        }
    }
}
