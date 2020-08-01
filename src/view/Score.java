package view;

import audio.Audio;
import enumeration.CoinType;
import enumeration.Status;

/**
 * a class responsible for computing the score on the screen
 */
public class Score {
    private static int score;

    public static void setScore(CoinType t, Status status) {
        if (status == Status.ALIVE) {
            Audio.stop();
            Audio.playSound("/sounds/coin_sound.wav");
            switch (t) {
                case PURPLE:
                    score += 2;
                    break;
                case GREEN:
                    score += 4;
                    break;
                case GOLD:
                    score += 6;
                    break;
            }
        }
    }

    // a method to divulge the score
    public static int getScore() {
        return score;
    }

    // a method used to set the score
    public static void setScore(int s) {
        score += s;
    }

    // a method to reset the score
    public static void resetScore(int s) {
        score = s;
    }
}
