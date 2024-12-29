package dev.by1337.virtualentity.api.virtual.animal.horse;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualTraderLlama extends VirtualLlama {
    static VirtualTraderLlama create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.TRADER_LLAMA, VirtualTraderLlama.class);
    }
}
