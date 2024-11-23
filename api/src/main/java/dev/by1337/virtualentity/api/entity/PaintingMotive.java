package dev.by1337.virtualentity.api.entity;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;

import java.util.EnumMap;

public enum PaintingMotive implements MappedEnum {
    KEBAB,
    AZTEC,
    ALBAN,
    AZTEC2,
    BOMB,
    PLANT,
    WASTELAND,
    POOL,
    COURBET,
    SEA,
    SUNSET,
    CREEBET,
    WANDERER,
    GRAHAM,
    MATCH,
    BUST,
    STAGE,
    VOID,
    SKULL_AND_ROSES,
    WITHER,
    FIGHTERS,
    POINTER,
    PIGSCENE,
    BURNING_SKULL,
    SKELETON,
    DONKEY_KONG,
    @SinceMinecraftVersion("1.19.4")
    EARTH,
    @SinceMinecraftVersion("1.19.4")
    FIRE,
    @SinceMinecraftVersion("1.19.4")
    WATER,
    @SinceMinecraftVersion("1.19.4")
    WIND
    ;
    private static final EnumMap<PaintingMotive, Integer> TO_ID = new EnumMap<>(PaintingMotive.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
