package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.SalmonVariant;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.by1337.blib.util.Version;

public class VirtualSalmonImpl extends VirtualAbstractFishImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualSalmon {
    private static final boolean IS_1_21_3_OR_NEWER = Version.VERSION.newerThanOrEqual(Version.V1_21_3);
    private static final boolean IS_1_21_4_OR_NEWER = Version.VERSION.newerThanOrEqual(Version.V1_21_4);
    @SinceMinecraftVersion("1.21.3")
    @Deprecated
    private static final EntityDataAccessor<String> DATA_TYPE;
    @SinceMinecraftVersion("1.21.4")
    private static final EntityDataAccessor<Integer> DATA_TYPE_INT;

    public VirtualSalmonImpl() {
        super(VirtualEntityType.SALMON);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        if (IS_1_21_4_OR_NEWER) {
            entityData.define(DATA_TYPE_INT, SalmonVariant.MEDIUM.ordinal());
        } else if (IS_1_21_3_OR_NEWER) {
            entityData.define(DATA_TYPE, SalmonVariant.MEDIUM.getId());
        }
    }

    @SinceMinecraftVersion("1.21.3")
    public void setType(final SalmonVariant type) {
        if (IS_1_21_4_OR_NEWER) {
            entityData.define(DATA_TYPE_INT, type.ordinal());
        } else {
            entityData.set(DATA_TYPE, type.getId());
        }
    }

    static {
        if (IS_1_21_4_OR_NEWER) {
            DATA_TYPE_INT = Mappings.findAccessor("Salmon", "DATA_TYPE");
            DATA_TYPE = null;
        } else if (IS_1_21_3_OR_NEWER) {
            DATA_TYPE = Mappings.findAccessor("Salmon", "DATA_TYPE");
            DATA_TYPE_INT = null;
        } else {
            DATA_TYPE = null;
            DATA_TYPE_INT = null;
        }
    }
}
