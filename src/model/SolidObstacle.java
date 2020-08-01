package model;

import audio.Audio;
import controllers.Keyboard;
import enumeration.JumpState;
import enumeration.UniverseState;
import view.baseGame.BaseLevel;

/**
 * a class for solid obstacles
 */
public class SolidObstacle extends Obstacle {
    private static SolidObstacle jumpObstacle;
    private static int key_id;

    public SolidObstacle(int coordX, int coordY, int width, int height, String img) {
        super(coordX, coordY, width, height, img);

    }

    public static int getKey_id() {
        return key_id;
    }

    public static void setKey_id(int key_id) {
        SolidObstacle.key_id = key_id;
    }

    public static SolidObstacle getJumpObstacle() {
        return jumpObstacle;
    }

    public static void setJumpObstacle(SolidObstacle jumpObstacle) {
        SolidObstacle.jumpObstacle = jumpObstacle;
    }

    // collision method to react to collision between a solid obstacle and an actor: proximity
    public void collideFloorJump(Actor actor) {

        if (actor.getHeight() + actor.getCoordY() > this.getCoordY() && this.getCoordY() + this.getHeight() > actor.getCoordY()) {
            // collision sound
            Audio.stop();
            Audio.playSound("/sounds/bump_sound.wav");
            // block movement of the screen
            BaseLevel.setMove(false);
            // get the value of the key which caused the blocking
            setKey_id(Keyboard.getKey_value());
            switch (actor.getState()) {
                case WalkRight:
                    actor.setState(UniverseState.StillRight);
                    break;
                case WalkLeft:
                    actor.setState(UniverseState.StillLeft);
                    break;
                default:
                    break;
            }
        }
        if (actor.getJumpState() == JumpState.JumpRight || actor.getJumpState() == JumpState.JumpLeft) {
            jumpObstacle = this;
        }
    }

    // a method which detects collision between an actor and the top of the obstacle
    public void collideTop(Actor actor) {
        // now the floor of the actor is the top of the pipe or brick
        actor.setFloor(this.getCoordY());
    }
}


