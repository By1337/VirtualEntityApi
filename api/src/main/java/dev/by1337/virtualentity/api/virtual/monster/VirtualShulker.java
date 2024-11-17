package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.annotations.DeprecatedInMinecraftVersion;
import dev.by1337.virtualentity.api.virtual.VirtualMob;
import org.by1337.blib.geom.Vec3i;
import org.by1337.blib.util.Direction;
import org.jetbrains.annotations.Nullable;

public interface VirtualShulker extends VirtualMob {
    Direction getAttachFace();

    void setAttachFace(Direction face);

    @DeprecatedInMinecraftVersion("1.17.1")
    @Nullable Vec3i getAttachPos();

    @DeprecatedInMinecraftVersion("1.17.1")
    void setAttachPos(@Nullable Vec3i pos);

    byte getPeek();

    void setPeek(byte peek);

    byte getColor();

    void setColor(byte color);
}
