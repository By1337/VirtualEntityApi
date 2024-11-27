package dev.by1337.virtualentity.core.virtual.vehicle;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.nms.NmsUtil;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

public abstract class VirtualAbstractMinecartImpl extends VirtualVehicleEntityImpl implements dev.by1337.virtualentity.api.virtual.vehicle.VirtualAbstractMinecart {
    private static final int DEFAULT_BLOCK = NmsUtil.getCombinedId(Material.DIRT.createBlockData());
    private static final EntityDataAccessor<Integer> DATA_ID_DISPLAY_BLOCK;
    private static final EntityDataAccessor<Integer> DATA_ID_DISPLAY_OFFSET;
    private static final EntityDataAccessor<Boolean> DATA_ID_CUSTOM_DISPLAY;

    public VirtualAbstractMinecartImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_DISPLAY_BLOCK, DEFAULT_BLOCK);
        this.entityData.define(DATA_ID_DISPLAY_OFFSET, 6);
        this.entityData.define(DATA_ID_CUSTOM_DISPLAY, false);
    }

    /**
     * Получает текущий блок, который отображается на вагонетке.
     *
     * @return идентификатор блока, который отображается.
     */
    @Override
    public int getDisplayBlock() {
        return this.entityData.get(DATA_ID_DISPLAY_BLOCK);
    }

    /**
     * Устанавливает новый блок для отображения на вагонетке.
     */
    @Override
    public void setDisplayBlock(BlockData displayBlock) {
        this.entityData.set(DATA_ID_DISPLAY_BLOCK, NmsUtil.getCombinedId(displayBlock));
    }

    /**
     * Получает текущее смещение для отображаемого блока на вагонетке.
     *
     * @return смещение отображаемого блока.
     */
    @Override
    public int getDisplayOffset() {
        return this.entityData.get(DATA_ID_DISPLAY_OFFSET);
    }

    /**
     * Устанавливает новое смещение для отображаемого блока на вагонетке.
     *
     * @param offset новое смещение блока.
     */
    @Override
    public void setDisplayOffset(int offset) {
        this.entityData.set(DATA_ID_DISPLAY_OFFSET, offset);
    }

    /**
     * Проверяет, имеет ли вагонетка пользовательское отображение.
     *
     * @return {@code true}, если отображение является пользовательским, иначе {@code false}.
     */
    @Override
    public boolean hasCustomDisplay() {
        return this.entityData.get(DATA_ID_CUSTOM_DISPLAY);
    }

    /**
     * Устанавливает, должно ли отображение вагонетки быть пользовательским.
     *
     * @param customDisplay {@code true}, если нужно использовать пользовательское отображение, иначе {@code false}.
     */
    @Override
    public void setCustomDisplay(boolean customDisplay) {
        this.entityData.set(DATA_ID_CUSTOM_DISPLAY, customDisplay);
    }

    static {
        DATA_ID_DISPLAY_BLOCK = Mappings.findAccessor("AbstractMinecart", "DATA_ID_DISPLAY_BLOCK");
        DATA_ID_DISPLAY_OFFSET = Mappings.findAccessor("AbstractMinecart", "DATA_ID_DISPLAY_OFFSET");
        DATA_ID_CUSTOM_DISPLAY = Mappings.findAccessor("AbstractMinecart", "DATA_ID_CUSTOM_DISPLAY");
    }
}
