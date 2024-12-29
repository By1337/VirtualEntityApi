package dev.by1337.virtualentity.api.virtual.monster.creaking;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;
import dev.by1337.virtualentity.api.virtual.monster.hoglin.VirtualHoglin;
import org.by1337.blib.geom.Vec3i;
import org.jetbrains.annotations.Nullable;

@SinceMinecraftVersion("1.21.3")
public interface VirtualCreaking extends VirtualMob {
    void setIsActive(boolean isActive);

    boolean isIsActive();

    void setCanMove(boolean canMove);

    boolean isCanMove();

    @SinceMinecraftVersion("1.21.4")
    void setHomePos(@Nullable Vec3i homePos);

    @SinceMinecraftVersion("1.21.4")
    @Nullable Vec3i getHomePos();

    @SinceMinecraftVersion("1.21.4")
    boolean isTearingDown();

    @SinceMinecraftVersion("1.21.4")
    void setTearingDown(boolean tearingDown);

    static VirtualCreaking create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.CREAKING, VirtualCreaking.class);
    }
}
