package model;


import audio.Audio;
import controllers.BrickFactory;
import controllers.CoinFactory;
import controllers.PipeFactory;
import enumeration.*;
import view.Level;
import view.Score;

import javax.swing.*;
import java.awt.*;

/**
 * An abstract class used to model actors and which contains methods common for all actors in the game
 */
public abstract class Actor extends Protagonist {
    private final int maxJump = 70;
    private final int walkingPace = 20;
    private final int jumpSpeed = 2;
    private final int fallSpeed = 1;
    private final int contactmargin = 3;
    /**
     * a method to check for collisions between actors and obstacles. floor collisions
     *
     * @param obstacle
     */
    int tempcounter = 0;
    private int walkCounter;
    private int jumpCounter;
    private Image image2;
    private Image image1;
    private UniverseState state;
    private JumpState jumpState;
    private ActorLocation location;
    private int floor;
    private int ceiling;

    public Actor(int coordX, int coordY, int width, int height, String img, UniverseState s, JumpState j, int f, int c) {
        super(coordX, coordY, width, height, img);
        state = s;
        jumpState = j;
        floor = f;
        ceiling = c;
        setLocation(ActorLocation.FLOOR);
    }

    /**
     * A method which makes an actor walk accordingly
     *
     * @param state
     * @param img1
     * @param img2
     * @return
     */
    public Image walk(UniverseState state, String img1, String img2) {
        switch (state) {
            case StillRight:
                image1 = (new ImageIcon(getClass().getResource(img1)).getImage());
                break;
            case StillLeft:
                image1 = (new ImageIcon(getClass().getResource(img1)).getImage());
                break;
            case WalkRight:
                image1 = walking(img1, img2);
                break;
            case WalkLeft:
                image1 = walking(img1, img2);
                break;
            default:
                break;
        }
        return image1;
    }

    /**
     * a helper method to the walk() method which makes an actor move
     *
     * @param img1
     * @param img2
     * @return
     */
    public Image walking(String img1, String img2) {

        // fall of the actor for walking on nothing
        // searching for the floor

        if (this.getFloor() < World.getFloor()) {
            boolean floors = false;
            // so nario is in the air
            // is the actor on top of an obstacle?
            boolean vide = true;
            // check if the actor is still on something: look for a floor for the actor
            for (int i = 0; i < BrickFactory.getBricks().size() && !floors; ++i) {
                Brick brick = BrickFactory.getBricks().get(i);
                if (this.getCoordX() + this.getWidth() > brick.getCoordX() && this.getCoordX() < brick.getCoordX() + brick.getWidth()) {
                    if (this.getCoordY() + this.getHeight() == brick.getCoordY()) {
                        this.setFloor(brick.getCoordY());
                        floors = true;
                        this.setLocation(ActorLocation.FLOOR);
                    }
                    // check if the actor is touching the an obstacle under it
                    if (this.getCoordY() < brick.getCoordY() && this.getFloor() == brick.getCoordY()) {
                        vide = false;
                        break;
                    }
                }
            }
            for (int i = 0; i < PipeFactory.getPipes().size() && !floors; ++i) {
                Pipe pipe = PipeFactory.getPipes().get(i);
                if (this.getCoordX() + this.getWidth() > pipe.getCoordX() && this.getCoordX() < pipe.getCoordX() + pipe.getWidth()) {
                    if (this.getCoordY() + this.getHeight() == pipe.getCoordY()) {
                        this.setFloor(pipe.getCoordY());
                        floors = true;
                        this.setLocation(ActorLocation.FLOOR);
                        break;
                    }
                    // check if the actor is touching the an obstacle under it
                    if (this.getCoordY() < pipe.getCoordY() && this.getFloor() == pipe.getCoordY()) {
                        vide = false;
                        break;
                    }
                }
            }
            if (this.getCoordY() + this.getHeight() == World.getFloor()) {
                this.setFloor(World.getFloor());
                this.setLocation(ActorLocation.FLOOR);
            }
            // if the actor is on nothing, make it fall
            if (vide && this.getCoordY() + this.getHeight() < World.getFloor() && !floors) {
                this.setFloor(World.getFloor());
                if (this.getState() == UniverseState.WalkRight) this.setJumpState(JumpState.JumpRight);
                if (this.getState() == UniverseState.WalkLeft) this.setJumpState(JumpState.JumpLeft);
                jumpCounter = maxJump + 1;
            }
        }
        // increment the counter
        ++walkCounter;
        if (walkCounter <= walkingPace) {
            image2 = (new ImageIcon(getClass().getResource(img1)).getImage());
        } else if (walkCounter <= (walkingPace * 2)) {
            image2 = (new ImageIcon(getClass().getResource(img2)).getImage());
        } else {
            walkCounter = 0;
        }
        return image2;
    }

