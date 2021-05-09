package me.milthe.core;

import me.milthe.entities.Entity;
import me.milthe.entities.Player;
import me.milthe.events.*;
import me.milthe.graphic.DrawIngameUi;
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

        //EventListeners
        Gui.scene.setOnMouseClicked(new MouseClicked());
        Gui.scene.setOnMouseMoved(new MouseMoved());
        Gui.scene.setOnKeyPressed(new KeyPressed());
        Gui.scene.setOnKeyReleased(new KeyReleased());


        circleSpawn = new CircleSpawn(this);
        entities = new CopyOnWriteArrayList<>();
        circleSpawn.start();
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
        circleSpawn.circles.clear();
        entities.clear();
        DrawIngameUi.score = 0;
        player = new Player();
        setEntity(player);

    }

    public static Player getPlayer() {
        return player;
    }



}
