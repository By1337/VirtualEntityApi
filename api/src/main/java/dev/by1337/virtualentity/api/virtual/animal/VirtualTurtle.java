package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;
import org.by1337.blib.geom.Vec3i;

public interface VirtualTurtle extends VirtualAgableMob {
    void setHomePos(Vec3i param0);

    Vec3i getHomePos();

    void setTravelPos(Vec3i param0);

    Vec3i getTravelPos();

    boolean hasEgg();

    void setHasEgg(boolean flag);

    boolean isLayingEgg();

    void setLayingEgg(boolean flag);

    boolean isGoingHome();

    void setGoingHome(boolean flag);

    boolean isTravelling();

    void setTravelling(boolean flag);
}