    /**
     * @param img1
     * @param img2 A method which makes an actor jump when it is necessary
     * @return
     */
    //boolean jump=true;
    public Image jump(String img1, String img2, UniverseState s) {
        // increment the counter
        ++jumpCounter;
        if (jumpCounter <= maxJump && World.getCeiling() < this.getCoordY() && (this.getJumpState() == JumpState.JumpRight || this.getJumpState() == JumpState.JumpLeft)) {
            if (jumpCounter <= 2) {
                Audio.playSound("/sounds/jump_sound.wav");
            }
            this.setCoordY(this.getCoordY() - jumpSpeed);
            this.setLocation(ActorLocation.AIR);
            // collision detection: if the actor jumps and hits the bottom part of an obstacle, the actor starts going down
            for (int i = 0; i < BrickFactory.getBricks().size(); ++i) {
                Brick brick = BrickFactory.getBricks().get(i);
                if (this.getCoordX() + this.getWidth() > brick.getCoordX() && this.getCoordX() < brick.getCoordX() + brick.getWidth()) {
                    if ((this.getCoordX() + this.getWidth() > brick.getCoordX() + contactmargin && this.getCoordX() < brick.getCoordX() + brick.getWidth() - contactmargin && brick.getHeight() + brick.getCoordY() >=
                            this.getCoordY() && this.getHeight() + this.getCoordY() > brick.getHeight() + brick.getCoordY())) {
                        // the jump is done. now the fall
                        Audio.stop();
                        Audio.playSound("/sounds/bump_sound.wav");
                        jumpCounter = maxJump + 1;
                    }
                }
            }
            // collision with coins as the actor goes up
            for (int i = 0; i < CoinFactory.getCoins().size(); ++i) {
                Coin coin = CoinFactory.getCoins().get(i);
                if (this.getCoordX() + this.getWidth() > coin.getCoordX() && this.getCoordX() < coin.getCoordX() + coin.getWidth()) {
                    if ((this.getCoordX() + this.getWidth() > coin.getCoordX() + contactmargin && this.getCoordX() < coin.getCoordX() + coin.getWidth() - contactmargin && coin.getHeight() + coin.getCoordY() >=
                            this.getCoordY() && coin.getHeight() + coin.getCoordY() < this.getCoordY() + this.getHeight())) {
                        // adjust the score
                        Score.setScore(coin.getType(), coin.getStatus());
                        // the coin state changes to dead
                        CoinFactory.getCoins().get(i).setStatus(Status.DEAD);
                    }
                }
            }
            return (new ImageIcon(getClass().getResource(img1)).getImage());
        }
        // detection of the end of the jump: the actor touches his ceiling or the counter expires by reaching maxJump. now the actor goes down.
        else if (this.getCeiling() >= this.getCoordY() || jumpCounter > maxJump) {
            this.setCoordY(this.getCoordY() + fallSpeed);
            boolean fall = true;
            // check for collisions with the bricks as the actor goes down
            for (int i = 0; i < BrickFactory.getBricks().size(); ++i) {
                if (this.getCoordX() + this.getWidth() > BrickFactory.getBricks().get(i).getCoordX() + contactmargin && this.getCoordX() <
                        BrickFactory.getBricks().get(i).getCoordX() + BrickFactory.getBricks().get(i).getWidth() - contactmargin) {
                    if (this.getCoordY() + this.getHeight() == BrickFactory.getBricks().get(i).getCoordY()) {
                        Audio.stop();
                        Audio.playSound("/sounds/landing_sound.wav");
                        this.setFloor(this.getCoordY() + this.getHeight());
                        fall = false;
                        break;
                    } else if (this.getCoordY() + this.getHeight() - BrickFactory.getBricks().get(i).getCoordY() == 1) {
                        Audio.stop();
                        Audio.playSound("/sounds/landing_sound.wav");
                        this.setCoordY(BrickFactory.getBricks().get(i).getCoordY() - this.getHeight());
                        this.setFloor(BrickFactory.getBricks().get(i).getCoordY());
                        fall = false;
                        break;
                    }
                }
            }

            // collision with pipes as the actor goes down
            for (int i = 0; i < PipeFactory.getPipes().size(); ++i) {
                Pipe pipe = PipeFactory.getPipes().get(i);
                if (this.getCoordX() + this.getWidth() > pipe.getCoordX() + contactmargin && this.getCoordX() < pipe.getCoordX() + pipe.getWidth() - contactmargin) {
                    if (this.getCoordY() + this.getHeight() == pipe.getCoordY()) {
                        Audio.stop();
                        Audio.playSound("/sounds/landing_sound.wav");
                        this.setFloor(this.getCoordY() + this.getHeight());
                        fall = false;
                        break;
                    }
                }
            }
            // collision with coins as the actor goes down
            for (int i = 0; i < CoinFactory.getCoins().size(); ++i) {
                Coin coin = CoinFactory.getCoins().get(i);
                if (this.getCoordX() + this.getWidth() > coin.getCoordX() + contactmargin && this.getCoordX() < coin.getCoordX() + coin.getWidth() - contactmargin) {
                    if (this.getCoordY() + this.getHeight() >= coin.getCoordY() && this.getCoordY() < coin.getCoordY()) {
                        // adjust the score
                        Score.setScore(coin.getType(), coin.getStatus());
                        // the coin state changes to dead
                        CoinFactory.getCoins().get(i).setStatus(Status.DEAD);
                    }
                }
            }
            if (fall) {
                this.setFloor(World.getFloor());
            }
            // check if nario has landed
            if (this.getFloor() == (this.getCoordY() + this.getHeight())) {
                // landing sound
                Audio.stop();
                Audio.playSound("/sounds/landing_sound.wav");
                // change the state to end the jump
                if (this.getState() != UniverseState.WalkLeft && this.getState() != UniverseState.WalkRight)
                    this.setState(s);
                this.setJumpState(jumpState.None);
                this.setLocation(ActorLocation.FLOOR);
                jumpCounter = 0;
                return (new ImageIcon(getClass().getResource(img2)).getImage());
            }
            return (new ImageIcon(getClass().getResource(img1)).getImage());
        }
        return null;
    }

