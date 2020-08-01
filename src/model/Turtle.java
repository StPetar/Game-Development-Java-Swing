package model;

import enumeration.*;
import view.Level;
import view.Main;
import view.Score;

import javax.swing.*;
import java.awt.*;

/**
 * A class which implements method specific to the turtles
 *
 * @author Nelson
 */
public class Turtle extends Opponent implements Runnable {
    private static boolean stop = false;
    private final int delay = 2;
    private final int jumpDelay = 7;
    private OpponentColor tc;
    private int counter;
    private int counterLimit = 1000 + secure.nextInt(2000);

    public Turtle(int coordX, int coordY, int width, int height, String img, int s, UniverseState st) {
        super(coordX, coordY, width, height, img, s, st);
        pos = Position.Floor;
        Main.threadManager.execute(this);
    }

    public Turtle(int coordX, int coordY, int width, int height, String img, int s, UniverseState st, OpponentColor c, Position p) {
        super(coordX, coordY, width, height, img, s, st);
        tc = c;
        setPos(p);
        Main.threadManager.execute(this);
    }

    // a method for stopping the infinite loop or enabling it
    public static void setTurtleStop(boolean s) {
        stop = s;
    }

    @Override
    public void run() {
        while (true) {
            if (stop) break;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
            if (Level.getLevel() == GameLevel.MediumLevel) {
                ++counter;
                // occasional jump of turtles
                if (counter >= counterLimit && this.getStatus() == Status.ALIVE) {
                    String str1 = "";
                    String str2 = "";
                    if (getState() == UniverseState.WalkRight) {
                        if (getTurtleColor() == OpponentColor.Green) {
                            str1 = "/images/greenturtleflyrightclose.png";
                            str2 = "/images/greenturtleflyrightopen.png";
                        } else if (getTurtleColor() == OpponentColor.Red) {
                            str1 = "/images/redturtleflyrightclose.png";
                            str2 = "/images/redturtleflyrightopen.png";
                        }
                    } else if (getState() == UniverseState.WalkLeft) {
                        if (getTurtleColor() == OpponentColor.Green) {
                            str1 = "/images/greenturtleflyleftclose.png";
                            str2 = "/images/greenturtleflyleftopen.png";
                        } else if (getTurtleColor() == OpponentColor.Red) {
                            str1 = "/images/redturtleflyleftclose.png";
                            str2 = "/images/redturtleflyleftopen.png";
                        }
                    }
                    while (true) {
                        try {
                            Thread.sleep(jumpDelay);
                        } catch (InterruptedException e) {
                        }
                        this.setImage(jump(str1, str2));
                        if (jumpCounter == 0) {
                            break;
                        }
                    }
                    // adjust the counter
                    counterLimit = secure.nextInt(1000) + 2000;
                    counter = 0;
                }
                // AI: if you sense a bullet, set the counter equal to the counter limit directly so that the opponent dodges the bullet
                counter = bulletDodge(counter, counterLimit);
            }
            // regularly set the image of this turtle to make it move
            if (this.getStatus() == Status.ALIVE) {
                this.setImage(walk());
            } else if (this.getStatus() == Status.DYING) {
                try {
                    Thread.sleep(delay * 7);
                } catch (InterruptedException e) {
                }
                this.setCoordY(this.getCoordY() + 1);
                this.setImage((new ImageIcon(getClass().getResource("/images/turtledead.png"))).getImage());
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

    // a method for enabling the turtle to walk
    @Override
    public Image walk() {
        GameLevel level = Level.getLevel();
        String img1 = "";
        String img2 = "";
        // determine the images
        switch (this.getState()) {
            case WalkRight:
                if (level == GameLevel.GroundLevel || level == GameLevel.BaseLevel) {
                    img1 = "/images/turtlestillright.png";
                    img2 = "/images/turtlewalkright.png";
                } else if (level == GameLevel.MediumLevel && tc != null) {
                    switch (tc) {
                        case Red:
                            img1 = "/images/redturtlestillright.png";
                            img2 = "/images/redturtlewalkright.png";
                            break;
                        case Green:
                            img1 = "/images/greenturtlestillright.png";
                            img2 = "/images/greenturtlewalkright.png";
                    }
                }
                break;
            case WalkLeft:
                if (level == GameLevel.GroundLevel || level == GameLevel.BaseLevel) {
                    img1 = "/images/turtlestillleft.png";
                    img2 = "/images/turtlewalkleft.png";
                } else if (level == GameLevel.MediumLevel && tc != null) {
                    switch (tc) {
                        case Red:
                            img1 = "/images/redturtlestillleft.png";
                            img2 = "/images/redturtlewalkleft.png";
                            break;
                        case Green:
                            img1 = "/images/greenturtlestillleft.png";
                            img2 = "/images/greenturtlewalkleft.png";
                    }
                }
            default:
                break;
        }
        return super.move(img1, img2);
    }

    // a method which sets the score after the killing of a turtle: + 4
    public void setScore() {
        if (this.getStatus() == Status.DYING) {
            Score.setScore(2);
        } else if (this.getStatus() == Status.BOOM) {
            Score.setScore(1);
        }
    }

    public OpponentColor getTurtleColor() {
        return tc;
    }

    public void setTurtleColor(OpponentColor tc) {
        this.tc = tc;
    }
}
