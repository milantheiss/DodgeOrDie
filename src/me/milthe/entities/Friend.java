package me.milthe.entities;

import javafx.scene.image.Image;
import me.milthe.graphic.Gui;

public class Friend extends Entity {
    private int friendIndex;
    private final Image SPRITE_IDLE = new Image("file:rsc/sprites/entities/enemies/friend/friend_idle.png");
    private double directionX, directionY;

    public Friend() {
        width = 100;
        height = 100;
        speed = 30; //Desto höher desto langsamer
        setPath();
        //speed = (int) (Math.random() * 10) + 1;
    }

    @Override
    public void move() {
        xPos += (int) Math.round(directionX * speed);
        yPos += (int) Math.round(directionY * speed);
    }

    @Override
    public Image getSprite() {
        return SPRITE_IDLE;
    }

    //TODO Pathing von Friends vereinfachen siehe Blatt
    public void setPath() { //Setzt den Path auf dem der Circle sich bewegt
        //Gibt an auf welcher Seite der Circle spawnt
        int startingSite = (int) (Math.random() * 4);

        if (startingSite == 0) {
            //start von Oben -> Y = 0
            xPos = (int) (Math.random() * Gui.width);
            yPos = 0;
        } else if (startingSite == 1) {
            //start von Rechts -> X = Screen width
            xPos = Gui.width;
            yPos = (int) (Math.random() * Gui.height);
        } else if (startingSite == 2) {
            //start von Unten -> Y = Screen height
            xPos = (int) (Math.random() * Gui.width);
            yPos = Gui.height;
        } else if (startingSite == 3) {
            //start von Links -> X = 0
            xPos = 0;
            yPos = (int) (Math.random() * Gui.height);
        }

        if ((Gui.width - xPos) < xPos) {
            directionX = Math.random()*(-1);
        } else{
            directionX = Math.random();
        }

        if ((Gui.height - yPos) < yPos){
            directionY = Math.random()*(-1);
        }else {
            directionY = Math.random();
        }

    }

    public void setFriendIndex(int index) {
        friendIndex = index;
    }

    public int getFriendIndex() {
        return friendIndex;
    }
}
