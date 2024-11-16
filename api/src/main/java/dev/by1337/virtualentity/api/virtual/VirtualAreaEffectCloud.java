package dev.by1337.virtualentity.api.virtual;

import dev.by1337.virtualentity.api.particles.ParticleOptions;
import org.bukkit.Color;

public interface VirtualAreaEffectCloud extends VirtualEntity {
    void setColor(Color color);

    Color getColor();

    void setRadius(float radius);

    float getRadius();

    void setWaiting(boolean waiting);

    boolean isWaiting();

    void setParticle(ParticleOptions<?> particle);

    ParticleOptions<?> getParticle();
}
