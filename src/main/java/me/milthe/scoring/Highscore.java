package me.milthe.scoring;

import me.milthe.core.Game;
import me.milthe.core.Gamestates;
import java.io.*;

/**
 * Organisiert den Highscore
 */
public class Highscore {
    private static long surviedtime;
    private static int surviedenemies;
    private static int highestnumberhealth;

    private File directory;

    private static File highscorelist;

    private static boolean newHighscore;

    /**
     * Erstellt beim erstellen eines neuen Highscore, falls nicht vorhanden, einen neuen Ordner "scoring" im Gamedirectory
     * @throws IOException
     */
    public Highscore() throws IOException {
        directory = new File(Game.getGameDirectory().getPath() + File.separator + "scoring");

        directory.mkdir();

        highscorelist = new File(directory.getPath() + File.separator + "highscore.txt");

        highscorelist.createNewFile();

        String temp;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(highscorelist))) {
            surviedtime = ((temp = bufferedReader.readLine()) != null) ? Long.parseLong(temp) : 0;
            surviedenemies = ((temp = bufferedReader.readLine()) != null) ? Integer.parseInt(temp) : 0;
            highestnumberhealth = ((temp = bufferedReader.readLine()) != null) ? Integer.parseInt(temp) : 0;
        }
        System.out.println(surviedtime + " " + surviedenemies + " " + highestnumberhealth);
    }

    /**
     * Gibt den Highscore für Zeit zurück
     * @return Highscore Zeit
     */
    public static long getSurviedtime() {
        return surviedtime;
    }

    /**
     * Setzt den Highscore für Zeit
     * @param surviedtime Neuer Highscore für Zeit
     */
    public static void setSurviedtime(long surviedtime) {
        Highscore.surviedtime = surviedtime;
    }

    /**
     * Gibt zurück, wie viele Gegner während des Highscore Runs überlebt wurden
     * @return Überlebte Gegner im Highscore Run
     */
    public static int getSurviedenemies() {
        return surviedenemies;
    }

    /**
     * Setzt die überlebten Gegner während eines Highscore Runs neu
     * @param surviedenemies Neue Anzahl überlebte Gegner
     */
    public static void setSurviedenemies(int surviedenemies) {
        Highscore.surviedenemies = surviedenemies;
    }

    /**
     * Gibt die höchste Zahl an Leben während eines Highscore Runs zurück
     * @return Meisten Leben während eines Highscore Runs
     */
    public static int getHighestnumberhealth() {
        return highestnumberhealth;
    }

    /**
     * Setzt die höchste Zahl an Leben während eines Highscore Runs neu
     * @param highestnumberhealth Neu Zahl für Leben
     */
    public static void setHighestnumberhealth(int highestnumberhealth) {
        Highscore.highestnumberhealth = highestnumberhealth;
    }

    /**
     * Testet ob die erreichte Zeit ein neuerer Highscore ist
     * @param newHighscore Erreichte Zeit
     * @param surviedenemies Überlebte Gegner während des Runs
     * @param highestnumberhealth Höchste Zahl an Leben während des Runs
     * @throws IOException
     */
    public static boolean isHighscoreBigger(long newHighscore, int surviedenemies, int highestnumberhealth) throws IOException {
        if (surviedtime < newHighscore) {
            if (Game.getGamestate() == Gamestates.ENDSCREEN) Game.getJukebox().playSoundEffect("sfx_highscore");
            setSurviedtime(newHighscore);
            setSurviedenemies(surviedenemies);
            setHighestnumberhealth(highestnumberhealth);
            System.out.println(newHighscore);
            writeHighscore();
            return true;
        } else {
            if (Game.getGamestate() == Gamestates.ENDSCREEN) Game.getJukebox().playSoundEffect("sfx_gameover");
            return false;
        }
    }

    /**
     * Schreibt den abgespeicherten Highscore um
     * @throws IOException
     */
    public static void writeHighscore() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(highscorelist));
        bufferedWriter.write("" + surviedtime);
        bufferedWriter.newLine();
        bufferedWriter.write("" + surviedenemies);
        bufferedWriter.newLine();
        bufferedWriter.write("" + highestnumberhealth);
        bufferedWriter.close();
    }

    /**
     * Gibt an ob ein neuer Highscore erreicht wurde
     * @return true wenn newHighscore auf true gesetzt wurde
     */
    public static boolean isNewHighscore() {
        return newHighscore;
    }

    /**
     * Setzt newHighscore um
     * @param newHighscore true wenn es einen neuen Highscore gibt
     */
    public static void setNewHighscore(boolean newHighscore) {
        Highscore.newHighscore = newHighscore;
    }
}
