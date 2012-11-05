package datamodels;

import datamodels.items.Item;
import datamodels.items.Key;

import java.util.HashSet;

public class Player {
    private HashSet<Item> inventory;
    private HashSet<Key> keys;
    private Room location;
    private boolean hasHeardVoiceMail = false;

    public Player(Room startLocation) {
        this.inventory = new HashSet<Item>();
        this.keys = new HashSet<Key>();
        this.location = startLocation;
    }

    public void changeLocation(Room newRoom) {
        location = newRoom;
    }

    public void addItem(Item item) {
        inventory.add(item);
        if (item instanceof Key) {
            keys.add((Key) item);
        }
    }

    public void removeItem(Item item) {
        inventory.remove(item);
        if (keys.contains(item)) {
            keys.remove(item);
        }
    }

    public void heardVoiceMail() {
        this.hasHeardVoiceMail = true;
    }

    public Room getLocation() {
        return location;
    }

    public HashSet<Key> getKeys() {
        return keys;
    }

    public boolean hasKeys() {
        return !keys.isEmpty();
    }

    public boolean hasHeardVoiceMail() {
        return hasHeardVoiceMail;
    }

    public boolean isHolding(Item item) {
        return inventory.contains(item);
    }

    public void printInventory() {
        System.out.println("Inventory: ");
        if (inventory.isEmpty()) {
            System.out.println("Nothing :(");
        } else {
            for (Item item : inventory) {
                System.out.println(item.getName());
            }
        }
    }
}
