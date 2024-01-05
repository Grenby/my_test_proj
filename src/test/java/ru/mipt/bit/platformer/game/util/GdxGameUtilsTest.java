package ru.mipt.bit.platformer.game.util;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GdxGameUtilsTest {

    @Test
    public void testMoveRectangleAtTileCenter() {
        final int W = 10;
        final int H = 10;
        TiledMapTileLayer layer = Mockito.mock(TiledMapTileLayer.class);
        Mockito.when(layer.getTileWidth()).thenReturn(W);
        Mockito.when(layer.getTileHeight()).thenReturn(H);

        Vector2 targetCenterCoord = new Vector2(W / 2.0f, H / 2.0f);

        Rectangle rectangle = new Rectangle(0, 0, 1, 1);
        GdxGameUtils.moveRectangleAtTileCenter(layer, rectangle, new GridPoint2(0, 0));

        assertEquals(1, rectangle.width);
        assertEquals(1, rectangle.height);
        assertEquals(targetCenterCoord, rectangle.getCenter(new Vector2()));
    }

    @Test
    public void testCreateBoundingRectangle() {
        final int W = 10;
        final int H = 20;
        TextureRegion textureRegion = Mockito.mock(TextureRegion.class);
        Mockito.when(textureRegion.getRegionWidth()).thenReturn(W);
        Mockito.when(textureRegion.getRegionHeight()).thenReturn(H);

        assertEquals(new Rectangle(0, 0, W, H), GdxGameUtils.createBoundingRectangle(textureRegion));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideDataForChangeCoordinates")
    public void testIncrementedY(GridPoint2 start) {
        GridPoint2 startCpy = start.cpy();
        GridPoint2 end = GdxGameUtils.incrementedY(startCpy);

        assertEquals(start, startCpy);
        assertEquals(start.x, end.x);
        assertEquals(start.y + 1, end.y);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideDataForChangeCoordinates")
    public void testIncrementedX() {
        GridPoint2 start = new GridPoint2(0, 0);
        GridPoint2 startCpy = start.cpy();
        GridPoint2 end = GdxGameUtils.incrementedX(startCpy);

        assertEquals(start, startCpy);
        assertEquals(start.x + 1, end.x);
        assertEquals(start.y, end.y);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideDataForChangeCoordinates")
    public void testDecrementedY() {
        GridPoint2 start = new GridPoint2(0, 0);
        GridPoint2 startCpy = start.cpy();
        GridPoint2 end = GdxGameUtils.decrementedY(startCpy);

        assertEquals(start, startCpy);
        assertEquals(start.x, end.x);
        assertEquals(start.y - 1, end.y);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideDataForChangeCoordinates")
    public void testDecrementedX() {
        GridPoint2 start = new GridPoint2(0, 0);
        GridPoint2 startCpy = start.cpy();
        GridPoint2 end = GdxGameUtils.decrementedX(startCpy);

        assertEquals(start, startCpy);
        assertEquals(start.x - 1, end.x);
        assertEquals(start.y, end.y);
    }

    private static Stream<Arguments> provideDataForChangeCoordinates() {
        return Stream.of(
                Arguments.of(new GridPoint2(0, 0)),
                Arguments.of(new GridPoint2(1, 0))
        );
    }

}