package dev.by1337.virtualentity.core.network;

import dev.by1337.virtualentity.core.nms.NmsUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.bukkit.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Packet {
    private static final Logger LOGGER = LoggerFactory.getLogger("VirtualEntityApi#Packet");
    public static boolean debug;

    public abstract void write(ByteBuf byteBuf);

    public void send(Player player) {
        Channel channel = NmsUtil.getChannel(player);
        ByteBuf byteBuf = channel.alloc().buffer();
        write(byteBuf);
        if (debug){
            LOGGER.info("Send {} bytes {} to player {}", this.getClass(), byteBuf.readableBytes(), player.getName());
        }
        channel.write(byteBuf);
    }

}
