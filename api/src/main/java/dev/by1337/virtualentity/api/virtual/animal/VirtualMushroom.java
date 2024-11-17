package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.entity.MushroomType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualMushroom extends VirtualAgeableMob {
    void setMushroomType(MushroomType type);

    MushroomType getMushroomType();
}
