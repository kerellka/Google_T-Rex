package view.gamestate;

public abstract class GameState {

    protected GameStateManager gsm;


    public abstract void init();
    public abstract void update();
    public abstract void draw(java.awt.Graphics g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);

}