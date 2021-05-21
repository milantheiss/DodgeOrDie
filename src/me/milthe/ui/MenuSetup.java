package me.milthe.ui;

public class MenuSetup {
    public final UiContainer MAIN_MENU_CONTAINER = new UiContainer();
    public final UiContainer PAUSE_MENU_CONTAINER = new UiContainer();
    public final UiContainer SPIELMODI_MENU_CONTAINER = new UiContainer();
    public final UiContainer SPIELMODI_INFINITE_MENU_CONTAINER = new UiContainer();
    public final UiContainer SPIELMODI_CUSTOM_MENU_CONTAINER = new UiContainer();
    public final UiContainer SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER = new UiContainer();

    public MenuSetup(){
        MAIN_MENU_CONTAINER.addButton("spielmodi", "file:rsc/sprites/buttons/spielmodi.png");
        MAIN_MENU_CONTAINER.addButton("tutorial", "file:rsc/sprites/buttons/tutorial.png");
        MAIN_MENU_CONTAINER.addButton("verlassen", "file:rsc/sprites/buttons/verlassen.png");
        MAIN_MENU_CONTAINER.components.get(1).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        MAIN_MENU_CONTAINER.components.get(2).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        MAIN_MENU_CONTAINER.centerContainerToScreen();

        PAUSE_MENU_CONTAINER.addButton("weiter", "file:rsc/sprites/buttons/weiter.png");
        PAUSE_MENU_CONTAINER.addButton("neustart", "file:rsc/sprites/buttons/neustart.png");
        PAUSE_MENU_CONTAINER.addButton("verlassen", "file:rsc/sprites/buttons/verlassen.png");
        PAUSE_MENU_CONTAINER.components.get(1).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        PAUSE_MENU_CONTAINER.components.get(2).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        PAUSE_MENU_CONTAINER.centerContainerToScreen();

        SPIELMODI_MENU_CONTAINER.addButton("infinite", "file:rsc/sprites/buttons/infinite.png");
        SPIELMODI_MENU_CONTAINER.addButton("custom", "file:rsc/sprites/buttons/custom.png");
        SPIELMODI_MENU_CONTAINER.addButton("zurueck", "file:rsc/sprites/buttons/zurueck.png");
        SPIELMODI_MENU_CONTAINER.components.get(1).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        SPIELMODI_MENU_CONTAINER.components.get(2).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        SPIELMODI_MENU_CONTAINER.centerContainerToScreen();

        SPIELMODI_INFINITE_MENU_CONTAINER.addButton("start", "file:rsc/sprites/buttons/start.png");
        SPIELMODI_INFINITE_MENU_CONTAINER.addButton("highscore", "file:rsc/sprites/buttons/highscore.png");
        SPIELMODI_INFINITE_MENU_CONTAINER.addButton("zurueck", "file:rsc/sprites/buttons/zurueck.png");
        SPIELMODI_INFINITE_MENU_CONTAINER.components.get(1).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        SPIELMODI_INFINITE_MENU_CONTAINER.components.get(2).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        SPIELMODI_INFINITE_MENU_CONTAINER.centerContainerToScreen();

        SPIELMODI_CUSTOM_MENU_CONTAINER.addButton("auswaehlen", "file:rsc/sprites/buttons/auswaehlen.png");
        SPIELMODI_CUSTOM_MENU_CONTAINER.addButton("tutorial", "file:rsc/sprites/buttons/tutorial.png");
        SPIELMODI_CUSTOM_MENU_CONTAINER.addButton("zurueck", "file:rsc/sprites/buttons/zurueck.png");
        SPIELMODI_CUSTOM_MENU_CONTAINER.components.get(1).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        SPIELMODI_CUSTOM_MENU_CONTAINER.components.get(2).setMarginTop(MAIN_MENU_CONTAINER.components.get(1).getHeight()/2);
        SPIELMODI_CUSTOM_MENU_CONTAINER.centerContainerToScreen();

        //TODO Sprite von Blanko zu "Level Name hier ändern"
        //TODO In Update nach visibility fragen wenn TextField bearbeitet wird
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.addButton("lvlname", "file:rsc/sprites/buttons/levelname.png");
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.components.get(0).setOverlapping(true);
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.addTextField("lvlnameTextField", "file:rsc/sprites/buttons/textfield.png");
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.components.get(1).setVisible(false);
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.addButton("start", "file:rsc/sprites/buttons/start.png");
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.addButton("zurueck", "file:rsc/sprites/buttons/zurueck.png");
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.components.get(2).setMarginTop(MAIN_MENU_CONTAINER.components.get(2).getHeight()/2);
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.components.get(3).setMarginTop(MAIN_MENU_CONTAINER.components.get(2).getHeight()/2);
        SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER.centerContainerToScreen();
    }
}
