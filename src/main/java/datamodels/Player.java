package datamodels;

import datamodels.items.Item;
import datamodels.items.Key;

import java.util.HashSet;

public class Player {
    private HashSet<Item> inventory;
    private HashSet<Key> keys;
    private Room location;
    private boolean hasHeardVoiceMail = false;

    public Player(Room aStartLocation) {
        this.inventory = new HashSet<Item>();
        this.keys = new HashSet<Key>();
        this.location = aStartLocation;
    }

    public void changeLocation(Room aLocation) {
        location = aLocation;
    }

    public void addItem(Item anItem) {
        inventory.add(anItem);
        if (anItem instanceof Key) {
            keys.add((Key) anItem);
        }
    }

    public void removeItem(Item anItem) {
        inventory.remove(anItem);
        if (keys.contains(anItem)) {
            keys.remove(anItem);
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
