package ru.mipt.bit.platformer.gameGenerator.generatolImpl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.GridPoint2;
import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.gameEntities.Tank;
import ru.mipt.bit.platformer.game.gameEntities.Tree;
import ru.mipt.bit.platformer.game.gameWorld.GameEngine;
import ru.mipt.bit.platformer.gameGenerator.GameGenerator;
import ru.mipt.bit.platformer.gameGenerator.GeneratedLevelInfo;
import ru.mipt.bit.platformer.gameGenerator.WorldConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@RequiredArgsConstructor
public class FromFileGenerator implements GameGenerator {

    private final String fileName;
    private final WorldConfig config;

    private GridPoint2 playerPosition;
    private List<GridPoint2> treesPositions;

    @Override
    public GeneratedLevelInfo generate() {
        GameEngine gameEngine = config.gameEngine();

        readCoordinatesFromFile();

        Tank playerTank = Tank.init(gameEngine, config.levelMap(), playerPosition)
                .build();

        config.gameEngine().addGameObject(playerTank);

        for (GridPoint2 position : treesPositions) {
            Tree tree = new Tree(position);
            gameEngine.addGameObject(tree);
        }

        return new GeneratedLevelInfo(gameEngine, playerTank);
    }

    private void readCoordinatesFromFile() {
        FileHandle fh = Gdx.files.internal(this.fileName);

        Scanner scanner = new Scanner(fh.read());
        List<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        int y = lines.size() - 1;

        treesPositions = new ArrayList<>();

        for (String s : lines) {
            for (int x = 0; x < s.length(); x++) {
                if (s.charAt(x) == 'T') {
                    treesPositions.add(new GridPoint2(x, y));
                } else if (s.charAt(x) == 'X') {
                    playerPosition = new GridPoint2();
                    playerPosition.set(x, y);
                }
            }
            y--;
        }
        scanner.close();
    }

}
