package me.milthe.ui;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Ermöglicht das Abspielen von Animationen
 */
public class GIF {
    private List<Image> frames = new ArrayList<>();
    private int width, height, xPos, yPos;
    private int currentFrameIndex;
    private final int millisecPerFrame;
    private long startTime;
    private boolean playing;

    /**
     * Erstellt neues GIF aus gegebenen Bilder in folderpath. Bilder müssen im Format [INDEX].png benannt sein.
     * @param numberOfFrames Gesamt Zahl an Bildern des GIFs
     * @param folderpath Dateipfad zu Bildern
     * @param millisecPerFrame Gibt an wie viele Millisekunden lang ein Frame angezeigt werden soll
     */
    public GIF(int numberOfFrames, String folderpath, int millisecPerFrame) {
        this.millisecPerFrame = millisecPerFrame;
        for (int i = 0; i < numberOfFrames; i++) {
            addFrame(folderpath + i + ".png");
        }
    }

    /**
     * Fügt neuen Frame in frames hinzu
     * @param filepath Dateipfad zum Bild
     */
    public void addFrame(String filepath) {
        frames.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(filepath))));
        width = (frames.get(frames.size() - 1).getWidth() > width) ? (int) Math.round(frames.get(frames.size() - 1).getWidth()) : width;
        height = (frames.get(frames.size() - 1).getHeight() > height) ? (int) Math.round(frames.get(frames.size() - 1).getHeight()) : height;
    }

    /**
     * Gibt Breite des GIFs zurück
     * @return Breite des GIFs
     */
    public int getWidth() {
        return width;
    }
    /**
     * Gibt Höhe des GIFs zurück
     * @return Höhe des GIFs
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gibt einzelnen Frame an Position index in GIF zurück
     * @param index Position des Frame in GIF
     * @return Bild an Position index in GIF
     */
    public Image getFrame(int index) {
        return frames.get(index);
    }

    /**
     * Gibt xPosition des GIFs zurück
     * @return xPosition des GIFs
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Gibt yPosition des GIFs zurück
     * @return yPosition des GIFs
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * Gibt Position des des momentan abgespielten Frames in GIF zurück
     * @return Position des des momentan abgespielten Frames in GIF
     */
    public int getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    /**
     * Spielt das GIF ab
     * @param xPos xPosition an das GIF abgespielt werden soll
     * @param yPos yPosition an das GIF abgespielt werden soll
     */
    public void playGIF(int xPos, int yPos) {
        currentFrameIndex = 0;
        this.xPos = xPos;
        this.yPos = yPos;
        this.startTime = System.currentTimeMillis();
        playing = true;
    }

    /**
     * Updatet Eigenschaften des GIF
     */
    public void updateGIF(){
        if ((System.currentTimeMillis() - startTime) >= millisecPerFrame) {
            startTime = System.currentTimeMillis();
            currentFrameIndex++;
            if (currentFrameIndex >= frames.size()) {
                playing = false;
                currentFrameIndex = 0;
            }
        }
    }

    /**
     * Gibt zurück ob das GIF gerade abgespielt wird
     * @return playing
     */
    public boolean isPlaying() {
        return playing;
    }
}
