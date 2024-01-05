package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

public interface Renderable extends Disposable {
    // void for render obj
    void render(Batch batch);

    Rectangle geRenderableRectangle();

}
