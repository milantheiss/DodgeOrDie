package me.milthe.core;

import me.milthe.graphic.Gui;
import me.milthe.graphic.Menustates;


public class GameLoop implements Runnable {
    public static final int UPDATES_PER_SECOND = 60;

    private final Game GAME;
    private final Update UPDATE;

    public GameLoop(Game game) {
        this.UPDATE = new Update(game);
        this.GAME = game;
    }


    @Override //Main Loop fürs Spiel
    public void run() {
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();

        try {
            while (true) {
                currentTime = System.currentTimeMillis();
                double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
                accumulator += lastRenderTimeInSeconds;
                lastUpdate = currentTime;

                double updateRate = 1.0d / UPDATES_PER_SECOND;
                if (accumulator >= updateRate) {
                    while (accumulator >= updateRate) {
                        update();
                        accumulator -= updateRate;
                    }
                    render();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fehler in Gameloop - while schleife");
        }
    }

    private void update() {
        UPDATE.runUpdate();
    }

    private void render() {
        Gui.gc_main.clearRect(0, 0, Gui.width, Gui.height);
        Gui.drawEnvironment.draw(Gui.gc_main);

        if (Game.state == Gamestates.INGAME || Game.state == Gamestates.PAUSE || Game.state == Gamestates.ENDSCREEN) {
            Gui.drawEntities.render(Gui.gc_main, GAME);
            Gui.drawIngameUi.render(Gui.gc_main);
        }

        if (Game.state == Gamestates.PAUSE || Game.state == Gamestates.MENU){
            if (Gui.menustate == Menustates.MAIN){
                Gui.drawUI.render(Gui.gc_main, Gui.menuSetup.MAIN_MENU_CONTAINER);
            }else if (Gui.menustate == Menustates.SPIELMODI){
                Gui.drawUI.render(Gui.gc_main, Gui.menuSetup.SPIELMODI_MENU_CONTAINER);
            }else if (Gui.menustate == Menustates.SPIELMODI_INFINITE){
                Gui.drawUI.render(Gui.gc_main, Gui.menuSetup.SPIELMODI_INFINITE_MENU_CONTAINER);
            }else if (Gui.menustate == Menustates.SPIELMODI_CUSTOM){
                Gui.drawUI.render(Gui.gc_main, Gui.menuSetup.SPIELMODI_CUSTOM_MENU_CONTAINER);
            }else if (Gui.menustate == Menustates.SPIELMODI_CUSTOM_SELECT){
                Gui.drawUI.render(Gui.gc_main, Gui.menuSetup.SPIELMODI_CUSTOM_SELECT_MENU_CONTAINER);
            }else if (Gui.menustate == Menustates.PAUSE){
                Gui.drawUI.render(Gui.gc_main, Gui.menuSetup.PAUSE_MENU_CONTAINER);
            }
        }

        if (Game.state == Gamestates.TUTORIAL) {
            Gui.drawTutorial.render(Gui.gc_main);
        }

        if (Game.state == Gamestates.ENDSCREEN) {
            Gui.drawEndscreen.render(Gui.gc_main);
        }
    }
}
