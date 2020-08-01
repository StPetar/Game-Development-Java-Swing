package controllers;

import enumeration.CoinType;
import enumeration.GameLevel;
import model.Coin;
import view.Level;

import java.util.ArrayList;

/**
 * A class responsible for creating coins in large numbers
 */
public class CoinFactory {

    private static ArrayList<Coin> coins;
    private static CoinFactory coinFactory;

    private CoinFactory() {
        coins = new ArrayList<>();
        GameLevel level = Level.getLevel();
        switch (level) {
            case BaseLevel:
                coins.add(new Coin(330, 73, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(315, 190, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(450, 369, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));

                coins.add(new Coin(625, 100, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(632, 371, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));

                coins.add(new Coin(980, 23, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(850, 295, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(920, 300, 23, 23, "/images/greencoin1.png", CoinType.GREEN));

                coins.add(new Coin(1360, 43, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(1380, 150, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(1150, 169, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));

                coins.add(new Coin(1715, 290, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(1715, 80, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(1900, 306, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(2200, 306, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));

                coins.add(new Coin(2385, 371, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));

                coins.add(new Coin(2630, 200, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(2820, 50, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));

                coins.add(new Coin(3199, 35, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(3255, 33, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(3401, 296, 23, 23, "/images/greencoin1.png", CoinType.GREEN));

                coins.add(new Coin(3689, 235, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(3783, 53, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));

                coins.add(new Coin(4151, 372, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(4335, 55, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(4451, 303, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));

                coins.add(new Coin(4651, 322, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(4901, 102, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(4907, 302, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));

                coins.add(new Coin(5150, 372, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(5380, 202, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(5330, 302, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(5600, 112, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                break;

            case GroundLevel:
                coins.add(new Coin(535, 245, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(640, 140, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(820, 140, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(895, 190, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(945, 190, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(995, 190, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(1035, 190, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(1105, 125, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(1215, 75, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(1345, 80, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(1375, 80, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(1510, 130, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(1625, 170, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(1870, 105, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(2185, 120, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(2285, 150, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(2425, 180, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(2652, 210, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(2760, 200, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(2865, 170, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(3076, 150, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(3215, 75, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(3275, 75, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(3445, 190, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(3603, 150, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(3753, 120, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(4040, 150, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(4202, 75, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(4310, 215, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(4575, 160, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(4852, 210, 23, 23, "/images/greencoin1.png", CoinType.GOLD));
                coins.add(new Coin(4985, 140, 23, 23, "/images/greencoin1.png", CoinType.GOLD));
                coins.add(new Coin(5108, 90, 23, 23, "/images/greencoin1.png", CoinType.GOLD));
                coins.add(new Coin(5258, 90, 23, 23, "/images/greencoin1.png", CoinType.GOLD));
                coins.add(new Coin(5402, 90, 23, 23, "/images/greencoin1.png", CoinType.GOLD));
                break;

            case MediumLevel:

                coins.add(new Coin(330, 43, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(250, 160, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(560, 105, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(640, 52, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(650, 303, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(800, 109, 23, 23, "/images/greencoin1.png", CoinType.GREEN));

                coins.add(new Coin(950, 40, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(980, 303, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(1060, 290, 23, 23, "/images/greencoin1.png", CoinType.GREEN));

                coins.add(new Coin(1180, 40, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(1320, 143, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(1580, 104, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(1900, 223, 23, 23, "/images/greencoin1.png", CoinType.GREEN));

                coins.add(new Coin(1960, 305, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(2060, 144, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(2270, 344, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(2561, 133, 23, 23, "/images/greencoin1.png", CoinType.GREEN));

                coins.add(new Coin(2650, 205, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(2820, 140, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(3150, 33, 23, 23, "/images/greencoin1.png", CoinType.GREEN));

                coins.add(new Coin(3460, 101, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(3650, 40, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(3810, 113, 23, 23, "/images/greencoin1.png", CoinType.GREEN));

                coins.add(new Coin(3720, 344, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(3950, 340, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(4050, 293, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(4090, 293, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(4450, 214, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));

                coins.add(new Coin(4750, 20, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(4950, 225, 23, 23, "/images/greencoin1.png", CoinType.GREEN));
                coins.add(new Coin(5110, 300, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));

                coins.add(new Coin(5350, 283, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(5450, 283, 23, 23, "/images/greencoin1.png", CoinType.GREEN));

                coins.add(new Coin(5700, 80, 23, 23, "/images/purplecoin1.png", CoinType.PURPLE));
                coins.add(new Coin(5900, 33, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                coins.add(new Coin(5960, 33, 23, 23, "/images/greencoin1.png", CoinType.GREEN));

                coins.add(new Coin(6320, 343, 23, 23, "/images/goldcoin1.png", CoinType.GOLD));
                break;
        }
    }

    public static ArrayList<Coin> getCoins() {
        if (coinFactory == null) {
            coinFactory = new CoinFactory();
        }
        return coins;
    }

    // a method to reset coin production
    public static void resetCoins() {
        coinFactory = null;
    }
}