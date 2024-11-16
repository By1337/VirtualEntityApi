package dev.by1337.virtualentity.api;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import org.by1337.blib.text.MessageFormatter;
import org.by1337.blib.util.Version;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class VirtualEntityFactory {
    private final Map<VirtualEntityType, VirtualEntityCreator> entityCreatorMap = new EnumMap<>(VirtualEntityType.class);

    public void register(VirtualEntityType type, Supplier<VirtualEntity> creator, Version supportedVersion) {
        if (entityCreatorMap.containsKey(type)) {
            throw new IllegalStateException(MessageFormatter.apply("The creator of the {} entity is already registered!", type));
        }
        entityCreatorMap.put(type, new VirtualEntityCreator(creator, supportedVersion));
    }

    @SuppressWarnings("unchecked")
    public <T extends VirtualEntity> T create(VirtualEntityType type, Class<T> c) {
        return (T) create(type);
    }

    public VirtualEntity create(VirtualEntityType type) {
        var creator = entityCreatorMap.get(type);
        if (creator == null) {
            throw new IllegalStateException(MessageFormatter.apply("The creator of the {} entity isn't registered!", type));
        }
        if (Version.VERSION.olderThan(creator.supportedVersion)) {
            throw new IllegalStateException(MessageFormatter.apply("Entity {} is not supported on version {}, must be version >= {}", type, Version.VERSION, creator.supportedVersion));
        }
        return creator.creator.get();
    }

    public record VirtualEntityCreator(Supplier<VirtualEntity> creator, Version supportedVersion) {
    }
}
