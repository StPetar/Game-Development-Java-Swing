package model;

import controllers.BrickFactory;
import controllers.WaterFactory;
import enumeration.GameLevel;
import enumeration.Position;
import enumeration.Status;
import enumeration.UniverseState;
import view.Level;

import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;

/**
 * A base class for all opponents in the game
 */
public abstract class Opponent extends Protagonist {
    protected final SecureRandom secure = new SecureRandom();
    private final int walkPace = 50;
    protected Position pos;
    protected int jumpCounter;
    private int speed;
    private int walkCounter;
    private UniverseState state;
    private int maxJump = 100;

    // constructor
    public Opponent(int coordX, int coordY, int width, int height, String img, int s, UniverseState st) {
        super(coordX, coordY, width, height, img);
        speed = s;
        state = st;
        setStatus(Status.ALIVE);
    }

    // AI: enable the opponents to dodge bullets
    public int bulletDodge(int counter, int counterLimit) {
        // AI: make the mushroom dodge a bullet
        for (int i = 0; i < Bullet.getBullets().size(); ++i) {
            Bullet bullet = Bullet.getBullets().get(i);
            // if you find the counter, match the counter limit immediately
            int bux = bullet.getCoordX();
            int buy = bullet.getCoordY();
            int buh = bullet.getHeight();
            int mx = this.getCoordX();
            int my = this.getCoordY();
            int mh = this.getHeight();
            int mw = this.getWidth();
            if ((this.getStatus() == Status.ALIVE && bullet.getStatus() == Status.ALIVE) && (buh + buy > my && my + mh > buy && jumpCounter == 0) && ((mx - 90 < bux && mx > bux) || (
                    mx + mw + 90 > bux && bux > mx))) {
                // you detected a bullet so jump immediately
                return counterLimit;
            }
        }
        return counter;
    }

    // getters and setters
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public UniverseState getState() {
        return state;
    }

    public void setState(UniverseState state) {
        this.state = state;
    }

    // a method enabling the opponent to jump occasionally
    public Image jump(String img1, String img2) {
        jumpCounter++;
        if (jumpCounter <= maxJump && this.getStatus() == Status.ALIVE) {
            setCoordY(getCoordY() - 1);
            if (this.getCoordY() == 0) {
                //jumpCounter = maxJump+1;
            }
            if (jumpCounter % 2 == 0) {
                return (new ImageIcon(getClass().getResource(img2))).getImage();
            } else {
                return (new ImageIcon(getClass().getResource(img1))).getImage();
            }
        } else if (jumpCounter <= 2 * maxJump && this.getStatus() == Status.ALIVE) {
            setCoordY(getCoordY() + 1);
            if (jumpCounter % 2 == 0) {
                return (new ImageIcon(getClass().getResource(img2))).getImage();
            } else {
                return (new ImageIcon(getClass().getResource(img1))).getImage();
            }
        } else {
            jumpCounter = 0;
        }
        return null;
    }

