package ru.mipt.bit.platformer.game.graphics.providers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.GameObject;
import ru.mipt.bit.platformer.game.gameEntities.Tank;
import ru.mipt.bit.platformer.game.graphics.Renderable;
import ru.mipt.bit.platformer.game.graphics.RenderableProvider;
import ru.mipt.bit.platformer.game.graphics.renderableImpl.TankRenderableImpl;
import ru.mipt.bit.platformer.game.util.TileMovement;

@RequiredArgsConstructor
public class TankRenderableProvider implements RenderableProvider {

    private final TileMovement tileMovement;
    private final AssetManager manager;

    @Override
    public Renderable getRenderable(GameObject gameObject) {
        if (gameObject instanceof Tank) {
            return new TankRenderableImpl(manager.get("images/tank_blue.png", Texture.class), (Tank) gameObject, tileMovement);
        }
        return null;
    }
}
