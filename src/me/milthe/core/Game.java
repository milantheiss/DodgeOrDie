package me.milthe.core;

import me.milthe.entities.Entity;
import me.milthe.entities.Player;
import me.milthe.events.*;
import me.milthe.graphic.Gui;
import me.milthe.spawner.CircleSpawn;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Game {
    public static Input input;
    public static Player player;

    public CircleSpawn circleSpawn;
    public List<Entity> entities;

    public Game(){
        input = new Input();

        Gamestate.state = GamestateEnum.menu;

        queueInitSpawn();

        //EventListeners
        Gui.scene.setOnMouseClicked(new MouseClicked());
        Gui.scene.setOnMouseMoved(new MouseMoved());
        Gui.scene.setOnKeyPressed(new KeyPressed());
        Gui.scene.setOnKeyReleased(new KeyReleased());
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntity(Entity entity) {
        entities.add(entity);
        entity.setListIndex(entities.indexOf(entity));
        entity.setGame(this);
    }

    public void removeEntity(int index){
        entities.remove(index);
        entities.forEach(entity -> entity.setListIndex(entities.indexOf(entity)));
    }

    public void removeCircleEntity(int index, int circleIndex){
        circleSpawn.removeCircle(circleIndex);
        removeEntity(index);
    }

    public void queueInitSpawn(){
        circleSpawn = new CircleSpawn(this);
        entities = new CopyOnWriteArrayList<>();
        player = new Player();
        setEntity(player);
        circleSpawn.start();
    }

    public static Player getPlayer() {
        return player;
    }

}
