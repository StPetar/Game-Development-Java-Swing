package model;

import enumeration.GameLevel;
import view.Level;

import javax.swing.*;
import java.awt.*;

// a class responsible for display the Nario world
public class World {
    // class variables
    private static int ceiling = 0;
    // distance between the floor and sky
    private static int floor = 403;
    // coordinates of the world image
    private int worldX;
    private int worldY;
    // an image for the world
    private Image worldImage;

    // constructor
    public World(int x, int y, String img) {
        GameLevel level = Level.getLevel();
        worldX = x;
        worldY = y;
        worldImage = (new ImageIcon(getClass().getResource(img))).getImage();
    }
    // getters and setters

    public static int getCeiling() {
        return ceiling;
    }

    public static void setCeiling(int c) {
        ceiling = c;
    }

    public static int getFloor() {
        return floor;
    }

    public static void setFloor(int f) {
        floor = f;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public Image getWorldImage() {
        return worldImage;
    }

    public void setWorldImage(Image worldImage) {
        this.worldImage = worldImage;
    }


}
