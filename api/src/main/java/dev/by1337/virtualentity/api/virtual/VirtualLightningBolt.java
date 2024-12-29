package dev.by1337.virtualentity.api.virtual;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualLightningBolt extends VirtualEntity {

    static VirtualLightningBolt create(){
        return VirtualEntityApi.getFactory().create(VirtualEntityType.LIGHTNING_BOLT, VirtualLightningBolt.class);
    }
}
