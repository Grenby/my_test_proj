package ru.mipt.bit.platformer.gameGenerator;

import com.badlogic.gdx.Game;
import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.gameWorld.CollisionsResolver;
import ru.mipt.bit.platformer.game.gameWorld.GameEngine;
import ru.mipt.bit.platformer.game.gameWorld.GameObjectListener;
import ru.mipt.bit.platformer.game.gameWorld.LevelMap;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class WorldConfig {

    private final LevelMap map;
    private final CollisionsResolver resolver;
    private final List<GameObjectListener> listenersSupplier;

    private GameEngine engine = null;

    public LevelMap levelMap(){
        return map;
    }

    public GameEngine gameEngine(){
        if (Objects.isNull(engine)){
            engine = new GameEngine(resolver,listenersSupplier);
        }
        return engine;
    }

}
