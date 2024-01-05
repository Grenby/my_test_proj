package ru.mipt.bit.platformer.game.commands.commandsImpl;

import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.commands.Command;
import ru.mipt.bit.platformer.game.gameEntities.Tank;

/**
 * Фабрика для команд движения для данного танка
 * Создаёт анонимные классы для отдачи команд танкам
 */
public final class TankMovingCommands {

    private TankMovingCommands(){
    }

    public static Command moveUP(Tank tank) {
        return moveToDir(Direction.UP, tank);
    }

    public static Command moveLeft(Tank tank) {
        return moveToDir(Direction.LEFT, tank);

    }

    public static Command moveDown(Tank tank) {
        return moveToDir(Direction.DOWN, tank);
    }

    public static Command moveRight(Tank tank) {
        return moveToDir(Direction.RIGHT, tank);
    }

    public static Command moveToDir(Direction direction, Tank tank) {
        return () -> tank.moveTo(direction.apply(tank.getCoordinates()));
    }

}
