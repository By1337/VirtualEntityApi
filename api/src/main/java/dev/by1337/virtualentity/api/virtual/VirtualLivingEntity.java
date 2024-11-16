package dev.by1337.virtualentity.api.virtual;


import dev.by1337.virtualentity.api.entity.InteractionHand;
import org.bukkit.Color;
import org.by1337.blib.geom.Vec3i;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface VirtualLivingEntity extends VirtualEntity {
    void removeEffectParticles();

    void setEffectParticles(Color color);

    float getHealth();

    void setHealth(float health);

    int getArrowCount();

    void setArrowCount(int count);

    int getStingerCount();

    void setStingerCount(int count);

    boolean isAutoSpinAttack();

    void startAutoSpinAttack();

    boolean isUsingItem();

    InteractionHand getUsedItemHand();

    void startUsingItem(InteractionHand hand);

    void stopUsingItem();

    Optional<Vec3i> getSleepingPos();

    void setSleepingPos(@Nullable Vec3i pos);

    void clearSleepingPos();
}
