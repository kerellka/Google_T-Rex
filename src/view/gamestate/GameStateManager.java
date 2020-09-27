package view.gamestate;

import java.util.ArrayList;

public class GameStateManager {

    private final ArrayList<GameState> gameStates;
    private int curState;

    public static final int MENUSTATE = 0;
    public static final int LEVEL1STATE = 1;
    public static final int LEVEL2STATE = 2;
    public static final int FINISHSTATE = 3;
    public static final int EDITORLEVEL = 4;


    public GameStateManager() {

        gameStates = new ArrayList<>();

        curState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new Level1State(this));
        gameStates.add(new Level2State(this));
        gameStates.add(new FinishState(this));
        gameStates.add(new EditorLevel(this));

    }

    public int getCurState() {
        return curState;
    }

    public void setState(int state) {
        curState = state;
        gameStates.get(state).init();
    }

    public void restartState(int state) {
        curState = state;
        if (state == LEVEL1STATE) {
            gameStates.set(state, new Level1State(this));
        }
        if (state == LEVEL2STATE) {
            gameStates.set(state, new Level2State(this));
        }
        if (state == FINISHSTATE) {
            gameStates.set(state, new FinishState(this));
        }
        if (state == MENUSTATE) {
            gameStates.set(state, new MenuState(this));
        }
    }

    public void restartEditor(int state, String filename) {
        curState = state;
        gameStates.set(state, new EditorLevel(this, true, filename));
    }

    public void update() {
        gameStates.get(curState).update();
    }

    public void draw(java.awt.Graphics g) {
        gameStates.get(curState).draw(g);
    }

    public void keyPressed(int key) {
        gameStates.get(curState).keyPressed(key);
    }

    public void keyReleased(int key) {
        gameStates.get(curState).keyReleased(key);
    }

}









