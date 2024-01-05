package ru.mipt.bit.platformer.game.util;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.mipt.bit.platformer.game.util.GdxGameUtils.moveRectangleAtTileCenter;

class TileMovementTest {

    @ParameterizedTest
    @ValueSource(floats = {0.0f, 0.5f, 1.0f})
    public void moveRectangleBetweenTileCenters(float progress) {
        //initial constants
        final int TILE_W = 10;
        final int TILE_H = 10;
        final float RECT_W = 2.0f;
        final float RECT_H = 1.0f;
        //init
        Interpolation interpolation = Interpolation.smooth;
        GridPoint2 start = new GridPoint2(0, 1);
        GridPoint2 end = new GridPoint2(0, 2);

        TiledMapTileLayer layer = Mockito.mock(TiledMapTileLayer.class);
        Mockito.when(layer.getTileWidth()).thenReturn(TILE_W);
        Mockito.when(layer.getTileHeight()).thenReturn(TILE_H);

        Rectangle startRect = moveRectangleAtTileCenter(layer, new Rectangle(0, 0, RECT_W, RECT_H), start);
        Rectangle endRect = moveRectangleAtTileCenter(layer, new Rectangle(0, 0, RECT_W, RECT_H), end);

        final float targetX = interpolation.apply(startRect.x, endRect.x, progress);
        final float targetY = interpolation.apply(startRect.y, endRect.y, progress);

        Rectangle rectangle = new Rectangle(0, 0, RECT_W, RECT_H);

        //apply
        TileMovement tileMovement = new TileMovement(layer, interpolation);
        tileMovement.moveRectangleBetweenTileCenters(rectangle, start, end, progress);

        //assert
        assertEquals(RECT_W, rectangle.width);
        assertEquals(RECT_H, rectangle.height);
        assertEquals(targetX, rectangle.x);
        assertEquals(targetY, rectangle.y);
    }

}