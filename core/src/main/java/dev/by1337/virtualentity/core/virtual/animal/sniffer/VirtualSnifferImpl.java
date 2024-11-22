package dev.by1337.virtualentity.core.virtual.animal.sniffer;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.SnifferState;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

@SinceMinecraftVersion("1.19.4")
public class VirtualSnifferImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.sniffer.VirtualSniffer {
    private static final EntityDataAccessor<SnifferState> DATA_STATE;
    private static final EntityDataAccessor<Integer> DATA_DROP_SEED_AT_TICK;

    public VirtualSnifferImpl() {
        super(VirtualEntityType.SNIFFER);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_STATE, SnifferState.IDLING);
        this.entityData.define(DATA_DROP_SEED_AT_TICK, 0);
    }

    @Override
    public void setState(SnifferState state) {
        this.entityData.set(DATA_STATE, state);
    }

    @Override
    public SnifferState getState() {
        return this.entityData.get(DATA_STATE);
    }

    @Override
    public void setDropSeedAtTick(int tick) {
        this.entityData.set(DATA_DROP_SEED_AT_TICK, tick);
    }

    @Override
    public int getDropSeedAtTick() {
        return this.entityData.get(DATA_DROP_SEED_AT_TICK);
    }

    static {
        DATA_STATE = Mappings.findAccessor("Sniffer", "DATA_STATE");
        DATA_DROP_SEED_AT_TICK = Mappings.findAccessor("Sniffer", "DATA_DROP_SEED_AT_TICK");
    }
}