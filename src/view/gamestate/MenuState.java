package view.gamestate;

import view.tilemap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

    private Background bg;

    private int curChoice = 0;
    private final String[] options = {"Start", "Editor lvl", "Quit"};

    private final Color titleColor;
    private final Font titleFont;

    private final Font font;


    public MenuState(GameStateManager gsm) {

        this.gsm = gsm;

        bg = new Background("/backgrounds/fon.gif", 1);
        bg.setVector(-0.4, 0);

        titleColor = Color.BLACK;
        titleFont = new Font("Press Start 2P", Font.BOLD, 16);

        font = new Font("Press Start 2P", Font.PLAIN, 12);

    }

    public void init() {
    }

    public void update() {
        bg.update();
    }

    public void draw(Graphics g) {
        bg.draw(g);

        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Google T-Rex", 60, 70);

        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == curChoice) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(new Color(218, 218, 218));
            }
            g.drawString(options[i], 120, 140 + i * 15);
        }

    }

    private void select() {
        if (curChoice == 0) {
            gsm.setState(GameStateManager.LEVEL1STATE);
        }
        if (curChoice == 1) {
            gsm.setState(GameStateManager.EDITORLEVEL);
        }
        if (curChoice == 2) {
            System.exit(0);
        }
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            curChoice--;
            if (curChoice == -1) {
                curChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            curChoice++;
            if (curChoice == options.length) {
                curChoice = 0;
            }
        }
    }

    public void keyReleased(int k) {
    }

}