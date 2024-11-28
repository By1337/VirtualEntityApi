package dev.by1337.virtualentity.api.virtual;

import org.by1337.blib.util.Direction;

public interface VirtualHangingEntity extends VirtualEntity {
    void setDirection(Direction direction);
    Direction direction();
}
