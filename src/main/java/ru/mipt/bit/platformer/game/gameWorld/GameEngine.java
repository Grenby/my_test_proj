package ru.mipt.bit.platformer.game.gameWorld;

import ru.mipt.bit.platformer.game.GameObject;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private final CollisionsResolver collisionsResolver;
    private final List<GameObject> gameObjects;
    private final List<GameObjectListener> listeners;

    private final List<GameObject> gameObjectsToAdd = new ArrayList<>();
    private final List<GameObject> gameObjectsToRemove = new ArrayList<>();

    private boolean update = false;


    public GameEngine(CollisionsResolver collisionsResolver) {
        gameObjects = new ArrayList<>();
        listeners = new ArrayList<>();
        this.collisionsResolver = collisionsResolver;
    }

    public GameEngine(CollisionsResolver collisionsResolver, List<GameObjectListener> listeners) {
        this(collisionsResolver);
        this.listeners.addAll(listeners);
    }

    public void addGameObjectReceiver(GameObjectListener gameObjectListener) {
        listeners.add(gameObjectListener);
    }

    public void addGameObject(GameObject gameObject) {
        if (update) {
            gameObjectsToAdd.add(gameObject);
            return;
        }
        gameObjects.add(gameObject);
        listeners.forEach(o -> o.addGameObject(gameObject));
    }

    public void removeGameObject(GameObject gameObject) {
        if (update) {
            gameObjectsToRemove.add(gameObject);
            return;
        }

        gameObjects.remove(gameObject);

        listeners.forEach(o -> o.removeGameObject(gameObject));
    }

    public List<GameObject> getAllGameObjects() {
        return this.gameObjects;
    }

    public void update(float delta) {
        update = true;
        collisionsResolver.resolveAllCollision();
        updateGameObjectsState(delta);
        update = false;

        gameObjectsToAdd.forEach(this::addGameObject);
        gameObjectsToRemove.forEach(this::removeGameObject);
        gameObjectsToRemove.clear();
        gameObjectsToAdd.clear();
    }

    private void updateGameObjectsState(float delta) {
        for (GameObject object : gameObjects) {
            object.updateState(delta);
        }
    }

}
