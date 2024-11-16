package dev.by1337.virtualentity.core.virtual.vehicle;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualMinecartFurnaceImpl extends VirtualAbstractMinecartImpl implements dev.by1337.virtualentity.api.virtual.vehicle.VirtualMinecartFurnace {
    private static final EntityDataAccessor<Boolean> DATA_ID_FUEL;

    public VirtualMinecartFurnaceImpl() {
        super(VirtualEntityType.FURNACE_MINECART);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_FUEL, false);
    }

    /**
     * Получает состояние топлива в печи.
     * Если топливо есть, возвращает true, иначе false.
     *
     * @return true, если в печи есть топливо, иначе false.
     */
    @Override
    public boolean hasFuel() {
        return this.entityData.get(DATA_ID_FUEL);
    }

    /**
     * Устанавливает состояние топлива в печи.
     *
     * @param hasFuel true, если в печи должно быть топливо, иначе false.
     */
    @Override
    public void setFuel(boolean hasFuel) {
        this.entityData.set(DATA_ID_FUEL, hasFuel);
    }

    static {
        DATA_ID_FUEL = Mappings.findAccessor("MinecartFurnace", "DATA_ID_FUEL");
    }
}
