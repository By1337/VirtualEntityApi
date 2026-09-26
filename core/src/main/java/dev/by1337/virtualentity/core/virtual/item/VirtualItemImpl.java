package dev.by1337.virtualentity.core.virtual.item;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.item.VirtualItem;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class VirtualItemImpl extends VirtualEntityImpl implements VirtualItem {
    private static final EntityDataAccessor<ItemStack> DATA_ITEM;

    public VirtualItemImpl() {
        super(VirtualEntityType.ITEM);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_ITEM, new ItemStack(Material.DIRT));
    }

    @Override
    public ItemStack getItem() {
        return entityData.get(DATA_ITEM);
    }

    @Override
    public void setItem(ItemStack item) {
        entityData.set(DATA_ITEM, item);
    }

    static {
        DATA_ITEM = Mappings.findAccessor("ItemEntity", "DATA_ITEM");
    }
}
