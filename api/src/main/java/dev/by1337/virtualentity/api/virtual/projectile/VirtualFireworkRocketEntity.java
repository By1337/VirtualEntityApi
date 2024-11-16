package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import org.bukkit.inventory.ItemStack;

import java.util.OptionalInt;

public interface VirtualFireworkRocketEntity extends VirtualEntity {
    ItemStack getFireworkItem();

    void setFireworkItem(ItemStack fireworkItem);

    OptionalInt getAttachedToTarget();

    void setAttachedToTarget(OptionalInt targetId);

    boolean isShotAtAngle();

    void setShotAtAngle(boolean shotAtAngle);
}
