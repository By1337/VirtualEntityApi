package dev.by1337.virtualentity.api.virtual.boss.enderdragon;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.EnderDragonPhase;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualEnderDragon extends VirtualMob {
    EnderDragonPhase getPhase();

    void setPhase(EnderDragonPhase phase);

    static VirtualEnderDragon create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ENDER_DRAGON, VirtualEnderDragon.class);
    }
}
