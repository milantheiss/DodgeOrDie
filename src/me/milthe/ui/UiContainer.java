package me.milthe.ui;

import me.milthe.graphic.Gui;

import java.util.ArrayList;
import java.util.List;

public class UiContainer {
    public int x, y, width, height;
    public List<ButtonUi> components = new ArrayList<>();
    public UiContainer(){

    }
    public void centerContainerToScreen(){
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
            buttonUi.setYToBeSum(tempY, this.y);
            buttonUi.applyMarginTop();
            tempY += buttonUi.getHeight()+ buttonUi.getMarginTop() + buttonUi.getMarginButton();
            buttonUi.setX(this.x);
        }
    }

    public void addComponent(String componentName, String filepath){
        components.add(new ButtonUi(componentName, filepath));
    }
}
