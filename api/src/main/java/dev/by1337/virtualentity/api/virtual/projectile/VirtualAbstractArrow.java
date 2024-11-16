package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;

public interface VirtualAbstractArrow extends VirtualEntity {
    void setCritArrow(boolean flag);

    boolean isCritArrow();

    void setPierceLevel(byte value);

    byte getPierceLevel();

    void setNoPhysics(boolean param0);

    boolean isNoPhysics();

    void setShotFromCrossbow(boolean flag);

    boolean isShotFromCrossbow();
}
