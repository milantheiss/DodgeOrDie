package me.milthe.UI;

import me.milthe.graphic.Gui;

import java.util.ArrayList;
import java.util.List;

public class UiContainer {
    public int x, y, width, height;
    public List<ButtonUi> components = new ArrayList<>();
    public UiContainer(){
        addCompontent("start", "file:rsc/sprites/start.png");
        int maxWidth = 0;
        for (ButtonUi buttonUI : components) {
            maxWidth = Math.max((buttonUI.getWidth()+ buttonUI.getMarginLeft() + buttonUI.getMarginRight()), maxWidth);
            System.out.println(maxWidth);
            this.height += buttonUI.getHeight()+ buttonUI.getMarginButton()+ buttonUI.getMarginTop();
        }
        this.width = maxWidth;
        this.x = (Gui.width-width)/2;
        this.y = (Gui.height-height)/2;
        components.forEach(buttonUi -> buttonUi.allign());
    }
    public void addCompontent(String compontenName, String filepath){
        components.add(new ButtonUi(compontenName, filepath, this));
    }
}
