package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
@SinceMinecraftVersion("1.17.1")
public class VirtualGlowSquidImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.VirtualGlowSquid {
    private static final EntityDataAccessor<Integer> DATA_DARK_TICKS_REMAINING;

    public VirtualGlowSquidImpl() {
        super(VirtualEntityType.GLOW_SQUID);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_DARK_TICKS_REMAINING, 0);
    }

    @Override
    public void setDarkTicks(int darkTicks) {
        this.entityData.set(DATA_DARK_TICKS_REMAINING, darkTicks);
    }

    @Override
    public int getDarkTicksRemaining() {
        return this.entityData.get(DATA_DARK_TICKS_REMAINING);
    }

    static {
        DATA_DARK_TICKS_REMAINING = Mappings.findAccessor("GlowSquid", "DATA_DARK_TICKS_REMAINING");
    }
}
