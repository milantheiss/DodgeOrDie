package me.milthe.scoring;

import me.milthe.core.Game;

import java.io.*;

public class Highscore {
    private static long surviedtime;
    private static int surviedenemies;
    private static int highestnumberhealth;

    private File directory;

    private static File highscorelist;

    public Highscore() throws IOException {
        directory = new File(Game.GAMEDATA_DIRECTORY.getPath() + File.separator + "scoring");

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

    public static long getSurviedtime() {
        return surviedtime;
    }

    public static void setSurviedtime(long surviedtime) {
        Highscore.surviedtime = surviedtime;
    }

    public static int getSurviedenemies() {
        return surviedenemies;
    }

    public static void setSurviedenemies(int surviedenemies) {
        Highscore.surviedenemies = surviedenemies;
    }

    public static int getHighestnumberhealth() {
        return highestnumberhealth;
    }

    public static void setHighestnumberhealth(int highestnumberhealth) {
        Highscore.highestnumberhealth = highestnumberhealth;
    }

    public static void isHighscoreBigger(long newHighscore, int surviedenemies, int highestnumberhealth) throws IOException {
        if (surviedtime < newHighscore) {
            System.out.println("New Highscore");
            setSurviedtime(newHighscore);
            setSurviedenemies(surviedenemies);
            setHighestnumberhealth(highestnumberhealth);
            System.out.println(newHighscore);
            writeHighscore();
        } else {
            System.out.println("No new Highscore");
        }
    }

    public static void writeHighscore() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(highscorelist));
        bufferedWriter.write("" + surviedtime);
        bufferedWriter.newLine();
        bufferedWriter.write("" + surviedenemies);
        bufferedWriter.newLine();
        bufferedWriter.write("" + highestnumberhealth);
        bufferedWriter.close();
    }
}
