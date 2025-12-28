package entity;

import java.util.Random;

public enum Position {
    SCENE,
    HALL,
    STAFF_ONLY,
    WATER_CLOSET,
    LEFT_HALL,
    RIGHT_HALL,
    OFFICE,
    JUMPSCARE;

    private static final Random RANDOM = new Random();
    private static final Position[] VALUES = {
            HALL, STAFF_ONLY, WATER_CLOSET, RIGHT_HALL
    };

    public static Position random() {
        return VALUES[RANDOM.nextInt(VALUES.length)];
    }
}
