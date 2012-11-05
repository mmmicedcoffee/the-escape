import datamodels.Player;
import ui.Game;
import ui.Model;
import ui.Parser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(scanner);

        System.out.println("Welcome to the Escape.");
        System.out.println("Do you wish to start a new game? (y/n)");

        if (parser.checkBegin()) {
            Model model = new Model();
            Player player = new Player(model.getStartRoom());
            Game myGame = new Game(parser, model, player);
            myGame.beginGame();
        } else {
            System.out.println("Farewell.");
        }

    }
}
