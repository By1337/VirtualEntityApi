package dev.by1337.virtualentity.api.util;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PlayerHashSet implements Set<Player> {
    private final HashMap<UUID, Player> map;

    public PlayerHashSet() {
        this.map = new HashMap<>();
    }

    public PlayerHashSet(Collection<Player> players) {
        this.map = new HashMap<>();
        this.addAll(players);
    }

    @Override
    public void forEach(Consumer<? super Player> action) {
        map.values().forEach(action);
    }

    @Override
    public boolean removeIf(Predicate<? super Player> filter) {
        return map.entrySet().removeIf(e -> filter.test(e.getValue()));
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Player player)) return false;
        return map.containsKey(player.getUniqueId());
    }

    @Override
    public @NotNull Iterator<Player> iterator() {
        return map.values().iterator();
    }

    @Override
    public @NotNull Object[] toArray() {
        return map.values().toArray();
    }

    @Override
    public @NotNull <T> T[] toArray(@NotNull T[] a) {
        return map.values().toArray(a);
    }

    @Override
    public boolean add(Player player) {
        return map.put(player.getUniqueId(), player) == null;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof Player player)) return false;
        return map.remove(player.getUniqueId()) != null;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends Player> c) {
        for (Player player : c) {
            map.put(player.getUniqueId(), player);
        }
        return true;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return map.keySet().retainAll(c.stream().map(v -> v instanceof Player pl ? pl.getUniqueId() : v).toList());
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return map.keySet().removeAll(c.stream().map(v -> v instanceof Player pl ? pl.getUniqueId() : v).toList());
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerHashSet players = (PlayerHashSet) o;
        return Objects.equals(map, players.map);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(map);
    }
}
