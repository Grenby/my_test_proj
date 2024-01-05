package ru.mipt.bit.platformer.game.gameWorld;

import ru.mipt.bit.platformer.game.GameObject;

public interface GameObjectListener {

    void addGameObject(GameObject gameObject);

    void removeGameObject(GameObject gameObject);
}
