package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.PigVariant;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualPig extends VirtualAgeableMob {
    int getBoostTime();

    void setBoostTime(int boostTime);

    @RemovedInMinecraftVersion("1.21.5")
    @Deprecated
    boolean isSaddle();

    @RemovedInMinecraftVersion("1.21.5")
    @Deprecated
    void setSaddle(boolean flag);

    @SinceMinecraftVersion("1.21.5")
    void setVariant(PigVariant variant);
    @SinceMinecraftVersion("1.21.5")
    PigVariant getVariant();

    static VirtualPig create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.PIG, VirtualPig.class);
    }
}
