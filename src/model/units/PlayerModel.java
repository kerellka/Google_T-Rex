package model.units;


public class PlayerModel extends UnitModel {

    private int health;
    private int maxHealth;
    private int eggCount;
    private int needEggs;
    private boolean dead;


    public PlayerModel() {

        moveSpeed = 0.5;
        maxSpeed = 1.6;
        stopSpeed = 0.4;
        fallSpeed = 0.15;
        maxFallSpeed = 4.0;
        jumpStart = -3.2;
        stopJumpSpeed = 0.3;

        dead = false;

        health = maxHealth = 5;
        eggCount = 0;

    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getEggCount() {
        return eggCount;
    }

    public int getNeedEggs() {
        return needEggs;
    }

    public boolean isDead() {
        return dead;
    }

    public void setEggCount(int eggCount) {
        this.eggCount = eggCount;
    }

    public void setNeedEggs(int needEggs) {
        this.needEggs = needEggs;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}