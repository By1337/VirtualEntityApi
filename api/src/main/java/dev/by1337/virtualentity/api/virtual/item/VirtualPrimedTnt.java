package dev.by1337.virtualentity.api.virtual.item;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;

public interface VirtualPrimedTnt extends VirtualEntity {
    int getFuse();

    void setFuse(int fuse);
}
