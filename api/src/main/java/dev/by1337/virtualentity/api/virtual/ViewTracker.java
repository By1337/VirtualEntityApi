package dev.by1337.virtualentity.api.virtual;

import org.bukkit.entity.Player;

import java.util.Set;

public interface ViewTracker{
    void tick(Set<Player> viewers);
}
