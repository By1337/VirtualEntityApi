package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualParrot extends VirtualTamableAnimal {
    int getVariant();

    void setVariant(int variant);

    static VirtualParrot create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.PARROT, VirtualParrot.class);
    }
}
