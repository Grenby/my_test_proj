package ru.mipt.bit.platformer.game;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.gameWorld.LevelMap;

/**
 * Interface to support collision between game object
 * For now it`s used for detection when is point on the map empty
 *
 * @see LevelMap
 * <p>
 * todo add object-object collision support
 * For more complex collision (like tank-tank, tank-bullet) a new method needs to be add
 * collide(Collision collision) where class Collision contains all metadata about collision
 */
public interface Collidable {

    boolean containsPoint(GridPoint2 point);

    boolean hasIntersection(Collidable collidable);

    boolean isSensor();

    void collide(Collidable collidable);



}
