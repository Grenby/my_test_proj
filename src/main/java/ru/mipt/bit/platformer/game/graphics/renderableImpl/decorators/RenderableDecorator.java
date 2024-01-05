package ru.mipt.bit.platformer.game.graphics.renderableImpl.decorators;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.graphics.Renderable;

@RequiredArgsConstructor
public class RenderableDecorator implements Renderable {

    private final Renderable renderable;

    @Override
    public void render(Batch batch) {
        renderable.render(batch);
    }

    @Override
    public Rectangle geRenderableRectangle() {
        return renderable.geRenderableRectangle();
    }

    @Override
    public void dispose() {
        renderable.dispose();
    }
}
