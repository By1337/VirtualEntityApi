package dev.by1337.virtualentity.api.virtual.decoration;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import org.by1337.blib.util.Direction;

@SinceMinecraftVersion("1.21.6")
public interface VirtualHangingEntityImpl extends VirtualEntity {
    @SinceMinecraftVersion("1.21.6")
    Direction getDirection();

    @SinceMinecraftVersion("1.21.6")
    void setDirection(Direction direction);
}
