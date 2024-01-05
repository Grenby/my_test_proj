package ru.mipt.bit.platformer.game.graphics;

import ru.mipt.bit.platformer.game.GameObject;

public interface RenderableProvider {

    Renderable getRenderable(GameObject gameObject);

}
