package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualTropicalFish extends VirtualAbstractFish {
    void setVariant(int variant);

    int getVariant();

    static VirtualTropicalFish create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.TROPICAL_FISH, VirtualTropicalFish.class);
    }
}
