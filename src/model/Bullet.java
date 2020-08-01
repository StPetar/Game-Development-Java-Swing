package model;

import audio.Audio;
import enumeration.JumpState;
import enumeration.Status;
import enumeration.UniverseState;
import view.Main;

import java.util.ArrayList;

/**
 * A class used to model bullets in the game. it contains a run method which defines the behaviour of bullets
 */
public class Bullet extends Universe implements Runnable {
    private static ArrayList<Bullet> bullets = new ArrayList<>();
    private static ArrayList<Bullet> pipeBullets = new ArrayList<>();
    private static Nario nario = Nario.getNario();
    private final int delay = 4;
    private final int speed = 15;
    private UniverseState state;

    private Bullet(int coordX, int coordY, int width, int height, String img, UniverseState s) {
        super(coordX, coordY, width, height, img);
        nario = Nario.getNario();
        state = s;
        // start the thread of this object: execute the run method
        Main.threadManager.execute(this);
    }

    private Bullet(int coordX, int coordY, int width, int height, String img) {
        super(coordX, coordY, width, height, img);
    }


    public static void createBullet() {
        nario = Nario.getNario();
        if (nario.getState() == UniverseState.WalkRight || nario.getJumpState() == JumpState.JumpRight || nario.getState() == UniverseState.StillRight) {
            bullets.add(new Bullet(nario.getCoordX() + nario.getWidth() - 10, nario.getCoordY() + (8 + (nario.getHeight() / 3)), 18, 12, "/images/fireballright.png", UniverseState.WalkRight));
        } else if (nario.getState() == UniverseState.WalkLeft || nario.getJumpState() == JumpState.JumpLeft || nario.getState() == UniverseState.StillLeft) {
            bullets.add(new Bullet(nario.getCoordX() - 15, nario.getCoordY() - 3 + (nario.getHeight() / 2), 18, 12, "/images/fireballleft.png", UniverseState.WalkLeft));
        }
    }

    // a method used to create and store pipe bullets
    public static Bullet createBullet(Pipe pipe) {
        Bullet bullet = new Bullet(pipe.getCoordX() + ((pipe.getWidth() - 50) / 2), pipe.getCoordY() - 38, 50, 38, "/images/p_fireball1.png");
        pipeBullets.add(bullet);
        return bullet;
    }

    // a method to get bullets for display
    public static ArrayList<Bullet> getBullets() {
        return bullets;
    }

    // a method to get pipe bullets for display
    public static ArrayList<Bullet> getPipeBullets() {
        return pipeBullets;
    }

    // reset pipe bullets
    public static void resetBullets() {
        pipeBullets = new ArrayList<Bullet>();
        bullets = new ArrayList<Bullet>();
    }

    // a method used to remove bullets no longer visible
    public static void deleteBullets() {
        for (int i = 0; i < bullets.size(); ++i) {
            Bullet bullet = bullets.get(i);
            if (bullet.getStatus() == Status.DEAD) {
                bullets.remove(i);
            } else if (bullet.getCoordX() > 7 * Main.GAMEWIDTH || bullet.getCoordX() < -7 * Main.GAMEWIDTH) {
                bullets.remove(i);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
            switch (state) {
                case WalkRight:
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                    if (this.getStatus() == Status.ALIVE) {
                        this.setCoordX(this.getCoordX() + speed);
                    }
                    break;
                case WalkLeft:
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                    if (this.getStatus() == Status.ALIVE) {
                        this.setCoordX(this.getCoordX() - speed);
                    }
                    break;
            }
            if (this.getCoordX() > 7 * Main.GAMEWIDTH || this.getCoordX() < -7 * Main.GAMEWIDTH) {
                break;
            }
        }
    }

    // collision between bullets and objects
    public void collision(Universe opponent) {
        if (this.getStatus() == Status.ALIVE && (opponent.getStatus() == Status.ALIVE) && (this.getCoordY() + this.getHeight() > opponent.getCoordY() + 2 && this.getCoordY() < opponent.getCoordY() + opponent.getHeight()) &&
                ((this.getCoordX() + this.getWidth() >= opponent.getCoordX() && this.getCoordX() < opponent.getCoordX()) ||
                        (opponent.getCoordX() + opponent.getWidth() > this.getCoordX() && this.getCoordX() > opponent.getCoordX()
                        ))) {
            // the bullet is no longer visible or harmful
            this.setStatus(Status.DEAD);
            if (opponent instanceof Opponent) {
                opponent.setStatus(Status.BOOM);
                Audio.stop();
                Audio.playSound("/sounds/boom.wav");
                // the player gains point for killing an opponent
                ((Opponent) opponent).setScore();
            }
        }
    }

    // a method which checks for collisions between pipe bullets and a protagonist
    public void collisions(Protagonist protagonist) {
        int bux = getCoordX();
        int buy = getCoordY();
        int buw = getWidth();
        int buh = getHeight();
        int prx = protagonist.getCoordX();
        int pry = protagonist.getCoordY();
        int prw = protagonist.getWidth();
        int prh = protagonist.getHeight();
        if (protagonist.getStatus() == Status.ALIVE && pry + prh == buy + buh && ((prx + prw > bux && bux + buw > prx) || (prx < bux + buw && prx + prw > bux))) {
            Audio.stop();
            Audio.playSound("/sounds/nario_dies_sound.wav");
            //protagonist.setStatus(Status.BOOM);
        }
    }

    public UniverseState getState() {
        return state;
    }
}
