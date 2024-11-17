package dev.by1337.virtualentity.api.entity;

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
    DONKEY_KONG;
    private static final EnumMap<PaintingMotive, Integer> TO_ID = new EnumMap<>(PaintingMotive.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
