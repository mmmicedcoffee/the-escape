package datamodels.items;

import datamodels.Player;

public class Painting extends Item {
    private boolean foundKey = false;
    private final Key key;

    public Painting(String aName,
                    String aDescription,
                    boolean canPickUp,
                    String probeMessage,
                    Key key) {
        super(aName, aDescription, canPickUp, probeMessage);
        this.key = key;
    }

    @Override
    public void interact(Player player) {
        if (player.hasHeardVoiceMail() && !foundKey) {
            System.out.println("You push a corner of the painting slightly, and a " +
                    "silver key falls to the floor.  You pick it up.");
            player.addItem(key);
            foundKey = true;
        } else {
            super.probe();
        }
    }
}
