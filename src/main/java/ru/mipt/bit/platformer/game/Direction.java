package ru.mipt.bit.platformer.game;

import com.badlogic.gdx.math.GridPoint2;

public enum Direction {

    UP(new GridPoint2(0, 1), 90),
    DOWN(new GridPoint2(0, -1), -90),
    LEFT(new GridPoint2(-1, 0), -180),
    RIGHT(new GridPoint2(1, 0), 0);

    private final GridPoint2 vector;
    private final float rotation;

    Direction(GridPoint2 vector, float rotation) {
        this.vector = vector;
        this.rotation = rotation;
    }

    public GridPoint2 apply(GridPoint2 point) {
        return new GridPoint2(point).add(vector);
    }

    public Direction rotate90(int dir) {
        if (dir > 0) {
            switch (this) {
                case UP:
                    return RIGHT;
                case RIGHT:
                    return DOWN;
                case DOWN:
                    return LEFT;
                case LEFT:
                    return UP;
                default:
                    return this;
            }
        } else {
            switch (this) {
                case UP:
                    return LEFT;
                case RIGHT:
                    return UP;
                case DOWN:
                    return RIGHT;
                case LEFT:
                    return DOWN;
                default:
                    return this;
            }
        }
    }

    public Direction rotate180() {
        switch (this) {
            case UP:
                return DOWN;
            case RIGHT:
                return LEFT;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            default:
                return this;
        }
    }

    public float getRotation() {
        return rotation;
    }

    public static Direction getDirect(GridPoint2 direction) {
        for (Direction d : values()) {
            if (d.vector.equals(direction)) {
                return d;
            }
        }
        return null;
    }


}

