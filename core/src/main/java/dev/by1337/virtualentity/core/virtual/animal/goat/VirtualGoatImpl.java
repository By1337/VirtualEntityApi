package dev.by1337.virtualentity.core.virtual.animal.goat;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

@SinceMinecraftVersion("1.17.1")
public class VirtualGoatImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.goat.VirtualGoat {
    private static final EntityDataAccessor<Boolean> DATA_IS_SCREAMING_GOAT;

    public VirtualGoatImpl() {
        super(VirtualEntityType.GOAT);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IS_SCREAMING_GOAT, false);
    }

    @Override
    public boolean isScreamingGoat() {
        return this.entityData.get(DATA_IS_SCREAMING_GOAT);
    }

    @Override
    public void setScreamingGoat(boolean flag) {
        this.entityData.set(DATA_IS_SCREAMING_GOAT, flag);
    }

    static {
        DATA_IS_SCREAMING_GOAT = Mappings.findAccessor("Goat", "DATA_IS_SCREAMING_GOAT");
    }
}
