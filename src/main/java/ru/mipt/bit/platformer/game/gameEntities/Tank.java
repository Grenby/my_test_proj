package ru.mipt.bit.platformer.game.gameEntities;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.mipt.bit.platformer.game.Collidable;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.GameObject;
import ru.mipt.bit.platformer.game.Healthable;
import ru.mipt.bit.platformer.game.gameEntities.tankStates.LightTankState;
import ru.mipt.bit.platformer.game.gameWorld.GameEngine;
import ru.mipt.bit.platformer.game.gameWorld.LevelMap;

import java.util.Objects;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.game.util.GdxGameUtils.continueProgress;


public class Tank implements Collidable, GameObject, Healthable {

    public static final float DEFAULT_TANK_SPEED = 2f;
    public static final float DEFAULT_TANK_HEALTH = 10f;

    private static final float MOVEMENT_COMPLETED = 1f;
    private static final int MOVEMENT_STARTED = 0;

    private final GameEngine engine;
    private final LevelMap map;

    @Getter
    private final float movementSpeed;
    private final float maxHealth;

    @Setter
    private TankState state;

    private Direction direction;
    private GridPoint2 coordinates;
    private GridPoint2 destinationCoordinates;
    private float health;
    private float movementProgress;


    private Tank(GridPoint2 coordinates, Direction direction, float movementSpeed, float health, GameEngine engine, LevelMap map) {
        this.engine = engine;
        this.map = map;
        this.movementProgress = 1;
        this.coordinates = coordinates;
        this.destinationCoordinates = coordinates.cpy();
        this.direction = direction;
        this.movementSpeed = movementSpeed;
        this.health = health;
        this.maxHealth = health;
        state = new LightTankState(new TankHolder());
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean containsPoint(GridPoint2 point) {
        return coordinates.equals(point) || destinationCoordinates.equals(point);
    }

    @Override
    public boolean hasIntersection(Collidable collidable) {
        return collidable.containsPoint(coordinates) || collidable.containsPoint(destinationCoordinates);
    }

    @Override
    public boolean isSensor() {
        return false;
    }

    @Override
    public void collide(Collidable collidable) {
        // to smth
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    /**
     * @return is tank moving now
     */
    public boolean isMoving() {
        return !isEqual(movementProgress, MOVEMENT_COMPLETED);
    }

    public void moveTo(GridPoint2 tankTargetCoordinates) {
        if (!isMoving()) {
            rotate(Direction.getDirect(new GridPoint2(tankTargetCoordinates).sub(coordinates)));
            if (map.isMapPointFree(tankTargetCoordinates)) {
                destinationCoordinates = tankTargetCoordinates;
                movementProgress = MOVEMENT_STARTED;
            }
        }
    }

    public void rotate(Direction direction) {
        this.direction = direction;
    }

    public void updateState(float deltaTime) {

        state.updateState();
        state.move(deltaTime);
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public Direction getDirection() {
        return direction;
    }

    public void shoot() {
        state.shoot();
    }

    @Override
    public void applyDamage(float damage) {
        this.health = MathUtils.clamp(this.health - damage,0,maxHealth);
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public float getMaxHealth() {
        return maxHealth;
    }

    @Override
    public float getCurrentHealth() {
        return health;
    }

    public static TankBuilder init(GameEngine engine, LevelMap map, GridPoint2 coordinates) {
        if (Objects.isNull(engine) || Objects.isNull(map) || Objects.isNull(coordinates)) {
            throw new IllegalArgumentException();
        }
        return new TankBuilder(engine, map, coordinates);
    }

    @Accessors(fluent = true, chain = true)
    @Setter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TankBuilder {

        private final GameEngine engine;
        private final LevelMap map;
        private final GridPoint2 coordinates;

        private float movementSpeed = DEFAULT_TANK_SPEED;
        private float health = DEFAULT_TANK_HEALTH;
        private Direction direction = Direction.UP;

        public Tank build() {
            return new Tank(
                    coordinates,
                    direction,
                    movementSpeed,
                    health,
                    engine,
                    map
            );
        }

    }

    public class TankHolder {

        private TankHolder() {
        }

        public Tank tank() {
            return Tank.this;
        }

        public void die() {
            engine.removeGameObject(Tank.this);
        }

        public void move(float deltaTime, float movementSpeed) {
            movementProgress = continueProgress(movementProgress, deltaTime, movementSpeed);
            if (!isMoving()) {
                // record that the player has reached his/her destination
                coordinates = destinationCoordinates;
            }
        }

        public void shoot() {
            if (!isMoving()) {
                engine.addGameObject(
                        Bullet.init(engine, direction, direction.apply(coordinates)).build()
                );
            }
        }

    }

}
