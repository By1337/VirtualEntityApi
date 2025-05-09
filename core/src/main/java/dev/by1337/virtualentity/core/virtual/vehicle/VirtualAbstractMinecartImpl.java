package dev.by1337.virtualentity.core.virtual.vehicle;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.nms.NmsUtil;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.by1337.blib.util.Version;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class VirtualAbstractMinecartImpl extends VirtualVehicleEntityImpl implements dev.by1337.virtualentity.api.virtual.vehicle.VirtualAbstractMinecart {
    private static final int DEFAULT_BLOCK = NmsUtil.getCombinedId(Material.AIR.createBlockData());
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    private static final EntityDataAccessor<Integer> DATA_ID_DISPLAY_BLOCK;
    @SinceMinecraftVersion("1.21.5")
    private static final EntityDataAccessor<Optional<BlockData>> DATA_ID_CUSTOM_DISPLAY_BLOCK;
    private static final EntityDataAccessor<Integer> DATA_ID_DISPLAY_OFFSET;
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    private static final EntityDataAccessor<Boolean> DATA_ID_CUSTOM_DISPLAY;

    public VirtualAbstractMinecartImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        if (DATA_ID_CUSTOM_DISPLAY_BLOCK != null) {
            entityData.define(DATA_ID_CUSTOM_DISPLAY_BLOCK, Optional.empty());
        } else {
            this.entityData.define(DATA_ID_DISPLAY_BLOCK, DEFAULT_BLOCK);
            this.entityData.define(DATA_ID_CUSTOM_DISPLAY, false);
        }
        this.entityData.define(DATA_ID_DISPLAY_OFFSET, 6);
    }

    public void setCustomDisplayBlock(@Nullable BlockData data) {
        if (DATA_ID_DISPLAY_BLOCK != null) {
            entityData.set(DATA_ID_DISPLAY_BLOCK, data == null ? DEFAULT_BLOCK : NmsUtil.getCombinedId(data));
        } else {
            this.entityData.set(DATA_ID_CUSTOM_DISPLAY_BLOCK, Optional.ofNullable(data));
        }
    }

    public Optional<BlockData> getCustomDisplayBlock() {
        if (DATA_ID_DISPLAY_BLOCK != null) {
            return Optional.empty();
        }
        return entityData.get(DATA_ID_CUSTOM_DISPLAY_BLOCK);
    }

    @Override
    public int getDisplayBlock() {
        if (DATA_ID_DISPLAY_BLOCK != null) {
            return this.entityData.get(DATA_ID_DISPLAY_BLOCK);
        } else {
            var opt = getCustomDisplayBlock();
            return opt.map(NmsUtil::getCombinedId).orElse(DEFAULT_BLOCK);
        }
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
        if (DATA_ID_CUSTOM_DISPLAY == null) return false;
        return this.entityData.get(DATA_ID_CUSTOM_DISPLAY);
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
        if (DATA_ID_CUSTOM_DISPLAY == null) return;
        this.entityData.set(DATA_ID_CUSTOM_DISPLAY, customDisplay);
    }

    static {
        if (Version.is1_21_5orNewer()) {
            DATA_ID_CUSTOM_DISPLAY_BLOCK = Mappings.findAccessor("AbstractMinecart", "DATA_ID_CUSTOM_DISPLAY_BLOCK");
            DATA_ID_DISPLAY_BLOCK = null;
            DATA_ID_CUSTOM_DISPLAY = null;
        } else {
            DATA_ID_DISPLAY_BLOCK = Mappings.findAccessor("AbstractMinecart", "DATA_ID_DISPLAY_BLOCK");
            DATA_ID_CUSTOM_DISPLAY = Mappings.findAccessor("AbstractMinecart", "DATA_ID_CUSTOM_DISPLAY");
            DATA_ID_CUSTOM_DISPLAY_BLOCK = null;
        }

        DATA_ID_DISPLAY_OFFSET = Mappings.findAccessor("AbstractMinecart", "DATA_ID_DISPLAY_OFFSET");

    }
}
