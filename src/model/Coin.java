package model;

import enumeration.CoinType;
import enumeration.JumpState;
import enumeration.Status;
import view.Main;
import view.Score;

import javax.swing.*;

/**
 * a class used to display coins and remember their type and state
 */
public class Coin extends EdibleObstacle implements Runnable {
    private static boolean stop = false;
    private final int delay = 3;
    private final int blinkingDuration = 100;
    private CoinType type;
    private String[] coins;
    // counter variable for the blinking activity
    private int counter;

    public Coin(int coordX, int coordY, int width, int height, String img, CoinType t) {
        super(coordX, coordY, width, height, img);
        type = t;
        coins = new String[2];
        // execute the run method
        Main.threadManager.execute(this);
    }

    // a method for stopping the infinite loop or enabling it
    public static void setCoinStop(boolean s) {
        stop = s;
    }

    public CoinType getType() {
        return type;
    }

    public void setType(CoinType t) {
        type = t;
    }

    @Override
    public void collideFloorJump(Actor actor) {
        if ((actor.getCoordY() == this.getCoordY() && actor.getCoordX() == this.getCoordX()) || (actor.getJumpState() == JumpState.None && actor.getCoordY() < this.getCoordY() + this.getHeight())) {
            Score.setScore(this.getType(), this.getStatus());
            this.setStatus(Status.DEAD);
        }
    }

    // a method used to make the coin blink
    @Override
    public void run() {
        switch (this.type) {
            case GOLD:
                coins[0] = "/images/goldcoin1.png";
                coins[1] = "/images/goldcoin2.png";
                break;
            case PURPLE:
                coins[0] = "/images/purplecoin1.png";
                coins[1] = "/images/purplecoin2.png";
                break;
            case GREEN:
                coins[0] = "/images/greencoin1.png";
                coins[1] = "/images/greencoin2.png";
                break;
        }

        while (true) {
            if (this.getStatus() == Status.DEAD || stop) {
                break;
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
            // increment the counter
            ++counter;
            if (counter <= blinkingDuration) {
                this.setImage((new ImageIcon(getClass().getResource(coins[0]))).getImage());
            } else if (counter <= (2 * blinkingDuration)) {
                this.setImage((new ImageIcon(getClass().getResource(coins[1]))).getImage());
            } else {
                counter = 0;
            }
        }
    }
}
