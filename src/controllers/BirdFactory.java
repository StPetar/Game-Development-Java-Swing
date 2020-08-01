package controllers;

import enumeration.*;
import model.Bird;
import model.World;
import view.Level;

import java.util.ArrayList;

/**
 * a class used to create birds: the bird factory. here birds are created in large numbers
 */
public class BirdFactory {
    private static ArrayList<Bird> birds;
    private static BirdFactory birdFactory = null;
    private final int speed = 2;

    private BirdFactory() {
        birds = new ArrayList<>();
        GameLevel level = Level.getLevel();
        switch (level) {
            case BaseLevel:
                birds.add(new Bird(560, 253, 31, 32, "/images/greenbirdright1.png", 2 * speed, UniverseState.StillRight, OpponentColor.Green, BirdDirection.RightUp));
                birds.add(new Bird(860, 253, 31, 32, "/images/greenbirdleft1.png", 2 * speed, UniverseState.StillLeft, OpponentColor.Green, BirdDirection.LeftUp));

                birds.add(new Bird(1060, 253, 31, 32, "/images/redbirdright1.png", 2 * speed, UniverseState.StillRight, OpponentColor.Red, BirdDirection.RightUp));
                birds.add(new Bird(1360, 253, 31, 32, "/images/redbirdleft1.png", 2 * speed, UniverseState.StillLeft, OpponentColor.Red, BirdDirection.LeftUp));

                birds.add(new Bird(1660, 253, 31, 32, "/images/greenbirdright1.png", 2 * speed, UniverseState.StillRight, OpponentColor.Green, BirdDirection.RightUp));
                birds.add(new Bird(1960, 253, 31, 32, "/images/greenbirdleft1.png", 2 * speed, UniverseState.StillLeft, OpponentColor.Green, BirdDirection.LeftUp));

                birds.add(new Bird(2260, 253, 31, 32, "/images/redbirdright1.png", 2 * speed, UniverseState.StillRight, OpponentColor.Red, BirdDirection.RightUp));
                birds.add(new Bird(2560, 253, 31, 32, "/images/redbirdleft1.png", 2 * speed, UniverseState.StillLeft, OpponentColor.Red, BirdDirection.LeftUp));

                birds.add(new Bird(2860, 253, 31, 32, "/images/greenbirdright1.png", 2 * speed, UniverseState.StillRight, OpponentColor.Green, BirdDirection.RightUp));
                birds.add(new Bird(3160, 253, 31, 32, "/images/redbirdleft1.png", 2 * speed, UniverseState.StillLeft, OpponentColor.Red, BirdDirection.LeftUp));

                birds.add(new Bird(3460, 253, 31, 32, "/images/greenbirdright1.png", 2 * speed, UniverseState.StillRight, OpponentColor.Green, BirdDirection.RightUp));
                birds.add(new Bird(3760, 253, 31, 32, "/images/redbirdleft1.png", 2 * speed, UniverseState.StillLeft, OpponentColor.Red, BirdDirection.LeftUp));

                birds.add(new Bird(4060, 253, 31, 32, "/images/greenbirdright1.png", 2 * speed, UniverseState.StillRight, OpponentColor.Green, BirdDirection.RightUp));
                birds.add(new Bird(4360, 253, 31, 32, "/images/redbirdleft1.png", 2 * speed, UniverseState.StillLeft, OpponentColor.Red, BirdDirection.LeftUp));

                birds.add(new Bird(4660, 253, 31, 32, "/images/greenbirdright1.png", 2 * speed, UniverseState.StillRight, OpponentColor.Green, BirdDirection.RightUp));
                birds.add(new Bird(4960, 253, 31, 32, "/images/redbirdleft1.png", 2 * speed, UniverseState.StillLeft, OpponentColor.Red, BirdDirection.LeftUp));

                birds.add(new Bird(5260, 253, 31, 32, "/images/greenbirdright1.png", 2 * speed, UniverseState.StillRight, OpponentColor.Green, BirdDirection.RightUp));
                birds.add(new Bird(5560, 253, 31, 32, "/images/redbirdleft1.png", 2 * speed, UniverseState.StillLeft, OpponentColor.Red, BirdDirection.LeftUp));
        }
    }

    // a method which returns birds to the caller
    public static ArrayList<Bird> getBirds() {
        if (birdFactory == null) {
            birdFactory = new BirdFactory();
        }
        return birds;
    }

    // a method to reset birds
    public static void resetBirds() {
        birdFactory = null;
    }

    // a method used to remove dead birds
    public static void removeBirds() {
        for (int i = 0; i < birds.size(); ++i) {
            Bird bird = birds.get(i);
            if ((bird.getStatus() == Status.DEAD || bird.getStatus() == Status.BOOM) && bird.getCoordY() - World.getFloor() > 100) {
                birds.remove(i);
            }
        }
    }
}
