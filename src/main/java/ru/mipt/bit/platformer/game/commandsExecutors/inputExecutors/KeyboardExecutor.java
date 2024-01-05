package ru.mipt.bit.platformer.game.commandsExecutors.inputExecutors;

import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.game.commands.Command;
import ru.mipt.bit.platformer.game.commandsExecutors.CommandsExecutor;

import java.util.HashMap;
import java.util.Map;

public class KeyboardExecutor implements CommandsExecutor {

    private final Map<Integer, Command> keyToCommandPressed;
    private final Map<Integer, Command> keyToCommandTouch;

    private final Input input;

    public KeyboardExecutor(Input input) {
        this.input = input;
        this.keyToCommandPressed = new HashMap<>();
        this.keyToCommandTouch = new HashMap<>();
    }

    @Override
    public void executeCommands() {
        for (Integer key : keyToCommandPressed.keySet()) {
            if (input.isKeyPressed(key)) {
                keyToCommandPressed.get(key).execute();
            }
        }

        for (Integer key : keyToCommandTouch.keySet()) {
            if (input.isKeyJustPressed(key)) {
                keyToCommandTouch.get(key).execute();
            }
        }

    }

    public void addMapping(int key, Command command, boolean pressed) {
        if (pressed) {
            keyToCommandPressed.put(key, command);
        } else {
            keyToCommandTouch.put(key, command);
        }
    }

}
