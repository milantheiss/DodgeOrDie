package me.milthe.ui;

/**
 * Setzt Menüs auf
 */
public class Menus {
    public final UiContainer MAIN_MENU_CONTAINER = new UiContainer();
    public final UiContainer PAUSE_MENU_CONTAINER = new UiContainer();
    public final UiContainer ENDLESS_MENU_CONTAINER = new UiContainer();

    /**
     * Erstellt jedes Menü des Projekts
     */
    public Menus(){
        MAIN_MENU_CONTAINER.addButton("spielen", getClass().getResourceAsStream("/sprites/buttons/spielen.png"));
        MAIN_MENU_CONTAINER.addButton("tutorial", getClass().getResourceAsStream("/sprites/buttons/tutorial.png"));
        MAIN_MENU_CONTAINER.addButton("verlassen", getClass().getResourceAsStream("/sprites/buttons/verlassen.png"));
        MAIN_MENU_CONTAINER.components.get(1).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        MAIN_MENU_CONTAINER.components.get(2).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        MAIN_MENU_CONTAINER.centerContainerToScreen();

        PAUSE_MENU_CONTAINER.addButton("weiter", getClass().getResourceAsStream("/sprites/buttons/weiter.png"));
        PAUSE_MENU_CONTAINER.addButton("neustart", getClass().getResourceAsStream("/sprites/buttons/neustart.png"));
        PAUSE_MENU_CONTAINER.addButton("verlassen", getClass().getResourceAsStream("/sprites/buttons/verlassen.png"));
        PAUSE_MENU_CONTAINER.components.get(1).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        PAUSE_MENU_CONTAINER.components.get(2).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        PAUSE_MENU_CONTAINER.centerContainerToScreen();

        ENDLESS_MENU_CONTAINER.addButton("start", getClass().getResourceAsStream("/sprites/buttons/start.png"));
        ENDLESS_MENU_CONTAINER.addButton("highscore", getClass().getResourceAsStream("/sprites/buttons/highscore.png"));
        ENDLESS_MENU_CONTAINER.addButton("zurueck", getClass().getResourceAsStream("/sprites/buttons/zurueck.png"));
        ENDLESS_MENU_CONTAINER.components.get(1).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        ENDLESS_MENU_CONTAINER.components.get(2).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        ENDLESS_MENU_CONTAINER.centerContainerToScreen();
    }
}
