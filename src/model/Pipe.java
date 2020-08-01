package model;

import enumeration.GameLevel;
import enumeration.Status;
import view.Level;
import view.Main;

import java.security.SecureRandom;

/**
 * A class used to model pipes in the game. it contains a run method which defines the behaviour of pipes
 */
public class Pipe extends SolidObstacle implements Runnable {
    private static final int delay = 8;
    private static boolean stop = false;
    private final SecureRandom secure = new SecureRandom();
    private int duration;

    // constructor
    public Pipe(int coordX, int coordY, int width, int height, String img) {
        super(coordX, coordY, width, height, img);
    }

    public Pipe(int coordX, int coordY, int width, int height, String img, int dur) {
        super(coordX, coordY, width, height, img);
        if (Level.getLevel() == GameLevel.MediumLevel) {
            duration = secure.nextInt(dur) + 200;
            // make pipes shoot fire in the medium level
            Main.threadManager.execute(this);
        }
    }

    // a method for stopping the infinite loop or enabling it
    public static void setPipeStop(boolean s) {
        stop = s;
    }

    /**
     * the run method is used to make pipes shoot
     */
    @Override
    public void run() {
        Bullet bullet = Bullet.createBullet(this);

        while (true) {
            if (stop) break;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
            // move the bullets
            bullet.setCoordY(bullet.getCoordY() - 1);
            if (bullet.getCoordY() < -duration) {
                bullet.setCoordY(this.getCoordY() - bullet.getHeight());
                bullet.setStatus(Status.ALIVE);
            }
        }
    }
}
