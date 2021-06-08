package me.milthe.ui;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GIF {
    private List<Image> frames = new ArrayList<>();
    private int width, height, xPos, yPos;
    private int currentFrameIndex;
    private final int millisecPerFrame;
    private long startTime;
    private boolean playing;

    public GIF(int numberOfFrames, String folderpath, int millisecPerFrame) {
        this.millisecPerFrame = millisecPerFrame;
        for (int i = 0; i < numberOfFrames; i++) {
            addFrame(folderpath + i + ".png");
        }
    }

    public void addFrame(String filepath) {
        frames.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(filepath))));
        width = (frames.get(frames.size() - 1).getWidth() > width) ? (int) Math.round(frames.get(frames.size() - 1).getWidth()) : width;
        height = (frames.get(frames.size() - 1).getHeight() > height) ? (int) Math.round(frames.get(frames.size() - 1).getHeight()) : height;
    }

    public List<Image> getFrames() {
        return frames;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getFrame(int index) {
        return frames.get(index);
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    public void playGIF(long startTime, int xPos, int yPos) {
        currentFrameIndex = 0;
        this.xPos = xPos;
        this.yPos = yPos;
        this.startTime = startTime;
        playing = true;
    }

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

    public boolean isPlaying() {
        return playing;
    }
}
