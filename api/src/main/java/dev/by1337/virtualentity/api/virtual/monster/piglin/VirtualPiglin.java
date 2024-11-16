package dev.by1337.virtualentity.api.virtual.monster.piglin;

public interface VirtualPiglin extends VirtualAbstractPiglin {
    boolean isBaby();

    void setBaby(boolean baby);

    boolean isChargingCrossbow();

    void setChargingCrossbow(boolean charging);

    boolean isDancing();

    void setDancing(boolean dancing);
}
