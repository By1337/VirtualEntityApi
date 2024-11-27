package dev.by1337.virtualentity.api.virtual.vehicle;

import dev.by1337.virtualentity.api.entity.BoatType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;

public interface VirtualBoat extends VirtualVehicleEntity {

    BoatType getBoatType();

    void setBoatType(BoatType boatType);

    boolean isPaddleLeftActive();

    void setPaddleLeftActive(boolean active);

    boolean isPaddleRightActive();

    void setPaddleRightActive(boolean active);

    int getBubbleTime();

    void setBubbleTime(int bubbleTime);
}
