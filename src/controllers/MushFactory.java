package controllers;

import enumeration.GameLevel;
import enumeration.Position;
import enumeration.Status;
import enumeration.UniverseState;
import model.Brick;
import model.Coin;
import model.Mushroom;
import model.World;
import view.Level;

import java.util.ArrayList;

/**
 * a class responsible for creating mushrooms in large numbers
 *
 * @author Nelson
 */
public class MushFactory {

    private static ArrayList<Mushroom> mushrooms;
    private static MushFactory mushFactory;
    private final int speed = 10;

    // constructor
    private MushFactory() {
        GameLevel level = Level.getLevel();
        mushrooms = new ArrayList<>();
        // create turtles
        switch (level) {
            case BaseLevel:
                mushrooms.add(new Mushroom(700, 373, 27, 30, "/images/mushstillleft.png", speed, UniverseState.StillLeft));
                mushrooms.add(new Mushroom(800, 373, 27, 30, "/images/mushstillright.png", speed, UniverseState.StillRight));

                mushrooms.add(new Mushroom(1300, 373, 27, 30, "/images/mushstillright.png", speed, UniverseState.StillRight));
                mushrooms.add(new Mushroom(1200, 373, 27, 30, "/images/mushstillleft.png", speed, UniverseState.StillLeft));

                mushrooms.add(new Mushroom(1800, 373, 27, 30, "/images/mushstillleft.png", speed, UniverseState.StillLeft));
                mushrooms.add(new Mushroom(1960, 373, 27, 30, "/images/mushstillleft.png", speed, UniverseState.StillLeft));

                mushrooms.add(new Mushroom(2200, 373, 27, 30, "/images/mushstillleft.png", 2 * speed, UniverseState.StillLeft));
                mushrooms.add(new Mushroom(2450, 373, 27, 30, "/images/mushstillleft.png", 2 * speed, UniverseState.StillLeft));
                mushrooms.add(new Mushroom(2700, 373, 27, 30, "/images/mushstillleft.png", 2 * speed, UniverseState.StillLeft));
                mushrooms.add(new Mushroom(2850, 373, 27, 30, "/images/mushstillleft.png", 2 * speed, UniverseState.StillLeft));
                mushrooms.add(new Mushroom(3960, 373, 27, 30, "/images/mushstillleft.png", 2 * speed, UniverseState.StillLeft));
                mushrooms.add(new Mushroom(3050, 373, 27, 30, "/images/mushstillleft.png", 2 * speed, UniverseState.StillLeft));
                mushrooms.add(new Mushroom(3300, 373, 27, 30, "/images/mushstillright.png", 2 * speed, UniverseState.StillRight));

                mushrooms.add(new Mushroom(3700, 373, 27, 30, "/images/mushstillleft.png", 2 * speed, UniverseState.StillLeft));

                mushrooms.add(new Mushroom(4250, 373, 27, 30, "/images/mushstillleft.png", 2 * speed, UniverseState.StillLeft));
                mushrooms.add(new Mushroom(4600, 373, 27, 30, "/images/mushstillleft.png", 2 * speed, UniverseState.StillLeft));
                mushrooms.add(new Mushroom(4950, 373, 27, 30, "/images/mushstillleft.png", 2 * speed, UniverseState.StillLeft));
                mushrooms.add(new Mushroom(5100, 373, 27, 30, "/images/mushstillleft.png", 2 * speed, UniverseState.StillLeft));
                mushrooms.add(new Mushroom(5050, 373, 27, 30, "/images/mushstillright.png", 2 * speed, UniverseState.StillRight));
                mushrooms.add(new Mushroom(5300, 373, 27, 30, "/images/mushstillright.png", 2 * speed, UniverseState.StillRight));
                break;

            case GroundLevel:
                mushrooms.add(new Mushroom(800, 373, 27, 30, "/images/mushstillright.png", speed, UniverseState.StillRight));
                mushrooms.add(new Mushroom(1200, 373, 27, 30, "/images/mushstillright.png", speed, UniverseState.StillLeft));
                mushrooms.add(new Mushroom(1900, 373, 27, 30, "/images/mushstillright.png", speed, UniverseState.StillRight));
                mushrooms.add(new Mushroom(2430, 373, 27, 30, "/images/mushstillleft.png", speed, UniverseState.StillLeft));
                mushrooms.add(new Mushroom(2830, 373, 27, 30, "/images/mushstillleft.png", speed, UniverseState.StillLeft));
                mushrooms.add(new Mushroom(3100, 373, 27, 30, "/images/mushstillright.png", speed, UniverseState.StillRight));
                mushrooms.add(new Mushroom(4100, 373, 27, 30, "/images/mushstillright.png", speed, UniverseState.StillRight));
                mushrooms.add(new Mushroom(4230, 373, 27, 30, "/images/mushstillleft.png", speed, UniverseState.StillLeft));
                break;

            case MediumLevel:
                mushrooms.add(new Mushroom(700, 337, 49, 36, "/images/mushleft.png", speed, UniverseState.StillLeft, Position.Floor));

                mushrooms.add(new Mushroom(940, 337, 49, 36, "/images/mushleft.png", speed, UniverseState.StillLeft, Position.Floor));
                mushrooms.add(new Mushroom(1050, 337, 49, 36, "/images/mushright.png", speed, UniverseState.StillRight, Position.Floor));
                mushrooms.add(new Mushroom(1300, 337, 49, 36, "/images/mushright.png", speed, UniverseState.StillRight, Position.Floor));
                mushrooms.add(new Mushroom(1950, 337, 49, 36, "/images/mushleft.png", speed, UniverseState.StillLeft, Position.Floor));

                mushrooms.add(new Mushroom(2450, 337, 49, 36, "/images/mushleft.png", speed, UniverseState.StillLeft, Position.Floor));
                mushrooms.add(new Mushroom(2220, 337, 49, 36, "/images/mushleft.png", speed, UniverseState.StillLeft, Position.Floor));
                mushrooms.add(new Mushroom(2510, 337, 49, 36, "/images/mushright.png", speed, UniverseState.StillRight, Position.Floor));

                mushrooms.add(new Mushroom(3060, 104, 49, 36, "/images/mushright.png", speed, UniverseState.StillRight, Position.Brick));

                mushrooms.add(new Mushroom(3950, 337, 49, 36, "/images/mushleft.png", speed, UniverseState.StillLeft, Position.Floor));
                mushrooms.add(new Mushroom(4050, 337, 49, 36, "/images/mushright.png", speed, UniverseState.StillRight, Position.Floor));
                mushrooms.add(new Mushroom(4680, 64, 49, 36, "/images/mushright.png", speed, UniverseState.StillRight, Position.Brick));
                mushrooms.add(new Mushroom(5050, 337, 49, 36, "/images/mushright.png", speed, UniverseState.StillRight, Position.Floor));

                mushrooms.add(new Mushroom(6290, 337, 49, 36, "/images/mushleft.png", speed, UniverseState.StillLeft, Position.Floor));
                mushrooms.add(new Mushroom(6350, 337, 49, 36, "/images/mushright.png", speed, UniverseState.StillRight, Position.Floor));
                break;
        }
    }

