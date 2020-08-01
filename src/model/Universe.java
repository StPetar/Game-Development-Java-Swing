package model;

import enumeration.Status;

import javax.swing.*;
import java.awt.*;

/**
 * The parent class of all model classes
 */
public abstract class Universe {
    private int coordX;
    private int coordY;
    private int width;
    private int height;
    private Image image;
    private Status status;

    // constructor
    public Universe(int coordX, int coordY, int width, int height, String img) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.width = width;
        this.height = height;
        status = Status.ALIVE;
        image = (new ImageIcon(getClass().getResource(img))).getImage();
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
