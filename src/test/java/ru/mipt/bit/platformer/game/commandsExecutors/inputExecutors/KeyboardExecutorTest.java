package ru.mipt.bit.platformer.game.commandsExecutors.inputExecutors;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mipt.bit.platformer.game.commands.Command;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeyboardExecutorTest {

    @Test
    @Disabled
    public void testExecuteCommand() {
        final int KEY_CODE_FOR_COMMAND_EXECUTE = 0;
        final int KEY_CODE_FOR_COMMAND_SLEEP = 1;

        final int NUM_EXECUTE_COMMAND_EXECUTE = 0;
        final int NUM_EXECUTE_COMMAND_SLEEP = 1;


        //list for detect that command was executed
        Command commandExecute = Mockito.mock(Command.class);
        Command commandSleep = Mockito.mock(Command.class);

        //Input mock
        Input input = Mockito.mock(Input.class);

        Mockito.when(input.isKeyPressed(KEY_CODE_FOR_COMMAND_EXECUTE)).thenReturn(true);
        Mockito.when(input.isKeyPressed(KEY_CODE_FOR_COMMAND_SLEEP)).thenReturn(false);

        //init input controller
        KeyboardExecutor controller = new KeyboardExecutor(input);

        controller.addMapping(KEY_CODE_FOR_COMMAND_EXECUTE, commandExecute,true);
        controller.addMapping(KEY_CODE_FOR_COMMAND_SLEEP, commandSleep,true);

        controller.executeCommands();

        Mockito.verify(commandExecute,Mockito.times(NUM_EXECUTE_COMMAND_EXECUTE)).execute();
        Mockito.verify(commandSleep,Mockito.times(NUM_EXECUTE_COMMAND_SLEEP)).execute();

    }


}