    // a method which returns turtles to the caller
    public static ArrayList<Mushroom> getMushrooms() {
        if (mushFactory == null) {
            mushFactory = new MushFactory();
        }
        return mushrooms;
    }

    // a method to check for collisions between opponents
    public static void collisionDetection() {
        if (mushFactory == null) {
            mushFactory = new MushFactory();
        }
        for (int i = 0; i < mushrooms.size(); ++i) {
            for (int j = 0; j < TurtleFactory.getTurtles().size(); ++j) {
                mushrooms.get(i).collision(TurtleFactory.getTurtles().get(j));
            }
        }
        for (int i = 0; i < mushrooms.size(); ++i) {
            for (int j = 0; j < mushrooms.size() && i != j; ++j) {
                mushrooms.get(i).collision(mushrooms.get(j));
            }
        }
        // collision detection between turtles and pipes
        for (int i = 0; i < mushrooms.size(); ++i) {
            for (int j = 0; j < PipeFactory.getPipes().size(); ++j) {
                mushrooms.get(i).collision(PipeFactory.getPipes().get(j));
            }
        }
        // collision detection between turtles and bricks
        for (int i = 0; i < mushrooms.size(); ++i) {
            for (int j = 0; j < BrickFactory.getBricks().size(); ++j) {
                Brick brick = BrickFactory.getBricks().get(j);
                if (brick.getHeight() + brick.getCoordY() >= mushrooms.get(i).getCoordY()) {
                    mushrooms.get(i).collision(brick);
                }
            }
        }
        // collision detection between turtles and coins
        for (int i = 0; i < mushrooms.size(); ++i) {
            for (int j = 0; j < CoinFactory.getCoins().size(); ++j) {
                Coin coin = CoinFactory.getCoins().get(j);
                if (coin.getCoordY() + coin.getHeight() >= mushrooms.get(i).getCoordY()) {
                    mushrooms.get(i).collision(coin);
                }
            }
        }
    }

    // a method to reset mushrooms
    public static void resetMushrooms() {
        mushFactory = null;
    }

    // a method used to remove dead mushrooms
    public static void removeMushrooms() {
        for (int i = 0; i < mushrooms.size(); ++i) {
            Mushroom mush = mushrooms.get(i);
            if ((mush.getStatus() == Status.DEAD || mush.getStatus() == Status.BOOM) && mush.getCoordY() - World.getFloor() > 100) {
                mushrooms.remove(i);
            }
        }
    }
}