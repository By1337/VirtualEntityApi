package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class VirtualEyeOfEnderImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.projectile.VirtualEyeOfEnder {
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK;

    public VirtualEyeOfEnderImpl() {
        super(VirtualEntityType.EYE_OF_ENDER);
    }

    @Override
    public void setItem(ItemStack itemStack) {
        this.entityData.set(DATA_ITEM_STACK, itemStack);
    }

    @Override
    public ItemStack getItem() {
        return this.entityData.get(DATA_ITEM_STACK);
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_ITEM_STACK, new ItemStack(Material.AIR));
    }

    static {
        DATA_ITEM_STACK = Mappings.findAccessor("EyeOfEnder", "DATA_ITEM_STACK");
    }
}
