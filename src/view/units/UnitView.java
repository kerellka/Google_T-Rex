package view.units;

import model.units.UnitModel;
import view.GameBoard;
import view.tilemap.Tile;
import view.tilemap.TileMap;

import java.awt.*;
import java.util.Objects;

public abstract class UnitView {

    protected TileMap tileMap;
    protected int tileSize;
    protected double xmap;
    protected double ymap;

    protected double x;
    protected double y;
    protected double dx;
    protected double dy;

    protected int width;
    protected int height;

    protected int cWidth;
    protected int cHeight;

    protected int currRow;
    protected int currCol;
    protected double xdest;
    protected double ydest;
    protected double xtemp;
    protected double ytemp;

    protected int leftTile;
    protected int rightTile;
    protected int topTile;
    protected int bottomTile;

    protected int tl;
    protected int tr;
    protected int bl;
    protected int br;

    protected Animation animation;
    protected int currentAction;
    protected boolean facingRight;

    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    protected boolean jumping;
    protected boolean falling;

    public abstract void update();

    public abstract UnitModel getModel();

    public UnitView(TileMap tm) {
        tileMap = tm;
        tileSize = tm.getTileSize();
    }

    public boolean intersects(UnitView o) {
        Rectangle r1 = getRectangle();
        Rectangle r2 = o.getRectangle();
        return r1.intersects(r2);
    }

    public void calculateCorners(double x, double y) {

        leftTile = (int) (x - cWidth / 2) / tileSize;
        rightTile = (int) (x + cWidth / 2 - 1) / tileSize;
        topTile = (int) (y - cHeight / 2) / tileSize;
        bottomTile = (int) (y + cHeight / 2 - 1) / tileSize;

        tl = tileMap.getType(topTile, leftTile);
        tr = tileMap.getType(topTile, rightTile);
        bl = tileMap.getType(bottomTile, leftTile);
        br = tileMap.getType(bottomTile, rightTile);


    }

    public void checkMapCollision() {

        currCol = (int) x / tileSize;
        currRow = (int) y / tileSize;

        xdest = x + dx;
        ydest = y + dy;

        xtemp = x;
        ytemp = y;

        calculateCorners(x, ydest);
        if (dy < 0) {
            if (tl == Tile.BLOCK|| tr == Tile.BLOCK) {
                dy = 0;
                ytemp = currRow * tileSize + cHeight / 2.0;
            } else {
                ytemp += dy;
            }
        }
        if (dy > 0) {
            if (bl == Tile.BLOCK || br == Tile.BLOCK) {
                dy = 0;
                falling = false;
                ytemp = (currRow + 1) * tileSize - cHeight / 2.0;
            } else {
                ytemp += dy;
            }
        }

        calculateCorners(xdest, y);
        if (dx < 0) {
            if (tl == Tile.BLOCK || bl == Tile.BLOCK) {
                dx = 0;
                xtemp = currCol * tileSize + cWidth / 2.0;
            } else {
                xtemp += dx;
            }
        }
        if (dx > 0) {
            if (tr == Tile.BLOCK || br == Tile.BLOCK) {
                dx = 0;
                xtemp = (currCol + 1) * tileSize - cWidth / 2.0;
            } else {
                xtemp += dx;
            }
        }

        if (!falling) {
            calculateCorners(x, ydest + 1);
            if (!(bl == Tile.BLOCK) && !(br == Tile.BLOCK)) {
                falling = true;
            }
        }

    }

    public Rectangle getRectangle() {
        return new Rectangle((int) x - cWidth, (int) y - cHeight,
                cWidth, cHeight);
    }

    public int getCurrentAction() {
        return currentAction;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCWidth() {
        return cWidth;
    }

    public int getCHeight() {
        return cHeight;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isJumping() {
        return jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void setMapPosition() {
        xmap = tileMap.getX();
        ymap = tileMap.getY();
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void setDown(boolean b) {
        down = b;
    }

    public void setJumping(boolean b) {
        jumping = b;
    }

    public void draw(Graphics g){
        if (facingRight) {
            g.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - height / 2),
                    null);
        } else {
            g.drawImage(
                    animation.getImage(), (int) (x + xmap + width / 2), (int) (y + ymap - height / 2),
                    -width, height, null);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnitView)) {
            return false;
        }
        UnitView unitView = (UnitView) o;

        return this.getModel().equals(unitView.getModel()) &&
                this.x == unitView.getX() &&
                this.y == unitView.getY();
    }
}
