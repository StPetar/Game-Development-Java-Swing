package model;

import controllers.Keyboard;
import enumeration.*;
import view.Level;
import view.Main;
import view.baseGame.BaseLevelThread;
import view.groundGame.GroundLevelThread;
import view.mediumGame.MediumLevel;
import view.mediumGame.MediumLevelThread;

import javax.swing.*;
import java.awt.*;

/**
 * A class which models Nario and contains methods which define its behaviour
 */
public class Nario extends Actor implements Runnable {
    private static Nario nario;
    private final int delay = 120;

    // private constructor. There can only be one mario in the game
    private Nario(int coordX, int coordY, int width, int height, String img, UniverseState s, JumpState j, int f, int c) {

        super(coordX, coordY, width, height, img, s, j, f, c);
        Main.threadManager.execute(this);
    }

    /**
     * A method which returns the unique instance of nario to the caller: singleton pattern
     *
     * @return
     */
    public static Nario getNario(int x, int y, int w, int h, String img, UniverseState s, JumpState j, int f, int c) {
        if (nario == null) {
            nario = new Nario(x, y, w, h, img, s, j, f, c);
        }
        return nario;
    }


    // a function which returns nario
    public static Nario getNario() {
        return nario;
    }

    public static void setNario(Nario n) {
        nario = n;
    }

    // the death method
    public Image death() {
        return (new ImageIcon(getClass().getResource("/images/boom.png")).getImage());
    }

    // walk method for nario
    @Override
    public Image walk() {
        GameLevel level = Level.getLevel();
        String img1 = "";
        String img2 = "";
        switch (super.getState()) {
            case StillRight:
                if (level == GameLevel.GroundLevel || level == GameLevel.BaseLevel) {
                    img1 = "/images/nariostillright.png";
                    img2 = "";
                } else if (level == GameLevel.MediumLevel && MediumLevel.isShoot() == Shooter.NoShoot) {
                    img1 = "/images/m_nariostillright.png";
                    img2 = "";
                }
                break;
            case StillLeft:
                if (level == GameLevel.GroundLevel || level == GameLevel.BaseLevel) {
                    img1 = "/images/nariostillleft.png";
                    img2 = "";
                } else if (level == GameLevel.MediumLevel && MediumLevel.isShoot() == Shooter.NoShoot) {
                    img1 = "/images/m_nariostillleft.png";
                    img2 = "";
                }
                break;
            case WalkRight:
                if (level == GameLevel.GroundLevel || level == GameLevel.BaseLevel) {
                    img1 = "/images/nariostillright.png";
                    img2 = "/images/nariowalkright.png";
                } else if (level == GameLevel.MediumLevel) {
                    img1 = "/images/m_nariostillright.png";
                    img2 = "/images/m_nariowalkright.png";
                }
                break;
            case WalkLeft:
                if (level == GameLevel.GroundLevel || level == GameLevel.BaseLevel) {
                    img1 = "/images/nariostillleft.png";
                    img2 = "/images/nariowalkleft.png";
                } else if (level == GameLevel.MediumLevel) {
                    img1 = "/images/m_nariostillleft.png";
                    img2 = "/images/m_nariowalkleft.png";
                }
                break;
            default:
                img1 = "";
                img2 = "";
        }
        return super.walk(super.getState(), img1, img2);
    }

    @Override
    public Image jump() {
        GameLevel level = Level.getLevel();
        String img1 = "";
        String img2 = "";
        UniverseState landingState = null;
        switch (super.getJumpState()) {
            case JumpRight:
                if (level == GameLevel.GroundLevel || level == GameLevel.BaseLevel) {
                    img1 = "/images/nariojumpright.png";
                    img2 = "/images/nariostillright.png";
                } else if (level == GameLevel.MediumLevel && MediumLevel.isShoot() == Shooter.NoShoot) {
                    img1 = "/images/m_nariojumpright.png";
                    img2 = "/images/m_nariostillright.png";
                }
                landingState = UniverseState.StillRight;
                break;
            case JumpLeft:
                if (level == GameLevel.GroundLevel || level == GameLevel.BaseLevel) {
                    img1 = "/images/nariojumpleft.png";
                    img2 = "/images/nariowalkleft.png";
                } else if (level == GameLevel.MediumLevel && MediumLevel.isShoot() == Shooter.NoShoot) {
                    img1 = "/images/m_nariojumpleft.png";
                    img2 = "/images/m_nariostillleft.png";
                }
                landingState = UniverseState.StillLeft;
                break;
            default:
                break;
        }
        return super.jump(img1, img2, landingState);
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e1) {
            }
            if (this != null && (this.getStatus() == Status.DEAD || this.getStatus() == Status.BOOM)) {
                String deathimage = "";
                if (Level.getLevel() == GameLevel.GroundLevel || Level.getLevel() == GameLevel.BaseLevel) {
                    deathimage = "/images/nariodeath.png";
                } else if (Level.getLevel() == GameLevel.MediumLevel) {
                    if (this.getStatus() == Status.BOOM) {
                        deathimage = "/images/boom.png";
                    } else {
                        deathimage = "/images/m_nariodeath.png";
                    }
                }
                this.setCoordY(this.getCoordY() + 4);
                this.setImage((new ImageIcon(getClass().getResource(deathimage)).getImage()));
                if (this.getCoordY() > 500) {
                    // stop all the model threads
                    Bird.setBirdStop(true);
                    Brick.setBrickStop(true);
                    Coin.setCoinStop(true);
                    Mushroom.setMushStop(true);
                    Pipe.setPipeStop(true);
                    Turtle.setTurtleStop(true);

                    // stop all existing game threads to free the memory
                    GroundLevelThread.setStop(true);
                    BaseLevelThread.setStop(true);
                    MediumLevelThread.setStop(true);

                    //if(Keyboard.getGameState()!= GameState.Resume) {
                    // make it possible for the game to restart with escape
                    Keyboard.setGameState(GameState.Resume);
                    nario = null;
                    //}
                    break;
                }
            }
        }
    }
}

