package ru.mipt.bit.platformer.game.graphics.renderableImpl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.game.gameEntities.Tree;
import ru.mipt.bit.platformer.game.graphics.Renderable;

import static ru.mipt.bit.platformer.game.util.GdxGameUtils.*;

public class TreeRenderableImpl implements Renderable {

    private final TextureRegion treeObstacleGraphics;
    private final Rectangle treeObstacleRectangle;

    public TreeRenderableImpl(Texture texture, Tree tree, TiledMapTileLayer tileLayer) {
        treeObstacleGraphics = new TextureRegion(texture);
        treeObstacleRectangle = createBoundingRectangle(treeObstacleGraphics);
        moveRectangleAtTileCenter(tileLayer, treeObstacleRectangle, tree.getCoordinates());
    }


    @Override
    public void render(Batch batch) {
        // render tree obstacle
        drawTextureRegionUnscaled(batch, treeObstacleGraphics, treeObstacleRectangle, 0f);
    }

    @Override
    public Rectangle geRenderableRectangle() {
        return treeObstacleRectangle;
    }

    @Override
    public void dispose() {

    }
}
