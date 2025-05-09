package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;
import org.by1337.blib.geom.Vec3i;

public interface VirtualTurtle extends VirtualAgeableMob {
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    void setHomePos(Vec3i param0);

    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    Vec3i getHomePos();

    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    void setTravelPos(Vec3i param0);

    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    Vec3i getTravelPos();

    boolean hasEgg();

    void setHasEgg(boolean flag);

    boolean isLayingEgg();

    void setLayingEgg(boolean flag);

    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    boolean isGoingHome();

    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    void setGoingHome(boolean flag);

    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    boolean isTravelling();

    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    void setTravelling(boolean flag);

    static VirtualTurtle create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.TURTLE, VirtualTurtle.class);
    }
}
