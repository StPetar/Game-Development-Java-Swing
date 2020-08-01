package controllers;

import enumeration.*;
import model.Brick;
import model.Coin;
import model.Turtle;
import model.World;
import view.Level;

import java.util.ArrayList;

/**
 * A class responsible for creating turtles in large numbers.
 */
public class TurtleFactory {

    private static ArrayList<Turtle> turtles;
    private static TurtleFactory turtleFactory = null;
    private final int speed = 10;

    // constructor: the factory
    private TurtleFactory() {
        turtles = new ArrayList<>();
        GameLevel level = Level.getLevel();
        switch (level) {
            case BaseLevel:
                turtles.add(new Turtle(560, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                turtles.add(new Turtle(900, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));

                turtles.add(new Turtle(1050, 353, 43, 50, "/images/turtlestillright.png", speed, UniverseState.StillRight));
                turtles.add(new Turtle(1100, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                turtles.add(new Turtle(1350, 353, 43, 50, "/images/turtlestillright.png", speed, UniverseState.StillRight));
                turtles.add(new Turtle(1600, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                turtles.add(new Turtle(1885, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));

                turtles.add(new Turtle(2100, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));

                turtles.add(new Turtle(2300, 353, 43, 50, "/images/turtlestillright.png", speed, UniverseState.StillRight));
                turtles.add(new Turtle(2650, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                turtles.add(new Turtle(2900, 353, 43, 50, "/images/turtlestillright.png", speed, UniverseState.StillRight));
                turtles.add(new Turtle(2800, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                turtles.add(new Turtle(3150, 353, 43, 50, "/images/turtlestillleft.png", speed, UniverseState.StillLeft));

                turtles.add(new Turtle(3400, 353, 43, 50, "/images/turtlestillleft.png", 2 * speed, UniverseState.StillLeft));
                turtles.add(new Turtle(3620, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                turtles.add(new Turtle(3760, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));

                turtles.add(new Turtle(4100, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                turtles.add(new Turtle(4350, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                turtles.add(new Turtle(4450, 353, 43, 50, "/images/turtlestillleft.png", 2 * speed, UniverseState.StillLeft));

                turtles.add(new Turtle(4680, 353, 43, 50, "/images/turtlestillleft.png", 2 * speed, UniverseState.StillLeft));
                turtles.add(new Turtle(4850, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));

                turtles.add(new Turtle(5200, 353, 43, 50, "/images/turtlestillleft.png", 2 * speed, UniverseState.StillLeft));
                turtles.add(new Turtle(5360, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                break;

            case GroundLevel:
                turtles.add(new Turtle(560, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                turtles.add(new Turtle(1390, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                turtles.add(new Turtle(1660, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                turtles.add(new Turtle(2260, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                turtles.add(new Turtle(2550, 353, 43, 50, "/images/turtlestillleft.png", 2 * speed, UniverseState.StillLeft));
                turtles.add(new Turtle(3110, 353, 43, 50, "/images/turtlestillleft.png", 2 * speed, UniverseState.StillLeft));
                turtles.add(new Turtle(3560, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                turtles.add(new Turtle(3710, 353, 43, 50, "/images/turtlestillleft.png", 2 * speed, UniverseState.StillLeft));
                turtles.add(new Turtle(4810, 353, 43, 50, "/images/turtlestillleft.png", 2 * speed, UniverseState.StillLeft));
                turtles.add(new Turtle(5060, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                turtles.add(new Turtle(5260, 353, 43, 50, "/images/turtlestillright.png", 2 * speed, UniverseState.StillRight));
                break;

            case MediumLevel:
                turtles.add(new Turtle(560, 311, 49, 62, "/images/redturtlestillright.png", speed, UniverseState.StillRight, OpponentColor.Red, Position.Floor));
                turtles.add(new Turtle(290, 93, 49, 62, "/images/redturtlestillright.png", speed, UniverseState.StillRight, OpponentColor.Red, Position.Brick));

                turtles.add(new Turtle(800, 311, 49, 62, "/images/redturtlestillright.png", speed, UniverseState.StillRight, OpponentColor.Red, Position.Floor));
                turtles.add(new Turtle(1200, 311, 49, 62, "/images/greenturtlestillleft.png", speed, UniverseState.StillLeft, OpponentColor.Green, Position.Floor));
                turtles.add(new Turtle(1700, 311, 49, 62, "/images/greenturtlestillleft.png", speed, UniverseState.StillLeft, OpponentColor.Green, Position.Floor));

                turtles.add(new Turtle(1800, 311, 49, 62, "/images/greenturtlestillright.png", speed, UniverseState.StillRight, OpponentColor.Green, Position.Floor));
                turtles.add(new Turtle(2050, 311, 49, 62, "/images/redturtlestillright.png", speed, UniverseState.StillRight, OpponentColor.Red, Position.Floor));
                turtles.add(new Turtle(2300, 311, 49, 62, "/images/greenturtlestillright.png", speed, UniverseState.StillRight, OpponentColor.Green, Position.Floor));

                turtles.add(new Turtle(2700, 311, 49, 62, "/images/redturtlestillleft.png", speed, UniverseState.StillLeft, OpponentColor.Red, Position.Floor));
                turtles.add(new Turtle(2800, 311, 49, 62, "/images/greenturtlestillright.png", speed, UniverseState.StillRight, OpponentColor.Green, Position.Floor));
                turtles.add(new Turtle(3150, 78, 49, 62, "/images/redturtlestillleft.png", speed, UniverseState.StillLeft, OpponentColor.Red, Position.Brick));
                turtles.add(new Turtle(3450, 311, 49, 62, "/images/greenturtlestillleft.png", speed, UniverseState.StillLeft, OpponentColor.Green, Position.Floor));

                turtles.add(new Turtle(3800, 311, 49, 62, "/images/greenturtlestillright.png", speed, UniverseState.StillRight, OpponentColor.Green, Position.Floor));
                turtles.add(new Turtle(4200, 311, 49, 62, "/images/redturtlestillleft.png", speed, UniverseState.StillLeft, OpponentColor.Red, Position.Floor));
                turtles.add(new Turtle(4300, 311, 49, 62, "/images/greenturtlestillright.png", speed, UniverseState.StillRight, OpponentColor.Green, Position.Floor));

                turtles.add(new Turtle(4830, 38, 49, 62, "/images/greenturtlestillleft.png", speed, UniverseState.StillLeft, OpponentColor.Green, Position.Brick));

                turtles.add(new Turtle(4950, 311, 49, 62, "/images/greenturtlestillleft.png", speed, UniverseState.StillLeft, OpponentColor.Green, Position.Floor));

                turtles.add(new Turtle(5300, 311, 49, 62, "/images/redturtlestillright.png", speed, UniverseState.StillRight, OpponentColor.Red, Position.Floor));
                turtles.add(new Turtle(5450, 311, 49, 62, "/images/greenturtlestillleft.png", speed, UniverseState.StillLeft, OpponentColor.Green, Position.Floor));

                turtles.add(new Turtle(6100, 311, 49, 62, "/images/redturtlestillright.png", speed, UniverseState.StillRight, OpponentColor.Red, Position.Floor));
                turtles.add(new Turtle(6500, 311, 49, 62, "/images/greenturtlestillleft.png", speed, UniverseState.StillLeft, OpponentColor.Green, Position.Floor));
                break;
        }
    }

    // a method which returns turtles to the caller
    public static ArrayList<Turtle> getTurtles() {
        if (turtleFactory == null) {
            turtleFactory = new TurtleFactory();
        }
        return turtles;
    }

    // a method to check for collisions between opponents
    public static void collisionDetection() {
        if (turtleFactory == null) {
            turtleFactory = new TurtleFactory();
        }
        // collision detection between turtles and mushrooms
        for (int i = 0; i < turtles.size(); ++i) {
            for (int j = 0; j < MushFactory.getMushrooms().size(); ++j) {
                turtles.get(i).collision(MushFactory.getMushrooms().get(j));
            }
        }
        // collision between turtles
        for (int i = 0; i < turtles.size(); ++i) {
            for (int j = 0; j < turtles.size() && i != j; ++j) {
                turtles.get(i).collision(turtles.get(j));
            }
        }
        // collision detection between turtles and pipes
        for (int i = 0; i < turtles.size(); ++i) {
            for (int j = 0; j < PipeFactory.getPipes().size(); ++j) {
                turtles.get(i).collision(PipeFactory.getPipes().get(j));
            }
        }
        // collision detection between turtles and bricks
        for (int i = 0; i < turtles.size(); ++i) {
            for (int j = 0; j < BrickFactory.getBricks().size(); ++j) {
                Brick brick = BrickFactory.getBricks().get(j);
                if (brick.getHeight() + brick.getCoordY() >= turtles.get(i).getCoordY()) {
                    turtles.get(i).collision(brick);
                }
            }
        }
        // collision detection between turtles and coins
        for (int i = 0; i < turtles.size(); ++i) {
            for (int j = 0; j < CoinFactory.getCoins().size(); ++j) {
                Coin coin = CoinFactory.getCoins().get(j);
                if (coin.getCoordY() + coin.getHeight() >= turtles.get(i).getCoordY()) {
                    turtles.get(i).collision(coin);
                }
            }
        }
    }

    // a method to reset turtles
    public static void resetTurtles() {
        turtleFactory = null;
    }

    // a method used to remove dead turtles
    public static void removeTurtles() {
        for (int i = 0; i < turtles.size(); ++i) {
            Turtle turtle = turtles.get(i);
            if ((turtle.getStatus() == Status.DEAD || turtle.getStatus() == Status.BOOM) && turtle.getCoordY() - World.getFloor() > 100) {
                turtles.remove(i);
            }
        }
    }
}
