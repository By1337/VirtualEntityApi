package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.monster.cubemob.VirtualAbstractCubeMobImpl;

public class VirtualMagmaCubeImpl extends VirtualAbstractCubeMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualMagmaCube {

    public VirtualMagmaCubeImpl() {
        super(VirtualEntityType.MAGMA_CUBE);
    }
}
