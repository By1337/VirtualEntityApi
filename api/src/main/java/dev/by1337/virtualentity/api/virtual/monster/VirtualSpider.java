package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualSpider extends VirtualMob {
    boolean isClimbing();

    void setClimbing(boolean flag);

    static VirtualStrider create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.SPIDER, VirtualStrider.class);
    }
}
