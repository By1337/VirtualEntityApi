package dev.by1337.virtualentity.api.virtual;

import dev.by1337.virtualentity.api.entity.EntityAnimation;
import dev.by1337.virtualentity.api.entity.EquipmentSlot;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.geom.Vec3d;

import java.util.UUID;

public interface VirtualEntityController {
    void clearEquipment();

    void setEquipment(EquipmentSlot slot, ItemStack item);

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

    int getId();

    void playAnimation(EntityAnimation animation);
}
