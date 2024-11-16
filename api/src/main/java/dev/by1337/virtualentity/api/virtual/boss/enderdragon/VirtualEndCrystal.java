package dev.by1337.virtualentity.api.virtual.boss.enderdragon;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import org.by1337.blib.geom.Vec3i;
import org.jetbrains.annotations.Nullable;

public interface VirtualEndCrystal extends VirtualEntity {
    void setBeamTarget(@Nullable Vec3i vec3i);

    @Nullable Vec3i getBeamTarget();

    void setShowBottom(boolean showBottom);

    boolean isShowBottom();
}