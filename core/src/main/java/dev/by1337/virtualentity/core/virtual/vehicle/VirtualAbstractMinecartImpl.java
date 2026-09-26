package dev.by1337.virtualentity.core.virtual.vehicle;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.nms.NmsUtil;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class VirtualAbstractMinecartImpl extends VirtualVehicleEntityImpl implements dev.by1337.virtualentity.api.virtual.vehicle.VirtualAbstractMinecart {
    private static final int DEFAULT_BLOCK = NmsUtil.getCombinedId(Material.AIR.createBlockData());

    private static final EntityDataAccessor<Optional<BlockData>> DATA_ID_CUSTOM_DISPLAY_BLOCK;
    private static final EntityDataAccessor<Integer> DATA_ID_DISPLAY_OFFSET;

    public VirtualAbstractMinecartImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_ID_CUSTOM_DISPLAY_BLOCK, Optional.empty());
        entityData.define(DATA_ID_DISPLAY_OFFSET, 6);
    }

    public void setCustomDisplayBlock(@Nullable BlockData data) {
        entityData.set(DATA_ID_CUSTOM_DISPLAY_BLOCK, Optional.ofNullable(data));
    }

    public Optional<BlockData> getCustomDisplayBlock() {
        return entityData.get(DATA_ID_CUSTOM_DISPLAY_BLOCK);
    }

    @Override
    public int getDisplayBlock() {
        return getCustomDisplayBlock().map(NmsUtil::getCombinedId).orElse(DEFAULT_BLOCK);
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
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    public boolean hasCustomDisplay() {
        return getCustomDisplayBlock().isPresent();
    }

    /**
     * Устанавливает, должно ли отображение вагонетки быть пользовательским.
     *
     * @param customDisplay {@code true}, если нужно использовать пользовательское отображение, иначе {@code false}.
     */
    @Override
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    public void setCustomDisplay(boolean customDisplay) {
        if (!customDisplay) setCustomDisplayBlock(null);
    }

    static {
        DATA_ID_CUSTOM_DISPLAY_BLOCK = Mappings.findAccessor("AbstractMinecart", "DATA_ID_CUSTOM_DISPLAY_BLOCK");

        DATA_ID_DISPLAY_OFFSET = Mappings.findAccessor("AbstractMinecart", "DATA_ID_DISPLAY_OFFSET");

    }
}
