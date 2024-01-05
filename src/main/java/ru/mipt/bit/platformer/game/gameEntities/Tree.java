package ru.mipt.bit.platformer.game.gameEntities;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Collidable;
import ru.mipt.bit.platformer.game.GameObject;
import ru.mipt.bit.platformer.game.Healthable;

public class Tree implements Collidable, GameObject, Healthable {

    private final GridPoint2 position;

    public Tree(GridPoint2 position) {
        this.position = position;
    }

    public GridPoint2 getCoordinates() {
        return position;
    }

    @Override
    public boolean containsPoint(GridPoint2 point) {
        return position.equals(point);
    }

    @Override
    public boolean hasIntersection(Collidable collidable) {
        return collidable.containsPoint(position);
    }

    @Override
    public boolean isSensor() {
        return false;
    }

    @Override
    public void collide(Collidable collidable) {
        //do smth smart
    }

    @Override
    public void updateState(float delta) {

    }

    @Override
    public void applyDamage(float damage) {

    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public float getMaxHealth() {
        return 1;
    }

    @Override
    public float getCurrentHealth() {
        return 1;
    }
}
