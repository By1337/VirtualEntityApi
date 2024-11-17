package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.annotations.DeprecatedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;
import org.by1337.blib.geom.Vec3i;
import org.by1337.blib.util.Direction;
import org.by1337.blib.util.Version;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class VirtualShulkerImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualShulker {

    private static final EntityDataAccessor<Direction> DATA_ATTACH_FACE_ID;
    @DeprecatedInMinecraftVersion("1.17.1")
    private static final EntityDataAccessor<Optional<Vec3i>> DATA_ATTACH_POS_ID;
    private static final EntityDataAccessor<Byte> DATA_PEEK_ID;
    private static final EntityDataAccessor<Byte> DATA_COLOR_ID;

    public VirtualShulkerImpl() {
        super(VirtualEntityType.SHULKER);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ATTACH_FACE_ID, Direction.DOWN);
        this.entityData.define(DATA_PEEK_ID, (byte) 0);
        this.entityData.define(DATA_COLOR_ID, (byte) 16);
        if (Version.VERSION.olderThan(Version.V1_17_1)) {
            this.entityData.define(DATA_ATTACH_POS_ID, Optional.empty());
        }
    }

    @Override
    public Direction getAttachFace() {
        return this.entityData.get(DATA_ATTACH_FACE_ID);
    }

    @Override
    public void setAttachFace(Direction face) {
        this.entityData.set(DATA_ATTACH_FACE_ID, face);
    }

    @Override
    @DeprecatedInMinecraftVersion("1.17.1")
    public @Nullable Vec3i getAttachPos() {
        return this.entityData.get(DATA_ATTACH_POS_ID).orElse(null);
    }

    @Override
    @DeprecatedInMinecraftVersion("1.17.1")
    public void setAttachPos(@Nullable Vec3i pos) {
        this.entityData.set(DATA_ATTACH_POS_ID, Optional.ofNullable(pos));
    }

    @Override
    public byte getPeek() {
        return this.entityData.get(DATA_PEEK_ID);
    }

    @Override
    public void setPeek(byte peek) {
        this.entityData.set(DATA_PEEK_ID, peek);
    }

    @Override
    public byte getColor() {
        return this.entityData.get(DATA_COLOR_ID);
    }

    @Override
    public void setColor(byte color) {
        this.entityData.set(DATA_COLOR_ID, color);
    }

    static {
        DATA_ATTACH_FACE_ID = Mappings.findAccessor("Shulker", "DATA_ATTACH_FACE_ID");
        if (Version.VERSION.olderThan(Version.V1_17_1))
            DATA_ATTACH_POS_ID = Mappings.findAccessor("Shulker", "DATA_ATTACH_POS_ID");
        else
            DATA_ATTACH_POS_ID = null;
        DATA_PEEK_ID = Mappings.findAccessor("Shulker", "DATA_PEEK_ID");
        DATA_COLOR_ID = Mappings.findAccessor("Shulker", "DATA_COLOR_ID");
    }
}
