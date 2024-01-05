package ru.mipt.bit.platformer.game.commandsExecutors.aiExecutors;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.commands.commandsImpl.TankMovingCommands;
import ru.mipt.bit.platformer.game.commands.commandsImpl.TankShootCommand;
import ru.mipt.bit.platformer.game.commandsExecutors.CommandsExecutor;
import ru.mipt.bit.platformer.game.gameEntities.Tank;
import ru.mipt.bit.platformer.game.gameWorld.LevelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AITankRandomImpl implements CommandsExecutor {

    private final Random random;
    private final LevelMap levelMap;
    private final List<Tank> tanks;
    private final List<GridPoint2> freePoints = new ArrayList<>();

    public AITankRandomImpl(LevelMap levelMap, List<Tank> tanks) {
        this.levelMap = levelMap;
        this.tanks = tanks;
        this.random = new Random();
    }

    @Override
    public void executeCommands() {

        for (Tank tank : tanks) {
            if (!tank.isMoving()) {
                int nextAction = random.nextInt(2);
                if (nextAction != 0) {
                    GridPoint2 targetPosition;
                    int val = random.nextInt(10);
                    if (val == 0) {
                        calculateFreePoints(tank.getCoordinates());
                        if (freePoints.isEmpty()) {
                            return;
                        }
                        int index = random.nextInt(freePoints.size());
                        targetPosition = freePoints.get(index);
                        TankMovingCommands.moveToDir(Direction.getDirect(targetPosition.sub(tank.getCoordinates())), tank).execute();
                    } else {
                        TankMovingCommands.moveToDir(tank.getDirection(), tank).execute();
                    }
                } else {
                    TankShootCommand.shoot(tank).execute();
                    tank.rotate(tank.getDirection().rotate90(1));
                }
            }
        }
    }

    private GridPoint2 getRandomTargetPosition(Tank tank) {
        // 0 - поворот в право, 1 назад 2- лево 3...6 - продолжить текущее
        int val = random.nextInt(7);
        Direction dir = tank.getDirection();

        switch (val) {
            case 0: {
                dir = tank.getDirection().rotate90(1);
                break;
            }
            case 1: {
                dir = tank.getDirection().rotate180();
                break;
            }
            case 2: {
                dir = tank.getDirection().rotate90(-1);
            }
        }
        return dir.apply(tank.getCoordinates());
    }

    private void calculateFreePoints(GridPoint2 point2) {
        freePoints.clear();
        for (Direction d : Direction.values()) {
            GridPoint2 point21 = d.apply(point2);
            if (levelMap.isMapPointFree(point21)) {
                freePoints.add(point21);
            }
        }
    }

}
