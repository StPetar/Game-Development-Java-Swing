package controllers;

import enumeration.GameLevel;
import model.Actor;
import model.Water;
import view.Level;

import java.util.ArrayList;

/**
 * A class responsible for creating water spots in the game
 *
 * @author Nelson
 */
public class WaterFactory {

    private static ArrayList<Water> waters;
    private static WaterFactory waterFactory;

    private WaterFactory() {
        waters = new ArrayList<>();
        GameLevel level = Level.getLevel();
        switch (level) {
            case BaseLevel:
                // no water yet at this level
                break;

            case GroundLevel:
                waters.add(new Water(5555, 401, 160, 107, "/images/blueimage1_6.png"));
                break;

            case MediumLevel:
                waters.add(new Water(1400, 373, 300, 107, "/images/blueimage3.png"));
                waters.add(new Water(2880, 373, 500, 107, "/images/blueimage4.png"));
                waters.add(new Water(3560, 373, 160, 107, "/images/blueimage1_6.png"));
                waters.add(new Water(4420, 373, 500, 107, "/images/blueimage4.png"));
                waters.add(new Water(5150, 373, 100, 107, "/images/blueimage1.png"));
                waters.add(new Water(5580, 373, 160, 107, "/images/blueimage1_6.png"));
                waters.add(new Water(5840, 373, 230, 107, "/images/blueimage2_3.png"));
                break;
        }
    }

    // get the waters
    public static ArrayList<Water> getWaters() {
        if (waterFactory == null) {
            waterFactory = new WaterFactory();
        }
        return waters;
    }

    // a method to reset waters
    public static void resetWaters() {
        waterFactory = null;
    }

    // collision with actors
    public static void collision(Actor actor) {
        if (waterFactory == null) {
            waterFactory = new WaterFactory();
        }

        for (int i = 0; i < waters.size(); ++i) {
            Water water = waters.get(i);
            water.collision(actor);
        }
    }
}