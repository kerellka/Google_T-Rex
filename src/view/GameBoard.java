package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JPanel;

import view.gamestate.GameStateManager;

public class GameBoard extends JPanel
        implements Runnable, KeyListener {

    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 3;

    private Thread thread;
    private boolean isRunning;
    private final int FPS = 60;

    private BufferedImage image;
    private Graphics g;

    private GameStateManager gsm;

    public GameBoard() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();
        gsm = new GameStateManager();

        isRunning = true;
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    public void run() {

        long start;
        long elapsed;
        long wait;
        long targetTime = 1000 / FPS;

        init();

        while (isRunning) {
            start = System.currentTimeMillis();

            update();
            render();
            paintOnComponent();

            elapsed = System.currentTimeMillis() - start;
            wait = targetTime - elapsed;

            if (wait <= 0) {
                wait = 5;
            }

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        gsm.update();
    }

    private void render() {
        gsm.draw(g);
    }

    public void paintOnComponent() {
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        g.dispose();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        gsm.keyPressed(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        gsm.keyReleased(e.getKeyCode());
    }

}