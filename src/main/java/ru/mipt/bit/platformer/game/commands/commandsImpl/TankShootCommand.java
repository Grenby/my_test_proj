package ru.mipt.bit.platformer.game.commands.commandsImpl;

import ru.mipt.bit.platformer.game.commands.Command;
import ru.mipt.bit.platformer.game.gameEntities.Tank;

public final class TankShootCommand {

    private TankShootCommand(){

    }

    public static Command shoot(Tank tank) {
        return tank::shoot;
    }

}
