package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;
import org.by1337.blib.util.Version;

public class VirtualSkeletonImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualSkeleton {

    @SinceMinecraftVersion("1.17.1")
    private static final EntityDataAccessor<Boolean> DATA_STRAY_CONVERSION_ID;

    public VirtualSkeletonImpl() {
        super(VirtualEntityType.SKELETON);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        if (Version.VERSION.newerThanOrEqual(Version.V1_17_1))
            entityData.define(DATA_STRAY_CONVERSION_ID, false);
    }

    @Override
    @SinceMinecraftVersion("1.17.1")
    public boolean isStrayConversion() {
        return entityData.get(DATA_STRAY_CONVERSION_ID);
    }

    @Override
    @SinceMinecraftVersion("1.17.1")
    public void setStrayConversion(boolean flag) {
        entityData.set(DATA_STRAY_CONVERSION_ID, flag);
    }

    static {
        if (Version.VERSION.newerThanOrEqual(Version.V1_17_1)) {
            DATA_STRAY_CONVERSION_ID = Mappings.findAccessor("Skeleton", "DATA_STRAY_CONVERSION_ID");
        } else {
            DATA_STRAY_CONVERSION_ID = null;
        }
    }
}
