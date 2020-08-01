package view.groundGame;

import audio.Audio;
import controllers.*;
import enumeration.GameLevel;
import enumeration.JumpState;
import enumeration.UniverseState;
import model.Bullet;
import model.Nario;
import model.Water;
import model.World;
import view.Level;
import view.Main;
import view.Score;
import view.Timer;
import view.baseGame.BaseLevel;
import view.baseGame.BaseLevelThread;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A class responsible for display the ground level
 */
@SuppressWarnings("serial")
public class GroundLevel extends Level {
    // there can be only one instance of this class in the game
    private static GroundLevel groundLevel;
    private static ArrayList<Water> waters;
    private Keyboard keyboard;
    private boolean focus = true;

    // constructor
    private GroundLevel() {
        // set the level
        Level.setLevel(GameLevel.GroundLevel);
        // declare the winning limit
        winningLimit = 5833;
        // declare the final score: 180
        finalScore = 180;
        winningsound = true;
        // set out of time to true
        outoftime = true;
        // instantiate world objects
        limit = 0;
        move = true;
        slide = 0;
        world1 = new World(0, 0, "/images/background1.png");
        world2 = new World(1000, 0, "/images/background1.png");
        // fieldLimit
        fieldLimit = 5833;
        // set the floor
        World.setFloor(Main.GROUNDFLOOR);
        // instantiate the keyboard object
        keyboard = new Keyboard();
        // instance of nario
        nario = Nario.getNario(126, 353, 28, 50, "/images/nariostillright.png", UniverseState.StillRight, JumpState.None, World.getFloor(), World.getCeiling());
        // instantiate objects
        pipes = PipeFactory.getPipes();
        bricks = BrickFactory.getBricks();
        coins = CoinFactory.getCoins();
        turtles = TurtleFactory.getTurtles();
        mushrooms = MushFactory.getMushrooms();
        decorations = DecorationFactory.getDocrations();
        waters = WaterFactory.getWaters();

        // create an instance of the timer and start it: 90
        timer = new Timer(90);
        timer.start();
        // reset the score
        Score.resetScore(0);
        timedeath = true;
    }

    // singleton pattern: there can be only one instance of this class in the game, otherwise a lot of things will go wrong
    public static GroundLevel getGroundLevelInstance() {
        if (groundLevel == null) {
            // creates new instance
            groundLevel = new GroundLevel();
        }
        return groundLevel;
    }

    // a method to reset everything in the ground level class
    public static void setGroundLevel(GroundLevel level) {
        // reset world
        world1.setWorldX(0);
        world1.setWorldY(0);
        world2.setWorldX(1000);
        world2.setWorldY(0);
        // reset the factories: make them produce again
        PipeFactory.resetPipes();
        BrickFactory.resetBricks();
        CoinFactory.resetCoins();
        DecorationFactory.resetDecorations();
        TurtleFactory.resetTurtles();
        MushFactory.resetMushrooms();
        WaterFactory.resetWaters();
        Bullet.resetBullets();
        // reposition nario
        limit = 0;
        Nario.setNario(null);
        // set high level to null
        groundLevel = level;
    }

    // a next level method as this is the ground level
    public static void nextLevel() {
        // enable model threads
        // stop the ground level thread: release memory
        GroundLevelThread.setStop(true);
        // get an instance of the main window
        JFrame window = Main.getWindow();
        // start (next level) the base level animation
        BaseLevel baseLevel = BaseLevel.getBaseLevelInstance();
        Audio.stop();
        Audio.playSound("/sounds/stage_done.wav");
        // set up the next level
        // execute the run method in the base level thread class
        Main.threadManager.execute(new BaseLevelThread());
        // display the base level for the user
        window.setContentPane(baseLevel);
        window.setVisible(true);
    }

    // start method to start this game level
    public void start() {
        Audio.stop();
        Audio.playSound("/sounds/stage_done.wav");
        // execute the run method of the HighLevelThread class
        Main.threadManager.execute(new GroundLevelThread());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // acquire focus
        if (focus) {
            // acquire focus
            this.setFocusable(true);
            this.requestFocusInWindow();
            // register a keyboard listener
            this.addKeyListener(keyboard);
            focus = false;
        }
        // acquire the 2D graphics object
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);
        // display background images
        continuousScrolling();
        displayWorld(g2, fieldLimit);
        // check for collisions between actors and obstacles
        NarioController.collision();
        // collision between bullets and other things
        BulletController.collision(pipes);
        BulletController.collision(bricks);
        BulletController.collision(coins);
        BulletController.collision(turtles);
        BulletController.collision(mushrooms);

        // check for collisions between opponents and obstacles
        MushFactory.collisionDetection();
        TurtleFactory.collisionDetection();
        // collisions between water and nario
        WaterFactory.collision(nario);
        // display pipes
        displayObjects(g2, pipes);
        displayObjects(g2, waters);
        // display decorations
        displayObjects(g2, decorations);
        // display bricks
        displayObjects(g2, bricks);
        // display coins
        displayObjects(g2, coins);
        // display opponents: turtles
        displayObjects(g2, turtles);
        // remove dead turtles
        TurtleFactory.removeTurtles();
        // display mushrooms
        displayObjects(g2, mushrooms);
        // remove dead mushrooms
        MushFactory.removeMushrooms();
        // display bullets
        bullets = Bullet.getBullets();
        displayObjects(g2, bullets);
        // remove dead or invisible bullets. free the memory
        Bullet.deleteBullets();
        // display nario
        displayActors(nario, g2);

        // display the current score of the game
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Consolas", Font.BOLD, 20));
        g2.drawString(finalScore + " Points Required!", 10, 20);
        g2.drawString("Accrued Points: " + Score.getScore(), 10, 40);
        // display the remaining time
        g2.drawString("Time remaining: " + timer.getTimer(), Main.GAMEWIDTH - 230, 20);
        // handle the end of the game
        gameOver(g2);
        // alert the actor that they are out of time
        outOfTime(timer.getTimer());
    }
}
