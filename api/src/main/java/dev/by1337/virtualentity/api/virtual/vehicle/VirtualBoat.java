package dev.by1337.virtualentity.api.virtual.vehicle;

import dev.by1337.virtualentity.api.entity.BoatType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;

public interface VirtualBoat extends VirtualEntity {
    int getHurt();

    void setHurt(int hurt);

    int getHurtDirection();

    void setHurtDirection(int hurtDir);

    float getDamage();

    void setDamage(float damage);

    BoatType getBoatType();

    void setBoatType(BoatType boatType);

    boolean isPaddleLeftActive();

    void setPaddleLeftActive(boolean active);

    boolean isPaddleRightActive();

    void setPaddleRightActive(boolean active);

    int getBubbleTime();

    void setBubbleTime(int bubbleTime);
}
