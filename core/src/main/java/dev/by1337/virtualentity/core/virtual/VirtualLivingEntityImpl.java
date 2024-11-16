package dev.by1337.virtualentity.core.virtual;


import dev.by1337.virtualentity.api.entity.InteractionHand;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualLivingEntity;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.bukkit.Color;
import org.by1337.blib.geom.Vec3i;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class VirtualLivingEntityImpl extends VirtualEntityImpl implements VirtualLivingEntity {
    protected static final EntityDataAccessor<Byte> DATA_LIVING_ENTITY_FLAGS;
    private static final EntityDataAccessor<Float> DATA_HEALTH_ID;
    private static final EntityDataAccessor<Integer> DATA_EFFECT_COLOR_ID;
    private static final EntityDataAccessor<Boolean> DATA_EFFECT_AMBIENCE_ID;
    private static final EntityDataAccessor<Integer> DATA_ARROW_COUNT_ID;
    private static final EntityDataAccessor<Integer> DATA_STINGER_COUNT_ID;
    private static final EntityDataAccessor<Optional<Vec3i>> SLEEPING_POS_ID;

    public VirtualLivingEntityImpl(VirtualEntityType type) {
        super(type);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_LIVING_ENTITY_FLAGS, (byte) 0);
        this.entityData.define(DATA_EFFECT_COLOR_ID, 0);
        this.entityData.define(DATA_EFFECT_AMBIENCE_ID, false);
        this.entityData.define(DATA_ARROW_COUNT_ID, 0);
        this.entityData.define(DATA_STINGER_COUNT_ID, 0);
        this.entityData.define(DATA_HEALTH_ID, 1.0F);
        this.entityData.define(SLEEPING_POS_ID, Optional.empty());
    }

    @Override
    public void removeEffectParticles() {
        this.entityData.set(DATA_EFFECT_AMBIENCE_ID, false);
        this.entityData.set(DATA_EFFECT_COLOR_ID, 0);
    }

    @Override
    public void setEffectParticles(Color color) {
        this.entityData.set(DATA_EFFECT_AMBIENCE_ID, true);
        this.entityData.set(DATA_EFFECT_COLOR_ID, color.asRGB());
    }

    @Override
    public float getHealth() {
        return this.entityData.get(DATA_HEALTH_ID);
    }

    @Override
    public void setHealth(float heal) {
        this.entityData.set(DATA_HEALTH_ID, heal);
    }

    @Override
    public int getArrowCount() {
        return this.entityData.get(DATA_ARROW_COUNT_ID);
    }

    @Override
    public void setArrowCount(int count) {
        this.entityData.set(DATA_ARROW_COUNT_ID, count);
    }

    @Override
    public int getStingerCount() {
        return this.entityData.get(DATA_STINGER_COUNT_ID);
    }

    @Override
    public void setStingerCount(int count) {
        this.entityData.set(DATA_STINGER_COUNT_ID, count);
    }

    @Override
    public boolean isAutoSpinAttack() {
        return (this.entityData.get(DATA_LIVING_ENTITY_FLAGS) & 4) != 0;
    }

    @Override
    public boolean isUsingItem() {
        return (this.entityData.get(DATA_LIVING_ENTITY_FLAGS) & 1) > 0;
    }

    @Override
    public InteractionHand getUsedItemHand() {
        return (this.entityData.get(DATA_LIVING_ENTITY_FLAGS) & 2) > 0 ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
    }

    @Override
    public Optional<Vec3i> getSleepingPos() {
        return this.entityData.get(SLEEPING_POS_ID);
    }

    @Override
    public void setSleepingPos(@Nullable Vec3i pos) {
        this.entityData.set(SLEEPING_POS_ID, Optional.ofNullable(pos));
    }

    @Override
    public void clearSleepingPos() {
        this.entityData.set(SLEEPING_POS_ID, Optional.empty());
    }

    protected void setLivingEntityFlag(int param0, boolean param1) {
        int var3 = this.entityData.get(DATA_LIVING_ENTITY_FLAGS);
        if (param1) {
            var3 |= param0;
        } else {
            var3 &= ~param0;
        }

        this.entityData.set(DATA_LIVING_ENTITY_FLAGS, (byte) var3);
    }

    @Override
    public void startAutoSpinAttack() {
        this.setLivingEntityFlag(4, true);
    }

    @Override
    public void startUsingItem(InteractionHand hand) {
        this.setLivingEntityFlag(1, true);
        this.setLivingEntityFlag(2, hand == InteractionHand.OFF_HAND);
    }

    @Override
    public void stopUsingItem() {
        this.setLivingEntityFlag(1, false);
    }

    static {
        DATA_LIVING_ENTITY_FLAGS = Mappings.findAccessor("LivingEntity", "DATA_LIVING_ENTITY_FLAGS");
        DATA_HEALTH_ID = Mappings.findAccessor("LivingEntity", "DATA_HEALTH_ID");
        DATA_EFFECT_COLOR_ID = Mappings.findAccessor("LivingEntity", "DATA_EFFECT_COLOR_ID");
        DATA_EFFECT_AMBIENCE_ID = Mappings.findAccessor("LivingEntity", "DATA_EFFECT_AMBIENCE_ID");
        DATA_ARROW_COUNT_ID = Mappings.findAccessor("LivingEntity", "DATA_ARROW_COUNT_ID");
        DATA_STINGER_COUNT_ID = Mappings.findAccessor("LivingEntity", "DATA_STINGER_COUNT_ID");
        SLEEPING_POS_ID = Mappings.findAccessor("LivingEntity", "SLEEPING_POS_ID");

    }
}
