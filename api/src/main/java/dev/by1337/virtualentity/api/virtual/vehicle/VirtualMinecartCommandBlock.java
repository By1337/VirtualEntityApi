package dev.by1337.virtualentity.api.virtual.vehicle;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import net.kyori.adventure.text.Component;

public interface VirtualMinecartCommandBlock extends VirtualAbstractMinecart {
    String getCommandName();

    void setCommandName(String commandName);

    Component getLastOutput();

    void setLastOutput(Component lastOutput);

    static VirtualMinecartCommandBlock create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.COMMAND_BLOCK_MINECART, VirtualMinecartCommandBlock.class);
    }
}
