package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.by1337.blib.util.Version;

@SinceMinecraftVersion("1.17.1")
public class VirtualGlowSquidImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.VirtualGlowSquid {
    private static final boolean IS_1_21_3_OR_NEWER = Version.VERSION.newerThanOrEqual(Version.V1_21_3);
    private static final EntityDataAccessor<Integer> DATA_DARK_TICKS_REMAINING;

    public VirtualGlowSquidImpl() {
        super(VirtualEntityType.GLOW_SQUID, IS_1_21_3_OR_NEWER);
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

    @Override
    @SinceMinecraftVersion("1.21.3")
    public boolean isBaby() {
        return super.isBaby();
    }

    @Override
    @SinceMinecraftVersion("1.21.3")
    public void setBaby(boolean flag) {
        super.setBaby(flag);
    }

    static {
        DATA_DARK_TICKS_REMAINING = Mappings.findAccessor("GlowSquid", "DATA_DARK_TICKS_REMAINING");
    }
}
