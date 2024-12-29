package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualCaveSpider extends VirtualSpider {

    static VirtualCaveSpider create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.CAVE_SPIDER, VirtualCaveSpider.class);
    }
}
