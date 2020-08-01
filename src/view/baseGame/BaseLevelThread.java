package view.baseGame;

/**
 * A class responsible for the animation of the base level. it repeatedly calls its paintComponent method
 */
public class BaseLevelThread implements Runnable {
    private static boolean stop = false;
    public final int delay = 2;

    // a method to set stop to stop this thread
    public static void setStop(boolean s) {
        stop = s;
    }

    @Override
    public void run() {

        BaseLevel baseLevel = BaseLevel.getBaseLevelInstance();
        while (true) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
            // a method which calls the paintComponent method of the caller object
            baseLevel.repaint();
            if (stop) break;
        }
    }
}
