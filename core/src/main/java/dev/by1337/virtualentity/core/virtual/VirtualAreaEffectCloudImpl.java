package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.particles.ParticleOptions;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.PacketType;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.bukkit.Color;
import org.bukkit.Particle;

public class VirtualAreaEffectCloudImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.VirtualAreaEffectCloud {
    private static final EntityDataAccessor<Float> DATA_RADIUS;
    private static final EntityDataAccessor<Integer> DATA_COLOR;
    private static final EntityDataAccessor<Boolean> DATA_WAITING;
    private static final EntityDataAccessor<ParticleOptions<?>> DATA_PARTICLE;

    public VirtualAreaEffectCloudImpl() {
        super(VirtualEntityType.AREA_EFFECT_CLOUD);
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_COLOR, 0);
        this.entityData.define(DATA_RADIUS, 0.5F);
        this.entityData.define(DATA_WAITING, false);
        this.entityData.define(DATA_PARTICLE, new ParticleOptions<>(Particle.SPELL, null));
    }

    /**
     * Устанавливает цвет облака эффекта области.
     *
     * @param color объект {@link Color}, представляющий новый цвет.
     */
    @Override
    public void setColor(Color color) {
        this.entityData.set(DATA_COLOR, color.asRGB());
    }

    /**
     * Получает цвет облака эффекта области.
     *
     * @return объект {@link Color}, соответствующий текущему цвету.
     */
    @Override
    public Color getColor() {
        return Color.fromRGB(this.entityData.get(DATA_COLOR));
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
        DATA_COLOR = Mappings.findAccessor("AreaEffectCloud", "DATA_COLOR");
        DATA_WAITING = Mappings.findAccessor("AreaEffectCloud", "DATA_WAITING");
        DATA_PARTICLE = Mappings.findAccessor("AreaEffectCloud", "DATA_PARTICLE");
    }
}
