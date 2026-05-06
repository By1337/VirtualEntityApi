package dev.by1337.virtualentity.api.virtual.player;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAreaEffectCloud;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@SinceMinecraftVersion("1.21.9")
public interface VirtualMannequin extends VirtualAvatar {
    boolean isImmovable();

    Optional<Component> getDescription();

    void setImmovable(boolean immovable);

    void setDescription(@Nullable Component description);

    static VirtualMannequin create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.MANNEQUIN, VirtualMannequin.class);
    }
}
