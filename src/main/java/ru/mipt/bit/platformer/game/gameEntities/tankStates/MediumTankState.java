package ru.mipt.bit.platformer.game.gameEntities.tankStates;

import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.gameEntities.Tank;
import ru.mipt.bit.platformer.game.gameEntities.TankState;

@RequiredArgsConstructor
public class MediumTankState implements TankState {

    private final Tank.TankHolder tankHolder;

    @Override
    public void updateState() {
        Tank tank = tankHolder.tank();
        if (tank.getMaxHealth() * 0.75 < tank.getCurrentHealth()) {
            TankState state = new LightTankState(tankHolder);
            tank.setState(state);
            state.updateState();
            return;
        }
        if (tank.getMaxHealth() * 0.40 > tank.getCurrentHealth()) {
            TankState state = new HeavyTankState(tankHolder);
            tank.setState(state);
            state.updateState();
        }
    }

    @Override
    public void move(float delta) {
        tankHolder.move(delta, tankHolder.tank().getMovementSpeed() / 2);
    }

    @Override
    public void shoot() {
        tankHolder.shoot();
    }
}

