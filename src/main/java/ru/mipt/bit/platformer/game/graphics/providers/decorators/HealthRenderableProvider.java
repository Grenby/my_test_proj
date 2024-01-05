package ru.mipt.bit.platformer.game.graphics.providers.decorators;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.game.GameObject;
import ru.mipt.bit.platformer.game.Healthable;
import ru.mipt.bit.platformer.game.Holder;
import ru.mipt.bit.platformer.game.gameEntities.Tank;
import ru.mipt.bit.platformer.game.graphics.Renderable;
import ru.mipt.bit.platformer.game.graphics.RenderableProvider;
import ru.mipt.bit.platformer.game.graphics.renderableImpl.HealthRenderImpl;
import ru.mipt.bit.platformer.game.graphics.renderableImpl.decorators.AdditionalRenderableDecorators;

import java.util.Objects;

public class HealthRenderableProvider extends RenderableProviderDecorator {

    private final AssetManager manager;
    private final Holder<Boolean> shouldRender;
    public HealthRenderableProvider(RenderableProvider provider, AssetManager manager, Holder<Boolean> shouldRender) {
        super(provider);
        this.manager = manager;
        this.shouldRender = shouldRender;
    }

    @Override
    public Renderable getRenderable(GameObject gameObject) {
        Renderable renderable = super.getRenderable(gameObject);
        if (Objects.isNull(renderable)){
            return null;
        }
        if (gameObject instanceof Healthable) {
            HealthRenderImpl healthRender = new HealthRenderImpl(
                    manager.get("images/health_bar.png", Texture.class),
                    manager.get("images/health.png", Texture.class),
                    renderable.geRenderableRectangle(),
                    (Tank) gameObject
            );
            return new AdditionalRenderableDecorators(renderable,healthRender,shouldRender);
        }
        return renderable;
    }
}
