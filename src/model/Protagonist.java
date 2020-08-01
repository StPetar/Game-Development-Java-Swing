package model;

import java.awt.*;

/**
 * a base class for all protagonist in the game
 */
public abstract class Protagonist extends Universe {
    public Protagonist(int coordX, int coordY, int width, int height, String img) {
        super(coordX, coordY, width, height, img);
    }

    // a method for making protagonists move
    public abstract Image walk();
}
