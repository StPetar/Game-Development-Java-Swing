package controllers;

import enumeration.GameLevel;
import model.Pipe;
import view.Level;

import java.util.ArrayList;

/**
 * A class responsible for creating pipes: the pipe factory
 */
public class PipeFactory {

    private static ArrayList<Pipe> pipes;
    private static PipeFactory pipeFactory;

    private PipeFactory() {
        pipes = new ArrayList<>();
        GameLevel level = Level.getLevel();
        // y = 480 - 75 - height -- 16 pipes
        switch (level) {
            case BaseLevel:
                pipes.add(new Pipe(500, 308, 48, 95, "/images/greenpipe6.png"));
                pipes.add(new Pipe(750, 298, 51, 105, "/images/bluepipe1.png"));
                pipes.add(new Pipe(1000, 255, 50, 148, "/images/redpipe4.png"));
                pipes.add(new Pipe(1500, 308, 48, 95, "/images/greenpipe5.png"));
                pipes.add(new Pipe(2000, 296, 38, 107, "/images/redpipe3.png"));
                pipes.add(new Pipe(2500, 318, 43, 85, "/images/bluepipe2.png"));
                pipes.add(new Pipe(2750, 255, 50, 148, "/images/redpipe4.png"));
                pipes.add(new Pipe(3000, 298, 51, 105, "/images/bluepipe1.png"));
                pipes.add(new Pipe(3250, 318, 43, 85, "/images/bluepipe2.png"));
                pipes.add(new Pipe(3500, 311, 48, 92, "/images/greenpipe5.png"));
                pipes.add(new Pipe(4000, 296, 38, 107, "/images/redpipe3.png"));
                pipes.add(new Pipe(4500, 318, 43, 85, "/images/bluepipe2.png"));
                pipes.add(new Pipe(4750, 255, 50, 148, "/images/redpipe4.png"));
                pipes.add(new Pipe(5000, 318, 43, 85, "/images/bluepipe2.png"));
                pipes.add(new Pipe(5250, 308, 48, 95, "/images/greenpipe6.png"));
                pipes.add(new Pipe(5500, 255, 50, 148, "/images/redpipe4.png"));
                break;

            case GroundLevel:
                pipes.add(new Pipe(750, 298, 51, 105, "/images/bluepipe1.png"));

                pipes.add(new Pipe(1500, 308, 48, 95, "/images/greenpipe5.png"));

                pipes.add(new Pipe(2000, 255, 50, 148, "/images/redpipe4.png"));
                pipes.add(new Pipe(2500, 318, 43, 85, "/images/bluepipe2.png"));

                pipes.add(new Pipe(2750, 255, 50, 148, "/images/redpipe4.png"));
                pipes.add(new Pipe(3000, 298, 51, 105, "/images/bluepipe1.png"));
                pipes.add(new Pipe(3500, 308, 48, 95, "/images/greenpipe6.png"));
                pipes.add(new Pipe(4000, 296, 38, 107, "/images/redpipe3.png"));
                pipes.add(new Pipe(4500, 318, 43, 85, "/images/bluepipe2.png"));
                pipes.add(new Pipe(4650, 255, 50, 148, "/images/redpipe4.png"));
                pipes.add(new Pipe(5000, 318, 43, 85, "/images/bluepipe2.png"));
                pipes.add(new Pipe(5250, 308, 48, 95, "/images/greenpipe6.png"));
                pipes.add(new Pipe(5500, 255, 50, 148, "/images/redpipe4.png"));

                break;

            case MediumLevel:
                pipes.add(new Pipe(500, 265, 59, 108, "/images/m_pipegreen.png", 150));
                pipes.add(new Pipe(750, 265, 43, 108, "/images/m_pipered.png", 100));
                pipes.add(new Pipe(1250, 263, 71, 110, "/images/m_pipeblue.png", 120));
                pipes.add(new Pipe(1750, 281, 57, 92, "/images/m_pipegreen2.png", 144));

                pipes.add(new Pipe(2000, 265, 59, 108, "/images/m_pipegreen.png", 200));
                pipes.add(new Pipe(2500, 265, 43, 108, "/images/m_pipered.png", 180));
                pipes.add(new Pipe(2750, 263, 71, 110, "/images/m_pipeblue.png", 150));
                pipes.add(new Pipe(3500, 281, 57, 92, "/images/m_pipegreen2.png", 189));

                pipes.add(new Pipe(3750, 265, 59, 108, "/images/m_pipegreen.png", 177));
                pipes.add(new Pipe(4250, 265, 43, 108, "/images/m_pipered.png", 200));
                pipes.add(new Pipe(5000, 263, 71, 110, "/images/m_pipeblue.png", 183));
                pipes.add(new Pipe(5250, 281, 57, 92, "/images/m_pipegreen2.png", 186));

                pipes.add(new Pipe(5500, 265, 59, 108, "/images/m_pipegreen.png", 119));
                pipes.add(new Pipe(5750, 265, 43, 108, "/images/m_pipered.png", 145));
                pipes.add(new Pipe(6080, 263, 71, 110, "/images/m_pipeblue.png", 195));
                pipes.add(new Pipe(6550, 281, 57, 92, "/images/m_pipegreen2.png", 175));
                break;
        }
    }

    public static ArrayList<Pipe> getPipes() {
        if (pipeFactory == null) {
            pipeFactory = new PipeFactory();
        }
        return pipes;
    }

    // a method to reset pipes
    public static void resetPipes() {
        pipeFactory = null;
    }
}
