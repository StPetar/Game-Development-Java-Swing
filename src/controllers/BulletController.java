package controllers;

import model.Bullet;
import model.Obstacle;
import model.Protagonist;
import model.Universe;

import java.util.ArrayList;

/**
 * A class used to control the collisions between bullets and other elements in the game
 *
 * @author Nelson
 */
public class BulletController {
    private static ArrayList<Bullet> bullets;

    // collision between bullets and universe elements
    public static void collision(ArrayList<?> objects) {
        bullets = Bullet.getBullets();
        for (int i = 0; i < bullets.size(); ++i) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < objects.size(); ++j) {
                Universe opponent = (Universe) objects.get(j);
                bullet.collision(opponent);
            }
        }
    }

    // collision between pipe bullets and a protagonist
    public static void collision(Protagonist protagonist) {
        bullets = Bullet.getPipeBullets();
        for (int i = 0; i < bullets.size(); ++i) {
            bullets.get(i).collisions(protagonist);
        }
    }

    // collision between bullets and obstacles
    public void collision(Obstacle obstacle) {

    }
}
