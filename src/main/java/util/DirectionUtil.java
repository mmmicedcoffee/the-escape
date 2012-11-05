package util;

import java.util.HashSet;

import static com.google.common.collect.Sets.newHashSet;

public class DirectionUtil {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static final HashSet<String> NORTH_SET = newHashSet("n", "north");
    public static final HashSet<String> SOUTH_SET = newHashSet("s", "south");
    public static final HashSet<String> WEST_SET = newHashSet("w", "west");
    public static final HashSet<String> EAST_SET = newHashSet("e", "east");

    public static String getString(int direction) {
        assert (direction >= 0 && direction < 4);
        switch (direction) {
            case 0:
                return "(N)orth";
            case 1:
                return "(E)ast";
            case 2:
                return "(S)outh";
            case 3:
                return "(W)est";
            default:
                return "Unidentified direction!";
        }
    }

    public static boolean isDirection(String direction) {
        return (NORTH_SET.contains(direction) || SOUTH_SET.contains(direction) ||
                WEST_SET.contains(direction) || EAST_SET.contains(direction));
    }

    public static int getDirection(String direction) {
        assert (isDirection(direction));
        if (NORTH_SET.contains(direction)) {
            return NORTH;
        } else if (SOUTH_SET.contains(direction)) {
            return SOUTH;
        } else if (EAST_SET.contains(direction)) {
            return EAST;
        } else {
            return WEST;
        }
    }
}