    // a method which performs the jump
    public abstract Image jump();

    // a method to handle the death of an actor
    public abstract Image death();

    public void collision(Obstacle obstacle) {
        // collision between a nario walking/jumping towards the right/left and an obstacle
        if (this.getStatus() == Status.ALIVE && (obstacle.getStatus() == Status.ALIVE) && ((this.getCoordX() + this.getWidth() >= obstacle.getCoordX() && this.getCoordX() < obstacle.getCoordX() && this.getState() == UniverseState.WalkRight
                && this.getCoordY() + this.getHeight() > obstacle.getCoordY() + contactmargin) ||
                (obstacle.getCoordX() + obstacle.getWidth() >= this.getCoordX() && this.getCoordX() > obstacle.getCoordX() && this.getState() == UniverseState.WalkLeft
                        && this.getCoordY() + this.getHeight() > obstacle.getCoordY() + contactmargin))) {

            obstacle.collideFloorJump(this);
        }
    }

    /**
     * A method which detects collisions between actors and opponents
     *
     * @param opponent
     */
    public void collision(Opponent opponent) {
        // collision between a nario walking/jumping towards the right/left and an obstacle
        // mario dies if he touches the opponent whilst on the floor
        if ((this.getStatus() == Status.ALIVE && opponent.getStatus() == Status.ALIVE) && ((this.getCoordX() + this.getWidth() > opponent.getCoordX() && this.getCoordX() < opponent.getCoordX()
        ) ||
                (opponent.getCoordX() + opponent.getWidth() > this.getCoordX() && this.getCoordX() > opponent.getCoordX())) &&
                (this.getCoordY() + this.getHeight() > opponent.getCoordY() && opponent.getCoordY() + opponent.getHeight() > this.getCoordY())) {
            Audio.stop();
            Audio.playSound("/sounds/nario_dies_sound.wav");
            //this.setStatus(Status.DYING);
        }

        // nario lands on a opponent and dies for level 2 and kills the opponent for level 1. or nario dies if it is a bird
        if ((this.getStatus() == Status.ALIVE && opponent.getStatus() == Status.ALIVE && this.getHeight() + this.getCoordY() == opponent.getCoordY()) &&
                ((this.getCoordX() + this.getWidth() > opponent.getCoordX() &&
                        this.getCoordX() <= opponent.getCoordX()) || (opponent.getCoordX() + opponent.getWidth()
                        > this.getCoordX() && this.getCoordX() >= opponent.getCoordX()))) {
            GameLevel level = Level.getLevel();
            if (!(opponent instanceof Bird) && (level == GameLevel.GroundLevel || level == GameLevel.BaseLevel)) {
                Audio.stop();
                Audio.playSound("/sounds/kill_opponent_sound.wav");
                opponent.setStatus(Status.DYING);
                // adjust the score
                opponent.setScore();
            } else if (level == GameLevel.MediumLevel || opponent instanceof Bird) {
                Audio.stop();
                Audio.playSound("/sounds/nario_dies_sound.wav");
                //this.setStatus(Status.DYING);
            }
        }
    }

    public ActorLocation getLocation() {
        return location;
    }

    public void setLocation(ActorLocation location) {
        this.location = location;
    }


    // getters and setters for the state
    public UniverseState getState() {
        return state;
    }

    public void setState(UniverseState s) {
        state = s;
    }

    public JumpState getJumpState() {
        return jumpState;
    }

    public void setJumpState(JumpState jumpState) {
        this.jumpState = jumpState;
    }


    public int getFloor() {
        return floor;
    }

    public void setFloor(int f) {
        floor = f;
    }

    public int getCeiling() {
        return ceiling;
    }

    public void setCeiling(int c) {
        ceiling = c;
    }
}
