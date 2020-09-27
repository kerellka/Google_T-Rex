package view.gamestate;

import model.units.PlayerModel;
import view.GameBoard;
import view.tilemap.Background;
import view.tilemap.TileMap;
import view.units.HUD;
import view.units.PlayerView;
import view.units.UnitView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedList;

public class EditorLevel extends GameState{

    private Background bg;
    private Font entry;

    private TileMap tileMap;

    private PlayerView player;
    private java.util.List<UnitView> enemies;

    private String filename;
    private boolean isInit;

    private HUD hud;

    public EditorLevel(GameStateManager gsm) {
        this.gsm = gsm;
        filename = null;
        isInit = false;
    }

    public EditorLevel(GameStateManager gsm, boolean isInit, String filename) {
        this.gsm = gsm;
        this.filename = filename;
        this.isInit = isInit;
        init();
    }

    public void chooseFile() {
        JFileChooser fileopen = new JFileChooser("C:\\Users\\Zip70\\Desktop\\editorLvl");
        int ret = fileopen.showDialog(null, "Оpen file");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            filename = file.getPath();
        }
    }

    @Override
    public void init() {
        if (!isInit) {
            chooseFile();
        }

        tileMap = new TileMap("/tileset/tileset_16pxNEW.png",
                filename, false);
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);

        enemies = new LinkedList<>();

        player = new PlayerView(tileMap, gsm);
        player.setPosition(50, 200);
        PlayerModel playerModel = player.getModel();
        playerModel.setNeedEggs(tileMap.getEggNum());

        tileMap.setUnitPosition(player, enemies);

        bg = new Background("/backgrounds/fon_lvl.gif", 0.1);

        entry = new Font("Press Start 2P", Font.PLAIN, 7);

        hud = new HUD(player);
        isInit = true;
    }

    @Override
    public void update() {
        if (isInit) {
            player.update();
            player.checkAttack(enemies);

            for (UnitView enemy : enemies) {
                enemy.update();
            }

            tileMap.setPosition(GameBoard.WIDTH / 2.0 - player.getX(),
                    GameBoard.HEIGHT / 2.0 - player.getY());
        }
    }

    @Override
    public void draw(Graphics g) {
        if (isInit) {
            bg.draw(g);

            for (UnitView enemy : enemies) {
                enemy.draw(g);
            }

            g.setFont(entry);
            g.setColor(Color.BLACK);

            tileMap.draw(g);
            player.draw(g);
            hud.draw(g);
            if (player.checkDeath()) {
                g.drawString("You are dead", 100, 100);
                g.drawString("Press ENTER to restart", 100, 120);
            }
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
                gsm.restartEditor(GameStateManager.EDITORLEVEL, filename);
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
