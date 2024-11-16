package dev.by1337.virtualentity.core.util;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ConcurrentPlayerHashSet implements Set<Player> {
    private final Lock lock = new ReentrantLock();
    private final HashMap<UUID, Player> map;

    public ConcurrentPlayerHashSet() {
        this.map = new HashMap<>();
    }

    public ConcurrentPlayerHashSet(Collection<Player> players) {
        this.map = new HashMap<>();
        this.addAll(players);
    }

    @Override
    public void forEach(Consumer<? super Player> action) {
        try (var ignored = new AutoCloseableLock()) {
            map.values().forEach(action);
        }
    }

    @Override
    public boolean removeIf(Predicate<? super Player> filter) {
        try (var ignored = new AutoCloseableLock()) {
           return map.entrySet().removeIf(e -> filter.test(e.getValue()));
        }
    }

    @Override
    public int size() {
        try (var ignored = new AutoCloseableLock()) {
            return map.size();
        }
    }

    @Override
    public boolean isEmpty() {
        try (var ignored = new AutoCloseableLock()) {
            return map.isEmpty();
        }
    }

    @Override
    public boolean contains(Object o) {
        try (var ignored = new AutoCloseableLock()) {
            if (!(o instanceof Player player)) return false;
            return map.containsKey(player.getUniqueId());
        }
    }

    @Override
    public @NotNull Iterator<Player> iterator() {
        try (var ignored = new AutoCloseableLock()) {
            return map.values().iterator();
        }
    }

    @Override
    public @NotNull Object[] toArray() {
        try (var ignored = new AutoCloseableLock()) {
            return map.values().toArray();
        }
    }

    @Override
    public @NotNull <T> T[] toArray(@NotNull T[] a) {
        try (var ignored = new AutoCloseableLock()) {
            return map.values().toArray(a);
        }
    }

    @Override
    public boolean add(Player player) {
        try (var ignored = new AutoCloseableLock()) {
            return map.put(player.getUniqueId(), player) == null;
        }
    }

    @Override
    public boolean remove(Object o) {
        try (var ignored = new AutoCloseableLock()) {
            if (!(o instanceof Player player)) return false;
            return map.remove(player.getUniqueId()) != null;
        }
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        try (var ignored = new AutoCloseableLock()) {
            for (Object o : c) {
                if (!contains(o)) return false;
            }
            return true;
        }
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends Player> c) {
        try (var ignored = new AutoCloseableLock()) {
            for (Player player : c) {
                map.put(player.getUniqueId(), player);
            }
            return true;
        }
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        try (var ignored = new AutoCloseableLock()) {
            return map.keySet().retainAll(c.stream().map(v -> v instanceof Player pl ? pl.getUniqueId() : v).toList());
        }
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        try (var ignored = new AutoCloseableLock()) {
            return map.keySet().removeAll(c.stream().map(v -> v instanceof Player pl ? pl.getUniqueId() : v).toList());
        }
    }

    @Override
    public void clear() {
        try (var ignored = new AutoCloseableLock()) {
            map.clear();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcurrentPlayerHashSet players = (ConcurrentPlayerHashSet) o;
        return Objects.equals(map, players.map);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(map);
    }

    private class AutoCloseableLock implements AutoCloseable {
        public AutoCloseableLock() {
            lock.lock();
        }

        @Override
        public void close() {
            lock.unlock();
        }
    }
}
