package ru.mipt.bit.platformer.game.gameWorld.collisions;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.mipt.bit.platformer.game.Collidable;
import ru.mipt.bit.platformer.game.gameWorld.map.LevelMapImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LevelMapImplTest {

    @Test
    public void testPointIsTakenByObject() {
        LevelMapImpl levelMap = new LevelMapImpl();
        GridPoint2 objectPosition = new GridPoint2(0, 0);
        GridPoint2 targetFreePosition = new GridPoint2(1, 0);

        Collidable collidable = Mockito.mock(Collidable.class);
        Mockito.when(collidable.containsPoint(objectPosition)).thenReturn(true);
        Mockito.when(collidable.containsPoint(targetFreePosition)).thenReturn(false);

        levelMap.addColliableObject(collidable);

        assertTrue(levelMap.isMapPointFree(targetFreePosition));
        assertFalse(levelMap.isMapPointFree(objectPosition));

    }

    @ParameterizedTest
    @MethodSource("provideDataForPointInBounders")
    public void testPointInBounders(GridPoint2 objectCoordinate, GridPoint2 leftDown,GridPoint2 rightUp) {
        LevelMapImpl levelMap = new LevelMapImpl(leftDown,rightUp);
        assertTrue(levelMap.isMapPointFree(objectCoordinate));
    }

    @Test
    @Disabled
    public void testPointNotInBounders(){
        GridPoint2 leftDown = new GridPoint2(0,0);
        GridPoint2 rightUp = new GridPoint2(2,2);
        GridPoint2 objCoordinates = new GridPoint2(-1,-1);

        LevelMapImpl levelMap = new LevelMapImpl(leftDown,rightUp);

        assertFalse(levelMap.isMapPointFree(objCoordinates));
    }

    private static Stream<Arguments> provideDataForPointInBounders() {
        return Stream.of(
                Arguments.of(new GridPoint2(0, 0),new GridPoint2(0,0),new GridPoint2(2,2)),
                Arguments.of(new GridPoint2(1, 1),new GridPoint2(0,0),new GridPoint2(2,2)),
                Arguments.of(new GridPoint2(0, 2),new GridPoint2(0,0),new GridPoint2(2,2)),
                Arguments.of(new GridPoint2(2, 0),new GridPoint2(0,0),new GridPoint2(2,2)),
                Arguments.of(new GridPoint2(2, 2),new GridPoint2(0,0),new GridPoint2(2,2))
        );
    }





}