package datamodels;

import datamodels.items.Item;

import java.util.HashSet;

import static com.google.common.collect.Sets.newHashSet;

public class Room {
    protected final String name;
    protected final String description;
    protected final Exit[] exits = new Exit[4];
    protected final HashSet<Item> items = newHashSet();

    public Room(String aName, String aDescription) {
        this.name = aName;
        this.description = aDescription;
    }

    public Room moveDirection(int direction) {
        if (exits[direction] == null || exits[direction].isLocked()) {
            return this;
        }
        return exits[direction].getAdjoiningRoom();
    }

    public void addExits(Exit... anExits) {
        for (Exit exit : anExits) {
            exits[exit.getDirection()] = exit;
        }
    }

    public Exit getExit(int direction) {
        return exits[direction];
    }

    public boolean hasItem(Item anItem) {
        return items.contains(anItem);
    }

    public void addItems(Item... anItems) {
        for (Item item : anItems) {
            items.add(item);
        }
    }

    public void removeItem(Item anItem) {
        items.remove(anItem);
    }

    private boolean noExits() {
        return (exits[0] == null && exits[1] == null && exits[2] == null && exits[3] == null);
    }

    @Override
    public String toString() {
        String myExits = "";
        if (!noExits()) {
            myExits += "Exits: \n";
            for (Exit exit : exits) {
                if (exit != null) {
                    myExits += exit.toString() + "\n";
                }
            }
        }

        String myItems = "";
        if (!items.isEmpty()) {
            myItems += "Interesting Items: \n";
            for (Item item : items) {
                myItems += item.toString() + "\n";
            }
        }

        return name + "\n" +
                description + "\n" +
                myItems + myExits;
    }
}
