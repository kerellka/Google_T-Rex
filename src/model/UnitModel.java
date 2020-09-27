package model;

public class UnitModel {

    // movement attributes
    protected double moveSpeed;
    protected double maxSpeed;
    protected double stopSpeed;
    protected double fallSpeed;
    protected double maxFallSpeed;
    protected double jumpStart;
    protected double stopJumpSpeed;

    public UnitModel() {

    }

    public void setFallSpeed(double fallSpeed) {
        this.fallSpeed = fallSpeed;
    }

    public void setJumpStart(double jumpStart) {
        this.jumpStart = jumpStart;
    }

    public void setMaxFallSpeed(double maxFallSpeed) {
        this.maxFallSpeed = maxFallSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void setStopJumpSpeed(double stopJumpSpeed) {
        this.stopJumpSpeed = stopJumpSpeed;
    }

    public void setStopSpeed(double stopSpeed) {
        this.stopSpeed = stopSpeed;
    }

    public double getFallSpeed() {
        return fallSpeed;
    }

    public double getJumpStart() {
        return jumpStart;
    }

    public double getMaxFallSpeed() {
        return maxFallSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public double getStopJumpSpeed() {
        return stopJumpSpeed;
    }

    public double getStopSpeed() {
        return stopSpeed;
    }
}

















