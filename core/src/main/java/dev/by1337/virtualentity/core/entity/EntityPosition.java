package dev.by1337.virtualentity.core.entity;


import org.by1337.blib.geom.Vec3d;
import org.by1337.blib.geom.Vec3f;

public class EntityPosition {
    private Vec3d pos = new Vec3d(0, 0, 0);
    private Vec3f rotation = new Vec3f(0, 0, 0);

    private Vec3d posOld = new Vec3d(0, 0, 0);
    private Vec3f rotationOld = new Vec3f(0, 0, 0);

    public void setPos(Vec3d pos) {
        this.pos = pos;
    }

    public void setRotation(Vec3f rotation) {
        this.rotation = rotation;
    }

    public void sync() {
        posOld = pos;
        rotationOld = rotation;
    }

    public Vec3d deltaPos() {
        return pos.sub(posOld);
    }

    public void setPosOld(Vec3d posOld) {
        this.posOld = posOld;
    }

    public Vec3d getPos() {
        return pos;
    }

    public boolean needPosUpdate() {
        return !pos.equals(posOld);
    }

    public boolean needRotUpdate() {
        return !rotation.equals(rotationOld);
    }

    public float getYaw(){
        return rotation.x;
    }
    public float getPitch(){
        return rotation.y;
    }

    public float getDimensions(){
        return rotation.z;
    }
    public byte yaw() {
        return (byte) ((int) (rotation.x % 360.0F * 256.0F / 360.0F));
    }

    public byte pitch() {
        return (byte) ((int) (rotation.y % 360.0F * 256.0F / 360.0F));
    }

    public byte dimensions() {
        return (byte) ((int) (rotation.z % 360.0F * 256.0F / 360.0F));
    }

    public void setYaw(float yaw) {
        rotation = new Vec3f(yaw, rotation.y, rotation.z);
    }

    public void setPitch(float pitch) {
        rotation = new Vec3f(rotation.x, pitch, rotation.z);
    }

    public void setDimensions(float dimensions) {
        rotation = new Vec3f(rotation.x, rotation.y, dimensions);
    }

    public Vec3f getRotation() {
        return rotation;
    }

    public Vec3d getPosOld() {
        return posOld;
    }

    public Vec3f getRotationOld() {
        return rotationOld;
    }

    @Override
    public String toString() {
        return "EntityPosition{" +
                "pos=" + pos +
                ", rotation=" + rotation +
                ", posOld=" + posOld +
                ", rotationOld=" + rotationOld +
                '}';
    }
}
