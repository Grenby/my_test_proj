package ru.mipt.bit.platformer.game.graphics.renderableImpl.decorators;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.game.Holder;
import ru.mipt.bit.platformer.game.graphics.Renderable;

public class AdditionalRenderableDecorators extends RenderableDecorator {

    private final Renderable target;
    private final Holder<Boolean> shouldRender;

    public AdditionalRenderableDecorators(Renderable defaultRenderable, Renderable target, Holder<Boolean> shouldRender) {
        super(defaultRenderable);
        this.target = target;
        this.shouldRender = shouldRender;
    }

    @Override
    public void render(Batch batch) {
        super.render(batch);
        if (shouldRender.getValue()){
            target.render(batch);
        }
    }

}
