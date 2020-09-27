package view.units;

import model.units.PlayerModel;
import view.gamestate.GameStateManager;
import view.tilemap.Tile;
import view.tilemap.TileMap;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerView extends UnitView {

    private final PlayerModel playerModel;

    private final GameStateManager gsm;

    private boolean damaged;
    private long damageTimer;

    private ArrayList<BufferedImage[]> sprites;

    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int DEAD = 2;

    public PlayerView(TileMap tm, GameStateManager gsm) {

        super(tm);

        this.gsm = gsm;
        playerModel = new PlayerModel();

        width = 16;
        height = 16;
        cWidth = 16;
        cHeight = 16;

        facingRight = true;

        try {
            int[] numFrames = {2, 2, 1};
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/sprites/player.png"));
            sprites = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                BufferedImage[] subSprite = new BufferedImage[numFrames[i]];

                for (int j = 0; j < numFrames[i]; j++) {
                    subSprite[j] = spritesheet.getSubimage(j * width, i * height, width, height);
                }

                sprites.add(subSprite);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        currentAction = IDLE;
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(1000);

    }

    @Override
    public PlayerModel getModel() {
        return playerModel;
    }



    private void getNextPosition() {

        if (right) {
            dx += playerModel.getMoveSpeed();
            if (dx > playerModel.getMaxSpeed()) {
                dx = playerModel.getMaxSpeed();
            }
        } else {
            if (dx > 0) {
                dx -= playerModel.getStopSpeed();
                if (dx < 0) {
                    dx = 0;
                }
            } else if (dx < 0) {
                dx += playerModel.getStopSpeed();
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
        if (left) {
            dx -= playerModel.getMoveSpeed();
            if (dx < -playerModel.getMaxSpeed()) {
                dx = -playerModel.getMaxSpeed();
            }
        }

        if (jumping && !falling) {
            dy = playerModel.getJumpStart();
            falling = true;
        }

        if (falling) {

            dy += playerModel.getFallSpeed();

            if (dy > 0) jumping = false;
            if (dy < 0 && !jumping) dy += playerModel.getStopJumpSpeed();

            if (dy > playerModel.getMaxFallSpeed()) dy = playerModel.getMaxFallSpeed();

        }

    }

    public void checkAttack(java.util.List<UnitView> enemies) {
        for (UnitView enemy : enemies) {
            if (intersects(enemy)) {
                hit(enemy.getModel().getDamage());
            }
        }
    }

    public void checkObjectCollision() {

        currCol = (int) x / tileSize;
        currRow = (int) y / tileSize;

        xdest = x + dx;
        ydest = y + dy;

        calculateCorners(x, ydest);
        if (dy < 0) {
            if (tl == Tile.EGG || tr == Tile.EGG) {
                playerModel.setEggCount(playerModel.getEggCount() + 1);
                if (tl == Tile.EGG) {
                    tileMap.getMap().setElement(topTile, leftTile, 0);
                }
                if (tr == Tile.EGG) {
                    tileMap.getMap().setElement(topTile, rightTile, 0);
                }
            } else if (tl == Tile.PORTAL || tr == Tile.PORTAL) {
                if (playerModel.getEggCount() == playerModel.getNeedEggs()) {
                    if (gsm.getCurState() != 4) {
                        gsm.setState(gsm.getCurState() + 1);
                    } else {
                        gsm.setState(3);
                    }
                }
            } else if (tl == Tile.SPIKE || tr == Tile.SPIKE) {
                playerModel.setHealth(0);
            }
        }

        if (dy > 0) {
            if (bl == Tile.EGG || br == Tile.EGG) {
                playerModel.setEggCount(playerModel.getEggCount() + 1);
                if (bl == Tile.EGG) {
                    tileMap.getMap().setElement(bottomTile, leftTile, 0);
                }
                if (br == Tile.EGG) {
                    tileMap.getMap().setElement(bottomTile, rightTile, 0);
                }
            } else if (bl == Tile.PORTAL || br == Tile.PORTAL) {
                if (playerModel.getEggCount() == playerModel.getNeedEggs()) {
                    if (gsm.getCurState() != 4) {
                        gsm.setState(gsm.getCurState() + 1);
                    } else {
                        gsm.setState(3);
                    }
                }
            } else if (bl == Tile.SPIKE || br == Tile.SPIKE) {
                playerModel.setHealth(0);
            }
        }

        calculateCorners(xdest, y);
        if (dx < 0) {
            if (tl == Tile.EGG || bl == Tile.EGG) {
                playerModel.setEggCount(playerModel.getEggCount() + 1);
                if (tl == Tile.EGG) {
                    tileMap.getMap().setElement(topTile, leftTile, 0);
                }
                if (bl == Tile.EGG) {
                    tileMap.getMap().setElement(bottomTile, leftTile, 0);
                }
            } else if (tl == Tile.PORTAL || bl == Tile.PORTAL) {
                if (playerModel.getEggCount() == playerModel.getNeedEggs()) {
                    if (gsm.getCurState() != 4) {
                        gsm.setState(gsm.getCurState() + 1);
                    } else {
                        gsm.setState(3);
                    }
                }
            } else if (tl == Tile.SPIKE || bl == Tile.SPIKE) {
                playerModel.setHealth(0);
            }
        }

        if (dx > 0) {
            if (tr == Tile.EGG || br == Tile.EGG) {
                playerModel.setEggCount(playerModel.getEggCount() + 1);
                if (tr == Tile.EGG) {
                    tileMap.getMap().setElement(topTile, rightTile, 0);
                }
                if (br == Tile.EGG) {
                    tileMap.getMap().setElement(bottomTile, rightTile, 0);
                }
            } else if (tr == Tile.PORTAL || br == Tile.PORTAL) {
                if (playerModel.getEggCount() == playerModel.getNeedEggs()) {
                    if (gsm.getCurState() != 4) {
                        gsm.setState(gsm.getCurState() + 1);
                    } else {
                        gsm.setState(3);
                    }
                }
            } else if (tr == Tile.SPIKE || br == Tile.SPIKE) {
                playerModel.setHealth(0);
            }
        }
    }


    public boolean checkDeath() {
        return playerModel.getHealth() == 0;
    }

    public void hit(int damage) {
        if (damaged) {
            return;
        }
        playerModel.setHealth(playerModel.getHealth() - damage);
        if (playerModel.getHealth() < 0) {
            playerModel.setHealth(0);
        }
        if (playerModel.getHealth() == 0) {
            playerModel.setDead(true);
        }
        damaged = true;
        damageTimer = System.currentTimeMillis();
    }


    public void update() {
        if (playerModel.getHealth() == 0) {
            if (currentAction != DEAD) {
                currentAction = DEAD;
                animation.setFrames(sprites.get(DEAD));
                animation.setDelay(100);
                width = 16;
                height = 16;
                cWidth = 16;
                cHeight = 16;
            }
        } else {
            getNextPosition();
            try {
                checkMapCollision();
                checkObjectCollision();
            } catch (Exception e) {
                playerModel.setHealth(0);
            }
            setPosition(xtemp, ytemp);

            if (damaged) {
                long elapsed = System.currentTimeMillis() - damageTimer;
                if (elapsed > 1000) {
                    damaged = false;
                }
            }

            if (right || left) {
                if (currentAction != WALKING) {
                    currentAction = WALKING;
                    animation.setFrames(sprites.get(WALKING));
                    animation.setDelay(100);
                    width = 16;
                    height = 16;
                    cWidth = 16;
                    cHeight = 16;
                }
            } else {
                if (currentAction != IDLE) {
                    currentAction = IDLE;
                    animation.setFrames(sprites.get(IDLE));
                    animation.setDelay(1000);
                    width = 16;
                    height = 16;
                    cWidth = 16;
                    cHeight = 16;
                }
            }

            animation.update();

            if (right) {
                facingRight = true;
            }
            if (left) {
                facingRight = false;
            }
        }

    }

    public void draw(Graphics g) {
        setMapPosition();

        if (damaged) {
            long elapsed = System.currentTimeMillis() - damageTimer;
            if (elapsed / 100 % 2 == 0) {
                return;
            }
        }

        super.draw(g);
    }

}