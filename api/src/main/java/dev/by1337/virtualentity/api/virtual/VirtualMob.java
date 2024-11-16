package dev.by1337.virtualentity.api.virtual;

public interface VirtualMob extends VirtualLivingEntity {
    void setNoAi(boolean flag);

    void setLeftHanded(boolean flag);

    void setAggressive(boolean flag);

    boolean isNoAi();

    boolean isLeftHanded();

    boolean isAggressive();
}
