package ru.mipt.bit.platformer.game.graphics.commands;

import ru.mipt.bit.platformer.game.Holder;
import ru.mipt.bit.platformer.game.commands.Command;

public class UpdateHealthRenderCommand {

    public static Command getCommand(Holder<Boolean> shouldRenderHealth){
        return ()-> shouldRenderHealth.setValue(!shouldRenderHealth.getValue());
    }

}
