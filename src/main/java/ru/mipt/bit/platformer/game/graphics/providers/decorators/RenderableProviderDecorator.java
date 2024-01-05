package ru.mipt.bit.platformer.game.graphics.providers.decorators;

import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.GameObject;
import ru.mipt.bit.platformer.game.graphics.Renderable;
import ru.mipt.bit.platformer.game.graphics.RenderableProvider;

@RequiredArgsConstructor
public class RenderableProviderDecorator implements RenderableProvider {

    private final RenderableProvider provider;

    @Override
    public Renderable getRenderable(GameObject gameObject) {
        return provider.getRenderable(gameObject);
    }

}
