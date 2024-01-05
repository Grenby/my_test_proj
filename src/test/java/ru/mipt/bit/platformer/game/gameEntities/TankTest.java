package ru.mipt.bit.platformer.game.gameEntities;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.gameWorld.LevelMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TankTest {

//    @Mock
//    private WorldInteraction worldInteraction;
//
//
//    private final static float FLOAT_ERR = 0.0001f;
//
//    @Test
//    public void testGetCoordinatesWithoutMoving() {
//
//        GridPoint2 coord = new GridPoint2(1, 2);
//        Tank tank = new Tank(coord, null, 0,
//                0,null);
//
//        assertEquals(coord, tank.getCoordinates());
//        assertEquals(coord, tank.getDestinationCoordinates());
//    }
//
//    @Test
//    public void testTankMoveToFreePoint() {
//        GridPoint2 start = new GridPoint2(0, 0);
//        GridPoint2 target = new GridPoint2(1, 0);
//
//        LevelMap mapWithFreeTargetPoint = Mockito.mock(LevelMap.class);
//        Mockito.when(mapWithFreeTargetPoint.isMapPointFree(target)).thenReturn(true);
//
//        Tank tank = new Tank(
//                start,
//                Direction.UP,
//                0,
//                0,
//                worldInteraction
//        );
//
//
//        tank.moveTo(target);
//
//
//        assertEquals(Direction.RIGHT, tank.getDirection());
//        assertEquals(target, tank.getDestinationCoordinates());
//    }
//
//    @Test
//    public void testTankMoveToNotFreePoint() {
//        GridPoint2 start = new GridPoint2(0, 0);
//        GridPoint2 target = new GridPoint2(1, 0);
//
//        LevelMap mapWithNotFreeTargetPoint = Mockito.mock(LevelMap.class);
//        Mockito.when(mapWithNotFreeTargetPoint.isMapPointFree(target)).thenReturn(false);
//
//        Tank tank = new Tank(
//                start,
//                Direction.UP,
//                0,
//                0,
//                worldInteraction);
//
//
//        tank.moveTo(target);
//
//        assertEquals(Direction.RIGHT, tank.getDirection());
//        assertEquals(start, tank.getDestinationCoordinates());
//    }
//
//    @Test
//    public void testTankMovingProgress() {
//        float speed = 1;
//
//        float delta = 0.5f;
//        float targetProgress = speed * delta;
//
//        GridPoint2 start = new GridPoint2(1, 0);
//        GridPoint2 target = new GridPoint2(1, 0);
//
//        LevelMap levelMap = Mockito.mock(LevelMap.class);
//        Mockito.when(levelMap.isMapPointFree(Mockito.any())).thenReturn(true);
//
//        Tank tank = new Tank(
//                start,
//                Direction.UP,
//                speed,
//                0,
//                worldInteraction
//        );
//
//        //act
//        tank.moveTo(target);
//        tank.updateState(delta);
//
//        assertTrue(tank.isMoving());
//        assertEquals(targetProgress, tank.getMovementProgress(), FLOAT_ERR);
//        assertEquals(target, tank.getDestinationCoordinates());
//        assertEquals(start, tank.getCoordinates());
//    }
//
//    @Test
//    public void testFinishMove() {
//        float speed = 1;
//        float timeToFinishMoving = (1f / speed) + FLOAT_ERR;
//
//        GridPoint2 target = new GridPoint2(1, 0);
//
//        LevelMap levelMap = Mockito.mock(LevelMap.class);
//        Mockito.when(levelMap.isMapPointFree(Mockito.any())).thenReturn(true);
//
//        Tank tank = new Tank(
//                new GridPoint2(0, 0),
//                Direction.UP,
//                speed,
//                0,
//                worldInteraction
//        );
//
//        //act
//        tank.moveTo(target);
//        tank.updateState(timeToFinishMoving);
//
//        assertEquals(target, tank.getCoordinates());
//        assertFalse(tank.isMoving());
//        assertEquals(Tank.MOVEMENT_COMPLETED, tank.getMovementProgress(), FLOAT_ERR);
//    }
//
//    @Test
//    public void testContainCoordinatePoint(){
//        GridPoint2 start = new GridPoint2(0, 0);
//
//        Tank tank = new Tank(
//                start,
//                Direction.UP,
//                0,
//                0,
//                worldInteraction);
//
//        assertTrue(tank.containsPoint(start));
//    }
//
//    @Test
//    public void testContainDestinationPoint(){
//        GridPoint2 start = new GridPoint2(0, 0);
//        GridPoint2 destination = new GridPoint2(0, 0);
//
//        Tank tank = new Tank(
//                start,
//                Direction.UP,
//                0,
//                0,
//                worldInteraction);
//
//        tank.moveTo(destination);
//
//        assertTrue(tank.containsPoint(destination));
//    }


}