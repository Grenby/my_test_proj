package ru.mipt.bit.platformer.game.gameWorld.map;

import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.Collidable;
import ru.mipt.bit.platformer.game.GameObject;
import ru.mipt.bit.platformer.game.gameWorld.GameObjectListener;

@RequiredArgsConstructor
public class LevelMapListener implements GameObjectListener {

    private final LevelMapImpl levelMap;

    @Override
    public void addGameObject(GameObject gameObject) {
        if (gameObject instanceof Collidable) {
            levelMap.addColliableObject((Collidable) gameObject);
        }
    }

    @Override
    public void removeGameObject(GameObject gameObject) {
        if (gameObject instanceof Collidable) {
            levelMap.removeColliableObject((Collidable) gameObject);
        }
    }
}
