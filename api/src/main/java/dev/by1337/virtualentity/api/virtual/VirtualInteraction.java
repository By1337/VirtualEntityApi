package dev.by1337.virtualentity.api.virtual;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;

@SinceMinecraftVersion("1.19.4")
public interface VirtualInteraction extends VirtualEntity {
    float getWidth();

    void setWidth(float width);

    float getHeight();

    void setHeight(float height);

    boolean hasResponse();

    void setResponse(boolean response);
}
