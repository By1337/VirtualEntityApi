package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@SinceMinecraftVersion("1.20.6")
public class VirtualOminousItemSpawnerImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.VirtualOminousItemSpawner {
    private static final EntityDataAccessor<ItemStack> DATA_ITEM;

    public VirtualOminousItemSpawnerImpl() {
        super(VirtualEntityType.OMINOUS_ITEM_SPAWNER);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_ITEM, new ItemStack(Material.AIR));
    }

    @Override
    public void setItem(ItemStack item) {
        entityData.set(DATA_ITEM, item);
    }

    @Override
    public ItemStack getItem() {
        return entityData.get(DATA_ITEM);
    }

    static {
        DATA_ITEM = Mappings.findAccessor("OminousItemSpawner", "DATA_ITEM");
    }
}