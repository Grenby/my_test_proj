package ru.mipt.bit.platformer.gameGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.GameObject;
import ru.mipt.bit.platformer.game.gameWorld.GameEngine;
import ru.mipt.bit.platformer.game.gameWorld.LevelMap;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class GeneratedLevelInfo {

    private final GameEngine engine;
    private final GameObject player;
    private LevelMap levelMap;
}
