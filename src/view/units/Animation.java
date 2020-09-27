package view.units;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] frames;
    private int currentFrame;

    private long startTime;
    private long delay;

    private boolean playedOnce;

    public Animation() {
        playedOnce = false;
    }

    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.currentTimeMillis();
        playedOnce = false;
    }

    public void setDelay(long d) {
        delay = d;
    }
    public void setFrame(int i) {
        currentFrame = i;
    }

    public void update() {

        if(delay == -1) return;

        long elapsed = System.currentTimeMillis() - startTime;
        if(elapsed > delay) {
            currentFrame++;
            startTime = System.currentTimeMillis();
        }
        if(currentFrame == frames.length) {
            currentFrame = 0;
            playedOnce = true;
        }

    }

    public int getFrame() { return currentFrame; }
    public BufferedImage getImage() { return frames[currentFrame]; }
    public boolean hasPlayedOnce() { return playedOnce; }

}