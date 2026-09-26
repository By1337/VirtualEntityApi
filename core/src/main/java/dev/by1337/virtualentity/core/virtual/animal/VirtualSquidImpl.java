package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

public class VirtualSquidImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualSquid {

    public VirtualSquidImpl() {
        super(VirtualEntityType.SQUID);
    }

    @Override
    public boolean isBaby() {
        return super.isBaby();
    }

    @Override
    public void setBaby(boolean flag) {
        super.setBaby(flag);
    }
}
