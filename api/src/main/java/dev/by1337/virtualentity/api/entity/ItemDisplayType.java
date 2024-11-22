package dev.by1337.virtualentity.api.entity;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;

import java.util.EnumMap;

@RemovedInMinecraftVersion("1.19.4")
public enum ItemDisplayType implements MappedEnum {
    NONE,
    THIRD_PERSON_LEFT_HAND,
    THIRD_PERSON_RIGHT_HAND,
    FIRST_PERSON_LEFT_HAND,
    FIRST_PERSON_RIGHT_HAND,
    HEAD,
    GUI,
    GROUND,
    FIXED,
    ;
    private static final EnumMap<ItemDisplayType, Integer> TO_ID = new EnumMap<>(ItemDisplayType.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
