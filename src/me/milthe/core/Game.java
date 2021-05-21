package me.milthe.core;

import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Entity;
import me.milthe.entities.Player;
import me.milthe.events.*;
import me.milthe.gamemode.Gamemodes;
import me.milthe.gamemode.Infinite;
import me.milthe.graphic.Gui;
import me.milthe.graphic.Menustates;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Game {
    public static Input input;

    public static Player player;
    public static List<Entity> entities;
    public static List<CircleEnemy> circleEnemyList;

    public static Gamestates state;
    public static Gamemodes mode;

    public Infinite infinite;

    public Levelloader levelloader = new Levelloader(this);

    public Game(){
        input = new Input();

        state = Gamestates.MENU;
        Gui.menustate = Menustates.MAIN;

        entities = new CopyOnWriteArrayList<>();
        circleEnemyList = new CopyOnWriteArrayList<>();

        infinite = new Infinite(this);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        entity.setListIndex(entities.indexOf(entity));
        entity.setGame(this);
    }

    public void removeEntity(int index){
        entities.remove(index);
        entities.forEach(entity -> entity.setListIndex(entities.indexOf(entity)));
    }

    public void addCircleEnemy(CircleEnemy circleEnemy){
        circleEnemyList.add(circleEnemy);
        circleEnemy.setCircleIndex(circleEnemyList.indexOf(circleEnemy));
        addEntity(circleEnemy);
        circleEnemy.setListIndex(entities.indexOf(circleEnemy));
        Scoring.totalEnemiesSpawned++;
    }

    public void removeCircleEnemy(int index, int circleIndex){
        circleEnemyList.remove(circleIndex);
        circleEnemyList.forEach(circle -> circle.setCircleIndex(circleEnemyList.indexOf(circle)));
        removeEntity(index);
    }

    public static Player getPlayer() {
        return player;
    }
}
