package me.milthe.ui;

import javafx.scene.image.Image;

import java.io.InputStream;

public abstract class UiCompontent {
    protected int x, y, width, height, marginTop, marginButton, marginLeft, marginRight;
    protected Image sprite;
    protected String componentName;
    protected boolean visible;
    private boolean overlapping;

    public UiCompontent(String name, InputStream filepathImage) {
        componentName = name;
        sprite = new Image(filepathImage);
        width = (int) Math.round(sprite.getWidth());
        height = (int) Math.round(sprite.getHeight());
    }

    public void applyMarginTop() {
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

    public void setY(int y) {
        this.y = y;
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

    public int getMarginLeft() {
        return marginLeft;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public String getComponentName() {
        return componentName;
    }

    public Image getSprite() {
        return sprite;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isOverlapping() {
        return overlapping;
    }

    public void setOverlapping(boolean overlapping) {
        this.overlapping = overlapping;
    }
}
