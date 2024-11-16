package dev.by1337.virtualentity.api.virtual.item;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import org.bukkit.block.data.BlockData;
import org.by1337.blib.geom.Vec3i;

public interface VirtualFallingBlockEntity extends VirtualEntity {
    void setStartPos(Vec3i vec3i);

    Vec3i getStartPos();

    void setBlockType(BlockData block);
}
