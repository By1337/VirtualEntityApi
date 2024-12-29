package dev.by1337.virtualentity.api.virtual.animal.horse;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualDonkey extends VirtualAbstractChestedHorse {
    static VirtualDonkey create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.DONKEY, VirtualDonkey.class);
    }
}
