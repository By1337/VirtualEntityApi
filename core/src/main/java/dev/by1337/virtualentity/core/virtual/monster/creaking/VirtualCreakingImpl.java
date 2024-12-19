package dev.by1337.virtualentity.core.virtual.monster.creaking;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;
import org.by1337.blib.geom.Vec3i;
import org.by1337.blib.util.Version;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@SinceMinecraftVersion("1.21.3")
public class VirtualCreakingImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.creaking.VirtualCreaking {
    private static final boolean IS_1_21_4_OR_NEWER = Version.VERSION.newerThanOrEqual(Version.V1_21_4);
    private static final EntityDataAccessor<Boolean> CAN_MOVE;
    private static final EntityDataAccessor<Boolean> IS_ACTIVE;
    @SinceMinecraftVersion("1.21.4")
    private static final EntityDataAccessor<Boolean> IS_TEARING_DOWN;
    @SinceMinecraftVersion("1.21.4")
    private static final EntityDataAccessor<Optional<Vec3i>> HOME_POS;

    public VirtualCreakingImpl() {
        super(VirtualEntityType.CREAKING);
    }

    public VirtualCreakingImpl(VirtualEntityType type) {
        super(type);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(CAN_MOVE, true);
        entityData.define(IS_ACTIVE, false);
        if (IS_1_21_4_OR_NEWER) {
            entityData.define(IS_TEARING_DOWN, false);
            entityData.define(HOME_POS, Optional.empty());
        }
    }

    @SinceMinecraftVersion("1.21.4")
    public void setHomePos(@Nullable Vec3i homePos) {
        entityData.set(HOME_POS, Optional.ofNullable(homePos));
    }

    @SinceMinecraftVersion("1.21.4")
    public @Nullable Vec3i getHomePos() {
        return entityData.get(HOME_POS).orElse(null);
    }

    @SinceMinecraftVersion("1.21.4")
    public boolean isTearingDown() {
        return entityData.get(IS_TEARING_DOWN);
    }

    @SinceMinecraftVersion("1.21.4")
    public void setTearingDown(boolean tearingDown) {
        entityData.set(IS_TEARING_DOWN, tearingDown);
    }

    @Override
    public void setIsActive(boolean isActive) {
        entityData.set(IS_ACTIVE, isActive);
    }

    @Override
    public boolean isIsActive() {
        return entityData.get(IS_ACTIVE);
    }

    @Override
    public void setCanMove(boolean canMove) {
        entityData.set(CAN_MOVE, canMove);
    }

    @Override
    public boolean isCanMove() {
        return entityData.get(CAN_MOVE);
    }

    static {
        CAN_MOVE = Mappings.findAccessor("Creaking", "CAN_MOVE");
        IS_ACTIVE = Mappings.findAccessor("Creaking", "IS_ACTIVE");
        if (IS_1_21_4_OR_NEWER) {
            IS_TEARING_DOWN = Mappings.findAccessor("Creaking", "IS_TEARING_DOWN");
            HOME_POS = Mappings.findAccessor("Creaking", "HOME_POS");
        } else {
            IS_TEARING_DOWN = null;
            HOME_POS = null;
        }
    }
}