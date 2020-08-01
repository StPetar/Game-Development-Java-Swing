package model;

/**
 * the base class for all obstacles
 */
public abstract class Obstacle extends Universe {

    public Obstacle(int coordX, int coordY, int width, int height, String img) {
        super(coordX, coordY, width, height, img);
    }

    // collision between the actor and the obstacle on the floor or in the air
    public abstract void collideFloorJump(Actor actor);

    // collision when the actor collides with the top of the obstacle
    public abstract void collideTop(Actor actor);

    // collision when the actor collides with the bottom of the obstacle
    public void collideBottom(Actor actor) {
    }

}
