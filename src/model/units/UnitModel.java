package model.units;

public abstract class UnitModel {

    protected double moveSpeed;
    protected double maxSpeed;
    protected double stopSpeed;
    protected double fallSpeed;
    protected double maxFallSpeed;
    protected double jumpStart;
    protected double stopJumpSpeed;
    protected int damage;


    public int getDamage() {
        return damage;
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

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof UnitModel)) {return false; }
        UnitModel unitModel = (UnitModel) o;
        return Double.compare(unitModel.moveSpeed, moveSpeed) == 0 &&
                Double.compare(unitModel.maxSpeed, maxSpeed) == 0 &&
                Double.compare(unitModel.stopSpeed, stopSpeed) == 0 &&
                Double.compare(unitModel.fallSpeed, fallSpeed) == 0 &&
                Double.compare(unitModel.maxFallSpeed, maxFallSpeed) == 0 &&
                Double.compare(unitModel.jumpStart, jumpStart) == 0 &&
                Double.compare(unitModel.stopJumpSpeed, stopJumpSpeed) == 0 &&
                damage == unitModel.damage;
    }
}

















