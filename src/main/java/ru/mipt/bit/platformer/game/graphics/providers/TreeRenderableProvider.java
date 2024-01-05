package ru.mipt.bit.platformer.game.graphics.providers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.GameObject;
import ru.mipt.bit.platformer.game.gameEntities.Tree;
import ru.mipt.bit.platformer.game.graphics.Renderable;
import ru.mipt.bit.platformer.game.graphics.RenderableProvider;
import ru.mipt.bit.platformer.game.graphics.renderableImpl.TreeRenderableImpl;

@RequiredArgsConstructor
public class TreeRenderableProvider implements RenderableProvider {

    private final TiledMapTileLayer tileLayer;
    private final AssetManager manager;

    @Override
    public Renderable getRenderable(GameObject gameObject) {
        if (gameObject instanceof Tree) {
            return new TreeRenderableImpl(manager.get("images/greenTree.png", Texture.class), (Tree) gameObject, tileLayer);
        }
        return null;
    }
}
