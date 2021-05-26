package me.milthe.core;

import me.milthe.entities.CircleEnemy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Levelloader {
    private static BufferedReader br;
    private final Game game;

    public Levelloader(Game game){
        this.game = game;
    }
    //filepath muss in String Format sein --> rsc/.. !!! Ohne file:... !!!
    public void start(String filePath) {
        new Thread(() -> {
            try {
                br = new BufferedReader(new FileReader(filePath));
                String tempLine = null;
                String[] substring = new String[3];

                while ((tempLine = br.readLine()) != null) {
                    substring = tempLine.split(" ");

                    if (substring[0].equals("spawn")) {
                        spawnEnemy(substring[1]);
                    } else if (substring[0].equals("pause")) {
                        try {
                            Thread.sleep(Integer.parseInt(substring[1]));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Unbekannter Befehl");
                    }
                }
                System.out.println("Level Ende");
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File nicht gefunden");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    //Callt endweder Konstruktor des Gegner oder eine Spawn-Methode
    //Wichtig! In gesamte ArrayList für Entity hinzufügen
    //Wichtig! Wichtige Parameter festlegen, die nicht vom Konstruktor festgelegt werden

    public void spawnEnemy(String type/*, String modifier*/) {
        //Modifier kann für Gegner angepasste werden. Z.B Anzahl an zu spawnenden Gegnern oder Farbe, Untertype etc.
        if (type.equals("circle")) {
            //for (int i = 0; i < Integer.parseInt(modifier); i++) {
                game.addCircleEnemy(new CircleEnemy());
            //}
        } else {
            System.out.println("Unbekannter Gegener");
        }
    }
}
