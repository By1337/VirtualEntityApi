package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

@SinceMinecraftVersion("1.19.4")
public class VirtualInteractionImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.VirtualInteraction {
    private static final EntityDataAccessor<Float> DATA_WIDTH_ID;
    private static final EntityDataAccessor<Float> DATA_HEIGHT_ID;
    private static final EntityDataAccessor<Boolean> DATA_RESPONSE_ID;

    public VirtualInteractionImpl() {
        super(VirtualEntityType.INTERACTION);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_WIDTH_ID, 1.0F);
        this.entityData.define(DATA_HEIGHT_ID, 1.0F);
        this.entityData.define(DATA_RESPONSE_ID, false);
    }

    @Override
    public float getWidth() {
        return this.entityData.get(DATA_WIDTH_ID);
    }

    @Override
    public void setWidth(float width) {
        this.entityData.set(DATA_WIDTH_ID, width);
    }

    @Override
    public float getHeight() {
        return this.entityData.get(DATA_HEIGHT_ID);
    }

    @Override
    public void setHeight(float height) {
        this.entityData.set(DATA_HEIGHT_ID, height);
    }

    @Override
    public boolean hasResponse() {
        return this.entityData.get(DATA_RESPONSE_ID);
    }

    @Override
    public void setResponse(boolean response) {
        this.entityData.set(DATA_RESPONSE_ID, response);
    }


    static {
        DATA_WIDTH_ID = Mappings.findAccessor("Interaction", "DATA_WIDTH_ID");
        DATA_HEIGHT_ID = Mappings.findAccessor("Interaction", "DATA_HEIGHT_ID");
        DATA_RESPONSE_ID = Mappings.findAccessor("Interaction", "DATA_RESPONSE_ID");
    }
}