package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualLightningBoltImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.VirtualLightningBolt {

    public VirtualLightningBoltImpl() {
        super(VirtualEntityType.LIGHTNING_BOLT);
    }
}
