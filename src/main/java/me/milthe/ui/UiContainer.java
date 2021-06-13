package me.milthe.ui;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Behälter um UiComponent zuhalten
 */
public class UiContainer {
    public int x, y, width, height;
    public List<UiComponent> components = new ArrayList<>();
    public List<UiButton> uiButtons = new ArrayList<>();

    /**
     * Zentriert den Container zum Screen
     */
    public void centerContainerToScreen() {
        int maxWidth = 0;
        int tempY = 0;
        for (UiComponent uiComponent : components) {
            maxWidth = Math.max((uiComponent.getWidth() + uiComponent.getMarginLeft() + uiComponent.getMarginRight()), maxWidth);
            this.height += uiComponent.getHeight() + uiComponent.getMarginButtom() + uiComponent.getMarginTop();
        }
        this.width = maxWidth;
        this.x = (Gui.width - width) / 2;
        this.y = (Gui.height - height) / 2;
        for (UiComponent uiComponent : components) {
            uiComponent.setYToBeSum(tempY, this.y);
            uiComponent.applyMarginTop();
            tempY += uiComponent.getHeight() + uiComponent.getMarginTop() + uiComponent.getMarginButtom();
            uiComponent.setX(this.x);
        }
    }

    /**
     * Fügt neuen Button zur Container hinzu
     * @param componentName Klarname des UiComponent
     * @param filepath Pfad zur Bilddatei des UiComponent
     */
    public void addButton(String componentName, InputStream filepath) {
        UiButton button = new UiButton(componentName, filepath);
        components.add(button);
        uiButtons.add(button);
    }
}
