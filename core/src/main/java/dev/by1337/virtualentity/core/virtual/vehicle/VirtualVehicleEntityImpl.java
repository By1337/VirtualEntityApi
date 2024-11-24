package dev.by1337.virtualentity.core.virtual.vehicle;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.vehicle.VirtualBoat;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;
import org.by1337.blib.util.Version;

public abstract class VirtualVehicleEntityImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.vehicle.VirtualVehicleEntity {
    private static final AccessorSet VEHICLE;
    private static final AccessorSet BOAT;
    private static final AccessorSet MINECART;

    private AccessorSet accessorSet;

    public VirtualVehicleEntityImpl(VirtualEntityType type) {
        super(type);
    }

    private AccessorSet getAccessorSet() {
        if (Version.VERSION.newerThanOrEqual(Version.V1_20_4)) {
            return VEHICLE;
        } else {
            if (this instanceof VirtualBoat) {
                return BOAT;
            } else {
                return MINECART;
            }
        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        accessorSet = getAccessorSet();
        this.entityData.define(accessorSet.idHurt(), 0);
        this.entityData.define(accessorSet.idHurtDir(), 1);
        this.entityData.define(accessorSet.idDamage(), 0.0F);
    }

    @Override
    public int getHurt() {
        return this.entityData.get(accessorSet.idHurt());
    }

    @Override
    public void setHurt(int hurt) {
        this.entityData.set(accessorSet.idHurt(), hurt);
    }

    @Override
    public int getHurtDirection() {
        return this.entityData.get(accessorSet.idHurtDir());
    }

    @Override
    public void setHurtDirection(int hurtDir) {
        this.entityData.set(accessorSet.idHurtDir(), hurtDir);
    }

    @Override
    public float getDamage() {
        return this.entityData.get(accessorSet.idDamage());
    }

    @Override
    public void setDamage(float damage) {
        this.entityData.set(accessorSet.idDamage(), damage);
    }


    static {
        if (Version.VERSION.newerThanOrEqual(Version.V1_20_4)) {
            VEHICLE = new VehicleEntityAccessors();
            BOAT = null;
            MINECART = null;
        } else {
            VEHICLE = null;
            BOAT = new BoatAccessors();
            MINECART = new MinecartAccessors();
        }
    }

    private interface AccessorSet {
        EntityDataAccessor<Integer> idHurt();

        EntityDataAccessor<Integer> idHurtDir();

        EntityDataAccessor<Float> idDamage();
    }

    @SinceMinecraftVersion("1.20.4")
    private static class VehicleEntityAccessors implements AccessorSet {
        private static final EntityDataAccessor<Integer> DATA_ID_HURT;
        private static final EntityDataAccessor<Integer> DATA_ID_HURTDIR;
        private static final EntityDataAccessor<Float> DATA_ID_DAMAGE;

        @Override
        public EntityDataAccessor<Integer> idHurt() {
            return DATA_ID_HURT;
        }

        @Override
        public EntityDataAccessor<Integer> idHurtDir() {
            return DATA_ID_HURTDIR;
        }

        @Override
        public EntityDataAccessor<Float> idDamage() {
            return DATA_ID_DAMAGE;
        }

        static {
            if (Version.VERSION.newerThanOrEqual(Version.V1_20_4)) {
                DATA_ID_HURT = Mappings.findAccessor("VehicleEntity", "DATA_ID_HURT");
                DATA_ID_HURTDIR = Mappings.findAccessor("VehicleEntity", "DATA_ID_HURTDIR");
                DATA_ID_DAMAGE = Mappings.findAccessor("VehicleEntity", "DATA_ID_DAMAGE");
            } else {
                DATA_ID_HURT = null;
                DATA_ID_HURTDIR = null;
                DATA_ID_DAMAGE = null;
            }
        }
    }

    private static class MinecartAccessors implements AccessorSet {
        private static final EntityDataAccessor<Integer> DATA_ID_HURT;
        private static final EntityDataAccessor<Integer> DATA_ID_HURTDIR;
        private static final EntityDataAccessor<Float> DATA_ID_DAMAGE;

        @Override
        public EntityDataAccessor<Integer> idHurt() {
            return DATA_ID_HURT;
        }

        @Override
        public EntityDataAccessor<Integer> idHurtDir() {
            return DATA_ID_HURTDIR;
        }

        @Override
        public EntityDataAccessor<Float> idDamage() {
            return DATA_ID_DAMAGE;
        }

        static {
            if (Version.VERSION.olderThan(Version.V1_20_4)) {
                DATA_ID_HURT = Mappings.findAccessor("AbstractMinecart", "DATA_ID_HURT");
                DATA_ID_HURTDIR = Mappings.findAccessor("AbstractMinecart", "DATA_ID_HURTDIR");
                DATA_ID_DAMAGE = Mappings.findAccessor("AbstractMinecart", "DATA_ID_DAMAGE");
            } else {
                DATA_ID_HURT = null;
                DATA_ID_HURTDIR = null;
                DATA_ID_DAMAGE = null;
            }
        }
    }

    private static class BoatAccessors implements AccessorSet {
        private static final EntityDataAccessor<Integer> DATA_ID_HURT;
        private static final EntityDataAccessor<Integer> DATA_ID_HURTDIR;
        private static final EntityDataAccessor<Float> DATA_ID_DAMAGE;

        @Override
        public EntityDataAccessor<Integer> idHurt() {
            return DATA_ID_HURT;
        }

        @Override
        public EntityDataAccessor<Integer> idHurtDir() {
            return DATA_ID_HURTDIR;
        }

        @Override
        public EntityDataAccessor<Float> idDamage() {
            return DATA_ID_DAMAGE;
        }

        static {
            if (Version.VERSION.olderThan(Version.V1_20_4)) {
                DATA_ID_HURT = Mappings.findAccessor("Boat", "DATA_ID_HURT");
                DATA_ID_HURTDIR = Mappings.findAccessor("Boat", "DATA_ID_HURTDIR");
                DATA_ID_DAMAGE = Mappings.findAccessor("Boat", "DATA_ID_DAMAGE");
            } else {
                DATA_ID_HURT = null;
                DATA_ID_HURTDIR = null;
                DATA_ID_DAMAGE = null;
            }
        }
    }

}
