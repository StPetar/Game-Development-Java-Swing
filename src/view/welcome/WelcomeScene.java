package view.welcome;

import audio.Audio;
import controllers.Keyboard;
import view.Main;

import javax.swing.*;
import java.awt.*;

/**
 * A class responsible for display the welcome window
 */
@SuppressWarnings("serial")
public class WelcomeScene extends JPanel {
    private static boolean focus = true;
    private static WelcomeScene welcomeScene;
    private final int blinkingLimit = 100;
    private final int blinkingHelpLimit = 800;
    private int blinkingCounter;
    private int blinkingHelp;
    private int jumpingNario = 252;
    private Keyboard keyboard;

    private WelcomeScene() {
        // welcome song
        Audio.playSound("/sounds/gamestart.wav");
        // create a keyboard object
        keyboard = new Keyboard();
        setFocusable(true);
        requestFocusInWindow();
        // register a keyboard listener
        addKeyListener(keyboard);
        // execute the run method of the WelcomeThread class
        Main.threadManager.execute(new WelcomeThread());
    }

    // singleton: a single instance of the welcome scene to be available
    public static WelcomeScene getWelcomeScene() {
        // gain focus
        if (welcomeScene == null) {
            welcomeScene = new WelcomeScene();
        }
        return welcomeScene;
    }

    // enable the welcome screen to gain screen focus
    public static void setFocus(boolean f) {
        focus = f;
    }

    /**
     * A method responsible for displaying the initial window to the user
     */
    @Override
    public void paintComponent(Graphics g) {
        // acquire focus
        if (focus) {
            // acquire focus
            this.setFocusable(true);
            this.requestFocusInWindow();
            // register a keyboard listener
            this.addKeyListener(keyboard);
            focus = false;
        }
        // call the super class
        super.paintComponent(g);
        // acquire the 2D graphics object
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setFont(new Font("Consolas", Font.BOLD, 22));
        // set the background image for the welcome page
        g2.drawImage((new ImageIcon(getClass().getResource("/images/background1.png"))).getImage(), 0, 0, null);
        // display the help on the screen
        enemyHelp(g2);
        g2.setFont(new Font("Consolas", Font.BOLD, 22));
        blinkingHelp(g2);

        // show the blinking start message
        displayStartMessage(g2);
        // display a welcome message and mario for the user
        g2.setColor(Color.BLACK);
        g2.fillRect(270, 40, 400, 50);
        g2.setColor(Color.WHITE);
        g2.drawString("Welcome to the Super Nario Game", 285, 73);
        // display mario
        g2.drawImage((new ImageIcon(getClass().getResource("/images/nario.png"))).getImage(), 430, jumpingNario, null);
    }

    /**
     * A method to display a blinking message informing the user on how to get started
     */
    public void displayStartMessage(Graphics2D g2) {

        // increment the counter
        ++blinkingCounter;
        if (blinkingCounter <= blinkingLimit) {
            g2.drawString("Press Enter to get started", 300, 220);
            ++jumpingNario;
        } else if (blinkingCounter > 2 * blinkingLimit) {
            blinkingCounter = 0;
            jumpingNario = 252;
        }
    }

