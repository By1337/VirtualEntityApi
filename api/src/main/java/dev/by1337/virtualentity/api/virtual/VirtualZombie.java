package dev.by1337.virtualentity.api.virtual;

public interface VirtualZombie extends VirtualMob{
    boolean isDrownConverting();
    boolean isBaby();
    void setBaby(boolean flag);
    void stopDrowning();
    void startDrownedConversion();
}
