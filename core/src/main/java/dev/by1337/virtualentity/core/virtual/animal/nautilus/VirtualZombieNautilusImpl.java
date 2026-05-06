package dev.by1337.virtualentity.core.virtual.animal.nautilus;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.entity.ZombieNautilusVariant;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

@SinceMinecraftVersion("1.21.11")
public class VirtualZombieNautilusImpl extends VirtualAbstractNautilusImpl implements dev.by1337.virtualentity.api.virtual.animal.nautilus.VirtualZombieNautilus {
    private static final EntityDataAccessor<ZombieNautilusVariant> DATA_VARIANT_ID;

    public VirtualZombieNautilusImpl() {
        super(VirtualEntityType.ZOMBIE_NAUTILUS);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, ZombieNautilusVariant.TEMPERATE);
    }

    @Override
    public ZombieNautilusVariant getVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    @Override
    public void setVariant(ZombieNautilusVariant variant) {
        this.entityData.set(DATA_VARIANT_ID, variant);
    }

    static {
        DATA_VARIANT_ID = Mappings.findAccessor("ZombieNautilus", "DATA_VARIANT_ID");
    }
}