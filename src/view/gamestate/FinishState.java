package view.gamestate;

import view.tilemap.Background;
import view.tilemap.TileMap;
import view.units.PlayerView;

import java.awt.*;
import java.awt.event.KeyEvent;

public class FinishState extends GameState{

    private Background bg;
    private Font congrats;

    private PlayerView player;
    private TileMap tileMap;


    public FinishState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        tileMap = new TileMap("/tileset/tileset_16pxNEW.png",
                "/map/mapFinish.txt", true);
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);


        player = new PlayerView(tileMap, gsm);
        player.setPosition(160, 200);

        bg = new Background("/backgrounds/fon_lvl.gif", 0.1);

        congrats = new Font("Press Start 2P", Font.PLAIN, 12);
    }

    @Override
    public void update() {
        bg.update();
        player.update();
    }

    @Override
    public void draw(Graphics g) {
        bg.draw(g);
        tileMap.draw(g);
        player.draw(g);

        g.setColor(Color.BLACK);
        g.setFont(congrats);
        g.drawString("Congrats!!!", 90, 100);
        g.drawString("You finished the game!", 40, 120);
        g.drawString("Press Enter", 90, 140);
    }

    @Override
    public void keyPressed(int key) {
        if (key == KeyEvent.VK_ESCAPE) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if (key == KeyEvent.VK_UP) {
            player.setJumping(true);
        }
        if (key == KeyEvent.VK_DOWN) {
            player.setDown(true);
        }
        if (key == KeyEvent.VK_LEFT) {
            player.setLeft(true);
        }
        if (key == KeyEvent.VK_RIGHT) {
            player.setRight(true);
        }
        if (key == KeyEvent.VK_ENTER) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(int key) {
        if (key == KeyEvent.VK_UP) {
            player.setJumping(false);
        }
        if (key == KeyEvent.VK_DOWN) {
            player.setDown(false);
        }
        if (key == KeyEvent.VK_RIGHT) {
            player.setRight(false);
        }
        if (key == KeyEvent.VK_LEFT) {
            player.setLeft(false);
        }
    }
}
