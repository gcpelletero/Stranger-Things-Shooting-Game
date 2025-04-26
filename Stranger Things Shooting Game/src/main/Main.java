/*
 * Problem Domain: Stranger things is a shooting game inspired by the netflix series "Stranger Things". The game mainly
 * consists of 2 characters, Eleven and the demogorgons. The goal is to save Will by earning enough points and without losing
 * all 3 lives.
 *
 * Potion 		Adds +1 life

 *
 * @author Pelletero, Gabrielle
 * @author Villena, Angela
 * section: YZ4L
 *
 */
package main;

import javafx.application.Application;
import javafx.stage.Stage;
import strangerthings.Game;

public class Main extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
       Game game = new Game();
       game.setStage(stage);
    }
}
