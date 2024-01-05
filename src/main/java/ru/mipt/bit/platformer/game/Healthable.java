package ru.mipt.bit.platformer.game;

public interface Healthable {

    void applyDamage(float damage);

    boolean isAlive();

    float getMaxHealth();

    float getCurrentHealth();

}
