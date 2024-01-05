package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.game.util.GdxGameUtils.createSingleLayerMapRenderer;

public class GameRender implements Disposable {

    private final Batch batch;
    private final List<Renderable> renderables;
    private final MapRenderer levelRenderer;

    public GameRender(TiledMap tiledMap) {
        this.renderables = new ArrayList<>();

        batch = new SpriteBatch();
        levelRenderer = createSingleLayerMapRenderer(tiledMap, batch);
    }

    public void addRenderable(Renderable renderable) {
        this.renderables.add(renderable);
    }

    public List<Renderable> getRenderables() {
        return renderables;
    }

    public void remove(Renderable renderable) {
        this.renderables.remove(renderable);
    }

    public void render() {
        clearScreen();

        levelRenderer.render();

        batch.begin();
        for (Renderable r : renderables) {
            r.render(batch);
        }
        batch.end();
    }

    private static void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose() {
        for (Renderable r : renderables) {
            r.dispose();
        }
        batch.dispose();
    }
}
