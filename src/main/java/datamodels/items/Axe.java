package datamodels.items;

import datamodels.Player;

public class Axe extends Item {
    public Axe(String name,
               String description,
               boolean canPickUp,
               String probeMessage) {
        super(name, description, canPickUp, probeMessage);
    }

    @Override
    public void interact(Player player) {
        super.probe();
    }
}
