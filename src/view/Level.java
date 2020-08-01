package view;

import audio.Audio;
import controllers.Keyboard;
import enumeration.*;
import model.*;
import view.baseGame.BaseLevel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The base class of all levels. This class implements some of the common functions in the game
 */
@SuppressWarnings("serial")
public abstract class Level extends JPanel {
    protected static int limit;
    protected static boolean move;
    protected static int slide;
    protected static World world1;
    protected static World world2;
    protected static int fieldLimit;
    protected static Nario nario;
    private static GameLevel level = GameLevel.GroundLevel;
    protected ArrayList<Decoration> decorations;
    protected ArrayList<Pipe> pipes;
    protected ArrayList<Brick> bricks;
    protected ArrayList<Coin> coins;
    protected ArrayList<Turtle> turtles;
    protected ArrayList<Mushroom> mushrooms;
    protected ArrayList<Bullet> bullets;
    protected boolean outoftime;
    protected Timer timer;
    protected int winningLimit;
    protected int finalScore;
    // a variable which indicates that nario died due to lack of time
    protected boolean timedeath;
    protected boolean winningsound;

    // getters and setters for continuous scrolling
    public static World getWorld1() {
        return world1;
    }

    public static void setWorld1(World world1) {
        BaseLevel.world1 = world1;
    }

    public static World getWorld2() {
        return world2;
    }

    public static void setWorld2(World world2) {
        BaseLevel.world2 = world2;
    }

    public static int getLimit() {
        return limit;
    }

    public static void setLimit(int l) {
        limit = l;
    }

    public static int getSlide() {
        return slide;
    }

    public static void setSlide(int s) {
        slide = s;
    }

    // getters and setters for the move variable which controls the sliding
    public static boolean isMove() {
        return move;
    }

    public static void setMove(boolean move) {
        BaseLevel.move = move;
    }

    /**
     * a function which returns the limit of the field
     *
     * @return
     */
    public static int getFieldLimit() {
        return fieldLimit;
    }

    // getters and setters for the game level variable
    public static GameLevel getLevel() {
        return level;
    }

    public static void setLevel(GameLevel l) {
        level = l;
    }

    // a method to display the background image
    public void displayWorld(Graphics2D g2, int fieldLimit) {
        // ensure that the game only progresses within the limits
        if (limit >= 0 && limit <= fieldLimit) {
            // disable sliding if the actor is blocked
            if (!move) {
                slide = 0;
            }
            // make the world slide
            world1.setWorldX(world1.getWorldX() - slide);
            world2.setWorldX(world2.getWorldX() - slide);
            // update the limit
            limit += slide;
        }
        // make sure that the value of the limit does not get wildly increase by the user
        if (limit > fieldLimit + 1) {
            limit = fieldLimit + 1;
        }
        g2.drawImage(world1.getWorldImage(), world1.getWorldX(), world1.getWorldY(), null);
        g2.drawImage(world2.getWorldImage(), world2.getWorldX(), world2.getWorldY(), null);
    }

    // start method for all levels
    public abstract void start();

    /**
     * A method which displays actors in the game: for example nario
     *
     * @param
     */
    public void displayActors(Actor actor, Graphics2D gd) {
        UniverseState state = actor.getState();
        JumpState jstate = actor.getJumpState();

        // handle the death of mario
        if (actor.getStatus() == Status.DYING) {
            actor.setStatus(Status.DEAD);
            // the power of polymorphism
            actor.setImage(actor.death());
        }
        // make the actor jump
        if (actor.getStatus() == Status.ALIVE && (jstate == JumpState.JumpRight || jstate == JumpState.JumpLeft)) {
            actor.setImage(actor.jump());
        } else if (state == UniverseState.StillRight || state == UniverseState.StillLeft || state == UniverseState.WalkRight || state == UniverseState.WalkLeft) {
            if (actor.getLocation() == ActorLocation.FLOOR && actor.getStatus() == Status.ALIVE)
                actor.setImage(actor.walk());
        }
        if (actor.getStatus() != Status.WON) {
            gd.drawImage(actor.getImage(), actor.getCoordX(), actor.getCoordY(), null);
        }
    }

    /**
     * a method which ensures continuous sliding
     */
    public void continuousScrolling() {
        if (world2.getWorldX() >= Main.GAMEWIDTH && slide < 0) {
            world2.setWorldX(-Main.GAMEWIDTH);
        }
        if (world1.getWorldX() >= Main.GAMEWIDTH && slide < 0) {
            world1.setWorldX(-Main.GAMEWIDTH);
        }
        if (world1.getWorldX() <= -Main.GAMEWIDTH && slide > 0) {
            world1.setWorldX(Main.GAMEWIDTH);
        }
        if (world2.getWorldX() <= -Main.GAMEWIDTH && slide > 0) {
            world2.setWorldX(Main.GAMEWIDTH);
        }
    }

