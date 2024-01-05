package ru.mipt.bit.platformer.game.graphics.renderableImpl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.game.gameEntities.Tank;
import ru.mipt.bit.platformer.game.graphics.Renderable;
import ru.mipt.bit.platformer.game.util.TileMovement;

import static ru.mipt.bit.platformer.game.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.game.util.GdxGameUtils.drawTextureRegionUnscaled;

public class TankRenderableImpl implements Renderable {

    private final Tank tank;
    private final TileMovement tileMovement;
    private final TextureRegion tankGraphics;
    private final Rectangle tankRectangle;

    public TankRenderableImpl(Texture texture, Tank tank, TileMovement tileMovement) {
        this.tank = tank;
        this.tileMovement = tileMovement;

        // Texture decodes an image file and loads it into GPU memory, it represents a native resource
        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
        tankGraphics = new TextureRegion(texture);
        tankRectangle = createBoundingRectangle(tankGraphics);
    }

    @Override
    public void render(Batch batch) {
        tileMovement.moveRectangleBetweenTileCenters(tankRectangle, tank.getCoordinates(), tank.getDestinationCoordinates(), tank.getMovementProgress());
        drawTextureRegionUnscaled(batch, tankGraphics, tankRectangle, tank.getDirection().getRotation());
    }

    @Override
    public Rectangle geRenderableRectangle() {
        return tankRectangle;
    }

    @Override
    public void dispose() {
    }

}
