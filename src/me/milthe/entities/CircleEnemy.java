package me.milthe.entities;

import javafx.scene.image.Image;
import me.milthe.core.Game;
import me.milthe.graphic.Gui;

public class CircleEnemy extends Entity{
    public int targetX, targetY, startingSite, circleIndex;

    public CircleEnemy() {
        width = 100;
        height = 100;
        speed = 30; //Desto höher desto langsamer
        sprite = new Image("file:rsc/sprites/enemy-circle.png");

        setPath();
    }

    public void move() {
        xPos += xVel;
        yPos += yVel;
    }

    public void setPath(){ //Setzt den Path auf dem der Circle sich bewegt
        //Gibt an auf welcher Seite der Circle spawnt
        startingSite = (int) (Math.random() * 4);

        //Circle bewegt sich immer auf einem Weg, der über den Punkt läuft auf dem sich der Player befindet wenn der Circle gespawnt wird
        targetX = Game.getPlayer().getxPos();
        targetY = Game.getPlayer().getyPos();

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
        int xOffset = targetX - xPos;
        int yOffset = targetY - yPos;

        //Je höher speed ist desto langsamer bewegt sich der Circle
        xVel = xOffset / speed;
        yVel = yOffset / speed;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setCircleIndex(int index){
        circleIndex = index;
    }

    @Override
    public Image getSprite() {
        return sprite;
    }
}
