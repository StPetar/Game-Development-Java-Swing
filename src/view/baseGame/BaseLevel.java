package view.baseGame;

import audio.Audio;
import controllers.*;
import enumeration.GameLevel;
import enumeration.JumpState;
import enumeration.UniverseState;
import model.Bird;
import model.Bullet;
import model.Nario;
import model.World;
import view.Level;
import view.Main;
import view.Score;
import view.Timer;
import view.mediumGame.MediumLevel;
import view.mediumGame.MediumLevelThread;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A class which directs the animation of the base level. it displays all its elements
 */
@SuppressWarnings("serial")
public class BaseLevel extends Level {
    // there can be only one instance of this class in the game
    private static BaseLevel baseLevel;
    private Keyboard keyboard;
    private boolean focus = true;
    private ArrayList<Bird> birds;

    // constructor
    private BaseLevel() {
        // instantiate birds
        birds = new ArrayList<Bird>();
        // set the level
        Level.setLevel(GameLevel.BaseLevel);
        // declare the winning limit
        winningLimit = 5626;
        // declare the final score: 120
        finalScore = 120;
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
        fieldLimit = 5625;
        // set the floor
        World.setFloor(Main.BASEFLOOR);
        // instantiate the keyboard object
        keyboard = new Keyboard();
        // instance of nario
        nario = Nario.getNario(260, 353, 28, 50, "/images/nariostillright.png", UniverseState.StillRight, JumpState.None, World.getFloor(), World.getCeiling());
        // instantiate objects
        pipes = PipeFactory.getPipes();
        bricks = BrickFactory.getBricks();
        coins = CoinFactory.getCoins();
        turtles = TurtleFactory.getTurtles();
        mushrooms = MushFactory.getMushrooms();
        decorations = DecorationFactory.getDocrations();
        birds = BirdFactory.getBirds();
        // create an instance of the timer and start it: 180
        timer = new Timer(180);
        timer.start();
        // reset the score
        Score.resetScore(0);
        timedeath = true;
    }

    // singleton pattern: there can be only one instance of this class in the game, otherwise a lot of things will go wrong
    public static BaseLevel getBaseLevelInstance() {
        if (baseLevel == null) {
            // creates new instance
            baseLevel = new BaseLevel();
        }
        return baseLevel;
    }

    // a method to go to the next level
    public static void nextLevel() {
        // enable the model threads
		/*
		 * Bird.setBirdStop(false);
		Coin.setCoinStop(false);
		Mushroom.setMushStop(false);
		Pipe.setPipeStop(false);
		Turtle.setTurtleStop(false);
		Brick.setBrickStop(false);*/
        // release memory by killing the thread of this level
        BaseLevelThread.setStop(true);
        // get an instance of the main window
        JFrame window = Main.getWindow();
        // start the base level animation and re-create everything anew
        MediumLevel mediumLevel = MediumLevel.getMediumLevelInstance();
        Audio.stop();
        Audio.playSound("/sounds/stage_done.wav");
        // set up the next level
        Main.threadManager.execute(new MediumLevelThread());
        // display the medium level for the user
        window.setContentPane(mediumLevel);
        window.setVisible(true);
    }

    // a method used to reset the base level
    public static void setBaseLevel(BaseLevel level) {
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
        BirdFactory.resetBirds();
        MushFactory.resetMushrooms();
        Bullet.resetBullets();
        // reposition nario
        limit = 0;
        Nario.setNario(null);
        baseLevel = level;
    }

    // start method to start the game
    public void start() {
        // start sound for the base level
        Audio.stop();
        Audio.playSound("/sounds/stage_done.wav");
        Main.threadManager.execute(new BaseLevelThread());
    }

    // a method which displays graphics on the screen. this method is repeatedly called in the baselevelthread class.
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
        Graphics2D g2 = (Graphics2D) g.create();
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
        BulletController.collision(birds);
        // check for collisions between opponents and obstacles
        MushFactory.collisionDetection();
        TurtleFactory.collisionDetection();
        // display pipes
        displayObjects(g2, pipes);
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
        // display deadly birds
        displayObjects(g2, birds);
        // remove dead birds for the list of birds
        BirdFactory.removeBirds();
        // display the current score of the game
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Consolas", Font.BOLD, 20));
        g2.drawString(finalScore + " Points required!", 10, 20);
        g2.drawString("Accrued Points: " + Score.getScore(), 10, 40);
        // display the remaining time
        g2.drawString("Time remaining: " + timer.getTimer(), Main.GAMEWIDTH - 230, 20);
        // handle the end of the game
        gameOver(g2);
        // alert the actor that they are out of time
        outOfTime(timer.getTimer());
    }
}