    /**
     * a method which display a blinking help for the users
     *
     * @param g2
     */
    public void blinkingHelp(Graphics2D g2) {
        // draw help rectangle
        g2.setColor(Color.BLACK);
        g2.fillRect(30, 40, 200, 270);
        g2.setColor(Color.WHITE);
        g2.drawString("CONTROLS", 80, 80);
        // display help
        ++blinkingHelp;
        if (blinkingHelp < blinkingHelpLimit / 4) {
            g2.setColor(Color.GRAY);
            g2.drawString("LEFT      A", 60, 120);
            g2.setColor(Color.WHITE);
            g2.drawString("RIGHT     D", 60, 160);
            g2.drawString("JUMP      W", 60, 200);
            g2.drawString("SHOOT     S", 60, 240);
            g2.drawString("HELP      H", 60, 280);
        } else if (blinkingHelp < blinkingHelpLimit / 2) {
            g2.setColor(Color.WHITE);
            g2.drawString("LEFT      A", 60, 120);
            g2.setColor(Color.GRAY);
            g2.drawString("RIGHT     D", 60, 160);
            g2.setColor(Color.WHITE);
            g2.drawString("JUMP      W", 60, 200);
            g2.drawString("SHOOT     S", 60, 240);
            g2.drawString("HELP      H", 60, 280);
        } else if (blinkingHelp < (blinkingHelpLimit - (blinkingHelpLimit / 4))) {
            g2.setColor(Color.WHITE);
            g2.drawString("LEFT      A", 60, 120);
            g2.drawString("RIGHT     D", 60, 160);
            g2.setColor(Color.GRAY);
            g2.drawString("JUMP      W", 60, 200);
            g2.setColor(Color.WHITE);
            g2.drawString("SHOOT     S", 60, 240);
            g2.drawString("HELP      H", 60, 280);
        } else if (blinkingHelp < (blinkingHelpLimit)) {
            g2.setColor(Color.WHITE);
            g2.drawString("LEFT      A", 60, 120);
            g2.drawString("RIGHT     D", 60, 160);
            g2.drawString("JUMP      W", 60, 200);
            g2.setColor(Color.GRAY);
            g2.drawString("SHOOT     S", 60, 240);
            g2.setColor(Color.WHITE);
            g2.drawString("HELP      H", 60, 280);
            g2.setColor(Color.WHITE);
        } else if (blinkingHelp < (blinkingHelpLimit + blinkingHelp / 4)) {
            g2.setColor(Color.WHITE);
            g2.drawString("LEFT      A", 60, 120);
            g2.drawString("RIGHT     D", 60, 160);
            g2.drawString("JUMP      W", 60, 200);
            g2.drawString("SHOOT     S", 60, 240);
            g2.setColor(Color.GRAY);
            g2.drawString("HELP      H", 60, 280);
            g2.setColor(Color.WHITE);
        } else {
            blinkingHelp = 0;
        }
    }

    public void enemyHelp(Graphics2D g2) {
        // draw help rectangle
        g2.setColor(Color.BLACK);
        g2.fillRect(Main.GAMEWIDTH - 300, 40, 275, 350);
        g2.setColor(Color.WHITE);
        g2.drawString("ENEMIES AND DANGERS", Main.GAMEWIDTH - 275, 70);
        g2.setFont(new Font("Consolas", Font.PLAIN, 15));
        g2.drawString("SHOOTING IS FREE ON THE 1ST LEVEL", Main.GAMEWIDTH - 295, 100);
        g2.drawString("BUT COSTS 1 AND 2 POINTS", Main.GAMEWIDTH - 260, 120);
        g2.drawString("ON THE 2ND AND 3RD LEVEL", Main.GAMEWIDTH - 260, 140);
        g2.drawString("THESE CAN BE TRAMPLED OR SHOT", Main.GAMEWIDTH - 275, 175);
        g2.drawImage((new ImageIcon(getClass().getResource("/images/turtlewalkright.png"))).getImage(), Main.GAMEWIDTH - 220, 180, null);
        g2.drawImage((new ImageIcon(getClass().getResource("/images/mushwalkright.png"))).getImage(), Main.GAMEWIDTH - 140, 200, null);
        g2.drawString("AND THESE CAN ONLY BE SHOT", Main.GAMEWIDTH - 265, 250);
        g2.drawImage((new ImageIcon(getClass().getResource("/images/greenturtlewalkright.png"))).getImage(), Main.GAMEWIDTH - 290, 260, null);
        g2.drawImage((new ImageIcon(getClass().getResource("/images/greenturtleflyrightclose.png"))).getImage(), Main.GAMEWIDTH - 240, 260, null);
        g2.drawImage((new ImageIcon(getClass().getResource("/images/redturtlewalkright.png"))).getImage(), Main.GAMEWIDTH - 190, 260, null);
        g2.drawImage((new ImageIcon(getClass().getResource("/images/redturtleflyrightclose.png"))).getImage(), Main.GAMEWIDTH - 140, 260, null);
        g2.drawImage((new ImageIcon(getClass().getResource("/images/mushright.png"))).getImage(), Main.GAMEWIDTH - 90, 280, null);

        g2.drawString("WATER IS DANGEROUS", Main.GAMEWIDTH - 240, 350);
        g2.drawString("NARIO CANNOT SWIM!", Main.GAMEWIDTH - 242, 370);
    }

}
