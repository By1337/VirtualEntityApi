package dev.by1337.virtualentity.api.virtual.decoration;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

@SinceMinecraftVersion("1.17.1")
public interface VirtualGlowItemFrame extends VirtualItemFrame {

    static VirtualGlowItemFrame create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.GLOW_ITEM_FRAME, VirtualGlowItemFrame.class);
    }
}
