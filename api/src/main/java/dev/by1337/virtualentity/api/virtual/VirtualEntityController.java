package dev.by1337.virtualentity.api.virtual;

import dev.by1337.virtualentity.api.entity.EntityAnimation;
import dev.by1337.virtualentity.api.entity.EntityEvent;
import dev.by1337.virtualentity.api.entity.EquipmentSlot;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.geom.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;

public interface VirtualEntityController extends ViewTracker {
    void clearEquipment();

    void setEquipment(EquipmentSlot slot, @Nullable ItemStack item);

    @Nullable
    ItemStack getEquipment(EquipmentSlot slot);

    void setPos(Vec3d pos);

    Vec3d getOldPos();

    Vec3d getPos();

    byte yaw();

    byte pitch();

    byte dimensions();

    void setYaw(float yaw);

    void setPitch(float pitch);

    void setDimensions(float dimensions);

    boolean isOnGround();

    void setOnGround(boolean onGround);

    UUID getUuid();

    VirtualEntityType getType();

    int getCustomEntityData();

    float getYaw();

    float getPitch();

    float getDimensions();

    void playAnimation(EntityAnimation animation);

    void lookAt(Vec3d at);

    default void onTick() {
    }

    void broadcastEntityEvent(EntityEvent event);

    void respawn();

    Set<Player> getLastViewers();

    default void setNoMotion() {
        setMotion(Vec3d.ZERO);
    }

    void setMotion(Vec3d motion);
}
