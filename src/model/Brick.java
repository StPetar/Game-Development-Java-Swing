package model;

import enumeration.Status;
import view.Main;

import java.security.SecureRandom;

/**
 * A class used to model bricks in the game. it contains a run method which defines the behaviour of bricks
 */
public class Brick extends SolidObstacle implements Runnable {
    private static boolean stop = false;
    private static Nario nario;
    private final int maxRise = 300;
    private final int maxFall = 150;
    private final int delay = 10;
    // make sure random cannot be instantiated a second time
    private final SecureRandom random = new SecureRandom();
    private int counter = 0;

    public Brick(int coordX, int coordY, int width, int height, String img) {
        super(coordX, coordY, width, height, img);
    }

    // a second constructor for the moving bricks
    public Brick(int coordX, int width, int height, String img) {
        /// 175
        super(coordX, 0, width, height, img);
        setCoordY(random.nextInt(10) + 170);
        Main.threadManager.execute(this);
    }

    // a method for stopping the infinite loop or enabling it
    public static void setBrickStop(boolean s) {
        stop = s;
    }

    // a method which detects the collision between actor and the bottom of a brick
    @Override
    public void collideBottom(Actor actor) {
        actor.setCeiling(actor.getCoordY());
    }

    @Override
    public void run() {
        while (true) {
            if (stop) break;
            // get an instance of nario
            if (nario == null) {
                nario = Nario.getNario();
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
            ++counter;
            if (counter <= maxFall) {
                // the brick goes down. nario is updated if he is on the brick
                if (nario != null && nario.getStatus() == Status.ALIVE && nario.getCoordY() + nario.getHeight() == this.getCoordY() &&
                        ((nario.getCoordX() + nario.getWidth() > this.getCoordX() && this.getCoordX() > nario.getCoordX()) || (
                                nario.getCoordX() < this.getCoordX() + this.getWidth() && nario.getCoordX() > this.getCoordX()))) {
                    nario.setCoordY(nario.getCoordY() + 1);
                }
                this.setCoordY(this.getCoordY() + 1);
            } else if (counter <= maxRise) {
                // the brick goes back up. nario is updated if he is on the brick
                if (nario != null && nario.getStatus() == Status.ALIVE && nario.getCoordY() + nario.getHeight() == this.getCoordY() &&
                        ((nario.getCoordX() + nario.getWidth() > this.getCoordX() && this.getCoordX() > nario.getCoordX()) || (
                                nario.getCoordX() < this.getCoordX() + this.getWidth() && nario.getCoordX() > this.getCoordX()))) {
                    nario.setCoordY(nario.getCoordY() - 1);
                }
                this.setCoordY(this.getCoordY() - 1);
            } else {
                counter = 0;
            }
        }
    }

}
