package me.milthe.ui;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Behälter um UiComponent zuhalten
 */
public class UiContainer {
    private int x, y, width, height;
    private List<UiComponent> components = new ArrayList<>();
    private List<UiButton> uiButtons = new ArrayList<>();

    /**
     * Zentriert den Container zum Screen
     */
    public void centerContainerToScreen() {
        int maxWidth = 0;
        int tempY = 0;
        for (UiComponent uiComponent : getComponents()) {
            maxWidth = Math.max((uiComponent.getWidth() + uiComponent.getMarginLeft() + uiComponent.getMarginRight()), maxWidth);
            this.height += uiComponent.getHeight() + uiComponent.getMarginButtom() + uiComponent.getMarginTop();
        }
        this.width = maxWidth;
        this.x = (Gui.WIDTH - width) / 2;
        this.y = (Gui.HEIGHT - height) / 2;
        for (UiComponent uiComponent : getComponents()) {
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
        getComponents().add(button);
        getUiButtons().add(button);
    }

    /**
     * Gibt Components Liste zurück
     * @return Components Liste
     */
    public List<UiComponent> getComponents() {
        return components;
    }

    /**
     * Gibt UiButtons Liste zurück
     * @return UiButton Liste
     */
    public List<UiButton> getUiButtons() {
        return uiButtons;
    }
}
