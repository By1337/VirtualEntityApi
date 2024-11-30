package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;
import org.by1337.blib.util.Version;

public class VirtualSquidImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualSquid {
    private static final boolean IS_1_21_3_OR_NEWER = Version.VERSION.newerThanOrEqual(Version.V1_21_3);

    public VirtualSquidImpl() {
        super(VirtualEntityType.SQUID, IS_1_21_3_OR_NEWER);
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
}
