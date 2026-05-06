package dev.by1337.virtualentity.core.virtual;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;
import dev.by1337.virtualentity.core.annotations.ASM;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.by1337.blib.util.Version;

public abstract class VirtualAgeableMobImpl extends VirtualMobImpl implements VirtualAgeableMob {

    private static final EntityDataAccessor<Boolean> DATA_BABY_ID;
    @SinceMinecraftVersion("26.1")
    private static final EntityDataAccessor<Boolean> AGE_LOCKED;
    private final boolean doDefineDataFlag;

    public VirtualAgeableMobImpl(VirtualEntityType type) {
        this(type, true);
    }

    @ASM
    public VirtualAgeableMobImpl(VirtualEntityType type, boolean doDefineDataFlag) {
        super(type);
        this.doDefineDataFlag = doDefineDataFlag;
        // invoke super after setting doDefineDataFlag
        String asm = """
                A:
                    aload 0
                    iload 2
                    putfield dev/by1337/virtualentity/core/virtual/VirtualAgeableMobImpl doDefineDataFlag Z
                B:
                    aload 0
                    aload 1
                    invokespecial dev/by1337/virtualentity/core/virtual/VirtualMobImpl <init> (Ldev/by1337/virtualentity/api/entity/VirtualEntityType;)V
                C:
                    return
                D:
                """;
        throw new IllegalStateException("ASM did not apply! " + asm);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        if (doDefineDataFlag) {
            this.entityData.define(DATA_BABY_ID, false);
        }
        if (ServerVersion.is26_1orNewer()){
            this.entityData.define(AGE_LOCKED, false);
        }
    }
    @SinceMinecraftVersion("26.1")
    public boolean isAgeLocked() {
        return entityData.get(AGE_LOCKED);
    }
    @SinceMinecraftVersion("26.1")
    public void setAgeLocked(boolean value) {
        entityData.set(AGE_LOCKED, value);
    }

    @Override
    public boolean isBaby() {
        return !this.entityData.get(DATA_BABY_ID);
    }

    @Override
    public void setBaby(boolean flag) {
        this.entityData.set(DATA_BABY_ID, flag);
    }

    static {
        if (Version.VERSION.olderThan(Version.V1_17_1))
            DATA_BABY_ID = Mappings.findAccessor("AgableMob", "DATA_BABY_ID");
        else
            DATA_BABY_ID = Mappings.findAccessor("AgeableMob", "DATA_BABY_ID");

        if (ServerVersion.is26_1orNewer()){
            AGE_LOCKED = Mappings.findAccessor("AgeableMob", "AGE_LOCKED");
        }else{
            AGE_LOCKED = null;
        }
    }
}
