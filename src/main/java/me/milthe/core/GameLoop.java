package me.milthe.core;

import javafx.animation.AnimationTimer;
import me.milthe.ui.Gui;
import me.milthe.ui.Menustates;

/**
 * Sobald gestartet, ruft GameLoop kontinuierliche integralen Bestandteile des Spiels ab. GameLoop implements Runnable
 */
public class GameLoop implements Runnable {
    private final UpdateController UPDATE_CONTROLLER;

    private static final int UPDATES_PER_SECOND = 60;
    private double accumulator = 0;
    private long currentTime, lastUpdate = System.currentTimeMillis();

    /**
     * Erstellt neuen GameLoop
     * @param game Das Hauptobjekt von Game.java des Projekts
     */
    public GameLoop(Game game) {
        this.UPDATE_CONTROLLER = new UpdateController(game);
    }

    /**
     * Startet Gameloop. Der Gameloop wird erst wenn das Programm geschlossen wird beendet.
     */
    @Override
    public void run() {
        try {
            //AnimationTimer --> Gameloop führt alles aus, das pro Tick ausgeführt werden muss
            new AnimationTimer() {
                @Override
                public void handle(long l) {
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
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ruft UPDATE_CONTROLLER auf und führt Updates aus
     */
    private void update() {
        UPDATE_CONTROLLER.runUpdate();
    }

    /**
     * Ruf je nach Gamestate verschiedene Draw Klassen auf. DrawEnvironment wird immer aufgerufen
     */
    private void render() {
        Gui.gc_main.clearRect(0, 0, Gui.width, Gui.height);
        Gui.drawEnvironment.draw(Gui.gc_main);

        if (Game.state == Gamestates.INGAME || Game.state == Gamestates.PAUSE || Game.state == Gamestates.ENDSCREEN) {
            Gui.drawEntities.render(Gui.gc_main);
            Gui.drawIngameUi.render(Gui.gc_main);
        }

        if (Game.state == Gamestates.PAUSE || Game.state == Gamestates.MENU) {
            if (Gui.menustate == Menustates.MAIN) {
                Gui.drawUI.render(Gui.gc_main, Gui.menuSetup.MAIN_MENU_CONTAINER);
            } else if (Gui.menustate == Menustates.ENDLESS) {
                Gui.drawUI.render(Gui.gc_main, Gui.menuSetup.ENDLESS_MENU_CONTAINER);
            } else if (Gui.menustate == Menustates.PAUSE) {
                Gui.drawUI.render(Gui.gc_main, Gui.menuSetup.PAUSE_MENU_CONTAINER);
            }
        }

        if (Game.state == Gamestates.HIGHSCORE) {
            Gui.drawHighscore.render(Gui.gc_main);
        }

        if (Game.state == Gamestates.TUTORIAL) {
            Gui.drawTutorial.render(Gui.gc_main);
        }

        if (Game.state == Gamestates.ENDSCREEN) {
            Gui.drawEndscreenEndless.render(Gui.gc_main);
        }
    }
}