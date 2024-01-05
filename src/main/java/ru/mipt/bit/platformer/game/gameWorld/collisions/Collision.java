package ru.mipt.bit.platformer.game.gameWorld.collisions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.Collidable;

@RequiredArgsConstructor
@Getter
public class Collision {

    private final Collidable a;
    private final Collidable b;

}
