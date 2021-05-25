package me.milthe.core;

import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Entity;
import me.milthe.entities.Friend;
import me.milthe.entities.Player;
import me.milthe.events.*;
import me.milthe.gamemode.Gamemodes;
import me.milthe.gamemode.Endless;
import me.milthe.graphic.Gui;
import me.milthe.graphic.Menustates;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Game {
    public static Input input;

    public static Player player;
    public static List<Entity> entities;
    public static Gamestates state;
    public static Gamemodes mode;

    public Endless endless;

    public Levelloader levelloader = new Levelloader(this);

    public Game(){
        input = new Input();

        state = Gamestates.MENU;
        Gui.menustate = Menustates.MAIN;

        entities = new CopyOnWriteArrayList<>();

        endless = new Endless(this);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        entity.setListIndex(entities.indexOf(entity));
        entity.setGame(this);
    }

    public void removeEntity(Entity entity){
        entities.remove(entity);
        entities.forEach(entity1 -> entity1.setListIndex(entities.indexOf(entity1)));
    }

    public void addCircleEnemy(CircleEnemy circleEnemy){
        addEntity(circleEnemy);
        Endless.totalEnemiesSpawned++;
    }

    public void addFriend(Friend friend){
        addEntity(friend);
    }

    public static Player getPlayer() {
        return player;
    }
}
