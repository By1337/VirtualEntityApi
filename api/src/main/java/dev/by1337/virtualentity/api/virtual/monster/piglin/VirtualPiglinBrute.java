package dev.by1337.virtualentity.api.virtual.monster.piglin;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualPiglinBrute extends VirtualAbstractPiglin {

    static VirtualPiglinBrute create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.PIGLIN_BRUTE, VirtualPiglinBrute.class);
    }
}
