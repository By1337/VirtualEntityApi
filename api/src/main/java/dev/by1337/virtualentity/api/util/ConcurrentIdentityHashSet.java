package dev.by1337.virtualentity.api.util;

import org.by1337.blib.util.collection.IdentityHashSet;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ConcurrentIdentityHashSet<T> implements Set<T> {

    private final Lock lock = new ReentrantLock();
    private final Set<T> source;

    public ConcurrentIdentityHashSet(Collection<T> source) {
        this.source = new IdentityHashSet<>(source);
    }

    public ConcurrentIdentityHashSet() {
        source = new IdentityHashSet<>();
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        try (var ignored = new AutoCloseableLock()) {
            return source.removeIf(filter);
        }
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        try (var ignored = new AutoCloseableLock()) {
            source.forEach(action);
        }
    }

    @Override
    public int size() {
        try (var ignored = new AutoCloseableLock()) {
            return source.size();
        }
    }

    @Override
    public boolean isEmpty() {
        try (var ignored = new AutoCloseableLock()) {
            return source.isEmpty();
        }
    }

    @Override
    public boolean contains(Object o) {
        try (var ignored = new AutoCloseableLock()) {
            return source.contains(o);
        }
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        try (var ignored = new AutoCloseableLock()) {
            return source.iterator();
        }
    }

    @Override
    public @NotNull Object[] toArray() {
        try (var ignored = new AutoCloseableLock()) {
            return source.toArray();
        }
    }

    @Override
    public @NotNull <T1> T1[] toArray(@NotNull T1[] a) {
        try (var ignored = new AutoCloseableLock()) {
            return source.toArray(a);
        }
    }

    @Override
    public boolean add(T t) {
        try (var ignored = new AutoCloseableLock()) {
            return source.add(t);
        }
    }

    @Override
    public boolean remove(Object o) {
        try (var ignored = new AutoCloseableLock()) {
            return source.remove(o);
        }
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        try (var ignored = new AutoCloseableLock()) {
            return source.containsAll(c);
        }
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        try (var ignored = new AutoCloseableLock()) {
            return source.addAll(c);
        }
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        try (var ignored = new AutoCloseableLock()) {
            return source.retainAll(c);
        }
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        try (var ignored = new AutoCloseableLock()) {
            return source.removeAll(c);
        }
    }

    @Override
    public void clear() {
        try (var ignored = new AutoCloseableLock()) {
            source.clear();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcurrentIdentityHashSet<?> that = (ConcurrentIdentityHashSet<?>) o;
        return Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(source);
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
