package view.units;

import model.units.PlayerModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HUD {

    private final PlayerView player;

    private BufferedImage[] hearts;
    private BufferedImage egg;

    public HUD(PlayerView p) {
        player = p;
        int width = 16;
        int height = 16;
        try {
            hearts = new BufferedImage[6];
            BufferedImage spriteHearts = ImageIO.read(getClass().getResourceAsStream("/hud/hearts.png"));
            for (int i = hearts.length - 1; i >= 0; i--) {
                hearts[i] = spriteHearts.getSubimage(0, height * (5 - i), width * 5, height);
            }

            egg = ImageIO.read(getClass().getResourceAsStream("/hud/egg.png"));
            egg = egg.getSubimage(0, 0, width, height);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {

        PlayerModel playerModel = player.getModel();
        String eggCount = Integer.toString(playerModel.getEggCount());

        g.drawImage(hearts[playerModel.getHealth()], 0, 0, hearts[playerModel.getHealth()].getWidth(),
                hearts[playerModel.getHealth()].getHeight(), null);

        g.drawImage(egg, 0, 16, egg.getWidth(), egg.getHeight(), null);

        g.drawString(eggCount, 20, 32);

    }

}
