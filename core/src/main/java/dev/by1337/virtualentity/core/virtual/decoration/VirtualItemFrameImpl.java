package dev.by1337.virtualentity.core.virtual.decoration;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualHangingEntityImpl;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.util.Direction;

public class VirtualItemFrameImpl extends VirtualHangingEntityImpl implements dev.by1337.virtualentity.api.virtual.decoration.VirtualItemFrame {
    private static final EntityDataAccessor<ItemStack> DATA_ITEM;
    private static final EntityDataAccessor<Integer> DATA_ROTATION;

    public VirtualItemFrameImpl() {
        super(VirtualEntityType.ITEM_FRAME);
    }

    public VirtualItemFrameImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ITEM, new ItemStack(Material.AIR));
        this.entityData.define(DATA_ROTATION, 0);
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
        // isHorizontal
        if (direction == Direction.NORTH || direction == Direction.SOUTH || direction == Direction.WEST || direction == Direction.EAST) {
            setPitch(0F);
            setYaw(to2dValue() * 90F);
        } else {
            setYaw(0);
            if (direction == Direction.DOWN) {
                setPitch(-90F);
            } else {
                setPitch(90F);
            }
        }
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
