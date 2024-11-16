package dev.by1337.virtualentity.core.virtual.npc;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualWanderingTraderImpl extends VirtualAbstractVillagerImpl implements VirtualWanderingTrader {

    public VirtualWanderingTraderImpl() {
        super(VirtualEntityType.WANDERING_TRADER);
    }
}
