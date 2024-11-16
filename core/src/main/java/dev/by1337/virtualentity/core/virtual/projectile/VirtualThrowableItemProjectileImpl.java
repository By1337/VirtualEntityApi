package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class VirtualThrowableItemProjectileImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.projectile.VirtualThrowableItemProjectile {
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK;

    public VirtualThrowableItemProjectileImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ITEM_STACK, new ItemStack(Material.AIR));
    }

    /**
     * Получает предмет, который был использован для создания снаряда.
     *
     * @return объект {@link ItemStack}, представляющий предмет.
     */
    public ItemStack getItemStack() {
        return this.entityData.get(DATA_ITEM_STACK);
    }

    /**
     * Устанавливает новый предмет для снаряда.
     *
     * @param itemStack новый предмет для снаряда.
     */
    public void setItemStack(ItemStack itemStack) {
        this.entityData.set(DATA_ITEM_STACK, itemStack);
    }

    static {
        DATA_ITEM_STACK = Mappings.findAccessor("ThrowableItemProjectile", "DATA_ITEM_STACK");
    }
}
