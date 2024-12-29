package dev.by1337.virtualentity.api.virtual.animal.horse;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualHorse extends VirtualAbstractHorse {
    void setTypeVariant(int typeVariant);

    int getTypeVariant();

    static VirtualLlama create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.LLAMA, VirtualLlama.class);
    }
}
