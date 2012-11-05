package datamodels.items;

import datamodels.Player;

public class Axe extends Item {
    public Axe(String aName,
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
