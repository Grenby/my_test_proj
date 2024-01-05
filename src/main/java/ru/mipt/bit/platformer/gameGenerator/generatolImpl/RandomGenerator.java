package ru.mipt.bit.platformer.gameGenerator.generatolImpl;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.gameEntities.Tank;
import ru.mipt.bit.platformer.game.gameEntities.Tree;
import ru.mipt.bit.platformer.game.gameWorld.GameEngine;
import ru.mipt.bit.platformer.game.gameWorld.LevelMap;
import ru.mipt.bit.platformer.gameGenerator.GameGenerator;
import ru.mipt.bit.platformer.gameGenerator.GeneratedLevelInfo;
import ru.mipt.bit.platformer.gameGenerator.WorldConfig;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class RandomGenerator implements GameGenerator {

    private final Set<GridPoint2> takenPoints = new HashSet<>();
    private final WorldConfig config;
    private final Random random = new Random();
    private final int numTrees;
    private final int w, h;
    private final int enemyTanks;

    public RandomGenerator(int w, int h, int numTrees, int enemyTanks, WorldConfig config) {
        this.w = w;
        this.h = h;
        this.numTrees = numTrees;
        this.enemyTanks = enemyTanks;
        this.config = config;
    }

    @Override
    public GeneratedLevelInfo generate() {

        Supplier<Tank> tankProvider = () -> Tank.init(config.gameEngine(), config.levelMap(), getRandomPoint(w, h))
                .build();

        Tank playerTank = tankProvider.get();

        config.gameEngine().addGameObject(playerTank);

        for (int i = 0; i < enemyTanks; i++) {
            Tank tank = tankProvider.get();
            config.gameEngine().addGameObject(tank);
        }


        for (int i = 0; i < numTrees; i++) {
            Tree tree = new Tree(
                    getRandomPoint(w, h)
            );
            config.gameEngine().addGameObject(tree);
        }

        LevelMap levelMap = config.levelMap();
        GameEngine gameEngine = config.gameEngine();
        for (int i = -1; i < w; i++) {
            if (levelMap.isMapPointFree(new GridPoint2(i, -1)))
                gameEngine.addGameObject(new Tree(new GridPoint2(i, -1)));
            if (levelMap.isMapPointFree(new GridPoint2(i, h)))
                gameEngine.addGameObject(new Tree(new GridPoint2(i, h)));
        }
        for (int i = -1; i < h; i++) {
            if (levelMap.isMapPointFree(new GridPoint2(-1, i)))
                gameEngine.addGameObject(new Tree(new GridPoint2(-1, i)));
            if (levelMap.isMapPointFree(new GridPoint2(w, i)))
                gameEngine.addGameObject(new Tree(new GridPoint2(w, i)));
        }


        return new GeneratedLevelInfo(gameEngine, playerTank, levelMap);
    }

    private GridPoint2 getRandomPoint(int w, int h) {
        GridPoint2 res = new GridPoint2();
        do {
            res.x = random.nextInt(w);
            res.y = random.nextInt(h);
        } while (takenPoints.contains(res));
        takenPoints.add(res);
        return res;
    }

}
