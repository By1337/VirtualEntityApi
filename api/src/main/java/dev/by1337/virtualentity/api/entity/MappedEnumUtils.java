package dev.by1337.virtualentity.api.entity;

import org.by1337.blib.util.Version;

import java.util.Map;

public class MappedEnumUtils {

    public static <T extends Enum<T>> int getId(T value, Map<T, Integer> map) {
        Integer id = map.get(value);
        if (id == null){
            throw new IllegalStateException("Unable to serialize value "+ value + " on version " + Version.VERSION);
        }
        return id;
    }
}
