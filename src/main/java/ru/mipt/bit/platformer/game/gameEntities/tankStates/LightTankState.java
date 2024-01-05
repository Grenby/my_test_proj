package ru.mipt.bit.platformer.game.gameEntities.tankStates;

import lombok.RequiredArgsConstructor;
import ru.mipt.bit.platformer.game.gameEntities.Tank;
import ru.mipt.bit.platformer.game.gameEntities.TankState;

@RequiredArgsConstructor
public class LightTankState implements TankState {

    private final Tank.TankHolder tankHolder;

    @Override
    public void updateState() {
        Tank tank = tankHolder.tank();
        if (tank.getMaxHealth() * 0.75 > tank.getCurrentHealth()) {
            TankState state = new MediumTankState(tankHolder);
            tank.setState(state);
            state.updateState();
        }
    }

    @Override
    public void move(float delta) {
        Tank tank = tankHolder.tank();
        tankHolder.move(delta, tank.getMovementSpeed());
    }

    @Override
    public void shoot() {
        tankHolder.shoot();
    }
}
