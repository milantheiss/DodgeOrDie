package me.milthe.core;

import me.milthe.graphic.Gui;


public class GameLoop implements Runnable{

    private final Game game;

    Update update;

    public GameLoop(Game game) {
        this.update = new Update(game);
        this.game = game;
    }


    @Override //Main Loop fürs Spiel
    public void run() {
        boolean running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();

        try {
            while(true) {
                currentTime = System.currentTimeMillis();
                double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
                accumulator += lastRenderTimeInSeconds;
                lastUpdate = currentTime;

                double updateRate = 1.0d / 60d;
                if(accumulator >= updateRate) {
                    while(accumulator >= updateRate) {
                        update();
                        accumulator -= updateRate;
                    }
                    render();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Fehler in Gameloop - while schleife");
        }
    }

    private void update() {
        update.runUpdate();
    }

    private void render() {
        Gui.gc_main.clearRect(0, 0, Gui.width, Gui.height);
        Gui.dm.draw(Gui.gc_main);
        Gui.drawEntities.render(Gui.gc_main, game);
        Gui.drawUI.render(Gui.gc_main);
    }
}
