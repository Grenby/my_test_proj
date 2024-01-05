package ru.mipt.bit.platformer.game.commands;

/**
 * Interface to give command to game objects
 * It doesn't guarantee that command will be executed, unit may not have the option to do this
 */
public interface Command {
    void execute();
}
