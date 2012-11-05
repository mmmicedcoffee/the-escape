package datamodels.items;

import datamodels.Player;

public abstract class Item {
    protected final String name;
    protected String description;
    protected boolean canPickUp;
    protected String probeMessage;

    public Item(String aName,
                String aDescription,
                boolean canPickUp,
                String probeMessage) {
        this.name = aName;
        this.description = aDescription;
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
