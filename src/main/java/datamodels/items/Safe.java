package datamodels.items;

import datamodels.Player;

public class Safe extends Item {
    private static final String PASSWORD = "codeacademy";
    private final Key key;
    private boolean openedSafe = false;

    public Safe(String aName,
                String aDescription,
                boolean canPickUp,
                String probeMessage,
                Key key) {
        super(aName, aDescription, canPickUp, probeMessage);
        this.key = key;
    }

    public boolean matchPassword(String guess) {
        return guess.equals(PASSWORD);
    }

    @Override
    public void interact(Player player) {
        if (!openedSafe) {
            System.out.println("The safe pops open!  A large brass key can be " +
                    "seen inside.");
            player.getLocation().addItems(key);
            description = "a small black safe lies open on the desk";
            probeMessage = "Open at last!";
            openedSafe = true;
        } else {
            super.probe();
        }
    }
}
