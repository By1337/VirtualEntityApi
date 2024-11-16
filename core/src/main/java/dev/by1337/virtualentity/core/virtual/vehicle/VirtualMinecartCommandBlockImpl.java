package dev.by1337.virtualentity.core.virtual.vehicle;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.vehicle.VirtualMinecartCommandBlock;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import net.kyori.adventure.text.Component;

public class VirtualMinecartCommandBlockImpl extends VirtualAbstractMinecartImpl implements VirtualMinecartCommandBlock {
    private static final EntityDataAccessor<String> DATA_ID_COMMAND_NAME;
    private static final EntityDataAccessor<Component> DATA_ID_LAST_OUTPUT;

    public VirtualMinecartCommandBlockImpl() {
        super(VirtualEntityType.COMMAND_BLOCK_MINECART);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_COMMAND_NAME, "");
        this.entityData.define(DATA_ID_LAST_OUTPUT, Component.text(""));
    }

    /**
     * Получает имя команды, которая установлена в командном блоке майнкарта.
     *
     * @return имя команды.
     */
    @Override
    public String getCommandName() {
        return this.entityData.get(DATA_ID_COMMAND_NAME);
    }

    /**
     * Устанавливает имя команды для командного блока майнкарта.
     *
     * @param commandName новое имя команды.
     */
    @Override
    public void setCommandName(String commandName) {
        this.entityData.set(DATA_ID_COMMAND_NAME, commandName);
    }

    /**
     * Получает последнее выведенное сообщение командного блока майнкарта.
     *
     * @return компонент с последним выводом (например, результат выполнения команды).
     */
    @Override
    public Component getLastOutput() {
        return this.entityData.get(DATA_ID_LAST_OUTPUT);
    }

    /**
     * Устанавливает последнее выведенное сообщение командного блока майнкарта.
     *
     * @param lastOutput компонент с последним выводом.
     */
    @Override
    public void setLastOutput(Component lastOutput) {
        this.entityData.set(DATA_ID_LAST_OUTPUT, lastOutput);
    }

    static {
        DATA_ID_COMMAND_NAME = Mappings.findAccessor("MinecartCommandBlock", "DATA_ID_COMMAND_NAME");
        DATA_ID_LAST_OUTPUT = Mappings.findAccessor("MinecartCommandBlock", "DATA_ID_LAST_OUTPUT");
    }
}
