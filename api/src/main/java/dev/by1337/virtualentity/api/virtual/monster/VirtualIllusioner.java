package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualIllusioner extends VirtualSpellcasterIllager {

    static VirtualIllusioner create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ILLUSIONER, VirtualIllusioner.class);
    }
}
