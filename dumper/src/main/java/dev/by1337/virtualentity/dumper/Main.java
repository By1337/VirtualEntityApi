package dev.by1337.virtualentity.dumper;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class Main extends JavaPlugin {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        try {
            if (args.length == 0) {
                getDataFolder().mkdirs();
                MappingsCreator.create(getDataFolder().toPath());
                sender.sendMessage("done");
            } else {
                File dump = new File(getDataFolder(), "dump");
                dump.mkdirs();
                EasyEntityDumper.dump(dump);
            }

        } catch (Throwable e) {
            getSLF4JLogger().error("Failed to create mappings file", e);
        }
        return true;
    }
}
