package dev.by1337.virtualentity.api.particles;

import org.bukkit.Particle;
import org.jetbrains.annotations.Nullable;

public record ParticleOptions<T>(@Nullable T value, Particle particle) {
    public static <T> ParticleOptions<T> of(Particle particle, @Nullable T value) {
        return new ParticleOptions<>(value, particle);
    }

    public static <T> ParticleOptions<T> of(Particle particle) {
        return new ParticleOptions<>(null, particle);
    }
}
