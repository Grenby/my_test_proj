package ru.mipt.bit.platformer.game.gameWorld.collisions;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.gameEntities.Bullet;
import ru.mipt.bit.platformer.game.gameEntities.Tree;
import ru.mipt.bit.platformer.game.gameWorld.CollisionsResolver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CollisionsResolverImplTest {
//    @Mock
//    Resolver resolver;
//    CollisionsResolverImpl collisionsResolver;
//
//    @Test
//    public void test(){
//        Tree tree = new Tree(new GridPoint2(0,0));
//        Bullet bullet = new Bullet(0,0, Direction.UP,new GridPoint2(0,0),null);
//
//        collisionsResolver = new CollisionsResolverImpl(List.of(resolver));
//        collisionsResolver.addGameObject(tree);
//        collisionsResolver.addGameObject(bullet);
//
//        collisionsResolver.resolveAllCollision();
//
//        Mockito.verify(resolver).resolve(Mockito.any());
//
//    }

}