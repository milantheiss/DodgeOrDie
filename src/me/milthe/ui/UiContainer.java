package me.milthe.ui;

import me.milthe.graphic.Gui;

import java.util.ArrayList;
import java.util.List;

public class UiContainer {
    public int x, y, width, height;
    public List<UiCompontent> components = new ArrayList<>();
    public List<UiButton> uiButtons = new ArrayList<>();
    public List<UiTextField> uiTextFields = new ArrayList<>();

    public void centerContainerToScreen(){
        int maxWidth = 0;
        int tempY = 0;
        for (UiCompontent uiCompontent : components) {
            maxWidth = Math.max((uiCompontent.getWidth()+ uiCompontent.getMarginLeft() + uiCompontent.getMarginRight()), maxWidth);
            if (!uiCompontent.overlapping) this.height += uiCompontent.getHeight() + uiCompontent.getMarginButton() + uiCompontent.getMarginTop();
        }
        this.width = maxWidth;
        this.x = (Gui.width-width)/2;
        this.y = (Gui.height-height)/2;
        for (UiCompontent uiCompontent : components){
            uiCompontent.setYToBeSum(tempY, this.y);
            uiCompontent.applyMarginTop();
            if (!uiCompontent.overlapping) tempY += uiCompontent.getHeight()+ uiCompontent.getMarginTop() + uiCompontent.getMarginButton();
            uiCompontent.setX(this.x);
        }
    }

    public void addButton(String componentName, String filepath){
        UiButton button = new UiButton(componentName, filepath);
        components.add(button);
        uiButtons.add(button);
    }

    public void addTextField(String componentName, String filepath){
        UiTextField uiTextField = new UiTextField(componentName, filepath);
        components.add(uiTextField);
        uiTextFields.add(uiTextField);
    }
}
