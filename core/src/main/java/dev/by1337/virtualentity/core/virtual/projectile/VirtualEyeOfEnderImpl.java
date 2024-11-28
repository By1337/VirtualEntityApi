package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.util.Version;

public class VirtualEyeOfEnderImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.projectile.VirtualEyeOfEnder {
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK;
    private static final ItemStack DEFAULT_ITEM;

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
        super.defineSynchedData();
        this.entityData.define(DATA_ITEM_STACK, DEFAULT_ITEM.clone());
    }

    static {
        DATA_ITEM_STACK = Mappings.findAccessor("EyeOfEnder", "DATA_ITEM_STACK");
        if (Version.VERSION.newerThanOrEqual(Version.V1_20_6)) {
            DEFAULT_ITEM = new ItemStack(Material.ENDER_EYE);
        } else {
            DEFAULT_ITEM = new ItemStack(Material.AIR);
        }
    }
}
