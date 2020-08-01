package model;

import enumeration.JumpState;
import enumeration.Status;

/**
 * A class for edible obstacles. obstacles that Nario can consume
 */
public abstract class EdibleObstacle extends Obstacle {
    public EdibleObstacle(int coordX, int coordY, int width, int height, String img) {
        super(coordX, coordY, width, height, img);
    }


    @Override
    public void collideTop(Actor actor) {
        if (actor.getCoordY() + actor.getHeight() >= this.getCoordY()) {
            this.setStatus(Status.DEAD);
        }

    }

    @Override
    public void collideBottom(Actor actor) {
        this.setStatus(Status.DEAD);
    }

    public void collide(Actor actor) {
        if ((actor.getCoordY() == this.getCoordY() && actor.getCoordX() == this.getCoordX()) || (actor.getJumpState() == JumpState.None && actor.getCoordY() < this.getCoordY() + this.getHeight())) {
            this.setStatus(Status.DEAD);
        }
    }

}
