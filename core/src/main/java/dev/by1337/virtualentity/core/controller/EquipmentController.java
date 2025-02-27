package dev.by1337.virtualentity.core.controller;

import dev.by1337.virtualentity.api.entity.EquipmentSlot;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EquipmentController {
    private static final int SLOT_COUNT = EquipmentSlot.values().length;
    private static final EquipmentSlot[] EQUIPMENT_SLOTS = EquipmentSlot.values();
    private static final ItemStack AIR = new ItemStack(Material.AIR);

    private final Map<EquipmentSlot, ItemStack> equipment = new EnumMap<>(EquipmentSlot.class);
    private final boolean[] dirtySlots = new boolean[SLOT_COUNT];
    private boolean isDirty;
    private final Lock lock = new ReentrantLock();


    public void clear() {
        lock.lock();
        try {
            for (EquipmentSlot slot : equipment.keySet()) {
                dirtySlots[slot.ordinal()] = true;
            }
            equipment.clear();
            isDirty = true;
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        lock.lock();
        try {
            return equipment.isEmpty();
        } finally {
            lock.unlock();
        }
    }

    public void set(@NotNull EquipmentSlot slot, @Nullable ItemStack item) {
        lock.lock();
        try {
            if (item == null || item.getType().isAir()) {
                equipment.remove(slot);
            } else {
                equipment.put(slot, item);
            }
            dirtySlots[slot.ordinal()] = true;
            isDirty = true;
        } finally {
            lock.unlock();
        }
    }

    @Nullable
    public ItemStack get(EquipmentSlot slot) {
        lock.lock();
        try {
            return equipment.get(slot);
        } finally {
            lock.unlock();
        }
    }

    public Map<EquipmentSlot, ItemStack> packAll() {
        lock.lock();
        try {
            return new EnumMap<>(equipment);
        } finally {
            lock.unlock();
        }
    }

    public Map<EquipmentSlot, ItemStack> packDirty() {
        lock.lock();
        try {
            if (!isDirty) return Map.of();
            Map<EquipmentSlot, ItemStack> packed = new HashMap<>();
            for (int slot = 0; slot < SLOT_COUNT; slot++) {
                if (dirtySlots[slot]) {
                    dirtySlots[slot] = false;
                    EquipmentSlot equipmentSlot = EQUIPMENT_SLOTS[slot];
                    packed.put(equipmentSlot, equipment.getOrDefault(equipmentSlot, AIR));
                }
            }
            isDirty = false;
            return packed;
        } finally {
            lock.unlock();
        }
    }

    public boolean isDirty() {
        lock.lock();
        try {
            return isDirty;
        } finally {
            lock.unlock();
        }
    }
}
