package me.milthe.entities;

import me.milthe.clocks.CircleSpawn;
import me.milthe.gui.Gui;

import java.util.ArrayList;
import java.util.List;

public class Entitylist {
    public List<Entity> entities = new ArrayList<>();

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntity(Entity entity) {
        entities.add(entity);
        entity.setListIndex(entities.indexOf(entity));
    }

    public void removeEntity(int index){
        entities.remove(index);
        entities.forEach(entity -> entity.setListIndex(entities.indexOf(entity)));
    }

    public void removeCircleEntity(int index, int circleIndex){
        Gui.circleSpawn.removeCircle(circleIndex);
        removeEntity(index);
    }

    public void queueInitSpawn(){
        setEntity(new Player());
        Gui.circleSpawn.start();
    }
}
