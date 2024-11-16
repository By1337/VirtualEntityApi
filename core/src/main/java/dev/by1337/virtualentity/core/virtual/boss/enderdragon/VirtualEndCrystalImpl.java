package dev.by1337.virtualentity.core.virtual.boss.enderdragon;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;
import org.by1337.blib.geom.Vec3i;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class VirtualEndCrystalImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.boss.enderdragon.VirtualEndCrystal {
    private static final EntityDataAccessor<Optional<Vec3i>> DATA_BEAM_TARGET;
    private static final EntityDataAccessor<Boolean> DATA_SHOW_BOTTOM;

    public VirtualEndCrystalImpl() {
        super(VirtualEntityType.END_CRYSTAL);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_BEAM_TARGET, Optional.empty());
        entityData.define(DATA_SHOW_BOTTOM, false);
    }

    @Override
    public void setBeamTarget(@Nullable Vec3i vec3i) {
        entityData.set(DATA_BEAM_TARGET, Optional.ofNullable(vec3i));
    }

    @Override
    public @Nullable Vec3i getBeamTarget() {
        return this.entityData.get(DATA_BEAM_TARGET).orElse(null);
    }

    @Override
    public void setShowBottom(boolean showBottom) {
        this.entityData.set(DATA_SHOW_BOTTOM, showBottom);
    }

    @Override
    public boolean isShowBottom() {
        return this.entityData.get(DATA_SHOW_BOTTOM);
    }

    static {
        DATA_BEAM_TARGET = Mappings.findAccessor("EndCrystal", "DATA_BEAM_TARGET");
        DATA_SHOW_BOTTOM = Mappings.findAccessor("EndCrystal", "DATA_SHOW_BOTTOM");
    }
}
