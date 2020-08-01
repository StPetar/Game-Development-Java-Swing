package view.mediumGame;

/**
 * a class responsible for animating the medium level
 */
public class MediumLevelThread implements Runnable {
    private static boolean stop = false;
    private final int delay = 2;

    // enable or disable the run method infinite loop
    public static void setStop(boolean s) {
        stop = s;
    }

    @Override
    public void run() {
        MediumLevel mediumLevel = MediumLevel.getMediumLevelInstance();
        while (true) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mediumLevel.repaint();
            if (stop) break;
        }

    }

}
