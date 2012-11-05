package datamodels;

import static util.DirectionUtil.getString;

public class Exit {
    private final int direction;
    private final Room adjoiningRoom;
    private boolean locked;

    public Exit(int direction, Room adjoiningRoom) {
        this.direction = direction;
        this.adjoiningRoom = adjoiningRoom;
        this.locked = false;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isLocked() {
        return locked;
    }

    public Room getAdjoiningRoom() {
        return adjoiningRoom;
    }

    public void setLocked(boolean isLocked) {
        locked = isLocked;
    }

    @Override
    public String toString() {
        return getString(direction);
    }
}
