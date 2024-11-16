package dev.by1337.virtualentity.api;

public class VirtualEntityApi {
    private static final VirtualEntityFactory FACTORY = new VirtualEntityFactory();

    public static VirtualEntityFactory getFactory() {
        return FACTORY;
    }
}
