package dev.by1337.virtualentity.api.virtual;

import dev.by1337.virtualentity.api.entity.EntityAnimation;
import dev.by1337.virtualentity.api.entity.EntityEvent;
import dev.by1337.virtualentity.api.entity.EquipmentSlot;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.task.TickTask;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.geom.Vec3d;
import org.by1337.blib.util.Version;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;

public interface VirtualEntityController extends ViewTracker, Identifiable {
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

    void sendEntityEvent(EntityEvent event);

    default void broadcastEntityEvent(EntityEvent event) {
        if (event == EntityEvent.TALISMAN_ACTIVATE && Version.is1_21_3orNewer()) {
            return;
        }
        if (event == EntityEvent.BAD_OMEN_TRIGGERED && Version.is1_20_6orNewer()) {
            return;
        }
        if (Version.is1_19_4orNewer()) {
            switch (event) {
                case DROWNED, HURT, FROZEN, POKED, BURNED -> {
                    //todo play take damage
                    return;
                }
                case THORNED -> {
                    return;
                }
            }
        }
    }

    void respawn();

    Set<Player> getLastViewers();

    default void setNoMotion() {
        setMotion(Vec3d.ZERO);
    }

    void setMotion(Vec3d motion);
    void addTickTask(TickTask task);
    boolean removeTickTask(TickTask task);
    void removeAllTickTask();
}
