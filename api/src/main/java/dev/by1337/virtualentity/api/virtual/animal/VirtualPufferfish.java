package dev.by1337.virtualentity.api.virtual.animal;

public interface VirtualPufferfish extends VirtualAbstractFish {
    int getPuffState();

    // 0 1 2
    void setPuffState(int state);
}
