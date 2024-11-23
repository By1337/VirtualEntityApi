package dev.by1337.virtualentity.api.virtual.animal.frog;

import dev.by1337.virtualentity.api.entity.FrogVariant;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

import java.util.OptionalInt;

public interface VirtualFrog extends VirtualAgeableMob {
    void setVariantId(FrogVariant variant);

    FrogVariant getVariantId();

    void setTargetId(int targetId);

    OptionalInt getTargetId();
}
