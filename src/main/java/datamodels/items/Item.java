package datamodels.items;

import datamodels.Player;

public abstract class Item {
    protected final String name;
    protected String description;
    protected boolean canPickUp;
    protected String probeMessage;

    public Item(String name,
                String description,
                boolean canPickUp,
                String probeMessage) {
        this.name = name;
        this.description = description;
        this.canPickUp = canPickUp;
        this.probeMessage = probeMessage;
    }

    public String getName() {
        return name;
    }

    public void pickedUp() {
        canPickUp = false;
    }

    public boolean canPickUp() {
        return canPickUp;
    }

    public void probe() {
        System.out.println(probeMessage);
    }

    abstract public void interact(Player player);

    @Override
    public String toString() {
        return description;
    }
}
