package me.milthe.core;

import me.milthe.graphic.Gui;


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

        if (Gamestate.state == GamestateEnum.ingame || Gamestate.state == GamestateEnum.pause) {
            Gui.drawEntities.render(Gui.gc_main, GAME);
            Gui.drawIngameUi.render(Gui.gc_main);
        }

        if (Gamestate.state == GamestateEnum.pause){
            Gui.drawUI.render(Gui.gc_main, Gui.pauseContainer);
        }

        if (Gamestate.state == GamestateEnum.menu) {
            Gui.drawUI.render(Gui.gc_main, Gui.menuContainer);
        }

        if (Gamestate.state == GamestateEnum.tutorial) {
            Gui.drawTutorial.render(Gui.gc_main);
        }
    }
}
