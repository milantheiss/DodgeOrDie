package me.milthe.ui;

public class MenuSetup {
    public final UiContainer MAIN_MENU_CONTAINER = new UiContainer();
    public final UiContainer PAUSE_MENU_CONTAINER = new UiContainer();
    public final UiContainer SPIELMODI_MENU_CONTAINER = new UiContainer();
    public final UiContainer SPIELMODI_INFINITE_MENU_CONTAINER = new UiContainer();
    public final UiContainer SPIELMODI_CUSTOM_MENU_CONTAINER = new UiContainer();
    public final UiContainer SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER = new UiContainer();

    public MenuSetup(){
        MAIN_MENU_CONTAINER.addButton("spielmodi", getClass().getResourceAsStream("/sprites/buttons/spielmodi.png"));
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

        SPIELMODI_MENU_CONTAINER.addButton("endless", getClass().getResourceAsStream("/sprites/buttons/endlos.png"));
        SPIELMODI_MENU_CONTAINER.addButton("custom", getClass().getResourceAsStream("/sprites/buttons/custom.png"));
        SPIELMODI_MENU_CONTAINER.addButton("zurueck", getClass().getResourceAsStream("/sprites/buttons/zurueck.png"));
        SPIELMODI_MENU_CONTAINER.components.get(1).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        SPIELMODI_MENU_CONTAINER.components.get(2).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        SPIELMODI_MENU_CONTAINER.centerContainerToScreen();

        SPIELMODI_INFINITE_MENU_CONTAINER.addButton("start", getClass().getResourceAsStream("/sprites/buttons/start.png"));
        SPIELMODI_INFINITE_MENU_CONTAINER.addButton("highscore", getClass().getResourceAsStream("/sprites/buttons/highscore.png"));
        SPIELMODI_INFINITE_MENU_CONTAINER.addButton("zurueck", getClass().getResourceAsStream("/sprites/buttons/zurueck.png"));
        SPIELMODI_INFINITE_MENU_CONTAINER.components.get(1).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        SPIELMODI_INFINITE_MENU_CONTAINER.components.get(2).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        SPIELMODI_INFINITE_MENU_CONTAINER.centerContainerToScreen();

        SPIELMODI_CUSTOM_MENU_CONTAINER.addButton("auswaehlen", getClass().getResourceAsStream("/sprites/buttons/auswaehlen.png"));
        SPIELMODI_CUSTOM_MENU_CONTAINER.addButton("tutorial", getClass().getResourceAsStream("/sprites/buttons/tutorial.png"));
        SPIELMODI_CUSTOM_MENU_CONTAINER.addButton("zurueck", getClass().getResourceAsStream("/sprites/buttons/zurueck.png"));
        SPIELMODI_CUSTOM_MENU_CONTAINER.components.get(1).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        SPIELMODI_CUSTOM_MENU_CONTAINER.components.get(2).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        SPIELMODI_CUSTOM_MENU_CONTAINER.centerContainerToScreen();

        //TODO In UpdateController nach visibility fragen wenn TextField bearbeitet wird
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.addButton("lvlname", getClass().getResourceAsStream("/sprites/buttons/levelname.png"));
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.components.get(0).setOverlapping(true);
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.addTextField("lvlnameTextField", getClass().getResourceAsStream("/sprites/buttons/textfield.png"));
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.components.get(1).setVisible(false);
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.addButton("start", getClass().getResourceAsStream("/sprites/buttons/start.png"));
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.addButton("zurueck", getClass().getResourceAsStream("/sprites/buttons/zurueck.png"));
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.components.get(2).setMarginTop(MAIN_MENU_CONTAINER.components.get(2).getHeight()/2);
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.components.get(3).setMarginTop(MAIN_MENU_CONTAINER.components.get(2).getHeight()/2);
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.centerContainerToScreen();
    }
}