    /**
     * a method to display objects of the universe such as obstacles, decorations, opponents. the power of polymorphism
     */
    public void displayObjects(Graphics2D gd, ArrayList<?> objects) {
        for (int i = 0; i < objects.size(); ++i) {
            // using the type Universe makes the method usable by all model classes
            Universe object = (Universe) objects.get(i);
            // adjust the coordinates of the pipes and other objects
            if (limit >= 0 && limit <= fieldLimit) {
                object.setCoordX(object.getCoordX() - slide);
            }
            // only display objects which are alive or visible
            if (object.getStatus() != Status.DEAD) {
                gd.drawImage(object.getImage(), object.getCoordX(), object.getCoordY(), null);
            }
        }
    }

    // inform the player that they are running out of time
    public void outOfTime(int time) {
        if (time < 30 && time > 20 && limit < 3000 && outoftime) {
            Audio.playSound("/sounds/outoftime_sound.wav");
            outoftime = false;
        }
    }

    /**
     * a method which handles the end of the game
     *
     * @param g
     */
    public void gameOver(Graphics2D g) {
        if ((nario.getStatus() == Status.BOOM || nario.getStatus() == Status.DEAD) && timer.getTimer() > 0) {
            g.setFont(new Font("Consolas", Font.BOLD, 45));
            int x = Main.GAMEWIDTH / 2 - 400;
            int y = 220;
            String text = "Game Over! Press Escape to Resume!";
            //outline
            g.setColor(Color.BLACK);
            g.drawString(text, x + 2, y - 2);
            g.drawString(text, x + 2, y + 2);
            g.drawString(text, x - 2, y - 2);
            g.drawString(text, x - 2, y + 2);

            g.setColor(Color.WHITE);
            g.drawString(text, x, y);
        } else if ((Level.getLevel() == GameLevel.GroundLevel || Level.getLevel() == GameLevel.BaseLevel) && timer.getTimer() <= 0 && (Score.getScore() < finalScore || limit < winningLimit)) {
            loser(g);
        } else if ((Level.getLevel() == GameLevel.GroundLevel || Level.getLevel() == GameLevel.BaseLevel) && timer.getTimer() > 0 && Score.getScore() >= finalScore && limit >= winningLimit) {
            winner(g);
        } else if (Level.getLevel() == GameLevel.MediumLevel && timer.getTimer() <= 0 && (limit < winningLimit)) {
            loser(g);
        } else if (Level.getLevel() == GameLevel.MediumLevel && timer.getTimer() > 0 && limit >= winningLimit) {
            winner(g);
        }
        if (nario.getStatus() == Status.DEAD && timedeath) {
            Audio.stop();
            Audio.playSound("/sounds/nario_dies_sound.wav");
            timedeath = false;
        }
    }

    public void loser(Graphics2D g) {
        nario.setStatus(Status.DEAD);

        g.setFont(new Font("Consolas", Font.BOLD, 35));
        //1st text
        int x = Main.GAMEWIDTH / 2 - 380;
        int y = 200;
        String text = "Game Over! Press Escape to Resume!";
        //outline
        g.setColor(Color.BLACK);
        g.drawString(text, x + 2, y - 2);
        g.drawString(text, x + 2, y + 2);
        g.drawString(text, x - 2, y - 2);
        g.drawString(text, x - 2, y + 2);

        g.setColor(Color.WHITE);
        g.drawString(text, x, y);

        //2nd text
        x = Main.GAMEWIDTH / 2 - 230;
        y = 230;
        text = "You ran out of time!";
        g.setColor(Color.BLACK);
        g.drawString(text, x + 2, y - 2);
        g.drawString(text, x + 2, y + 2);
        g.drawString(text, x - 2, y - 2);
        g.drawString(text, x - 2, y + 2);

        g.setColor(Color.WHITE);
        g.drawString(text, x, y);
    }

    // winner method
    public void winner(Graphics2D g) {
        // stop all the threads
        Bird.setBirdStop(true);
        Brick.setBrickStop(true);
        Coin.setCoinStop(true);
        Mushroom.setMushStop(true);
        Pipe.setPipeStop(true);
        Turtle.setTurtleStop(true);

        if (winningsound) {
            Audio.stop();
            Audio.playSound("/sounds/stage_done.wav");
            winningsound = false;
        }
        //1st text
        int x = Main.GAMEWIDTH / 2 - 320;
        int y = 200;
        String text = "Press Enter to go to the next level.";
        g.setFont(new Font("Consolas", Font.BOLD, 35));
        Keyboard.setGameState(GameState.Start);
        if (Level.getLevel() == GameLevel.MediumLevel) {
            x = Main.GAMEWIDTH / 2 - 470;
            g.setFont(new Font("Consolas", Font.BOLD, 20));
            text = "Congratulations! You Won. Press escape to resume";
            Keyboard.setGameState(GameState.Resume);
        }
        nario.setStatus(Status.WON);
        g.setFont(new Font("Consolas", Font.BOLD, 35));


        //outline
        g.setColor(Color.BLACK);
        g.drawString(text, x + 2, y - 2);
        g.drawString(text, x + 2, y + 2);
        g.drawString(text, x - 2, y - 2);
        g.drawString(text, x - 2, y + 2);

        g.setColor(Color.WHITE);
        g.drawString(text, x, y);
    }

}
