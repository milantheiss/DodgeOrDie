package me.milthe.UI;

import javafx.scene.image.Image;

public class ButtonUi {
    private int x, y, width, height, marginTop, marginButton, marginLeft, marginRight;
    public Image sprite;
    public String buttonName;

    public ButtonUi(String name, String filepath){
        buttonName = name;
        sprite = new Image(filepath);
        width = (int)Math.round(sprite.getWidth());
        height = (int)Math.round(sprite.getHeight());
    }

    public void applyMarginTop(){
        y += marginTop;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setYToBeSum(int summand1, int summand2) {
        this.y = summand1 + summand2;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public int getMarginButton() {
        return marginButton;
    }

    public void setMarginButton(int marginButton) {
        this.marginButton = marginButton;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public String getButtonName() {
        return buttonName;
    }
}
