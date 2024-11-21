package dev.by1337.virtualentity.core.virtual.display;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.ItemDisplayType;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@SinceMinecraftVersion("1.19.4")
public class VirtualItemDisplayImpl extends VirtualDisplayImpl implements dev.by1337.virtualentity.api.virtual.display.VirtualItemDisplay {
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK_ID;
    private static final EntityDataAccessor<Byte> DATA_ITEM_DISPLAY_ID;

    public VirtualItemDisplayImpl() {
        super(VirtualEntityType.ITEM_DISPLAY);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ITEM_STACK_ID, new ItemStack(Material.DIRT));
        this.entityData.define(DATA_ITEM_DISPLAY_ID, (byte) ItemDisplayType.NONE.getId());
    }

    @Override
    public ItemStack getItem() {
        return this.entityData.get(DATA_ITEM_STACK_ID);
    }

    @Override
    public void setItem(ItemStack item) {
        this.entityData.set(DATA_ITEM_STACK_ID, item);
    }

    @Override
    public Byte getDisplayId() {
        return this.entityData.get(DATA_ITEM_DISPLAY_ID);
    }

    @Override
    public void setDisplayId(ItemDisplayType type) {
        this.entityData.set(DATA_ITEM_DISPLAY_ID, (byte) type.getId());
    }

    static {
        DATA_ITEM_STACK_ID = Mappings.findAccessor("ItemDisplay", "DATA_ITEM_STACK_ID");
        DATA_ITEM_DISPLAY_ID = Mappings.findAccessor("ItemDisplay", "DATA_ITEM_DISPLAY_ID");
    }
}