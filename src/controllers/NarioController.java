package controllers;

import model.Nario;

/**
 * a class which controls actors of type nario. it handles collisions
 * between Nario and other elements in the game
 */
public class NarioController {
    // there is only one instance of nario in the game
    private static Nario nario;

    // handle collisions of nario
    public static void collision() {
        // get nario
        nario = Nario.getNario();
        if (nario != null) {
            // collision with pipes
            for (int i = 0; i < PipeFactory.getPipes().size(); ++i) {
                nario.collision(PipeFactory.getPipes().get(i));
            }
            // collisions with bricks
            for (int i = 0; i < BrickFactory.getBricks().size(); ++i) {
                nario.collision(BrickFactory.getBricks().get(i));
            }
            // collisions with coins
            for (int i = 0; i < CoinFactory.getCoins().size(); ++i) {
                nario.collision(CoinFactory.getCoins().get(i));
            }
            // collision between mario and mushrooms
            for (int i = 0; i < MushFactory.getMushrooms().size(); ++i) {
                nario.collision(MushFactory.getMushrooms().get(i));
            }
            // collision betwen nario and turtles
            for (int i = 0; i < TurtleFactory.getTurtles().size(); ++i) {
                nario.collision(TurtleFactory.getTurtles().get(i));
            }
            // collision between nario and birds
            for (int i = 0; i < BirdFactory.getBirds().size(); ++i) {
                nario.collision(BirdFactory.getBirds().get(i));
            }
        }
    }
}
