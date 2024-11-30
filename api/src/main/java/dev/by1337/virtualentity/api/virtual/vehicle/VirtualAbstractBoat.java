package dev.by1337.virtualentity.api.virtual.vehicle;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.BoatType;

public interface VirtualAbstractBoat extends dev.by1337.virtualentity.api.virtual.vehicle.VirtualVehicleEntity {
    @RemovedInMinecraftVersion("1.20.3")
    BoatType getBoatType();

    @RemovedInMinecraftVersion("1.20.3")
    void setBoatType(BoatType boatType);

    boolean isPaddleLeftActive();

    void setPaddleLeftActive(boolean active);

    boolean isPaddleRightActive();

    void setPaddleRightActive(boolean active);

    int getBubbleTime();

    void setBubbleTime(int bubbleTime);
}
