package datamodels.items;

import datamodels.Player;

public class Note extends Item {
    public Note(String aName,
                String aDescription,
                boolean canPickUp,
                String probeMessage) {
        super(aName, aDescription, canPickUp, probeMessage);
    }

    @Override
    public void interact(Player player) {
        super.probe();
    }
}
