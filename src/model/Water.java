package model;

import audio.Audio;
import enumeration.GameLevel;
import enumeration.Status;
import view.Level;

/**
 * A class used to model water marks in the game.
 */
public class Water extends Universe {

    public Water(int coordX, int coordY, int width, int height, String img) {
        super(coordX, coordY, width, height, img);
    }

    // collision with actors. actor drowns
    public void collision(Actor actor) {
        if ((this.getStatus() == Status.ALIVE && actor.getStatus() == Status.ALIVE && actor.getHeight() + actor.getCoordY() == this.getCoordY()) &&
                ((actor.getCoordX() + actor.getWidth() < this.getCoordX() + this.getWidth() + 10 &&
                        actor.getCoordX() >= this.getCoordX() - 3))) {
            GameLevel level = Level.getLevel();
            if (level == GameLevel.GroundLevel || level == GameLevel.MediumLevel) {
                Audio.stop();
                Audio.playSound("/sounds/nario_dies_sound.wav");
                //actor.setStatus(Status.DEAD);
            }
        }
    }

}
