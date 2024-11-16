package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.virtual.VirtualMob;
import org.by1337.blib.geom.Vec3i;

public interface VirtualDolphin extends VirtualMob {
    void setTreasurePos(Vec3i pos);

    Vec3i getTreasurePos();

    boolean gotFish();

    void setGotFish(boolean flag);

    int getMoistnessLevel();

    void setMoisntessLevel(int level);
}
