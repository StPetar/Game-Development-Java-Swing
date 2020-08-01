package model;

import enumeration.BirdDirection;
import enumeration.OpponentColor;
import enumeration.Status;
import enumeration.UniverseState;
import view.Level;
import view.Main;
import view.Score;

import javax.swing.*;
import java.awt.*;

/**
 * A class used to model birds in the game. it contains a run method which defines the behaviour of birds
 */
public class Bird extends Opponent implements Runnable {
    private static boolean stop = false;
    private final int delay = 2;
    private OpponentColor opColor;
    private BirdDirection direction;
    private int counter = 0;
    private int maxCount = 5;

    // constructor of the bird class
    public Bird(int coordX, int coordY, int width, int height, String img, int s, UniverseState st, OpponentColor oc, BirdDirection bd) {
        super(coordX, coordY, width, height, img, s, st);
        opColor = oc;
        direction = bd;
        // execute the run method of this Bird object
        Main.threadManager.execute(this);
    }

    // a method for stopping the infinite loop or enabling it
    public static void setBirdStop(boolean s) {
        stop = s;
    }

    @Override
    public void setScore() {
        // set the score when a bird is killed
        if (this.getStatus() == Status.BOOM) {
            Score.setScore(2);
        }
    }

    // a method which will determine the movement of the Bird
    @Override
    public Image walk() {
        String img1 = "";
        String img2 = "";
        // determine the images
        switch (this.getState()) {
            case WalkRight:
                if (opColor != null) {
                    switch (opColor) {
                        case Red:
                            img1 = "/images/redbirdright1.png";
                            img2 = "/images/redbirdright2.png";
                            break;
                        case Green:
                            img1 = "/images/greenbirdright1.png";
                            img2 = "/images/greenbirdright2.png";
                    }
                }
                break;
            case WalkLeft:
                if (opColor != null) {
                    switch (opColor) {
                        case Red:
                            img1 = "/images/redbirdleft1.png";
                            img2 = "/images/redbirdleft2.png";
                            break;
                        case Green:
                            img1 = "/images/greenbirdleft1.png";
                            img2 = "/images/greenbirdleft2.png";
                    }
                }
            default:
                break;
        }
        return super.move(img1, img2);
    }

    @Override
    public void run() {
        while (true) {
            if (stop) break;
            try {
                // necessary delay for everything to function well
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // make the bird fly if it is alive
            if (this.getStatus() == Status.ALIVE) {

                // change horizontal direction if necessary (when a field limit is reached)
                if (this.getState() == UniverseState.WalkRight && this.getCoordX() > 6200 - Level.getLimit()) {
                    this.setState(UniverseState.WalkLeft);
                    direction = BirdDirection.LeftUp;
                } else if (this.getState() == UniverseState.WalkLeft && this.getCoordX() < 1 - Level.getLimit()) {
                    this.setState(UniverseState.WalkRight);
                    direction = BirdDirection.RightUp;
                }

                // change bird direction when necessary
                if (this.getCoordY() < 0 && direction == BirdDirection.RightUp) {
                    direction = BirdDirection.RightDown;
                } else if (this.getCoordY() + this.getHeight() > Main.BASEFLOOR && direction == BirdDirection.RightDown) {
                    direction = BirdDirection.RightUp;
                } else if (this.getCoordY() < 0 && direction == BirdDirection.LeftUp) {
                    direction = BirdDirection.LeftDown;
                } else if (this.getCoordY() + this.getHeight() > Main.BASEFLOOR && direction == BirdDirection.LeftDown) {
                    direction = BirdDirection.LeftUp;
                }
                ++counter;
                if (counter < maxCount) {
                    // update the coordinates for the movement
                    if (direction == BirdDirection.RightDown) {
                        this.setCoordX(this.getCoordX() + 1);
                        this.setCoordY(this.getCoordY() + 1);
                    } else if (direction == BirdDirection.RightUp) {
                        this.setCoordX(this.getCoordX() + 1);
                        this.setCoordY(this.getCoordY() - 1);
                    } else if (direction == BirdDirection.LeftDown) {
                        this.setCoordX(this.getCoordX() - 1);
                        this.setCoordY(this.getCoordY() + 1);
                    } else if (direction == BirdDirection.LeftUp) {
                        this.setCoordX(this.getCoordX() - 1);
                        this.setCoordY(this.getCoordY() - 1);
                    }
                } else if (counter > 2 * maxCount) {
                    counter = 0;
                }


                // display the image
                this.setImage(walk());
            } else if (this.getStatus() == Status.BOOM) {
                try {
                    Thread.sleep(delay * 7);
                } catch (InterruptedException e) {
                }
                this.setCoordY(this.getCoordY() + 1);
                this.setImage((new ImageIcon(getClass().getResource("/images/boom.png"))).getImage());
            }
            if (this.getCoordY() - World.getFloor() > 100) {
                break;
            }
        }
    }

}
