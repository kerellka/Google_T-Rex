package model;


public class PlayerModel extends UnitModel {

    // player stuff
    private int health;
    private int maxHealth;
    private boolean dead;


    public PlayerModel() {

        moveSpeed = 0.3;
        maxSpeed = 1.6;
        stopSpeed = 0.4;
        fallSpeed = 0.15;
        maxFallSpeed = 4.0;
        jumpStart = -4.8;
        stopJumpSpeed = 0.3;

        health = maxHealth = 5;

    }

    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }


}