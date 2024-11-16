package dev.by1337.virtualentity.api.virtual.vehicle;

import net.kyori.adventure.text.Component;

public interface VirtualMinecartCommandBlock extends VirtualAbstractMinecart {
    String getCommandName();

    void setCommandName(String commandName);

    Component getLastOutput();

    void setLastOutput(Component lastOutput);
}
