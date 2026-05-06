package dev.by1337.virtualentity.core.virtual.player;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import net.kyori.adventure.text.Component;
import org.by1337.blib.nbt.impl.CompoundTag;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@SinceMinecraftVersion("1.21.9")
public class VirtualMannequinImpl extends VirtualAvatarImpl implements dev.by1337.virtualentity.api.virtual.player.VirtualMannequin {
    private static final Component DEFAULT_DESCRIPTION = Component.translatable("entity.minecraft.mannequin.label");
    //todo?
    //private static final EntityDataAccessor<net.minecraft.world.item.component.ResolvableProfile> DATA_PROFILE;
    private static final EntityDataAccessor<Boolean> DATA_IMMOVABLE;
    private static final EntityDataAccessor<Optional<Component>> DATA_DESCRIPTION;

    public VirtualMannequinImpl() {
        super(VirtualEntityType.MANNEQUIN);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IMMOVABLE, false);
        this.entityData.define(DATA_DESCRIPTION, Optional.of(DEFAULT_DESCRIPTION));
      //  this.entityData.define(DATA_PROFILE, DEFAULT_PROFILE); // todo?
    }
    @Override
    public boolean isImmovable() {
        return entityData.get(DATA_IMMOVABLE);
    }
    @Override
    public Optional<Component> getDescription() {
        return entityData.get(DATA_DESCRIPTION);
    }

    @Override
    public void setImmovable(boolean immovable) {
        entityData.set(DATA_IMMOVABLE, immovable);
    }
    @Override
    public void setDescription(@Nullable Component description) {
        entityData.set(DATA_DESCRIPTION, Optional.ofNullable(description));
    }

    static {
      //  DATA_PROFILE = Mappings.findAccessor("Mannequin", "DATA_PROFILE");
        DATA_IMMOVABLE = Mappings.findAccessor("Mannequin", "DATA_IMMOVABLE");
        DATA_DESCRIPTION = Mappings.findAccessor("Mannequin", "DATA_DESCRIPTION");
    }
}