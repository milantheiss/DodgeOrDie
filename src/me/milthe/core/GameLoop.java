package me.milthe.core;

import me.milthe.graphic.Gui;


public class GameLoop implements Runnable{
    public static final int UPDATES_PER_SECOND = 60;

    private final Game game;

    Update update;

    private long nextStatTime;
    private int fps, ups;

    public GameLoop(Game game) {
        this.update = new Update(game);
        this.game = game;
    }


    @Override //Main Loop fürs Spiel
    public void run() {
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() + 1000;

        try {
            while(true) {
                currentTime = System.currentTimeMillis();
                double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
                accumulator += lastRenderTimeInSeconds;
                lastUpdate = currentTime;

                double updateRate = 1.0d / UPDATES_PER_SECOND;
                if(accumulator >= updateRate) {
                    while(accumulator >= updateRate) {
                        update();
                        accumulator -= updateRate;
                    }
                    render();
                }
                printStats();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Fehler in Gameloop - while schleife");
        }
    }

    private void printStats() {
        if (System.currentTimeMillis() > nextStatTime){
            System.out.printf("FPS: %d, UPS: %d%n", fps, ups);
            fps = 0;
            ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }

    private void update() {
        update.runUpdate();
        ups++;
    }

    private void render() {
        Gui.gc_main.clearRect(0, 0, Gui.width, Gui.height);
        Gui.dm.draw(Gui.gc_main);
        Gui.drawEntities.render(Gui.gc_main, game);
        Gui.drawUI.render(Gui.gc_main);
        fps++;
    }
}
