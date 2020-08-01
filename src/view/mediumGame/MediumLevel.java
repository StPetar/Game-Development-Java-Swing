package view.mediumGame;

import audio.Audio;
import controllers.*;
import enumeration.GameLevel;
import enumeration.JumpState;
import enumeration.Shooter;
import enumeration.UniverseState;
import model.Bullet;
import model.Nario;
import model.Water;
import model.World;
import view.Level;
import view.Main;
import view.Score;
import view.Timer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class marks the starting point of the medium level
 */
@SuppressWarnings("serial")
public class MediumLevel extends Level {
    private static MediumLevel mediumLevel;
    private static ArrayList<Water> waters;
    private static Shooter shoot = Shooter.NoShoot;
    private boolean focus = true;
    private ArrayList<Bullet> pipeBullets;
    // the main controller: the keyboard object
    private Keyboard keyboard;

    private MediumLevel() {
        // set focus
        focus = true;
        // set the level
        Level.setLevel(GameLevel.MediumLevel);
        // reset the score
        Score.resetScore(0);
        // field limit
        fieldLimit = 6628;
        // initialisations
        winningsound = true;
        // winning limit
        winningLimit = fieldLimit;
        // set the floor
        World.setFloor(Main.MEDIUMFLOOR);
        // instantiate timer variable: 210
        timer = new Timer(210);
        // start the timer
        timer.start();
        // set the level
        keyboard = new Keyboard();
        // instantiate the keyboard class
        // instantiate world objects
        world1 = new World(0, 0, "/images/background_10.png");
        world2 = new World(1000, 0, "/images/background_10.png");
        limit = 0;
        move = true;
        // instance of nario
        nario = Nario.getNario(130, World.getFloor() - 50, 28, 50, "/images/m_nariostillright.png", UniverseState.StillRight, JumpState.None, World.getFloor(), World.getCeiling());
        decorations = DecorationFactory.getDocrations();
        pipes = PipeFactory.getPipes();
        waters = WaterFactory.getWaters();
        bricks = BrickFactory.getBricks();
        coins = CoinFactory.getCoins();
        pipeBullets = Bullet.getPipeBullets();
        turtles = TurtleFactory.getTurtles();
        mushrooms = MushFactory.getMushrooms();
        bullets = Bullet.getBullets();
        timedeath = true;
    }

    // singleton: a method to get the single instance of this class
    public static MediumLevel getMediumLevelInstance() {
        if (mediumLevel == null) {
            mediumLevel = new MediumLevel();
        }
        return mediumLevel;
    }

    public static Shooter isShoot() {
        return shoot;
    }

    // paint component method to show things on the screen

    public static void setShoot(Shooter s) {
        shoot = s;
    }

    // a method used to reset the base level
    public static void setMediumLevel(MediumLevel level) {
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
        mediumLevel = level;
    }

    // start method to start the game
    public void start() {
        // start sound for the base level
        Audio.stop();
        Audio.playSound("/sounds/stage_done.wav");
        Main.threadManager.execute(new MediumLevelThread());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // acquire the focus
        if (focus) {
            // acquire the focus
            this.setFocusable(true);
            this.requestFocusInWindow();
            // register a keyboard listener
            this.addKeyListener(keyboard);
            focus = false;
        }
        // here enter your code
        Graphics2D g2 = (Graphics2D) g;
        // display background
        continuousScrolling();
        displayWorld(g2, fieldLimit);
        // display decoration
        displayObjects(g2, decorations);
        // display pipes
        displayObjects(g2, pipes);
        displayObjects(g2, waters);
        // collisions between water and nario
        WaterFactory.collision(nario);
        displayObjects(g2, bricks);
        // display pipe bullets
        displayObjects(g2, pipeBullets);
        // collision between nario and the bullets
        BulletController.collision(nario);
        // display coins
        displayObjects(g2, coins);
        // display turtles
        displayObjects(g2, turtles);
        // remove dead turtles
        TurtleFactory.removeTurtles();
        // check for turtle collision
        TurtleFactory.collisionDetection();
        // display mushrooms
        displayObjects(g2, mushrooms);
        // remove dead turtles
        MushFactory.removeMushrooms();
        MushFactory.collisionDetection();

        // check for collisions between actors and obstacles
        NarioController.collision();
        // display bullets
        displayObjects(g2, bullets);
        displayObjects(g2, bullets);
        // remove dead or invisible bullets. free the memory
        Bullet.deleteBullets();
        // display nario
        displayActors(nario, g2);
        // collision between bullets and other things
        BulletController.collision(pipes);
        BulletController.collision(bricks);
        BulletController.collision(coins);
        BulletController.collision(turtles);
        BulletController.collision(mushrooms);
        // display nario in his shooting position where applicable
        displayShooter(g2);
        // alert the actor that they are out of time
        outOfTime(timer.getTimer());
        // display the current score of the game
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Consolas", Font.BOLD, 20));
        g2.drawString(finalScore + " Points required!", 10, 20);
        g2.drawString("Accrued Points: " + Score.getScore(), 10, 40);
        // display the remaining time
        g2.drawString("Time remaining: " + timer.getTimer(), Main.GAMEWIDTH - 230, 20);
        // game over handling
        gameOver(g2);
    }

    // a method which display shooter nario
    public void displayShooter(Graphics2D g2) {
        if (shoot == Shooter.Shoot && (nario.getState() == UniverseState.StillRight || nario.getJumpState() == JumpState.JumpRight)) {
            nario.setImage((new ImageIcon(getClass().getResource("/images/m_narioshootright.png")).getImage()));
            g2.drawImage(nario.getImage(), nario.getCoordX(), nario.getCoordY(), null);
        } else if (shoot == Shooter.Shoot && (nario.getState() == UniverseState.StillLeft || nario.getJumpState() == JumpState.JumpLeft)) {
            nario.setImage((new ImageIcon(getClass().getResource("/images/m_narioshootleft.png")).getImage()));
            g2.drawImage(nario.getImage(), nario.getCoordX(), nario.getCoordY(), null);
        }
        if (shoot == Shooter.StopShoot) {
            shoot = Shooter.NoShoot;
        }
    }
}
