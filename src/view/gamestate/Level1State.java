package view.gamestate;

import model.units.PlayerModel;
import view.GameBoard;
import view.units.*;
import view.tilemap.Background;
import view.tilemap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Level1State extends GameState {

    private Background bg;
    private Font entry;

    private TileMap tileMap;

    private PlayerView player;

    private HUD hud;


    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        tileMap = new TileMap("/tileset/tileset_16pxNEW.png",
                "/map/map1.txt", true);
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);


        player = new PlayerView(tileMap, gsm);
        player.setPosition(50, 200);
        PlayerModel playerModel = player.getModel();
        playerModel.setNeedEggs(tileMap.getEggNum());


        bg = new Background("/backgrounds/fon_lvl.gif", 0.1);

        entry = new Font("Press Start 2P", Font.PLAIN, 7);

        hud = new HUD(player);
    }

    @Override
    public void update() {
        player.update();

        tileMap.setPosition(GameBoard.WIDTH / 2.0 - player.getX(),
                GameBoard.HEIGHT / 2.0 - player.getY());
    }

    @Override
    public void draw(Graphics g) {
        bg.draw(g);


        if (player.getX() >= 0 && player.getX() <= 150) {
            g.setFont(entry);
            g.drawString("Use arrows to move T-Rex", 40, 100);
            g.drawString("Collect all eggs and enter the portal", 40, 120);
            g.drawString("to finish level", 40, 130);
            g.setColor(Color.BLACK);
        }

        tileMap.draw(g);
        player.draw(g);
        hud.draw(g);
        if (player.checkDeath()) {
            g.drawString("You are dead", 100, 100);
            g.drawString("Press ENTER to restart", 100, 120);
        }
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
        if (player.checkDeath()) {
            if (key == KeyEvent.VK_ENTER) {
                gsm.restartState(GameStateManager.LEVEL1STATE);
            }
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
