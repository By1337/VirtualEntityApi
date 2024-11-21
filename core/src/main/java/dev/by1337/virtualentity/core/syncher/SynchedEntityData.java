package dev.by1337.virtualentity.core.syncher;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

public class SynchedEntityData {
    private boolean isDirty;
    private final Consumer<EntityDataAccessor<?>> onUpdate;
    private final Map<Integer, DataItem<?>> itemsById = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public SynchedEntityData(Consumer<EntityDataAccessor<?>> onUpdate) {
        this.onUpdate = onUpdate;
    }

    public <T> void define(EntityDataAccessor<T> accessor, T value) {
        int id = accessor.id();
        if (id > 254) {
            throw new IllegalArgumentException("Data value id is too big with " + id + "! (Max is " + 254 + ")");
        } else if (this.itemsById.containsKey(id)) {
            throw new IllegalArgumentException("Duplicate id value for " + id + "!");
        } else {
            this.createDataItem(accessor, value);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> DataItem<T> getItem(EntityDataAccessor<T> param0) {
        this.lock.readLock().lock();
        try {
            return (DataItem<T>) this.itemsById.get(param0.id());
        } finally {
            this.lock.readLock().unlock();
        }
    }

    public <T> T get(EntityDataAccessor<T> accessor) {
        return this.getItem(accessor).getValue();
    }

    public <T> void set(EntityDataAccessor<T> accessor, T value) {
        set(accessor, value, false);
    }
    public <T> void set(EntityDataAccessor<T> accessor, T value, boolean force) {
        this.lock.writeLock().lock();
        try {
            DataItem<T> dataItem = this.getItem(accessor);
            if (force || !Objects.equals(value, dataItem.getValue())) {
                dataItem.setValue(value);
                onUpdate.accept(accessor);
                dataItem.setDirty(true);
                this.isDirty = true;
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public boolean isDirty() {
        return this.isDirty;
    }

    public List<DataItem<?>> packDirty() {
        List<DataItem<?>> result = new ArrayList<>();
        if (this.isDirty) {
            this.lock.readLock().lock();
            for (DataItem<?> dataItem : itemsById.values()) {
                if (dataItem.dirty) {
                    result.add(dataItem);
                    dataItem.dirty = false;
                }
            }
            this.lock.readLock().unlock();
        }
        this.isDirty = false;
        return result;
    }

    public List<DataItem<?>> packAll() {
        this.lock.readLock().lock();
        List<DataItem<?>> result = new ArrayList<>(this.itemsById.values());
        this.lock.readLock().unlock();
        return result;
    }

    private <T> void createDataItem(EntityDataAccessor<T> accessor, T value) {
        DataItem<T> var3 = new DataItem<>(accessor, value);
        this.lock.writeLock().lock();
        this.itemsById.put(accessor.id(), var3);
        this.lock.writeLock().unlock();
    }


    public static class DataItem<T> {
        private final EntityDataAccessor<T> accessor;
        private T value;
        private boolean dirty;

        public DataItem(EntityDataAccessor<T> param0, T param1) {
            this.accessor = param0;
            this.value = param1;
            this.dirty = true;
        }

        public EntityDataAccessor<T> getAccessor() {
            return this.accessor;
        }

        public void setValue(T param0) {
            this.value = param0;
        }

        public T getValue() {
            return this.value;
        }

        public boolean isDirty() {
            return this.dirty;
        }

        public void setDirty(boolean param0) {
            this.dirty = param0;
        }

        public DataItem<T> copy() {
            return new DataItem<>(this.accessor, value);
        }
    }
}
