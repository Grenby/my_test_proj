package ru.mipt.bit.platformer.game.commandsExecutors.adaptersExecutors;

import lombok.RequiredArgsConstructor;
import org.awesome.ai.AI;
import org.awesome.ai.Action;
import org.awesome.ai.Recommendation;
import org.awesome.ai.state.GameState;
import org.awesome.ai.state.immovable.Obstacle;
import org.awesome.ai.state.movable.Actor;
import org.awesome.ai.state.movable.Bot;
import org.awesome.ai.state.movable.Orientation;
import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.game.Direction;
import ru.mipt.bit.platformer.game.GameObject;
import ru.mipt.bit.platformer.game.commands.commandsImpl.TankMovingCommands;
import ru.mipt.bit.platformer.game.commands.commandsImpl.TankShootCommand;
import ru.mipt.bit.platformer.game.commandsExecutors.CommandsExecutor;
import ru.mipt.bit.platformer.game.gameEntities.Tank;
import ru.mipt.bit.platformer.game.gameEntities.Tree;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AdapterAIExecutor implements CommandsExecutor {

    private final AI ai;

    private final int levelHeight;
    private final int levelWidth;

    private final GameObject playerGameObject;
    private final List<GameObject> gameObjects;



    @Override
    public void executeCommands() {
        List<Recommendation> recommendations = ai.recommend(buildGameState());
        for (Recommendation recommendation : recommendations) {
            Actor actor = recommendation.getActor();
            Action action = recommendation.getAction();

            if (actor.getSource() instanceof Tank) {
                Tank tank = (Tank) actor.getSource();
                switch (action) {
                    case Shoot:
                        TankShootCommand.shoot(tank).execute();
                        break;
                    case MoveSouth:
                        TankMovingCommands.moveDown(tank).execute();
                        break;
                    case MoveNorth:
                        TankMovingCommands.moveUP(tank).execute();
                        break;
                    case MoveEast:
                        TankMovingCommands.moveRight(tank).execute();
                        break;
                    case MoveWest:
                        TankMovingCommands.moveLeft(tank).execute();
                        break;
                }
            }
        }
    }

    private GameState buildGameState() {
        return GameState.builder()
                .player(getPlayer())
                .levelHeight(levelHeight)
                .levelWidth(levelWidth)
                .obstacles(getObstacles())
                .bots(getBots())
                .build();
    }

    private List<Obstacle> getObstacles() {
        return gameObjects.stream()
                .filter(o -> o instanceof Tree)
                .map(o -> (Tree) o)
                .map(t -> new Obstacle(t.getCoordinates().x, t.getCoordinates().y))
                .collect(Collectors.toList());
    }

    private List<Bot> getBots() {
        return gameObjects.stream()
                .filter(o -> (o instanceof Tank && o != playerGameObject))
                .map(o -> (Tank) o)
                .map(this::getBot)
                .collect(Collectors.toList());
    }

    private Bot getBot(Tank tank) {
        return new Bot.BotBuilder()
                .source(tank)
                .x(tank.getCoordinates().x)
                .y(tank.getCoordinates().y)
                .destX(tank.getDestinationCoordinates().x)
                .destY(tank.getDestinationCoordinates().y)
                .orientation(getOrientation(tank.getDirection()))
                .build();
    }

    private Player getPlayer() {
        if (playerGameObject instanceof Tank) {
            Tank tank = (Tank) playerGameObject;
            return new Player.PlayerBuilder()
                    .source(playerGameObject)
                    .x(tank.getCoordinates().x)
                    .y(tank.getCoordinates().y)
                    .destX(tank.getDestinationCoordinates().x)
                    .destY(tank.getDestinationCoordinates().y)
                    .orientation(getOrientation(tank.getDirection()))
                    .build();
        }
        return null;
    }

    private Orientation getOrientation(Direction direction) {
        switch (direction) {
            case UP:
                return Orientation.N;
            case DOWN:
                return Orientation.S;
            case RIGHT:
                return Orientation.E;
            case LEFT:
                return Orientation.W;
        }
        return null;
    }

}
