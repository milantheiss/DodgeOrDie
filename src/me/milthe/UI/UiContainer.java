package me.milthe.UI;

import me.milthe.graphic.Gui;

import java.util.ArrayList;
import java.util.List;

public class UiContainer {
    public int x, y, width, height;
    public List<ButtonUi> components = new ArrayList<>();
    public UiContainer(){
        addCompontent("start", "file:rsc/sprites/start.png");
        addCompontent("verlassen", "file:rsc/sprites/verlassen.png");
        components.get(1).setMarginTop(components.get(1).getHeight()/2);
        int maxWidth = 0;
        int tempY = 0;
        for (ButtonUi buttonUI : components) {
            maxWidth = Math.max((buttonUI.getWidth()+ buttonUI.getMarginLeft() + buttonUI.getMarginRight()), maxWidth);
            this.height += buttonUI.getHeight()+ buttonUI.getMarginButton()+ buttonUI.getMarginTop();

        }
        this.width = maxWidth;
        this.x = (Gui.width-width)/2;
        this.y = (Gui.height-height)/2;
        for (ButtonUi buttonUi : components){
            buttonUi.setY(tempY);
            tempY += buttonUi.getHeight();
        }
        components.forEach(buttonUi -> buttonUi.setX(this.x));
        components.forEach(ButtonUi::applyMarginTop);
    }
    public void addCompontent(String compontenName, String filepath){
        components.add(new ButtonUi(compontenName, filepath, this));
    }
}
