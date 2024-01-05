package ru.mipt.bit.platformer.game.graphics;

import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.GameObject;
import ru.mipt.bit.platformer.game.gameWorld.GameObjectListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class RenderableListener implements GameObjectListener {

    private final Map<GameObject, Renderable> gameObjectRenderableMap = new HashMap<>();

    private final GameRender gameRender;
    private final List<RenderableProvider> providers;

    @Override
    public void addGameObject(GameObject gameObject) {
        if (gameObjectRenderableMap.containsKey(gameObject)) {
            return;
        }
        for (RenderableProvider provider : providers) {
            Renderable renderable = provider.getRenderable(gameObject);
            if (renderable != null) {
                gameObjectRenderableMap.put(gameObject, renderable);
                gameRender.addRenderable(renderable);
                return;
            }
        }
    }

    @Override
    public void removeGameObject(GameObject gameObject) {
        if (gameObjectRenderableMap.containsKey(gameObject)) {
            gameRender.remove(gameObjectRenderableMap.get(gameObject));
        }
    }
}
