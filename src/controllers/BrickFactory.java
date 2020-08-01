package controllers;

import enumeration.GameLevel;
import model.Brick;
import view.Level;

import java.util.ArrayList;

/**
 * A class responsible for creating bricks in large numbers
 */
public class BrickFactory {
    private static ArrayList<Brick> bricks;
    private static BrickFactory brickFactory;

    private BrickFactory() {
        bricks = new ArrayList<>();
        GameLevel level = Level.getLevel();
        switch (level) {
            case BaseLevel:
                bricks.add(new Brick(300, 253, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(410, 213, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(440, 213, 31, 31, "/images/brick1.png"));

                bricks.add(new Brick(630, 202, 31, 31, "/images/brick1.png"));

                bricks.add(new Brick(810, 192, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(870, 132, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(940, 70, 31, 31, "/images/brick1.png"));

                bricks.add(new Brick(1100, 160, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(1340, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(1370, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(1400, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(1425, 212, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(1620, 260, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(1720, 200, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(1750, 200, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(2580, 200, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(2700, 100, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(3070, 210, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(3120, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(3150, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(3180, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(3210, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(3240, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(3270, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(3300, 112, 31, 31, "/images/brick1.png"));

                bricks.add(new Brick(3600, 200, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(3750, 175, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(4200, 90, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(4700, 300, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(4300, 292, 30, 111, "/images/brickwallvertical.png"));
                bricks.add(new Brick(4100, 180, 31, 31, "/images/brick1.png"));

                bricks.add(new Brick(4850, 116, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(4920, 216, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(5450, 302, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(1250, 292, 30, 111, "/images/brickwallvertical.png"));
                bricks.add(new Brick(2250, 292, 30, 111, "/images/brickwallvertical.png"));
                bricks.add(new Brick(1850, 292, 30, 111, "/images/brickwallvertical.png"));
                break;

            case GroundLevel:
                bricks.add(new Brick(470, 371, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(500, 371, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(500, 341, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(530, 371, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(530, 341, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(530, 311, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(890, 260, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(920, 260, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(950, 260, 31, 31, "/images/brick0.png"));
                bricks.add(new Brick(980, 260, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(1010, 260, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(1040, 260, 31, 31, "/images/brick0.png"));

                bricks.add(new Brick(1100, 160, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(1210, 110, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(1310, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(1340, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(1370, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(1400, 112, 31, 31, "/images/brick1.png"));

                bricks.add(new Brick(1620, 260, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(1720, 200, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(1750, 200, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(2150, 200, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(2180, 200, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(2210, 200, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(2320, 250, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(2350, 250, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(2650, 250, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(2870, 250, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(3070, 210, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(3150, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(3180, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(3210, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(3240, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(3270, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(3300, 112, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(3330, 112, 31, 31, "/images/brick1.png"));

                bricks.add(new Brick(3600, 200, 31, 31, "/images/brick0.png"));
                bricks.add(new Brick(3750, 175, 31, 31, "/images/brick0.png"));

                bricks.add(new Brick(4100, 220, 31, 31, "/images/brick0.png"));
                bricks.add(new Brick(4200, 120, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(4260, 250, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(4290, 250, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(4320, 250, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(4350, 250, 31, 31, "/images/brick1.png"));

                bricks.add(new Brick(4850, 250, 31, 31, "/images/brick0.png"));

                bricks.add(new Brick(4980, 180, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(5100, 130, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(5250, 130, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(5400, 130, 30, 30, "/images/brick0.png"));
                break;

            case MediumLevel:
                bricks.add(new Brick(290, 155, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(320, 155, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(350, 155, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(380, 155, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(410, 155, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(440, 155, 31, 31, "/images/brick1.png"));

                bricks.add(new Brick(610, 150, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(640, 150, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(950, 140, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(1090, 155, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(1180, 100, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(1020, 262, 30, 111, "/images/brickwallvertical.png"));

                bricks.add(new Brick(1420, 280, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(1480, 200, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(1580, 200, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(1650, 250, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(2170, 200, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(2170, 200, 31, 31, "/images/brick1.png"));

                bricks.add(new Brick(2880, 260, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(2960, 200, 31, 31, "/images/brick1.png"));

                bricks.add(new Brick(3060, 140, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(3090, 140, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(3120, 140, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(3150, 140, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(3180, 140, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(3210, 140, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(3270, 220, 31, 31, "/images/brick1.png"));

                Brick brick1 = new Brick(3600, 90, 30, "/images/horizontalbricks.png");
                bricks.add(brick1);

                bricks.add(new Brick(4000, 262, 30, 111, "/images/brickwallvertical.png"));
                bricks.add(new Brick(4425, 290, 30, 30, "/images/brick0.png"));

                Brick brick2 = new Brick(4500, 90, 30, "/images/horizontalbricks.png");
                bricks.add(brick2);

                bricks.add(new Brick(4680, 100, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(4710, 100, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(4740, 100, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(4770, 100, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(4800, 100, 30, 30, "/images/brick0.png"));
                bricks.add(new Brick(4830, 100, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(4890, 200, 30, 30, "/images/brick0.png"));

                bricks.add(new Brick(5150, 200, 31, 31, "/images/brick1.png"));
                bricks.add(new Brick(5400, 200, 31, 31, "/images/brick1.png"));

                Brick brick3 = new Brick(5630, 30, 30, "/images/brick0.png");
                bricks.add(brick3);

                Brick brick4 = new Brick(5850, 90, 30, "/images/horizontalbricks.png");
                bricks.add(brick4);

                Brick brick5 = new Brick(5980, 90, 30, "/images/horizontalbricks.png");
                bricks.add(brick5);
                break;
        }
    }

    // a method which returns the bricks created
    public static ArrayList<Brick> getBricks() {
        if (brickFactory == null) {
            brickFactory = new BrickFactory();
        }
        return bricks;
    }

    // a method to reset bricks
    public static void resetBricks() {
        brickFactory = null;
    }
}

