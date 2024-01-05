package ru.mipt.bit.platformer.game.gameWorld;

import com.badlogic.gdx.math.GridPoint2;

public interface LevelMap {

    boolean isMapPointFree(GridPoint2 point);

}
