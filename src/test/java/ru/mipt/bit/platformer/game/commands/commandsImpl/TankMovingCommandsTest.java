package ru.mipt.bit.platformer.game.commands.commandsImpl;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mockito;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.commands.Command;
import ru.mipt.bit.platformer.game.gameEntities.Tank;
import ru.mipt.bit.platformer.game.gameWorld.LevelMap;

import static org.junit.jupiter.api.Assertions.*;

class TankMovingCommandsTest {

    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testCommandMoveToDirection(Direction direction) {
//        GridPoint2 start = new GridPoint2(0, 0);
//        GridPoint2 targetCoordinates = direction.apply(start);
//
//        LevelMap levelMap = Mockito.mock(LevelMap.class);
//        Mockito.when(levelMap.isMapPointFree(Mockito.any())).thenReturn(true);
//
//        Tank tank = new Tank(
//                start,
//                Direction.UP,
//                1,0,
//                levelMap
//        );
//
//        Command command = TankMovingCommands.moveToDir(direction, tank);
//        assertNotNull(command);
//
//        command.execute();
//
//        assertEquals(targetCoordinates, tank.getDestinationCoordinates());
        //   assertEquals(direction, tank.getDirection());
    }

}