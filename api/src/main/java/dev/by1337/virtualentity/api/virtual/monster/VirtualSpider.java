package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualSpider extends VirtualMob {
    boolean isClimbing();

    void setClimbing(boolean flag);
}
