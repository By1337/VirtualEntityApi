package dev.by1337.virtualentity.api.virtual;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import org.bukkit.inventory.ItemStack;

@SinceMinecraftVersion("1.20.6")
public interface VirtualOminousItemSpawner extends VirtualEntity {
    void setItem(ItemStack item);

    ItemStack getItem();

    static VirtualOminousItemSpawner create(){
        return VirtualEntityApi.getFactory().create(VirtualEntityType.OMINOUS_ITEM_SPAWNER, VirtualOminousItemSpawner.class);
    }
}
