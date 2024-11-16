package dev.by1337.virtualentity.core.virtual.decoration;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class VirtualItemFrameImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.decoration.VirtualItemFrame {
    private static final EntityDataAccessor<ItemStack> DATA_ITEM;
    private static final EntityDataAccessor<Integer> DATA_ROTATION;

    public VirtualItemFrameImpl() {
        super(VirtualEntityType.ITEM_FRAME);
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_ITEM, new ItemStack(Material.AIR));
        this.entityData.define(DATA_ROTATION, 0);
    }
    @Override
    public ItemStack getItem() {
        return this.entityData.get(DATA_ITEM);
    }

    @Override
    public void setItem(ItemStack param0) {
        this.entityData.set(DATA_ITEM, param0);
    }

    @Override
    public int getRotation() {
        return this.entityData.get(DATA_ROTATION);
    }
    @Override
    public void setRotation(int rotate) {
        this.entityData.set(DATA_ROTATION, rotate % 8);
    }

    static {
        DATA_ITEM = Mappings.findAccessor("ItemFrame", "DATA_ITEM");
        DATA_ROTATION = Mappings.findAccessor("ItemFrame", "DATA_ROTATION");
    }
}
