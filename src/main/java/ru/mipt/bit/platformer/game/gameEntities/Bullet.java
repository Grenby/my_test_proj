package ru.mipt.bit.platformer.game.gameEntities;

import com.badlogic.gdx.math.GridPoint2;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.mipt.bit.platformer.game.Collidable;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.GameObject;
import ru.mipt.bit.platformer.game.Healthable;
import ru.mipt.bit.platformer.game.gameWorld.GameEngine;

import java.util.Objects;

import static ru.mipt.bit.platformer.game.util.GdxGameUtils.continueProgress;

public class Bullet implements GameObject, Collidable, Healthable {

    private static final float DEFAULT_BULLET_SPEED = 4f;
    private static final float DEFAULT_BULLET_DAMAGE = 2f;


    private static final float MOVEMENT_COMPLETED = 1f;
    private static final int MOVEMENT_STARTED = 0;

    private final GameEngine engine;
    private final float movementSpeed;
    private final float damage;
    private final Direction direction;

    private GridPoint2 coordinates;
    private GridPoint2 destinationCoordinates;
    private float movementProgress;
    private boolean isAlive;

    private Bullet(float movementSpeed, float damage, Direction direction, GridPoint2 coordinates, GameEngine engine) {
        this.movementSpeed = movementSpeed;
        this.movementProgress = 0;

        this.direction = direction;
        this.coordinates = coordinates;
        this.destinationCoordinates = direction.apply(coordinates);

        this.damage = damage;
        this.isAlive = true;

        this.engine = engine;
    }

    @Override
    public void updateState(float delta) {
        if (!isAlive) {
            engine.removeGameObject(this);
            return;
        }
        movementProgress = continueProgress(movementProgress, delta, movementSpeed);
        if (movementProgress >= MOVEMENT_COMPLETED) {
            // record that the player has reached his/her destination
            coordinates = destinationCoordinates;
            movementProgress = MOVEMENT_STARTED;
            destinationCoordinates = direction.apply(coordinates);
        }
    }

    @Override
    public boolean containsPoint(GridPoint2 point) {
        return movementProgress > 0.5f ? point.equals(destinationCoordinates) : point.equals(coordinates);
    }

    @Override
    public boolean hasIntersection(Collidable collidable) {
        return movementProgress > 0.5f ? collidable.containsPoint(destinationCoordinates) : collidable.containsPoint(coordinates);
    }

    @Override
    public boolean isSensor() {
        return false;
    }

    @Override
    public void collide(Collidable collidable) {
        if (collidable instanceof Healthable){
            Healthable healthable = (Healthable) collidable;
            healthable.applyDamage(damage);
            this.isAlive = false;
        }
    }

    public float getDamage() {
        return damage;
    }

    @Override
    public void applyDamage(float damage) {
        isAlive = false;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public float getMaxHealth() {
        return 0;
    }

    @Override
    public float getCurrentHealth() {
        return 0;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public Direction getDirection() {
        return direction;
    }

    public static Bullet.BulletBuilder init(GameEngine engine, Direction direction, GridPoint2 coordinates) {
        if (Objects.isNull(engine) || Objects.isNull(direction) || Objects.isNull(coordinates)) {
            throw new IllegalArgumentException();
        }
        return new Bullet.BulletBuilder(engine, direction, coordinates);
    }

    @Accessors(fluent = true, chain = true)
    @Setter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static class BulletBuilder {
        private final GameEngine engine;
        private final Direction direction;
        private final GridPoint2 coordinates;

        private float movementSpeed = DEFAULT_BULLET_SPEED;
        private float damage = DEFAULT_BULLET_DAMAGE;

        public Bullet build() {
            return new Bullet(
                    movementSpeed,
                    damage,
                    direction,
                    coordinates,
                    engine
            );
        }
    }

}
