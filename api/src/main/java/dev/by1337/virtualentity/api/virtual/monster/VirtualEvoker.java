package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualEvoker extends VirtualSpellcasterIllager {

    static VirtualEvoker create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.EVOKER, VirtualEvoker.class);
    }
}
