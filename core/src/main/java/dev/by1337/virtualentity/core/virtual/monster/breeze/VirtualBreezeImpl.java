package dev.by1337.virtualentity.core.virtual.monster.breeze;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

@SinceMinecraftVersion("1.20.4")
public class VirtualBreezeImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.breeze.VirtualBreeze {

    public VirtualBreezeImpl() {
        super(VirtualEntityType.BREEZE);
    }
}