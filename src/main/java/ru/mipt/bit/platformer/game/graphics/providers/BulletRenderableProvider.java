package ru.mipt.bit.platformer.game.graphics.providers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.GameObject;
import ru.mipt.bit.platformer.game.gameEntities.Bullet;
import ru.mipt.bit.platformer.game.graphics.Renderable;
import ru.mipt.bit.platformer.game.graphics.RenderableProvider;
import ru.mipt.bit.platformer.game.graphics.renderableImpl.BulletRenderableImpl;
import ru.mipt.bit.platformer.game.util.TileMovement;

@RequiredArgsConstructor
public class BulletRenderableProvider implements RenderableProvider {

    private final TileMovement tileMovement;
    private final AssetManager manager;
    @Override
    public Renderable getRenderable(GameObject gameObject) {
        if (gameObject instanceof Bullet) {
            return new BulletRenderableImpl(manager.get("images/bullet.png", Texture.class), (Bullet) gameObject, tileMovement);
        }
        return null;
    }
}
