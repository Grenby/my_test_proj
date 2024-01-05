package ru.mipt.bit.platformer.game;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {

    private final static float FLOAT_ERR = 0.0001f;


    @ParameterizedTest
    @MethodSource("provideDataForGetDirection")
    public void testGetDirection(GridPoint2 gridPointDirection, Direction targetDirection) {
        GridPoint2 initialGridPointDirection = new GridPoint2(gridPointDirection);

        Direction newDirection = Direction.getDirect(gridPointDirection);

        assertEquals(targetDirection, newDirection);
        assertEquals(initialGridPointDirection, gridPointDirection, "Grid point was changed");
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testApply(Direction direction) {
        GridPoint2 startPos = new GridPoint2(0, 0);
        GridPoint2 position = new GridPoint2(startPos);

        GridPoint2 targetPosition = getTargetPosition(position, direction);

        assertEquals(targetPosition, direction.apply(startPos));
        assertEquals(startPos, position, "grid point was changed");
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testGetRotation(Direction direction) {
        float rotation = getTargetRotation(direction);
        float delta = direction.getRotation() - rotation;

        assertEquals(0, delta % 360, FLOAT_ERR);
    }

    private float getTargetRotation(Direction direction) {
        switch (direction) {
            case LEFT:
                return 180;
            case UP:
                return 90;
            case DOWN:
                return -90;
            case RIGHT:
                return 0;
        }
        return 0;
    }

    private GridPoint2 getTargetPosition(GridPoint2 pos, Direction direction) {
        switch (direction) {
            case LEFT:
                return new GridPoint2(pos).add(-1, 0);
            case UP:
                return new GridPoint2(pos).add(0, 1);
            case DOWN:
                return new GridPoint2(pos).add(0, -1);
            case RIGHT:
                return new GridPoint2(pos).add(1, 0);
        }
        return null;
    }

    private static Stream<Arguments> provideDataForGetDirection() {
        return Stream.of(
                Arguments.of(new GridPoint2(0, 1), Direction.UP),
                Arguments.of(new GridPoint2(0, -1), Direction.DOWN),
                Arguments.of(new GridPoint2(1, 0), Direction.RIGHT),
                Arguments.of(new GridPoint2(-1, 0), Direction.LEFT),
                Arguments.of(new GridPoint2(1, 1), null)
        );
    }

}