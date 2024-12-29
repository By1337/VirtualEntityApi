package dev.by1337.virtualentity.api.virtual.display;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.ItemDisplayType;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import org.bukkit.inventory.ItemStack;

@SinceMinecraftVersion("1.19.4")
public interface VirtualItemDisplay extends VirtualDisplay {
    ItemStack getItem();

    void setItem(ItemStack item);

    Byte getDisplayId();

    void setDisplayId(ItemDisplayType type);

    static VirtualItemDisplay create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ITEM_DISPLAY, VirtualItemDisplay.class);
    }
}
