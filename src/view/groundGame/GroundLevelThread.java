package view.groundGame;

/**
 * a class responsible for animating the ground level
 */
public class GroundLevelThread implements Runnable {
    private static boolean stop = false;
    private final int delay = 2;

    // a method use to enable or disable the run method
    public static void setStop(boolean s) {
        stop = s;
    }

    @Override
    public void run() {
        GroundLevel highLevel = GroundLevel.getGroundLevelInstance();
        while (true) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
            // continuously call the paintComponent method of the highLevel class
            highLevel.repaint();
            if (stop) break;
        }
    }

}
