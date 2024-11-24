package dev.by1337.virtualentity.api.virtual.vehicle;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;

public interface VirtualVehicleEntity extends VirtualEntity {
    int getHurt();

    void setHurt(int hurt);

    int getHurtDirection();

    void setHurtDirection(int hurtDir);

    float getDamage();

    void setDamage(float damage);
}
