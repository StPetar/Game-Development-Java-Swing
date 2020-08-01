/**
 * The Genesis. this is the main class. the starting point of the application
 */
package view;

import view.welcome.WelcomeScene;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// the starting point of the Super Nario game
public class Main implements Runnable {
    // the executors  framework to manage the threads of this application and improve its performance
    public static final ExecutorService threadManager = Executors.newCachedThreadPool();
    public static final int GAMEWIDTH = 1000;
    public static final int GAMEHEIGHT = 480;
    public static final int GROUNDFLOOR = 403;
    public static final int BASEFLOOR = 403;
    public static final int MEDIUMFLOOR = 373;
    private static WelcomeScene welcomeScene;
    private static JFrame gameWindow;

    public static void main(String[] args) {
        // this is the Genesis: it starts here. execute the run method of this class
        threadManager.execute(new Main());
    }

    // a method to return the welcome scene
    public static WelcomeScene getWelcomeScene() {
        return welcomeScene;
    }

    // a method which returns the game window
    public static JFrame getWindow() {
        return gameWindow;
    }

    // a method which enables the welcome scene to regain the foreground
    public static void welcomeForeground() {
        gameWindow.setContentPane(welcomeScene);
        // make the game window visible
        gameWindow.setVisible(true);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        // create the window for the game
        gameWindow = new JFrame();
        // configure the game window
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set the size of the game window
        gameWindow.setSize(GAMEWIDTH, GAMEHEIGHT);
        // make it non-resizable
        gameWindow.setResizable(false);
        // make it always appear on top
        gameWindow.setAlwaysOnTop(true);
        // center the game window on the screen
        gameWindow.setLocationRelativeTo(null);
        // display the welcome window
        welcomeScene = WelcomeScene.getWelcomeScene();
        // set the content of the game window
        gameWindow.setContentPane(welcomeScene);
        // make the game window visible
        gameWindow.setVisible(true);
    }

}
