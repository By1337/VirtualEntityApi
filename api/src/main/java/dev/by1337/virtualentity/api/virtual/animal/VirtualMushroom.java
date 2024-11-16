package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.entity.MushroomType;
import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;

public interface VirtualMushroom extends VirtualAgableMob {
    void setMushroomType(MushroomType type);

    MushroomType getMushroomType();
}
