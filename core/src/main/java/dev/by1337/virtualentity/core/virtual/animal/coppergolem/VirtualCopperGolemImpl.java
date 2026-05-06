package dev.by1337.virtualentity.core.virtual.animal.coppergolem;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.CopperGolemState;
import dev.by1337.virtualentity.api.entity.CopperWeatherState;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

@SinceMinecraftVersion("1.21.9")
public class VirtualCopperGolemImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.animal.coppergolem.VirtualCopperGolem {
    private static final EntityDataAccessor<CopperWeatherState> DATA_WEATHER_STATE;
    private static final EntityDataAccessor<CopperGolemState> COPPER_GOLEM_STATE;

    public VirtualCopperGolemImpl() {
        super(VirtualEntityType.COPPER_GOLEM);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_WEATHER_STATE, CopperWeatherState.UNAFFECTED);
        this.entityData.define(COPPER_GOLEM_STATE, CopperGolemState.IDLE);
    }
    @Override
    public CopperWeatherState getWeatherState() {
        return this.entityData.get(DATA_WEATHER_STATE);
    }
    @Override
    public CopperGolemState getGolemState() {
        return this.entityData.get(COPPER_GOLEM_STATE);
    }
    @Override
    public void setWeatherState(CopperWeatherState weatherState) {
        this.entityData.set(DATA_WEATHER_STATE, weatherState);
    }
    @Override
    public void setGolemState(CopperGolemState golemState) {
        this.entityData.set(COPPER_GOLEM_STATE, golemState);
    }

    static {
        DATA_WEATHER_STATE = Mappings.findAccessor("CopperGolem", "DATA_WEATHER_STATE");
        COPPER_GOLEM_STATE = Mappings.findAccessor("CopperGolem", "COPPER_GOLEM_STATE");
    }
}