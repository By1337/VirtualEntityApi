package dev.by1337.virtualentity.core.virtual.decoration;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;

public class VirtualPaintingImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.decoration.VirtualPainting {

    public VirtualPaintingImpl() {
        super(VirtualEntityType.PAINTING);
    }
}
