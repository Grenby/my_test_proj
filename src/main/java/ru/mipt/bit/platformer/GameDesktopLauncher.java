package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import org.awesome.ai.strategy.NotRecommendingAI;
import ru.mipt.bit.platformer.game.GameObject;
import ru.mipt.bit.platformer.game.Holder;
import ru.mipt.bit.platformer.game.commands.commandsImpl.TankMovingCommands;
import ru.mipt.bit.platformer.game.commands.commandsImpl.TankShootCommand;
import ru.mipt.bit.platformer.game.commandsExecutors.CommandsExecutor;
import ru.mipt.bit.platformer.game.commandsExecutors.adaptersExecutors.AdapterAIExecutor;
import ru.mipt.bit.platformer.game.commandsExecutors.aiExecutors.AITankRandomImpl;
import ru.mipt.bit.platformer.game.commandsExecutors.inputExecutors.KeyboardExecutor;
import ru.mipt.bit.platformer.game.gameEntities.Tank;
import ru.mipt.bit.platformer.game.gameWorld.GameEngine;
import ru.mipt.bit.platformer.game.gameWorld.GameObjectListener;
import ru.mipt.bit.platformer.game.gameWorld.LevelMap;
import ru.mipt.bit.platformer.game.gameWorld.collisions.CollisionResolverListener;
import ru.mipt.bit.platformer.game.gameWorld.collisions.CollisionsResolverImpl;
import ru.mipt.bit.platformer.game.gameWorld.map.LevelMapImpl;
import ru.mipt.bit.platformer.game.gameWorld.map.LevelMapListener;
import ru.mipt.bit.platformer.game.graphics.GameRender;
import ru.mipt.bit.platformer.game.graphics.RenderableListener;
import ru.mipt.bit.platformer.game.graphics.commands.UpdateHealthRenderCommand;
import ru.mipt.bit.platformer.game.graphics.providers.BulletRenderableProvider;
import ru.mipt.bit.platformer.game.graphics.providers.TankRenderableProvider;
import ru.mipt.bit.platformer.game.graphics.providers.TreeRenderableProvider;
import ru.mipt.bit.platformer.game.graphics.providers.decorators.HealthRenderableProvider;
import ru.mipt.bit.platformer.game.util.TileMovement;
import ru.mipt.bit.platformer.gameGenerator.GameGenerator;
import ru.mipt.bit.platformer.gameGenerator.GeneratedLevelInfo;
import ru.mipt.bit.platformer.gameGenerator.WorldConfig;
import ru.mipt.bit.platformer.gameGenerator.generatolImpl.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Input.Keys.*;
import static ru.mipt.bit.platformer.game.util.GdxGameUtils.getSingleLayer;

public class GameDesktopLauncher implements ApplicationListener {

    private GameRender gameRender;
    private GameEngine gameEngine;
    private KeyboardExecutor inputController;
    private List<CommandsExecutor> commandsExecutors;

    private AssetManager manager;

    @Override
    public void create() {
        manager = new AssetManager();
        Holder<Boolean> shouldHealthRender = new Holder<>(false);

        GameGenerator gameGenerator = new RandomGenerator(10, 8, 4, 5, config(shouldHealthRender));

        GeneratedLevelInfo levelInfo = gameGenerator.generate();
        GameObject player = levelInfo.getPlayer();
        this.gameEngine = levelInfo.getEngine();

        commandsExecutors = new ArrayList<>();
        commandsExecutors.add(initKeyMappings(player));
        commandsExecutors.add(initAI(gameEngine.getAllGameObjects(), player, levelInfo.getLevelMap()));
        //commandsExecutors.add(initNotRecommendedAI(10, 8, player, gameEngine.getAllGameObjects()));
        inputController.addMapping(L,UpdateHealthRenderCommand.getCommand(shouldHealthRender),false);

    }

