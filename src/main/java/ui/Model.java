package ui;

import datamodels.Exit;
import datamodels.Room;
import datamodels.items.*;

import java.util.HashMap;

import static com.google.common.collect.Maps.newHashMap;
import static util.DirectionUtil.*;

public class Model {
    public static final int BEDROOM = 0;
    public static final int DARK_CORRIDOR = 1;
    public static final int STUDY = 2;
    public static final int BALLROOM = 3;
    public static final int LIVING_ROOM = 4;
    public static final int FOYER = 5;
    public static final int EXIT = 6;
    private Room[] rooms;
    private HashMap<String, Item> items;

    public Model() {
        makeRooms();
        makeExits();
        makeItems();
    }

    private void makeRooms() {
        rooms = new Room[]{
                new Room("-- Bedroom --",
                        "A small, dark bedroom.  Why are there no windows?"),
                new Room("-- Dark Corridor --",
                        "There are no windows here either.  Apparently, whoever designed this " +
                                "place had really bad outdoor allergies."),
                new Room("-- Study --",
                        "Filled with books and things.  An imposing chair has been placed behind " +
                                "a gorgeous mahogany desk."),
                new Room("-- Ballroom --",
                        "Expensive tapestries line the walls.  Wait, is this a mini-ballroom? " +
                                "What is it doing here?  Oh well, dress classy and dance cheesy!"),
                new Room("-- Living Room --",
                        "How hospitable.  A loveseat and an armchair are arranged around a quaint " +
                                "coffee table."),
                new Room("-- Foyer --",
                        "There is a large steel door to the north.  That might just be the way " +
                                "out!"),
                new Room("-- ESCAPED! --",
                        "You live another day.")};
    }

    private void makeExits() {
        rooms[BEDROOM].addExits(new Exit(NORTH, rooms[DARK_CORRIDOR]));
        rooms[DARK_CORRIDOR].addExits(new Exit(NORTH, rooms[STUDY]),
                new Exit(SOUTH, rooms[BEDROOM]),
                new Exit(WEST, rooms[BALLROOM]));
        rooms[DARK_CORRIDOR].getExit(NORTH).setLocked(true);
        rooms[STUDY].addExits(new Exit(SOUTH, rooms[DARK_CORRIDOR]));
        rooms[BALLROOM].addExits(new Exit(EAST, rooms[DARK_CORRIDOR]),
                new Exit(WEST, rooms[LIVING_ROOM]));
        rooms[LIVING_ROOM].addExits(new Exit(NORTH, rooms[FOYER]),
                new Exit(EAST, rooms[BALLROOM]));
        rooms[FOYER].addExits(new Exit(NORTH, rooms[EXIT]),
                new Exit(SOUTH, rooms[LIVING_ROOM]));
        rooms[FOYER].getExit(NORTH).setLocked(true);
    }

    private void makeItems() {
        items = newHashMap();
        items.put("note",
                new Note("a note",
                        "a hastily scrawled note sits on the nightstand",
                        true,
                        "The note reads: When your parents pay the ransom, you will be freed.\n" +
                                "You notice the letters 'dace am coy ed' are scrawled on the back."));
        items.put("axe",
                new Axe("a fire axe",
                        "a fire axe rests against the door",
                        true, "Eek! It's sharp."));
        items.put("telephone",
                new Telephone("a telephone",
                        "a telephone rests inside a sturdy glass class",
                        false, "A glass case prevents you from getting to it.",
                        (Axe) items.get("axe")));
        items.put("study key",
                new Key("a small silver key",
                        "a small silver key glistens on the floor",
                        false, "I wonder what this opens.",
                        rooms[DARK_CORRIDOR]));
        items.put("key",
                new Key("a large brass key",
                        "a large brass key can be seen in the safe",
                        true, "Surprisingly, it's not that heavy.",
                        rooms[FOYER]));
        items.put("painting",
                new Painting("a painting of Julius Caesar",
                        "a terrifying life-sized painting of Julius Caesar hangs on the wall",
                        false, "Ah, poor Caesar.",
                        (Key) items.get("study key")));
        items.put("safe",
                new Safe("a safe",
                        "a small black safe rests on the desk",
                        false, "The safe is securely locked.  I bet a wizard would know how to open it...",
                        (Key) items.get("key")));

        rooms[BEDROOM].addItems(items.get("note"));
        rooms[STUDY].addItems(items.get("safe"));
        rooms[BALLROOM].addItems(items.get("painting"));
        rooms[LIVING_ROOM].addItems(items.get("telephone"));
        rooms[FOYER].addItems(items.get("axe"));
    }

    public Room getFinalRoom() {
        return rooms[EXIT];
    }

    public Item getItemFromName(String anItemName) {
        return items.get(anItemName);
    }

    public Room getStartRoom() {
        return rooms[BEDROOM];
    }

    public Room getRoom(int roomIndex) {
        assert (roomIndex < rooms.length);
        return rooms[roomIndex];
    }
}
