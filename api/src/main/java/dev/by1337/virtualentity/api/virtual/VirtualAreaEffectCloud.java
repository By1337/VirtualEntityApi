package dev.by1337.virtualentity.api.virtual;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.particles.ParticleOptions;
import org.bukkit.Color;

public interface VirtualAreaEffectCloud extends VirtualEntity {
    @RemovedInMinecraftVersion("1.20.6")
    void setColor(Color color);

    @RemovedInMinecraftVersion("1.20.6")
    Color getColor();

    void setRadius(float radius);

    float getRadius();

    void setWaiting(boolean waiting);

    boolean isWaiting();

    void setParticle(ParticleOptions<?> particle);

    ParticleOptions<?> getParticle();

    static VirtualAreaEffectCloud create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.AREA_EFFECT_CLOUD, VirtualAreaEffectCloud.class);
    }
}
