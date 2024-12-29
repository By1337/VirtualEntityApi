package dev.by1337.virtualentity.api.virtual.decoration;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualHangingEntity;
import org.bukkit.inventory.ItemStack;

public interface VirtualItemFrame extends VirtualHangingEntity {
    ItemStack getItem();

    void setItem(ItemStack param0);

    int getRotation();

    void setRotation(int rotate);

    static VirtualItemFrame create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ITEM_FRAME, VirtualItemFrame.class);
    }
}
