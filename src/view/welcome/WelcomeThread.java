package view.welcome;


import enumeration.GameLevel;
import view.Level;
import view.Main;
import view.baseGame.BaseLevel;
import view.groundGame.GroundLevel;
import view.mediumGame.MediumLevel;

import javax.swing.*;

/**
 * a class responsible for animating the welcome window
 */
public class WelcomeThread implements Runnable {
    public static final int delay = 2;
    private static boolean gameStart = false;
    private static boolean stop = false;

    /**
     * a method which can be used to start the game by setting the variable to true
     */

    public static void setGameStart(boolean g) {
        gameStart = g;
    }

    // stop the thread when needed
    public static void setWelcomeStop(boolean s) {
        stop = s;
    }

    @Override
    public void run() {

        WelcomeScene welcomeScene = Main.getWelcomeScene();
        while (true) {
            if (stop) break;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
            // a method which simply calls the paintComponent() method of the caller
            welcomeScene.repaint();
            // display the next jpanel when the user presses the enter
            if (gameStart) {
                // get an instance of the main window
                JFrame window = Main.getWindow();
                // start the level animation
                Level level = null;
                GameLevel game = Level.getLevel();
                switch (game) {
                    case GroundLevel:
                        level = GroundLevel.getGroundLevelInstance();
                        break;
                    case BaseLevel:
                        level = BaseLevel.getBaseLevelInstance();
                        break;
                    case MediumLevel:
                        level = MediumLevel.getMediumLevelInstance();
                        break;
                }
                // start the game: the power of polymorphism
                level.start();
                // display the base level for the user
                window.setContentPane(level);
                window.setVisible(true);
                gameStart = false;
                // stop the thread and make memory available for reuse
                break;
            }
        }
    }

}
