package view.units;

import model.units.CactusModel;
import view.tilemap.Tile;
import view.tilemap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Objects;

public class CactusView extends UnitView {


    private final CactusModel cactus;
    private BufferedImage[] sprites;

    public CactusView(TileMap tm) {

        super(tm);


        cactus = new CactusModel();
        //model = cactus;

        width = 16;
        height = 16;
        cWidth = 16;
        cHeight = 16;

        // load sprites
        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/sprites/enemies/cactus.png"));

            sprites = new BufferedImage[2];
            for(int i = 0; i < sprites.length; i++) {
                sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(300);

        right = true;
        facingRight = true;

    }

    private void getNextPosition() {

        if(left) {
            dx -= cactus.getMoveSpeed();
            if(dx < -cactus.getMaxSpeed()) {
                dx = -cactus.getMaxSpeed();
            }
        }
        else if(right) {
            dx += cactus.getMoveSpeed();
            if(dx > cactus.getMaxSpeed()) {
                dx = cactus.getMaxSpeed();
            }
        }

        if (falling) {

            dy += cactus.getFallSpeed();

            if (dy > 0) jumping = false;
            if (dy < 0 && !jumping) dy += cactus.getStopJumpSpeed();

            if (dy > cactus.getMaxFallSpeed()) dy = cactus.getMaxFallSpeed();

        }

    }

    public void update() {

        getNextPosition();
        checkMapCollision();
        setPosition(xtemp, ytemp);

        if (right && !(br == Tile.BLOCK)) {
            dx = -10;
            right = false;
            left = true;
            facingRight = false;
        }
        if (left && !(bl == Tile.BLOCK)) {
            dx += 10;
            right = true;
            left = false;
            facingRight = true;
        }
        if(right && dx == 0) {
            right = false;
            left = true;
            facingRight = false;
        }
        else if(left && dx == 0) {
            right = true;
            left = false;
            facingRight = true;
        }

        animation.update();

    }

    @Override
    public CactusModel getModel() {
        return cactus;
    }

    public void draw(Graphics g) {

        setMapPosition();

        super.draw(g);

    }
}
