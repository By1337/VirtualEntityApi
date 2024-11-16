package dev.by1337.virtualentity.core.network;

import dev.by1337.virtualentity.core.nms.NmsUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bukkit.entity.Player;

public abstract class Packet {
    protected abstract void write(ByteBuf byteBuf);

    public void send(Player player) {
        ByteBuf byteBuf = Unpooled.buffer();
        try {
            write(byteBuf);
            NmsUtil.send(player, byteBuf);
        } finally {
            byteBuf.release();
        }
    }
}
