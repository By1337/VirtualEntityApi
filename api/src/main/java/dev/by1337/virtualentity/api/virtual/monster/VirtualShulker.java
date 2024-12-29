package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;
import org.by1337.blib.geom.Vec3i;
import org.by1337.blib.util.Direction;
import org.jetbrains.annotations.Nullable;

public interface VirtualShulker extends VirtualMob {
    Direction getAttachFace();

    void setAttachFace(Direction face);

    @RemovedInMinecraftVersion("1.17.1")
    @Nullable Vec3i getAttachPos();

    @RemovedInMinecraftVersion("1.17.1")
    void setAttachPos(@Nullable Vec3i pos);

    byte getPeek();

    void setPeek(byte peek);

    byte getColor();

    void setColor(byte color);

    static VirtualShulker create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.SHULKER, VirtualShulker.class);
    }
}
