package model.map;

public class Map {

    private int[][] map;
    private int numRows;
    private int numCols;


    public Map(int rows, int cols) {
        map = new int[rows][cols];

        this.numRows = rows;
        this.numCols = cols;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getNumRows() {
        return numRows;
    }

    public int[][] getMatrix() {
        return map;
    }

    public int getElement(int row, int col) {
        return map[row][col];
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public void setElement(int row, int col, int value) {
        map[row][col] = value;
    }
}
