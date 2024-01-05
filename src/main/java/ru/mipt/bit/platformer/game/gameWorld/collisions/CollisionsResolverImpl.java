package ru.mipt.bit.platformer.game.gameWorld.collisions;

import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.Collidable;
import ru.mipt.bit.platformer.game.gameWorld.CollisionsResolver;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CollisionsResolverImpl implements CollisionsResolver {

    private final List<Collidable> collidableList = new ArrayList<>();
    private final List<Collision> collisions = new ArrayList<>();

    @Override
    public void resolveAllCollision() {
        collisions.clear();
        for (int i = 0; i < collidableList.size(); i++) {
            for (int j = i + 1; j < collidableList.size(); j++) {
                Collidable a = collidableList.get(i);
                Collidable b = collidableList.get(j);
                if (a.hasIntersection(b) || b.hasIntersection(a)) {
                    collisions.add(new Collision(a, b));
                }
            }
        }
        for (Collision c : collisions) {
            c.getA().collide(c.getB());
            c.getB().collide(c.getA());
        }
    }

    public void addGameObject(Collidable collidable) {
        collidableList.add(collidable);
    }

    public void removeGameObject(Collidable collidable) {
        collidableList.remove(collidable);
    }
}
