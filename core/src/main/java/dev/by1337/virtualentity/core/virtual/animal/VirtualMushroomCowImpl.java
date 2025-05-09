package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.MushroomType;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;
import org.by1337.blib.util.Version;

public class VirtualMushroomCowImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualMushroom {
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    private static final EntityDataAccessor<String> DATA_TYPE;
    @SinceMinecraftVersion("1.21.5")
    private static final EntityDataAccessor<Integer> DATA_TYPE_INT;


    public VirtualMushroomCowImpl() {
        super(VirtualEntityType.MOOSHROOM);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        if (DATA_TYPE != null) {
            this.entityData.define(DATA_TYPE, MushroomType.RED.type());
        } else {
            this.entityData.define(DATA_TYPE_INT, MushroomType.RED.ordinal());
        }

    }

    public MushroomType getMushroomType() {
        if (DATA_TYPE != null) {
            return MushroomType.byId(this.entityData.get(DATA_TYPE));
        } else {
            return MushroomType.byId(this.entityData.get(DATA_TYPE_INT));
        }
    }

    public void setMushroomType(MushroomType type) {
        if (DATA_TYPE != null){
            this.entityData.set(DATA_TYPE, type.type());
        }else {
            this.entityData.set(DATA_TYPE_INT, type.ordinal());
        }

    }

    static {
        if (Version.is1_21_5orNewer()) {
            DATA_TYPE_INT = Mappings.findAccessor("MushroomCow", "DATA_TYPE");
            DATA_TYPE = null;
        } else {
            DATA_TYPE = Mappings.findAccessor("MushroomCow", "DATA_TYPE");
            DATA_TYPE_INT = null;
        }
    }
}
