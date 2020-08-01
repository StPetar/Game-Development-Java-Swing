package controllers;

import enumeration.*;
import model.*;
import view.Timer;
import view.*;
import view.baseGame.BaseLevel;
import view.baseGame.BaseLevelThread;
import view.groundGame.GroundLevel;
import view.groundGame.GroundLevelThread;
import view.mediumGame.MediumLevel;
import view.mediumGame.MediumLevelThread;
import view.welcome.WelcomeScene;
import view.welcome.WelcomeThread;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This is the main controller. the class enables the user to play the game from the keyboard
 */
public class Keyboard implements KeyListener {
    private static int key_value;
    private static GameState gameState = GameState.Start;
    private Actor nario;

    // a method which delivers the value of variable escape
    public static GameState getGameState() {
        return gameState;
    }

    // a method to set the escape variable
    public static void setGameState(GameState g) {
        gameState = g;
    }

    public static int getKey_value() {
        return key_value;
    }

    public static void setKey_value(int key_value) {
        Keyboard.key_value = key_value;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // reset everything after the user attempts to go out of scope
        if (Level.getLimit() == -1) {
            Level.setLimit(0);
            // reset the position of the world
            Level.getWorld1().setWorldX(0);
            Level.getWorld2().setWorldX(Main.GAMEWIDTH);
        } else if (Level.getLimit() == Level.getFieldLimit() + 1) {
            Level.setLimit(Level.getFieldLimit());
            // reset the position of the world
            Level.getWorld1().setWorldX(0);
            Level.getWorld2().setWorldX(Main.GAMEWIDTH);
        }

        switch (e.getKeyCode()) {
            // pause the game
            case KeyEvent.VK_SPACE:
                if (Timer.isPause()) {
                    Timer.setPause(false);
                } else {
                    Timer.setPause(true);
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                // shooting possibilities for nario
                nario = Nario.getNario();
                if (nario != null && nario.getStatus() == Status.ALIVE) {
                    GameLevel level = Level.getLevel();
                    switch (level) {
                        case GroundLevel:
                            Bullet.createBullet();
                            break;
                        case BaseLevel:
                            if (Score.getScore() >= 1) {
                                Bullet.createBullet();
                                Score.setScore(-1);
                            }
                            break;
                        case MediumLevel:
                            if (Score.getScore() >= 2) {
                                Bullet.createBullet();
                                Score.setScore(-2);
                                MediumLevel.setShoot(Shooter.Shoot);
                            }
                            break;
                    }
                }
                break;
            case KeyEvent.VK_ENTER:
                if (gameState == GameState.Start) {
                    // with the value started, the keys enter and escape can only influence the game again after nario dies
                    gameState = GameState.Started;
                    if ((nario == null && Level.getLevel() == GameLevel.GroundLevel)) {
                        // start a new level and enable the run method
                        GroundLevelThread.setStop(false);
                        WelcomeThread.setGameStart(true);
                    } else if ((nario == null && Level.getLevel() == GameLevel.BaseLevel)) {
                        // start a new level and enable the run method
                        enableThreads();
                        BaseLevel.setBaseLevel(null);
                        BaseLevelThread.setStop(false);
                        WelcomeThread.setGameStart(true);
                    } else if ((nario == null && Level.getLevel() == GameLevel.MediumLevel)) {
                        // enable all model threads
                        enableThreads();
                        MediumLevel.setMediumLevel(null);
                        // enable the level's thread
                        MediumLevelThread.setStop(false);
                        // start the game
                        WelcomeThread.setGameStart(true);
                    }
                    // go to the next level when possible (base): nario wins the ground level
                    else if (nario != null && nario.getStatus() == Status.WON && Level.getLevel() == GameLevel.GroundLevel) {
                        // reset everything for the base level and enable its run method
                        BaseLevelThread.setStop(false);
                        BaseLevel.setBaseLevel(null);
                        enableThreads();
                        GroundLevel.nextLevel();
                        nario.setStatus(Status.ALIVE);
                    }
                    // go to the next level (medium) when possible: nario wins the base level
                    else if (nario != null && nario.getStatus() == Status.WON && Level.getLevel() == GameLevel.BaseLevel) {
                        // reset everything for the medium level and enable its run method to run indefinitely
                        MediumLevelThread.setStop(false);
                        MediumLevel.setMediumLevel(null);
                        enableThreads();
                        BaseLevel.nextLevel();
                        nario.setStatus(Status.ALIVE);
                    }
                }
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                // verify that nario is still blocked in the air
                block(e.getKeyCode());
                // remember the value of the key which may have cause the blocking of the screen
                key_value = e.getKeyCode();
                nario = Nario.getNario();
                // set the state of the actor
                if (nario != null && nario.getStatus() == Status.ALIVE && !Timer.isPause()) {
                    nario.setState(UniverseState.WalkRight);
                    //shift the background images to the left
                    Level.setSlide(1);
                }
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                // verify blocking
                block(e.getKeyCode());
                // remember the value of the key which may have cause the blocking
                key_value = e.getKeyCode();
                nario = Nario.getNario();
                // set the state of the actor
                if (nario != null && nario.getStatus() == Status.ALIVE && !Timer.isPause()) {
                    nario.setState(UniverseState.WalkLeft);
                    //shift the background images to the right
                    Level.setSlide(-1);
                }
                break;

            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (!Level.isMove() && SolidObstacle.getKey_id() != e.getKeyCode()) {
                    Level.setMove(true);
                }
                // remember the value of the key which may have cause the blocking
                key_value = KeyEvent.VK_UP;
                nario = Nario.getNario();
                if (nario != null && (nario.getState() == UniverseState.StillRight || nario.getState() == UniverseState.WalkRight && !Timer.isPause())) {
                    nario.setJumpState(JumpState.JumpRight);
                } else if (nario != null && (nario.getState() == UniverseState.StillLeft || nario.getState() == UniverseState.WalkLeft && !Timer.isPause())) {
                    nario.setJumpState(JumpState.JumpLeft);
                }
                break;
            case KeyEvent.VK_ESCAPE:
                // resume at the beginning
                if (gameState == GameState.Resume) {
                    if (Level.getLevel() == GameLevel.BaseLevel || Level.getLevel() == GameLevel.MediumLevel) {
                        Level.setLevel(GameLevel.GroundLevel);
                    }
                    // re-enable all the threads
                    enableThreads();
                    // reset the high level
                    GroundLevel.setGroundLevel(null);
                    // display the welcome window
                    welcomeWindow();
                    gameState = GameState.Start;
                }
                break;
            case KeyEvent.VK_H:
                // go the help page
                JFrame frame = Main.getWindow();
                frame.setContentPane(new Help());
                frame.setVisible(true);
                break;
            case KeyEvent.VK_C:
                // set the state to start if it is resume
                if (gameState == GameState.Resume && Level.getLevel() != GameLevel.GroundLevel) {
                    gameState = GameState.Start;
                    // display the welcome window
                    welcomeWindow();
                }
                break;
        }
    }

    // a method to place the welcome scene on the foreground
    public void welcomeWindow() {
        // make the welcome scene regain focus
        WelcomeScene.setFocus(true);
        // restart the game. go back to the welcome page
        Main.threadManager.execute(new WelcomeThread());
        Main.welcomeForeground();
    }

    // a method to enable threads
    public void enableThreads() {
        Bird.setBirdStop(false);
        Coin.setCoinStop(false);
        Mushroom.setMushStop(false);
        Pipe.setPipeStop(false);
        Turtle.setTurtleStop(false);
        Brick.setBrickStop(false);
    }

    // a method which unblocks the screen if the screen should no longer be blocked. for example when nario hits an obstacle in the air. the screen gets blocked. this
    // method then unblocks it
    public void block(int pressedKey) {
        if ((nario != null && SolidObstacle.getJumpObstacle() != null && (nario.getCoordY() > SolidObstacle.getJumpObstacle().getCoordY() + SolidObstacle.getJumpObstacle().getHeight()
                || nario.getCoordY() + nario.getHeight() < SolidObstacle.getJumpObstacle().getCoordY())) || (!Level.isMove() && SolidObstacle.getKey_id() != pressedKey)) {
            BaseLevel.setMove(true);
        }
        SolidObstacle.setJumpObstacle(null);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Level.setSlide(0);
        // reset the state
        if (nario != null && (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A)) {
            Level.setSlide(0);
            switch (nario.getState()) {
                case WalkRight:
                    nario.setState(UniverseState.StillRight);
                    break;
                case WalkLeft:
                    nario.setState(UniverseState.StillLeft);
                    break;
                default:
                    break;
            }
        }
        if ((e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)) {
            MediumLevel.setShoot(Shooter.StopShoot);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
