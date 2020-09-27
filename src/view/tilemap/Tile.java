package view.tilemap;

import java.awt.image.BufferedImage;

public class Tile {

    private final BufferedImage image;
    private final int type;

    public static final int AVAILABLE = 0;
    public static final int BLOCK = 1;
    public static final int EGG = 2;
    public static final int SPIKE = 3;
    public static final int PORTAL = 4;

    public Tile(BufferedImage image, int type) {
        this.image = image;
        this.type = type;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getType() {
        return type;
    }
}
