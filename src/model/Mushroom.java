package model;

import enumeration.GameLevel;
import enumeration.Position;
import enumeration.Status;
import enumeration.UniverseState;
import view.Level;
import view.Main;
import view.Score;

import javax.swing.*;
import java.awt.*;

/**
 * A class used to model mushrooms in the game. it contains a run method which defines the behaviour of mushrooms
 */
public class Mushroom extends Opponent implements Runnable {
    private static boolean stop = false;
    private final int delay = 2;
    private final int jumpDelay = 7;
    private int counter;
    private int counterLimit = 1000 + secure.nextInt(2000);

    // a constructor for base level mushrooms
    public Mushroom(int coordX, int coordY, int width, int height, String img, int s, UniverseState st) {
        super(coordX, coordY, width, height, img, s, st);
        pos = Position.Floor;
        Main.threadManager.execute(this);
    }

    // a constructor for medium level mushrooms
    public Mushroom(int coordX, int coordY, int width, int height, String img, int s, UniverseState st, Position p) {
        super(coordX, coordY, width, height, img, s, st);
        setPos(p);
        Main.threadManager.execute(this);
    }

    // a method for stopping the infinite loop or enabling it
    public static void setMushStop(boolean s) {
        stop = s;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }

            if (Level.getLevel() == GameLevel.MediumLevel) {
                // occasional jump
                ++counter;
                if (counter >= counterLimit && this.getStatus() == Status.ALIVE) {
                    String str1 = "/images/mushleft.png";
                    String str2 = "/images/mushright.png";
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
                    counterLimit = secure.nextInt(2000) + 1000;
                    counter = 0;
                }
                // AI: make the mushroom dodge a bullet
                // AI: if you sense a bullet, set the counter equal to the counter limit directly so that the opponent dodges the bullet
                counter = bulletDodge(counter, counterLimit);
            }

            // regularly set the image of this mushroom to make it move
            if (this.getStatus() == Status.ALIVE) {
                this.setImage(walk());
            } else if (this.getStatus() == Status.DYING) {
                try {
                    Thread.sleep(delay * 7);
                } catch (InterruptedException e) {
                }
                this.setCoordY(this.getCoordY() + 1);
                String image = "";
                switch (this.getState()) {
                    case WalkRight:
                        image = "/images/mushdeadright.png";
                        break;
                    case WalkLeft:
                        image = "/images/mushdeadleft.png";
                        break;
                    default:
                        break;
                }
                this.setImage((new ImageIcon(getClass().getResource(image))).getImage());
            } else if (this.getStatus() == Status.BOOM) {
                try {
                    Thread.sleep(delay * 7);
                } catch (InterruptedException e) {
                }
                this.setCoordY(this.getCoordY() + 1);
                this.setImage((new ImageIcon(getClass().getResource("/images/boom.png"))).getImage());
            }
            if ((this.getCoordY() - World.getFloor() > 100) || stop) {
                break;
            }
        }
    }

    @Override
    public Image walk() {
        GameLevel level = Level.getLevel();
        String img1 = "";
        String img2 = "";
        // determine the images
        switch (this.getState()) {
            case WalkRight:
                if (level == GameLevel.GroundLevel || level == GameLevel.BaseLevel) {
                    img1 = "/images/mushstillright.png";
                    img2 = "/images/mushwalkright.png";
                } else if (level == GameLevel.MediumLevel) {
                    img1 = "/images/mushright.png";
                    img2 = "/images/mushleft.png";
                }
                break;
            case WalkLeft:
                if (level == GameLevel.GroundLevel || level == GameLevel.BaseLevel) {
                    img1 = "/images/mushstillleft.png";
                    img2 = "/images/mushwalkleft.png";
                } else if (level == GameLevel.MediumLevel) {
                    img1 = "/images/mushleft.png";
                    img2 = "/images/mushright.png";
                }
            default:
                break;
        }
        return super.move(img1, img2);
    }

    // a method which sets the score after the killing of a mushroom: +2
    public void setScore() {
        if (this.getStatus() == Status.DYING) {
            Score.setScore(2);
        } else if (this.getStatus() == Status.BOOM) {
            Score.setScore(1);
        }
    }

}
