package me.milthe.ui;

import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * Teil des Menüs muss als UiButton angewandt werden
 */
public abstract class UiComponent {
    protected int x, y, width, height, marginTop, marginButtom, marginLeft, marginRight;
    protected Image sprite;
    protected String componentName;

    /**
     * Erstellt neues UiComponent aus dem Namen und der Text aus filepath
     * @param name Klarname des UiComponent
     * @param filepath Pfad zur Bilddatei des UiComponent
     */
    public UiComponent(String name, InputStream filepath) {
        componentName = name;
        sprite = new Image(filepath);
        width = (int) Math.round(sprite.getWidth());
        height = (int) Math.round(sprite.getHeight());
    }

    /**
     * Wendet die Top Margin des UiComponent auf sich selbst an
     */
    public void applyMarginTop() {
        y += marginTop;
    }

    /**
     * Gibt die X Position des UiComponent zurück
     * @return X Position
     */
    public int getX() {
        return x;
    }

    /**
     * Setzt die X Position
     * @param x neue X Position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Geibt die Y Position des UiComponent zurück
     * @return Y Position
     */
    public int getY() {
        return y;
    }

    /**
     * Setzt die Y Position
     * @param y neue Y Position
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Setzt Y als die Summe von zwei Werten
     * @param summand1 erster Wert
     * @param summand2 zweiter Wert
     */
    public void setYToBeSum(int summand1, int summand2) {
        this.y = summand1 + summand2;
    }

    /**
     * Gibt die Breite des UiComponent zurück
     * @return Breite des UiComponent
     */
    public int getWidth() {
        return width;
    }

    /**
     * Setzt einen neue Breite für den UiComponent
     * @param width neue Breite des UiComponent
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gibt die Höhe des UiComponent zurück
     * @return Höhe des UiComponent
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setzt einen neue Höhe für den UiComponent
     * @param height neue Höhe des UiComponent
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gibt die Top Margin zurück
     * @return Top Margin
     */
    public int getMarginTop() {
        return marginTop;
    }

    /**
     * Setzt die Top Margin neu
     * @param marginTop neu Top Margin
     */
    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    /**
     * Gibt die Button Margin zurück
     * @return Button Margin
     */
    public int getMarginButtom() {
        return marginButtom;
    }

    /**
     * Gibt die Left Margin zurück
     * @return Left Margin
     */
    public int getMarginLeft() {
        return marginLeft;
    }

    /**
     * Gibt die Right Margin zurück
     * @return Right Margin
     */
    public int getMarginRight() {
        return marginRight;
    }

    /**
     * Gibt Klarnamen des UiComponent zurück
     * @return Klarnamen des UiComponent
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * Gibt die Spite des UiComponent zurück
     * @return Spite des UiComponent
     */
    public Image getSprite() {
        return sprite;
    }
}
