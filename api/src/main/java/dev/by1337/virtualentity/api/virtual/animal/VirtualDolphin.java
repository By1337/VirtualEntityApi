package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;
import org.by1337.blib.geom.Vec3i;

public interface VirtualDolphin extends VirtualAgeableMob {
    void setTreasurePos(Vec3i pos);

    Vec3i getTreasurePos();

    boolean gotFish();

    void setGotFish(boolean flag);

    int getMoistnessLevel();

    void setMoisntessLevel(int level);

    @Override
    @SinceMinecraftVersion("1.21.3")
    boolean isBaby();

    @Override
    @SinceMinecraftVersion("1.21.3")
    void setBaby(boolean flag);

    static VirtualDolphin create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.DOLPHIN, VirtualDolphin.class);
    }
}
