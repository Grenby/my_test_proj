package ru.mipt.bit.platformer.game.gameWorld.map;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.Collidable;
import ru.mipt.bit.platformer.game.gameWorld.LevelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LevelMapImpl implements LevelMap {

    private final List<Collidable> collidableList;
    private final GridPoint2 leftDownBounder;
    private final GridPoint2 rightUpBounder;


    public LevelMapImpl() {
        collidableList = new ArrayList<>();
        leftDownBounder = null;
        rightUpBounder = null;
    }

    /**
     * @param leftDownBounder - координаты левого нижнего угла карты
     * @param rightUpBounder  - координаты правого верхнего
     */
    public LevelMapImpl(GridPoint2 leftDownBounder, GridPoint2 rightUpBounder) {
        this.collidableList = new ArrayList<>();
        this.leftDownBounder = leftDownBounder;
        this.rightUpBounder = rightUpBounder;
    }

    public void addColliableObject(Collidable collidable) {
        collidableList.add(collidable);
    }

    public void removeColliableObject(Collidable collidable) {
        collidableList.remove(collidable);
    }

    @Override
    public boolean isMapPointFree(GridPoint2 point) {
//        if (!checkPointInBounders(point)) {
//            return false;
//        }
        for (Collidable collidable : collidableList) {
            if (collidable.containsPoint(point)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkPointInBounders(GridPoint2 pos) {
        if (Objects.isNull(leftDownBounder) || Objects.isNull(rightUpBounder)) {
            return true;
        }
        GridPoint2 l = pos.cpy().sub(leftDownBounder);
        GridPoint2 r = rightUpBounder.cpy().sub(pos);
        return l.x >= 0 && l.y >= 0 && r.x >= 0 && r.y >= 0;
    }

}
