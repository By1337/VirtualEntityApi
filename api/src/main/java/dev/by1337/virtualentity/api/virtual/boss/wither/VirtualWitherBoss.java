package dev.by1337.virtualentity.api.virtual.boss.wither;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualWitherBoss extends VirtualMob {
    int getInvulnerableTicks();

    void setInvulnerableTicks(int ticks);

    int getAlternativeTarget(int target);

    void setAlternativeTarget(int target, int value);

    static VirtualWitherBoss create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.WITHER, VirtualWitherBoss.class);
    }
}
