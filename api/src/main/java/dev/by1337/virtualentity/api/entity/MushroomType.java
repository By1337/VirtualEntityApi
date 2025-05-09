package dev.by1337.virtualentity.api.entity;

import java.util.Map;

public enum MushroomType {
    RED("red"),
    BROWN("brown");
    public static final Map<String, MushroomType> idToType = Map.of(
            RED.type, RED,
            BROWN.type, BROWN
    );
    private static final MushroomType[] VALUES = values();
    private final String type;

    MushroomType(String param2) {
        this.type = param2;
    }
    public static MushroomType byId(String param1) {
        return idToType.get(param1);
    }
    public static MushroomType byId(int param1) {
        return VALUES[param1];
    }

    public String type() {
        return type;
    }
}
