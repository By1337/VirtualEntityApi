package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.particles.ParticleOptions;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.bukkit.Color;
import org.bukkit.Particle;

public class VirtualAreaEffectCloudImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.VirtualAreaEffectCloud {
    private static final Particle ENTITY_EFFECT_PARTICLE;
    private static final EntityDataAccessor<Float> DATA_RADIUS;
    private static final EntityDataAccessor<Boolean> DATA_WAITING;
    private static final EntityDataAccessor<ParticleOptions<?>> DATA_PARTICLE;

    public VirtualAreaEffectCloudImpl() {
        super(VirtualEntityType.AREA_EFFECT_CLOUD);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_PARTICLE, new ParticleOptions<>(Color.PURPLE, ENTITY_EFFECT_PARTICLE));
        this.entityData.define(DATA_RADIUS, 0.5F);
        this.entityData.define(DATA_WAITING, false);

    }

    /**
     * Устанавливает цвет облака эффекта области.
     *
     * @param color объект {@link Color}, представляющий новый цвет.
     */
    @Override
    @RemovedInMinecraftVersion("1.20.6")
    public void setColor(Color color) {
        this.entityData.set(DATA_PARTICLE, new ParticleOptions<>(color, ENTITY_EFFECT_PARTICLE));
    }

    /**
     * Получает цвет облака эффекта области.
     *
     * @return объект {@link Color}, соответствующий текущему цвету.
     */
    @Override
    @RemovedInMinecraftVersion("1.20.6")
    public Color getColor() {
        return Color.AQUA;
    }

    @Override
    public void setRadius(float radius) {
        this.entityData.set(DATA_RADIUS, radius);
    }

    @Override
    public float getRadius() {
        return this.entityData.get(DATA_RADIUS);
    }

    @Override
    public void setWaiting(boolean waiting) {
        this.entityData.set(DATA_WAITING, waiting);
    }

    @Override
    public boolean isWaiting() {
        return this.entityData.get(DATA_WAITING);
    }

    @Override
    public void setParticle(ParticleOptions<?> particle) {
        this.entityData.set(DATA_PARTICLE, particle);
    }

    @Override
    public ParticleOptions<?> getParticle() {
        return this.entityData.get(DATA_PARTICLE);
    }

    static {
        DATA_RADIUS = Mappings.findAccessor("AreaEffectCloud", "DATA_RADIUS");
        ENTITY_EFFECT_PARTICLE = Particle.ENTITY_EFFECT;

        DATA_WAITING = Mappings.findAccessor("AreaEffectCloud", "DATA_WAITING");
        DATA_PARTICLE = Mappings.findAccessor("AreaEffectCloud", "DATA_PARTICLE");
    }
}
