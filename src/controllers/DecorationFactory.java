package controllers;

import enumeration.GameLevel;
import model.Decoration;
import view.Level;

import java.util.ArrayList;

/**
 * a class responsible for creating decorations for the screens
 */
public class DecorationFactory {

    private static ArrayList<Decoration> decorations;
    private static DecorationFactory decorationFactory;

    private DecorationFactory() {
        decorations = new ArrayList<>();
        GameLevel level = Level.getLevel();
        switch (level) {
            case BaseLevel:
                decorations.add(new Decoration(20, 203, 172, 200, "/images/castle.png"));
                decorations.add(new Decoration(212, 343, 50, 60, "/images/start.png"));
                decorations.add(new Decoration(5800, 168, 200, 235, "/images/castle_4.png"));
                break;

            case GroundLevel:
                decorations.add(new Decoration(20, 103, 172, 200, "/images/castle_5.png"));
                decorations.add(new Decoration(5850, 108, 172, 200, "/images/castle_5.png"));
                break;

            case MediumLevel:
                decorations.add(new Decoration(20, 73, 172, 200, "/images/castle_5.png"));
                decorations.add(new Decoration(6650, 73, 172, 200, "/images/castle_5.png"));
                break;
        }
    }

    public static ArrayList<Decoration> getDocrations() {
        if (decorationFactory == null) {
            decorationFactory = new DecorationFactory();
        }
        return decorations;
    }

    // a method to reset decorations
    public static void resetDecorations() {
        decorationFactory = null;
    }
}