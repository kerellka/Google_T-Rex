package view.gamestate;

import model.units.PlayerModel;
import view.GameBoard;
import view.units.*;
import view.tilemap.Background;
import view.tilemap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Level2State extends GameState {

    private PlayerView player;

    private java.util.List<UnitView> enemies;

    private TileMap tileMap;
    private Background bg;
    private HUD hud;

    private Font entry;


    public Level2State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        tileMap = new TileMap("/tileset/tileset_16pxNEW.png",
                "/map/map2.txt", true);
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);

        enemies = new LinkedList<>();

        player = new PlayerView(tileMap, gsm);
        player.setPosition(50, 100);
        PlayerModel playerModel = player.getModel();
        playerModel.setNeedEggs(tileMap.getEggNum());

        CactusView cactus = new CactusView(tileMap);
        cactus.setPosition(200, 200);

        PterodactylView ptero = new PterodactylView(tileMap);
        ptero.setPosition(200, 200);

        enemies.add(cactus);
        enemies.add(ptero);

        bg = new Background("/backgrounds/fon_lvl.gif", 0.1);

        entry = new Font("Press Start 2P", Font.PLAIN, 7);

        hud = new HUD(player);
    }

    @Override
    public void update() {
        player.checkAttack(enemies);
        player.update();

        for (UnitView enemy : enemies) {
            enemy.update();
        }

        tileMap.setPosition(GameBoard.WIDTH / 2.0 - player.getX(),
                GameBoard.HEIGHT / 2.0 - player.getY());
    }

    @Override
    public void draw(Graphics g) {
        bg.draw(g);

        if (player.getX() >= 0 && player.getX() <= 100 && player.getY() <= 235) {
            g.setFont(entry);
            g.drawString("Avoid cacti and pterodactyls", 50, 100);
            g.setColor(Color.BLACK);
        }

        tileMap.draw(g);
        player.draw(g);
        for (UnitView enemy : enemies) {
            enemy.draw(g);
        }
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
                gsm.restartState(GameStateManager.LEVEL2STATE);
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
