package ru.mipt.bit.platformer.game.gameEntities.tankStates;

import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.gameEntities.Tank;
import ru.mipt.bit.platformer.game.gameEntities.TankState;

@RequiredArgsConstructor
public class HeavyTankState implements TankState {

    private final Tank.TankHolder tankHolder;

    @Override
    public void updateState() {
        Tank tank = tankHolder.tank();
        if (!tank.isAlive()) {
            tankHolder.die();
            return;
        }
        if (tank.getMaxHealth() * 0.40 < tank.getCurrentHealth()) {
            TankState state = new MediumTankState(tankHolder);
            tank.setState(state);
            state.updateState();
        }
    }

    @Override
    public void move(float delta) {
        tankHolder.move(delta, tankHolder.tank().getMovementSpeed() / 3);
    }

    @Override
    public void shoot() {
    }
}
