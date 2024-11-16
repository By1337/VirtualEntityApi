package dev.by1337.virtualentity.api.particles;

import org.bukkit.Particle;
import org.jetbrains.annotations.Nullable;

public record ParticleOptions<T>(@Nullable T value, Particle particle) {
}
