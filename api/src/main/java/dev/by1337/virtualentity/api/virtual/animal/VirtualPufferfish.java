package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualPufferfish extends VirtualAbstractFish {
    int getPuffState();

    // 0 1 2
    void setPuffState(int state);

    static VirtualPufferfish create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.PUFFERFISH, VirtualPufferfish.class);
    }
}
