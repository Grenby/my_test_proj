package ru.mipt.bit.platformer.game.gameWorld.collisions;

import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.Collidable;
import ru.mipt.bit.platformer.game.GameObject;
import ru.mipt.bit.platformer.game.gameWorld.GameObjectListener;

@RequiredArgsConstructor
public class CollisionResolverListener implements GameObjectListener {

    private final CollisionsResolverImpl collisionsResolver;

    @Override
    public void addGameObject(GameObject gameObject) {
        if (gameObject instanceof Collidable){
            collisionsResolver.addGameObject((Collidable) gameObject);
        }
    }

    @Override
    public void removeGameObject(GameObject gameObject) {
        if (gameObject instanceof Collidable){
            collisionsResolver.removeGameObject((Collidable) gameObject);
        }
    }
}
