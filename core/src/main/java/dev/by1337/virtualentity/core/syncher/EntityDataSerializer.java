package dev.by1337.virtualentity.core.syncher;

import io.netty.buffer.ByteBuf;

public interface EntityDataSerializer<T> {
    void write(T val, ByteBuf byteBuf);
}