    @Override
    public void render() {
        checkAppClose();

        float deltaTime = Gdx.graphics.getDeltaTime();

        commandsExecutors.forEach(CommandsExecutor::executeCommands);

        gameEngine.update(deltaTime);

        gameRender.render();

    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        gameRender.dispose();
        manager.dispose();
    }

    private CommandsExecutor initKeyMappings(GameObject player) {
        if (player instanceof Tank) {
            Tank tank = (Tank) player;
            inputController = new KeyboardExecutor(Gdx.input);
            inputController.addMapping(UP, TankMovingCommands.moveUP(tank), true);
            inputController.addMapping(W, TankMovingCommands.moveUP(tank), true);
            inputController.addMapping(LEFT, TankMovingCommands.moveLeft(tank), true);
            inputController.addMapping(A, TankMovingCommands.moveLeft(tank), true);
            inputController.addMapping(DOWN, TankMovingCommands.moveDown(tank), true);
            inputController.addMapping(S, TankMovingCommands.moveDown(tank), true);
            inputController.addMapping(RIGHT, TankMovingCommands.moveRight(tank), true);
            inputController.addMapping(D, TankMovingCommands.moveRight(tank), true);
            inputController.addMapping(SPACE, TankShootCommand.shoot(tank), false);
            return inputController;
        }
        return null;
    }

    private GameObjectListener getRenderableReceiver(Holder<Boolean> shouldRenderHealth) {
        loadDefaultTextures();

        TiledMap level = manager.get("level.tmx");

        this.gameRender = new GameRender(level);

        TiledMapTileLayer groundLayer = getSingleLayer(level);
        TileMovement tileMovement = new TileMovement(groundLayer, Interpolation.linear);

        return new RenderableListener(
                gameRender,
                List.of(
                        new HealthRenderableProvider(
                                new TankRenderableProvider(tileMovement,manager),
                                manager,
                                shouldRenderHealth
                        ),
                        new BulletRenderableProvider(tileMovement,manager),
                        new TreeRenderableProvider(groundLayer,manager)
                )
        );
    }

    private void loadDefaultTextures() {
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load("images/tank_blue.png", Texture.class);
        manager.load("images/bullet.png", Texture.class);
        manager.load("images/greenTree.png", Texture.class);
        manager.load("images/health.png", Texture.class);
        manager.load("images/health_bar.png", Texture.class);

        manager.load("level.tmx",TiledMap.class);

        manager.finishLoading();
    }

    private LevelMapImpl getLevelMap(int w, int h) {
        return new LevelMapImpl(new GridPoint2(0, 0), new GridPoint2(w - 1, h - 1));
    }

    private CommandsExecutor initAI(List<GameObject> gameObjects, GameObject player, LevelMap levelMap) {
        List<Tank> enemies = new ArrayList<>();

        for (GameObject go : gameObjects) {
            if (go instanceof Tank) {
                Tank tank = (Tank) go;
                if (tank != player) {
                    enemies.add(tank);
                }
            }
        }

        return new AITankRandomImpl(levelMap, enemies);
    }

    private CommandsExecutor initNotRecommendedAI(int w, int h, GameObject player, List<GameObject> gameObjects) {
        return new AdapterAIExecutor(new NotRecommendingAI(),w, h, player, gameObjects);
    }

    private void checkAppClose() {
        if (Gdx.input.isKeyPressed(ESCAPE)) {
            dispose();
            Gdx.app.exit();
        }
    }

    private WorldConfig config(Holder<Boolean> booleanHolder){
        int w = 10;
        int h = 8;
        LevelMapImpl levelMap = getLevelMap(w, h);
        CollisionsResolverImpl resolver = new CollisionsResolverImpl();
        return new WorldConfig(
                levelMap,
                resolver,
                List.of(getRenderableReceiver(booleanHolder),
                        new LevelMapListener(levelMap),
                        new CollisionResolverListener(resolver))
        );
    }

}
