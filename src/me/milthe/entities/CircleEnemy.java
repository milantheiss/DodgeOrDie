package me.milthe.entities;

import javafx.scene.image.Image;
import me.milthe.Main;
import me.milthe.gui.Gui;

//TODO CircleEnemy Klasse aufräumen

public class CircleEnemy extends Entity {
    public int xPos, yPos, targetX, targetY, width = 100, height = 100, startingSite;
    Image sprite = new Image("file:rsc/player.png");


    public CircleEnemy(Main main){
        //Beim erstellen eines Neuen Circle Enemies wird der Startpunkt festgelegt und der Richtung
        startingSite = (int) (Math.random() * 4);
        //Sucht einen Random Punkt im Screen
        targetX = main.getEntities().get(0).xPos;
        targetY = main.getEntities().get(0).yPos;

        if (startingSite == 0) {
            //start von Oben -> Y = 0
            xPos = (int) (Math.random() * Gui.width);
            yPos = 0;
            //System.out.println(xPos + " " + yPos);
        } else if (startingSite == 1) {
            //start von Rechts -> X = Screen width
            xPos = Gui.width;
            yPos = (int) (Math.random() * Gui.height);
            //System.out.println(xPos + " " + yPos);
        } else if (startingSite == 2) {
            //start von Unten -> Y = Screen height
            xPos = (int) (Math.random() * Gui.width);
            yPos = Gui.height;
            //System.out.println(xPos + " " + yPos);
        } else if (startingSite == 3) {
            //start von Links -> X = 0
            xPos = 0;
            yPos = (int) (Math.random() * Gui.height);
            //System.out.println(xPos + " " + yPos);
        }
        int xOffset = targetX - xPos;
        int yOffset = targetY - yPos;

        xVel = xOffset / speed;
        yVel = yOffset / speed;
    }

    //Umgehen von static --> getter und setter
    public void move() {
        xPos += xVel;
        yPos += yVel;
    }

    @Override
    public Image getSprite() {
        return sprite;
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
}
