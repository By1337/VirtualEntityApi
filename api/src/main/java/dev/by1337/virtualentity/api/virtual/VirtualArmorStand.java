package dev.by1337.virtualentity.api.virtual;


import org.by1337.blib.geom.Vec3f;

public interface VirtualArmorStand extends VirtualLivingEntity {

    void setSmall(boolean flag);

    boolean isSmall();

    void setShowArms(boolean flag);

    boolean isShowArms();

    void setNoBasePlate(boolean flag);

    boolean isNoBasePlate();

    void setMarker(boolean flag);

    boolean isMarker();

    void setHeadPose(Vec3f pos);

    Vec3f getHeadPose();

    void setBodyPose(Vec3f pos);

    Vec3f getBodyPose();

    void setLeftArmPose(Vec3f pos);

    Vec3f getLeftArmPose();

    void setRightArmPose(Vec3f pos);

    Vec3f getRightArmPose();

    void setLeftLegPose(Vec3f pos);

    Vec3f getLeftLegPose();

    void setRightLegPose(Vec3f pos);

    Vec3f getRightLegPose();
}
