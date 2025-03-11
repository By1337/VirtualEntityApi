package dev.by1337.virtualentity.core.entity;


import org.by1337.blib.geom.Vec3d;
import org.by1337.blib.geom.Vec3f;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EntityPosition {
    private Vec3d pos = new Vec3d(0, 0, 0);
    private Vec3f rotation = new Vec3f(0, 0, 0);

    private Vec3d posOld = new Vec3d(0, 0, 0);
    private Vec3f rotationOld = new Vec3f(0, 0, 0);
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void setPos(Vec3d pos) {
        lock.writeLock().lock();
        try {
            this.pos = pos;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void setRotation(Vec3f rotation) {
        lock.readLock().lock();
        try {
            this.rotation = rotation;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void sync() {
        lock.writeLock().lock();
        try {
            posOld = pos;
            rotationOld = rotation;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Vec3d deltaPos() {
        lock.readLock().lock();
        try {
            return pos.sub(posOld);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setPosOld(Vec3d posOld) {
        lock.writeLock().lock();
        try {
            this.posOld = posOld;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Vec3d getPos() {
        lock.readLock().lock();
        try {
            return pos;
        } finally {
            lock.readLock().unlock();
        }
    }

    public boolean needPosUpdate() {
        lock.readLock().lock();
        try {
            return !pos.equals(posOld);
        } finally {
            lock.readLock().unlock();
        }
    }

    public boolean needRotUpdate() {
        lock.readLock().lock();
        try {
            return !rotation.equals(rotationOld);
        } finally {
            lock.readLock().unlock();
        }
    }

    public float getYaw() {
        lock.readLock().lock();
        try {
            return rotation.x;
        } finally {
            lock.readLock().unlock();
        }
    }

    public float getPitch() {
        lock.readLock().lock();
        try {
            return rotation.y;
        } finally {
            lock.readLock().unlock();
        }
    }

    public float getDimensions() {
        lock.readLock().lock();
        try {
            return rotation.z;
        } finally {
            lock.readLock().unlock();
        }
    }

    public byte yaw() {
        lock.readLock().lock();
        try {
            return (byte) ((int) (rotation.x % 360.0F * 256.0F / 360.0F));
        } finally {
            lock.readLock().unlock();
        }
    }

    public byte pitch() {
        lock.readLock().lock();
        try {
            return (byte) ((int) (rotation.y % 360.0F * 256.0F / 360.0F));
        } finally {
            lock.readLock().unlock();
        }
    }

    public byte dimensions() {
        lock.readLock().lock();
        try {
            return (byte) ((int) (rotation.z % 360.0F * 256.0F / 360.0F));
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setYaw(float yaw) {
        lock.writeLock().lock();
        try {
            rotation = new Vec3f(yaw, rotation.y, rotation.z);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void setPitch(float pitch) {
        lock.writeLock().lock();
        try {
            rotation = new Vec3f(rotation.x, pitch, rotation.z);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void setDimensions(float dimensions) {
        lock.writeLock().lock();
        try {
            rotation = new Vec3f(rotation.x, rotation.y, dimensions);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Vec3f getRotation() {
        lock.readLock().lock();
        try {
            return rotation;
        } finally {
            lock.readLock().unlock();
        }
    }

    public Vec3d getPosOld() {
        lock.readLock().lock();
        try {
            return posOld;
        } finally {
            lock.readLock().unlock();
        }
    }

    public Vec3f getRotationOld() {
        lock.readLock().lock();
        try {
            return rotationOld;
        } finally {
            lock.readLock().unlock();
        }
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
