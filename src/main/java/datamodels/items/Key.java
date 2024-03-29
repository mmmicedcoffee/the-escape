package datamodels.items;

import datamodels.Player;
import datamodels.Room;

import static util.DirectionUtil.NORTH;

public class Key extends Item {
    private final Room unlockableRoom;

    public Key(String name,
               String description,
               boolean canPickUp,
               String probeMessage,
               Room unlockableRoom) {
        super(name, description, canPickUp, probeMessage);
        this.unlockableRoom = unlockableRoom;
    }

    public void tryUnlock(Player player) {
        if (player.getLocation() == unlockableRoom) {
            System.out.println("You have unlocked the door to the north.  " +
                    "You no longer have need of this key, so you dispose of it.");
            player.removeItem(this);
            player.getLocation().getExit(NORTH).setLocked(false);
        } else {
            System.out.println("Your key does not fit in any of the nearby doors.");
        }
    }

    @Override
    public void interact(Player player) {
        super.probe();
    }
}
