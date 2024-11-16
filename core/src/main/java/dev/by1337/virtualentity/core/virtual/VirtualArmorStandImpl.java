package dev.by1337.virtualentity.core.virtual;


import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualArmorStand;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.by1337.blib.geom.Vec3f;

public class VirtualArmorStandImpl extends VirtualLivingEntityImpl implements VirtualArmorStand {
    public static final EntityDataAccessor<Byte> DATA_CLIENT_FLAGS;
    public static final EntityDataAccessor<Vec3f> DATA_HEAD_POSE;
    public static final EntityDataAccessor<Vec3f> DATA_BODY_POSE;
    public static final EntityDataAccessor<Vec3f> DATA_LEFT_ARM_POSE;
    public static final EntityDataAccessor<Vec3f> DATA_RIGHT_ARM_POSE;
    public static final EntityDataAccessor<Vec3f> DATA_LEFT_LEG_POSE;
    public static final EntityDataAccessor<Vec3f> DATA_RIGHT_LEG_POSE;

    private static final Vec3f DEFAULT_HEAD_POSE = new Vec3f(0.0F, 0.0F, 0.0F);
    private static final Vec3f DEFAULT_BODY_POSE = new Vec3f(0.0F, 0.0F, 0.0F);
    private static final Vec3f DEFAULT_LEFT_ARM_POSE = new Vec3f(-10.0F, 0.0F, -10.0F);
    private static final Vec3f DEFAULT_RIGHT_ARM_POSE = new Vec3f(-15.0F, 0.0F, 10.0F);
    private static final Vec3f DEFAULT_LEFT_LEG_POSE = new Vec3f(-1.0F, 0.0F, -1.0F);
    private static final Vec3f DEFAULT_RIGHT_LEG_POSE = new Vec3f(1.0F, 0.0F, 1.0F);

    public VirtualArmorStandImpl() {
        super(VirtualEntityType.ARMOR_STAND);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_CLIENT_FLAGS, (byte) 0);
        this.entityData.define(DATA_HEAD_POSE, DEFAULT_HEAD_POSE);
        this.entityData.define(DATA_BODY_POSE, DEFAULT_BODY_POSE);
        this.entityData.define(DATA_LEFT_ARM_POSE, DEFAULT_LEFT_ARM_POSE);
        this.entityData.define(DATA_RIGHT_ARM_POSE, DEFAULT_RIGHT_ARM_POSE);
        this.entityData.define(DATA_LEFT_LEG_POSE, DEFAULT_LEFT_LEG_POSE);
        this.entityData.define(DATA_RIGHT_LEG_POSE, DEFAULT_RIGHT_LEG_POSE);
    }

    @Override
    public void setSmall(boolean flag) {
        this.entityData.set(DATA_CLIENT_FLAGS, this.setBit(this.entityData.get(DATA_CLIENT_FLAGS), 0x1, flag));
    }

    @Override
    public boolean isSmall() {
        return (this.entityData.get(DATA_CLIENT_FLAGS) & 0x1) != 0;
    }

    @Override
    public void setShowArms(boolean flag) {
        this.entityData.set(DATA_CLIENT_FLAGS, this.setBit(this.entityData.get(DATA_CLIENT_FLAGS), 0x4, flag));
    }

    @Override
    public boolean isShowArms() {
        return (this.entityData.get(DATA_CLIENT_FLAGS) & 0x4) != 0;
    }

    @Override
    public void setNoBasePlate(boolean flag) {
        this.entityData.set(DATA_CLIENT_FLAGS, this.setBit(this.entityData.get(DATA_CLIENT_FLAGS), 0x8, flag));
    }

    @Override
    public boolean isNoBasePlate() {
        return (this.entityData.get(DATA_CLIENT_FLAGS) & 0x8) != 0;
    }

    @Override
    public void setMarker(boolean flag) {
        this.entityData.set(DATA_CLIENT_FLAGS, this.setBit(this.entityData.get(DATA_CLIENT_FLAGS), 0x10, flag));
    }

    @Override
    public boolean isMarker() {
        return (this.entityData.get(DATA_CLIENT_FLAGS) & 0x10) != 0;
    }

    private byte setBit(byte source, int shift, boolean flag) {
        if (flag) {
            source = (byte) (source | shift);
        } else {
            source = (byte) (source & ~shift);
        }

        return source;
    }

    @Override
    public void setHeadPose(Vec3f pos) {
        this.entityData.set(DATA_HEAD_POSE, pos);
    }

    @Override
    public void setBodyPose(Vec3f pos) {
        this.entityData.set(DATA_BODY_POSE, pos);
    }

    @Override
    public void setLeftArmPose(Vec3f pos) {
        this.entityData.set(DATA_LEFT_ARM_POSE, pos);
    }

    @Override
    public void setRightArmPose(Vec3f pos) {
        this.entityData.set(DATA_RIGHT_ARM_POSE, pos);
    }

    @Override
    public void setLeftLegPose(Vec3f pos) {
        this.entityData.set(DATA_LEFT_LEG_POSE, pos);
    }

    @Override
    public void setRightLegPose(Vec3f pos) {
        this.entityData.set(DATA_RIGHT_LEG_POSE, pos);
    }

    @Override
    public Vec3f getHeadPose() {
        return this.entityData.get(DATA_HEAD_POSE);
    }

    @Override
    public Vec3f getBodyPose() {
        return this.entityData.get(DATA_BODY_POSE);
    }

    @Override
    public Vec3f getLeftArmPose() {
        return this.entityData.get(DATA_LEFT_ARM_POSE);
    }

    @Override
    public Vec3f getRightArmPose() {
        return this.entityData.get(DATA_RIGHT_ARM_POSE);
    }

    @Override
    public Vec3f getLeftLegPose() {
        return this.entityData.get(DATA_LEFT_LEG_POSE);
    }

    @Override
    public Vec3f getRightLegPose() {
        return this.entityData.get(DATA_RIGHT_LEG_POSE);
    }

    static {
        DATA_CLIENT_FLAGS = Mappings.findAccessor("ArmorStand", "DATA_CLIENT_FLAGS");
        DATA_HEAD_POSE = Mappings.findAccessor("ArmorStand", "DATA_HEAD_POSE");
        DATA_BODY_POSE = Mappings.findAccessor("ArmorStand", "DATA_BODY_POSE");
        DATA_LEFT_ARM_POSE = Mappings.findAccessor("ArmorStand", "DATA_LEFT_ARM_POSE");
        DATA_RIGHT_ARM_POSE = Mappings.findAccessor("ArmorStand", "DATA_RIGHT_ARM_POSE");
        DATA_LEFT_LEG_POSE = Mappings.findAccessor("ArmorStand", "DATA_LEFT_LEG_POSE");
        DATA_RIGHT_LEG_POSE = Mappings.findAccessor("ArmorStand", "DATA_RIGHT_LEG_POSE");
    }
}
