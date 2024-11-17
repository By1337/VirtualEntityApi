package dev.by1337.virtualentity.core.virtual.animal.axolotl;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.AxolotVariant;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

@SinceMinecraftVersion("1.17.1")
public class VirtualAxolotlImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.axolotl.VirtualAxolotl {
    private static final EntityDataAccessor<Integer> DATA_VARIANT;
    private static final EntityDataAccessor<Boolean> DATA_PLAYING_DEAD;
    private static final EntityDataAccessor<Boolean> FROM_BUCKET;

    public VirtualAxolotlImpl() {
        super(VirtualEntityType.AXOLOTL);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT, 0);
        this.entityData.define(DATA_PLAYING_DEAD, false);
        this.entityData.define(FROM_BUCKET, false);
    }

    @Override
    public void setVariant(AxolotVariant variant) {
        entityData.set(DATA_VARIANT, variant.getId());
    }

    @Override
    public void setPlayingDead(boolean flag) {
        entityData.set(DATA_PLAYING_DEAD, flag);
    }

    @Override
    public boolean isPlayingDead() {
        return entityData.get(DATA_PLAYING_DEAD);
    }

    @Override
    public void setFromBucket(boolean flag) {
        entityData.set(FROM_BUCKET, flag);
    }

    @Override
    public boolean isFromBucket() {
        return entityData.get(FROM_BUCKET);
    }

    static {
        DATA_VARIANT = Mappings.findAccessor("Axolotl", "DATA_VARIANT");
        DATA_PLAYING_DEAD = Mappings.findAccessor("Axolotl", "DATA_PLAYING_DEAD");
        FROM_BUCKET = Mappings.findAccessor("Axolotl", "FROM_BUCKET");
    }
}
