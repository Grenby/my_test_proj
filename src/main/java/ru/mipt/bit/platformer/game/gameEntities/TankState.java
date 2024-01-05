package ru.mipt.bit.platformer.game.gameEntities;

public interface TankState {

    void updateState();

    void move(float delta);

    void shoot();

}
