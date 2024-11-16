package dev.by1337.virtualentity.core.virtual.monster.piglin;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualPiglinBruteImpl extends VirtualAbstractPiglinImpl implements dev.by1337.virtualentity.api.virtual.monster.piglin.VirtualPiglinBrute {

    public VirtualPiglinBruteImpl() {
        super(VirtualEntityType.PIGLIN_BRUTE);
    }
}
