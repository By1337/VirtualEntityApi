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
    WIND,

    @SinceMinecraftVersion("1.21")
    BACKYARD,
    @SinceMinecraftVersion("1.21")
    BAROQUE,
    @SinceMinecraftVersion("1.21")
    BOUQUET,
    @SinceMinecraftVersion("1.21")
    CAVEBIRD,
    @SinceMinecraftVersion("1.21")
    CHANGING,
    @SinceMinecraftVersion("1.21")
    COTAN,
    @SinceMinecraftVersion("1.21")
    ENDBOSS,
    @SinceMinecraftVersion("1.21")
    FERN,
    @SinceMinecraftVersion("1.21")
    FINDING,
    @SinceMinecraftVersion("1.21")
    HUMBLE,
    @SinceMinecraftVersion("1.21")
    LOWMIST,
    @SinceMinecraftVersion("1.21")
    MEDITATIVE,
    @SinceMinecraftVersion("1.21")
    ORB,
    @SinceMinecraftVersion("1.21")
    OWLEMONS,
    @SinceMinecraftVersion("1.21")
    PASSAGE,
    @SinceMinecraftVersion("1.21")
    POND,
    @SinceMinecraftVersion("1.21")
    PRAIRIE_RIDE,
    @SinceMinecraftVersion("1.21")
    SUNFLOWERS,
    @SinceMinecraftVersion("1.21")
    TIDES,
    @SinceMinecraftVersion("1.21")
    UNPACKED,
    @SinceMinecraftVersion("1.21.7")
    DENNIS,
    ;

    private static final EnumMap<PaintingMotive, Integer> TO_ID = new EnumMap<>(PaintingMotive.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }


}
