package dev.by1337.virtualentity.api.tracker;

import dev.by1337.virtualentity.api.util.ConcurrentIdentityHashSet;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.by1337.blib.geom.Vec3d;
import org.by1337.blib.util.collection.IdentityHashSet;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PlayerTracker {
    protected final World world;
    protected final Set<VirtualEntity> entities;
    protected Vec3d center;
    protected int radiusSq;

    public PlayerTracker(final World world, Vec3d center) {
        this(world, Collections.emptyList(), center, 30);
    }

    public PlayerTracker(final World world, final VirtualEntity entity, Vec3d center) {
        this(world, List.of(entity), center, 30);
    }

    public PlayerTracker(final World world, final List<VirtualEntity> entities, Vec3d center, int radius) {
        this.world = world;
        this.entities = new ConcurrentIdentityHashSet<>(entities);
        this.center = center;
        radiusSq = radius * radius;
    }

    public void setRadius(int radius) {
        this.radiusSq = radius * radius;
    }

    public void tick() {
        Set<Player> actualViewers = new IdentityHashSet<>();
        for (Player player : world.getPlayers()) {
            Location loc = player.getLocation();
            if (center.distanceSquared(new Vec3d(loc.getX(), loc.getY(), loc.getZ())) <= radiusSq) {
                actualViewers.add(player);
            }
        }
        entities.forEach(e -> e.tick(actualViewers));
    }

    public void removeAll() {
        entities.forEach(e -> e.tick(Collections.emptySet()));
        entities.clear();
    }

    public void addEntity(final VirtualEntity entity) {
        entities.add(entity);
    }

    public void removeEntity(final VirtualEntity entity) {
        entity.tick(Collections.emptySet());
        entities.remove(entity);
    }
}
