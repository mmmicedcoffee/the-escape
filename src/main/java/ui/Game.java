package ui;

import datamodels.Player;
import datamodels.Room;
import datamodels.items.Item;
import datamodels.items.Key;
import datamodels.items.Safe;

import static util.DirectionUtil.getDirection;
import static ui.Model.BALLROOM;

public class Game {
    private final Parser parser;
    private final Model model;
    private final Player player;

    public Game(Parser aParser, Model aModel, Player aPlayer) {
        this.parser = aParser;
        this.model = aModel;
        this.player = aPlayer;
    }

    public void beginGame() {
        System.out.println("You wake up to find yourself in an unfamiliar room, in an " +
                "unfamiliar bed.  For some reason, the darkness of the room instills " +
                "an unnatural fear within you.  (Enter 'help' for command info.)");
        displayLocation();
        playGame();
    }

    private void playGame() {
        parser.processNextPrompt();
        if (!parser.isGameOver()) {
            if (parser.isDirection()) {
                movePlayer(getDirection(parser.getPrompt()));
            } else if (parser.isHelp()) {
                displayHelp();
            } else if (parser.isLook()) {
                displayLocation();
            } else if (parser.isInventory()) {
                player.printInventory();
            } else if (parser.isProbe()) {
                handleProbe();
            } else if (parser.isUnlock()) {
                handleUnlock();
            } else if (parser.isDance()) {
                handleDance();
            } else {
                System.out.println("Sorry, I didn't catch that.");
            }

            if (player.getLocation() != model.getFinalRoom()) {
                playGame();
            }
        } else {
            System.out.println("Farewell.");
        }
    }

    private void handleDance() {
        if (player.getLocation() == model.getRoom(BALLROOM)) {
            System.out.println("Seeing that no one is around, you do an embarrassing " +
                    "rendition of Gangnam Style, but stop as soon as you realized that " +
                    "your abilities don't even come close to MIT's " +
                    "(http://www.youtube.com/watch?v=lJtHNEDnrnY).");
        }
    }

    private void handleProbe() {
        String itemName = parser.getPrompt().split(" ")[1];
        Item item = model.getItemFromName(itemName);
        Room currentRoom = player.getLocation();
        if (currentRoom.hasItem(item)) {
            if (item.canPickUp()) {
                currentRoom.removeItem(item);
                player.addItem(item);
                System.out.println("You have added " + item.getName() +
                        " to your inventory.");
                item.pickedUp();
                item.probe();
            } else {
                if (item instanceof Safe) {
                    interactSafe((Safe) item);
                } else {
                    item.interact(player);
                }
            }
        } else if (player.isHolding(item)) {
            item.probe();
        } else {
            System.out.println("Sorry, I didn't catch that.");
        }
    }

    private void interactSafe(Safe safe) {
        System.out.println("The safe requires a password.  What is the password " +
                "(no spaces)?");
        parser.processNextPrompt();
        if (safe.matchPassword(parser.getPrompt())) {
            safe.interact(player);
        } else if (parser.getPrompt().equals("alohamora")) {
            System.out.println("No luck.  Guess I'm not a wizard after all.");
        } else {
            System.out.println("Sorry, wrong password.  Ugh, this would be a piece of cake for Hermione...  " +
                    "I should try probing the safe again.");
        }
    }

    private void handleUnlock() {
        if (player.hasKeys()) {
            for (Key k : player.getKeys()) {
                k.tryUnlock(player);
            }
        } else {
            System.out.println("You have no keys.");
        }
    }

    private void displayLocation() {
        System.out.print(player.getLocation());
    }

    private void displayHelp() {
        System.out.println("Commands: (n,s,e,w) to move, (l)ook, " +
                "(unlock door), (p)robe [item], (inv)entory, " +
                "(h)elp, (qq) to quit.");
    }

    private void movePlayer(int direction) {
        if (player.getLocation().getExit(direction) == null) {
            System.out.println("Are you crazy?");
        } else if (player.getLocation().getExit(direction).isLocked()) {
            System.out.println("It appears to be locked.");
        } else {
            player.changeLocation(player.getLocation().moveDirection(direction));
            displayLocation();
        }
    }
}
