package me.milthe.core;

import me.milthe.entities.CircleEnemy;
import me.milthe.entities.Entity;
import me.milthe.entities.Friend;
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
    public static List<Friend> friendList;

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
        friendList = new CopyOnWriteArrayList<>();

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

    public void removeEntity(Entity entity){
        entities.remove(entity);
        entities.forEach(entity1 -> entity1.setListIndex(entities.indexOf(entity1)));
    }

    public void addCircleEnemy(CircleEnemy circleEnemy){
        circleEnemyList.add(circleEnemy);
        circleEnemy.setCircleIndex(circleEnemyList.indexOf(circleEnemy));
        addEntity(circleEnemy);
        Scoring.totalEnemiesSpawned++;
    }

    public void removeCircleEnemy(CircleEnemy circleEnemy){
        circleEnemyList.remove(circleEnemy.getCircleIndex());
        circleEnemyList.forEach(circle -> circle.setCircleIndex(circleEnemyList.indexOf(circle)));
        removeEntity(circleEnemy);
    }

    public void addFriend(Friend friend){
        friendList.add(friend);
        friend.setFriendIndex(friendList.indexOf(friend));
        addEntity(friend);
    }

    public void removeFriend(Friend friend){
        friendList.remove(friend.getFriendIndex());
        friendList.forEach(friend1 -> friend1.setFriendIndex(friendList.indexOf(friend1)));
        removeEntity(friend);
    }

    public static Player getPlayer() {
        return player;
    }
}
