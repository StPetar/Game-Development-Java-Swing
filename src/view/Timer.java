package view;

import enumeration.Status;
import model.Nario;

/**
 * a class responsible for computing the time displayed on the screen
 */
public class Timer implements Runnable {
    private static boolean pause;
    private static Nario nario;
    // delay of 1 second
    private final int delay = 1000;
    // the duration of the game is 100 seconds
    private int timer;

    // constructor
    public Timer(int t) {
        timer = t;
    }

    public static boolean isPause() {
        return pause;
    }

    public static void setPause(boolean pause) {
        Timer.pause = pause;
    }
    // getters and setters

    // a method to start this thread
    public void start() {
        Main.threadManager.execute(this);
    }

    @Override
    public void run() {
        while (true) {
            nario = Nario.getNario();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
            // pause
            if (pause) {
                continue;
            }
            // the timer decreases by 1s after 1000 ms
            timer -= 1;
            if (timer <= 0 || (nario != null && nario.getStatus() != Status.ALIVE)) {
                break;
            }
        }

    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int t) {
        timer = t;
    }
}
