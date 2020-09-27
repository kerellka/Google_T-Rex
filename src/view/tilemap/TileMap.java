package view.tilemap;

import model.map.Map;
import view.GameBoard;
import view.units.CactusView;
import view.units.PlayerView;
import view.units.PterodactylView;
import view.units.UnitView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TileMap {

    private double x;
    private double y;

    private double xmin;
    private double ymin;
    private double xmax;
    private double ymax;

    private double tween;

    private Map map;

    private Tile[][] tiles;

    private int rowOffset;
    private int colOffset;
    private final int rowsToDraw;
    private final int colsToDraw;

    private final boolean isResource;

    public static final int TILESIZE = 16;

    public TileMap(String tilesetFile, String mapFile, boolean isResource) {
        this.isResource = isResource;
        loadTileset(tilesetFile);
        loadMap(mapFile);
        rowsToDraw = GameBoard.HEIGHT / TILESIZE + 4;
        colsToDraw = GameBoard.WIDTH / TILESIZE + 4;
    }

    private void loadTileset(String filename) {
        try {
            BufferedImage tileset = ImageIO.read(getClass().getResourceAsStream(filename));
            int numCols = tileset.getWidth() / TILESIZE;
            int numRows = tileset.getHeight() / TILESIZE;
            tiles = new Tile[numRows][numCols];

            BufferedImage tile;

            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    tile = tileset.getSubimage(j * TILESIZE, i * TILESIZE, TILESIZE, TILESIZE);
                    tiles[i][j] = new Tile(tile, Tile.AVAILABLE + i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMap(String filename) {
        try {
            Scanner input;
            if (isResource) {
                input = new Scanner(getClass().getResourceAsStream(filename));
            } else {
                input = new Scanner(new FileReader(filename));
            }
            int row = Integer.parseInt(input.nextLine());
            int col = Integer.parseInt(input.nextLine());
            map = new Map(row, col);

            xmin = GameBoard.WIDTH - (col * TILESIZE);
            xmax = 0;
            ymin = GameBoard.HEIGHT - (row * TILESIZE);
            ymax = 0;

            for (row = 0; row < map.getNumRows(); row++) {
                if (input.hasNext()) {
                    StringTokenizer strTok = new StringTokenizer(input.nextLine(), " ");
                    if (strTok.hasMoreTokens()) {
                        for (col = 0; col < map.getNumCols(); col++) {
                            map.setElement(row, col, Integer.parseInt(strTok.nextToken()));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getTileSize() {
        return TILESIZE;
    }

    public Map getMap() {
        return map;
    }

    public int getType(int row, int col) {
        int element = map.getElement(row, col);
        if (element == 0) {
            return Tile.AVAILABLE;
        }
        return tiles[element / 10 - 1][element % 10 - 1].getType();
    }

    public int getEggNum() {
        int counter = 0;
        for (int row = 0; row < map.getNumRows(); row++) {
            for (int col = 0; col < map.getNumCols(); col++) {
                if(map.getElement(row, col) == 32) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public void setPosition(double x, double y) {
        this.y += (y - this.y) * tween;
        this.x += (x - this.x) * tween;

        checkBounds();

        colOffset = (int) -this.x / TILESIZE;
        rowOffset = (int) -this.y / TILESIZE;
    }

    public void setTween(double tween) {
        this.tween = tween;
    }

    public void setUnitPosition(PlayerView player, java.util.List<UnitView> enemies) {
        java.util.List<CactusView> enemiesCactus = new LinkedList<>();
        java.util.List<PterodactylView> enemiesPtero = new LinkedList<>();

        for (int row = 0; row < map.getNumRows(); row++) {
            for (int col = 0; col < map.getNumCols(); col++) {
                if(map.getElement(row, col) == 55) {
                    player.setPosition(col * TILESIZE, row * TILESIZE);
                    map.setElement(row, col, 0);
                }
                if (map.getElement(row, col) == 46) {
                    CactusView cactus = new CactusView(this);
                    cactus.setPosition(col * TILESIZE, row * TILESIZE);
                    enemiesCactus.add(cactus);
                    map.setElement(row, col, 0);
                }
                if (map.getElement(row, col) == 45) {
                    PterodactylView ptero = new PterodactylView(this);
                    ptero.setPosition(col * TILESIZE, row * TILESIZE);
                    enemiesPtero.add(ptero);
                    map.setElement(row, col, 0);
                }
            }
        }
        enemies.addAll(enemiesCactus);
        enemies.addAll(enemiesPtero);
    }

    private void checkBounds() {
        if (x < xmin) {
            x = xmin;
        }
        if (y < ymin) {
            y = ymin;
        }
        if (x > xmax) {
            x = xmax;
        }
        if (y > ymax) {
            y = ymax;
        }
    }

    public void draw(Graphics g) {
        for (int row = rowOffset; row < rowsToDraw + rowOffset; row++) {
            if (row >= map.getNumRows()) {
                break;
            }
            for (int col = colOffset; col < colsToDraw + colOffset; col++) {
                if (col >= map.getNumCols()) {
                    break;
                }
                if (map.getElement(row, col) == 0 || map.getElement(row, col) == 45 ||
                        map.getElement(row, col) == 46 || map.getElement(row, col) == 55) {
                    continue;
                }
                int firstInd = map.getElement(row, col) / 10 - 1;
                int secondInd = map.getElement(row, col) % 10 - 1;
                g.drawImage(tiles[firstInd][secondInd].getImage(), (int) x + (col * TILESIZE),
                        (int) y + (row * TILESIZE), null);
            }
        }
    }
}
