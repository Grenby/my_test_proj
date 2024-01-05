package ru.mipt.bit.platformer.game.graphics.renderableImpl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.game.gameEntities.Bullet;
import ru.mipt.bit.platformer.game.graphics.Renderable;
import ru.mipt.bit.platformer.game.util.TileMovement;

import static ru.mipt.bit.platformer.game.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.game.util.GdxGameUtils.drawTextureRegionScaled;

public class BulletRenderableImpl implements Renderable {

    private final Bullet bullet;
    private final TileMovement tileMovement;
    private final TextureRegion bulletGraphics;
    private final Rectangle bulletRectangle;

    public BulletRenderableImpl(Texture texture, Bullet bullet, TileMovement tileMovement) {
        this.bullet = bullet;
        this.tileMovement = tileMovement;

        // Texture decodes an image file and loads it into GPU memory, it represents a native resource
        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
        bulletGraphics = new TextureRegion(texture);
        bulletRectangle = createBoundingRectangle(bulletGraphics);
    }

    @Override
    public void render(Batch batch) {
        tileMovement.moveRectangleBetweenTileCenters(bulletRectangle, bullet.getCoordinates(), bullet.getDestinationCoordinates(), bullet.getMovementProgress());
        drawTextureRegionScaled(batch, bulletGraphics, bulletRectangle, bullet.getDirection().getRotation(),0.01f);
    }

    @Override
    public Rectangle geRenderableRectangle() {
        return null;
    }

    @Override
    public void dispose() {
    }

}