    // a method to make an opponent move
    public Image move(String image1, String image2) {
        if (state == UniverseState.StillLeft) {
            state = UniverseState.WalkLeft;
        } else if (state == UniverseState.StillRight) {
            state = UniverseState.WalkRight;
        }
        // modify the x variable regularly
        ++walkCounter;
        if (walkCounter <= walkPace) {
            // update the x variable to make it walk
            if (walkCounter <= speed) {
                if (state == UniverseState.WalkLeft) {
                    if (!(this instanceof Bird) && (Level.getLevel() == GameLevel.GroundLevel || Level.getLevel() == GameLevel.BaseLevel)) {
                        this.setCoordX(this.getCoordX() - 1);
                    }
                    // advance as long as you do not see water on the floor
                    else if (Level.getLevel() == GameLevel.MediumLevel && this.getPos() == Position.Floor) {
                        boolean fand = false;
                        int opx = this.getCoordX();
                        for (int i = 0; i < WaterFactory.getWaters().size(); ++i) {
                            Water water = WaterFactory.getWaters().get(i);
                            int wx = water.getCoordX();
                            if (opx <= wx + water.getWidth() && opx > wx && this.getCoordY() + this.getHeight() == water.getCoordY()) {
                                // water is found
                                fand = true;
                                break;
                            }
                        }
                        if (fand == false) {
                            // there is the floor ahead, so move
                            this.setCoordX(this.getCoordX() - 1);
                        } else {
                            this.setState(UniverseState.WalkRight);
                        }
                    }
                    // search for the next brick before walking left
                    // is the opponent on a brick?
                    else if (Level.getLevel() == GameLevel.MediumLevel && this.getPos() == Position.Brick) {
                        boolean found = false;
                        for (int i = 0; i < BrickFactory.getBricks().size(); ++i) {
                            Brick brick = BrickFactory.getBricks().get(i);
                            int brx = brick.getCoordX();
                            int bry = brick.getCoordY();
                            int opx = this.getCoordX();
                            int opy = this.getCoordY();
                            int oph = this.getHeight();
                            int brw = brick.getWidth();
                            if (((brx + brw >= opx && brx < opx) || (brx >= opx && brx < opx - brw) || (brx < opx + (this.getWidth() / 2) && brx + brw > opx)) && bry == opy + oph) {
                                // there is a brick ahead
                                found = true;
                                // move
                                this.setCoordX(this.getCoordX() - 1);
                                this.setCoordY(brick.getCoordY() - this.getHeight());
                                break;
                            }
                        }
                        // if there is none, then change directions
                        if (found == false) {
                            this.setState(UniverseState.WalkRight);
                        }
                    }
                } else if (state == UniverseState.WalkRight) {
                    if (!(this instanceof Bird) && (Level.getLevel() == GameLevel.GroundLevel || Level.getLevel() == GameLevel.BaseLevel)) {
                        this.setCoordX(this.getCoordX() + 1);
                    }
                    // advance as long as you do not see water on the floor
                    else if (Level.getLevel() == GameLevel.MediumLevel && this.getPos() == Position.Floor) {
                        boolean find = false;
                        double opx = this.getCoordX();
                        for (int i = 0; i < WaterFactory.getWaters().size(); ++i) {
                            Water water = WaterFactory.getWaters().get(i);
                            double wx = water.getCoordX();
                            if (opx + this.getWidth() >= wx && opx < wx && this.getCoordY() + this.getHeight() == water.getCoordY()) {
                                find = true;
                                break;
                            }
                        }
                        if (find == false) {
                            this.setCoordX(this.getCoordX() + 1);
                        } else {
                            // if you find water change directions
                            this.setState(UniverseState.WalkLeft);
                        }
                    }
                    // check bricks on the flood before moving
                    else if (Level.getLevel() == GameLevel.MediumLevel && this.getPos() == Position.Brick) {
                        boolean found = false;
                        for (int i = 0; i < BrickFactory.getBricks().size(); ++i) {
                            Brick brick = BrickFactory.getBricks().get(i);
                            int brx = brick.getCoordX();
                            int bry = brick.getCoordY();
                            int opx = this.getCoordX();
                            int opy = this.getCoordY();
                            int oph = this.getHeight();
                            int brw = brick.getWidth();
                            // is there a brick ahead?
                            if (((brx >= opx + brw && brx < opx + 2 * brw) || (brx >= opx && brx < opx + brw)) && bry == opy + oph) {
                                // there is a brick ahead
                                found = true;
                                // move
                                this.setCoordX(this.getCoordX() + 1);
                                this.setCoordY(brick.getCoordY() - this.getHeight());
                                break;
                            }
                        }
                        // if there is no brick ahead, then change directions as you are in the air
                        if (found == false) {
                            this.setState(UniverseState.WalkLeft);
                        }
                    }
                }
            }
            return new ImageIcon(getClass().getResource(image1)).getImage();
        } else if (walkCounter <= (walkPace * 2)) {
            return new ImageIcon(getClass().getResource(image2)).getImage();
        } else {
            walkCounter = 0;
        }
        return null;
    }

    /**
     * A method which detects a collision between 2 opponents
     *
     * @param opponent1
     * @param opponent2
     */
    public void collision(Opponent opponent) {
        if ((getState() == UniverseState.WalkLeft && opponent.getState() == UniverseState.WalkRight || (getState() == UniverseState.WalkRight && opponent.getState() == UniverseState.WalkRight)) &&
                opponent.getCoordX() + opponent.getWidth() >= getCoordX() && getCoordX() > opponent.getCoordX() && opponent.getStatus() == Status.ALIVE
                && this.getStatus() == Status.ALIVE) {
            setState(UniverseState.WalkRight);
            opponent.setState(UniverseState.WalkLeft);
        } else if ((getState() == UniverseState.WalkRight && opponent.getState() == UniverseState.WalkLeft || (getState() == UniverseState.WalkLeft && opponent.getState() == UniverseState.WalkLeft)) && getCoordX()
                + getWidth() >= opponent.getCoordX() && getCoordX() < opponent.getCoordX() && opponent.getStatus() == Status.ALIVE &&
                this.getStatus() == Status.ALIVE) {
            setState(UniverseState.WalkLeft);

            opponent.setState(UniverseState.WalkRight);
        }
    }

    /**
     * A method which detects a collision between an opponent and an obstacle. obstacles are immobile in the game, so the method
     * only needs to be implemented here
     *
     * @param obstacle
     */
    public void collision(Obstacle obstacle) {
        int opx = getCoordX();
        int opy = getCoordY();
        int obx = obstacle.getCoordX();
        int oby = obstacle.getCoordY();
        int obh = obstacle.getHeight();
        if (obstacle.getStatus() == Status.ALIVE) {
            if (getState() == UniverseState.WalkLeft &&
                    obx + obstacle.getWidth() >= opx && opx > obx && opy + getHeight() >= oby + obh && opy < oby + obh) {

                setState(UniverseState.WalkRight);
            } else if (getState() == UniverseState.WalkRight && opx
                    + getWidth() >= obx && opx < obx && opy + getHeight() >= oby + obh && opy < oby + obh) {
                setState(UniverseState.WalkLeft);
            }
        }
    }


    // position of the opponent: on a brick or on the floor
    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    // a method used to set the score of the player
    public abstract void setScore();
}
