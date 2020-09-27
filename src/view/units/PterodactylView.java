package view.units;

import model.units.PterodactylModel;
import view.tilemap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PterodactylView extends UnitView {


    private final PterodactylModel ptero;
    private BufferedImage[] sprites;

    public PterodactylView(TileMap tm) {

        super(tm);

        ptero = new PterodactylModel();

        width = 16;
        height = 16;
        cWidth = 16;
        cHeight = 16;

        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/sprites/enemies/ptero.png"));

            sprites = new BufferedImage[2];
            for(int i = 0; i < sprites.length; i++) {
                sprites[i] = spritesheet.getSubimage(width * i, 0, width, height);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(200);

        right = false;
        left = true;
        facingRight = false;

    }

    private void getNextPosition() {

        if(left) {
            dx -= ptero.getMoveSpeed();
            if(dx < -ptero.getMaxSpeed()) {
                dx = -ptero.getMaxSpeed();
            }
        }
        else if(right) {
            dx += ptero.getMoveSpeed();
            if(dx > ptero.getMaxSpeed()) {
                dx = ptero.getMaxSpeed();
            }
        }

    }

    public void update() {
        getNextPosition();
        checkMapCollision();
        setPosition(xtemp, ytemp);

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
    public PterodactylModel getModel() {
        return ptero;
    }

    public void draw(Graphics g) {
        setMapPosition();

        super.draw(g);
    }
}
