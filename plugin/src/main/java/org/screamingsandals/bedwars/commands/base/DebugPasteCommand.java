package org.screamingsandals.bedwars.commands.base;

import org.bukkit.command.CommandSender;
import org.screamingsandals.bedwars.Main;
import org.screamingsandals.bedwars.api.Permissions;
import org.screamingsandals.bedwars.commands.BedWarsCommand;
import org.screamingsandals.lib.commands.common.RegisterCommand;
import org.screamingsandals.lib.commands.common.SubCommandBuilder;
import org.screamingsandals.lib.commands.common.interfaces.ScreamingCommand;

import java.util.Collections;
import java.util.List;

@RegisterCommand
public class DebugPasteCommand implements ScreamingCommand {

    @Override
    public void register() {
        SubCommandBuilder.bukkitSubCommand().createSubCommand(BedWarsCommand.COMMAND_NAME, "debugpaste", Permissions.BASE_COMMAND_DEBUG_PASTE, Collections.emptyList())
                .handleSubPlayerCommand(this::handle)
                .handleSubConsoleCommand(this::handle);
    }

    public void handle(CommandSender sender, List<String> args) {
        final var dataFolder = Main.getInstance().getDataFolder();

        if (!dataFolder.exists()) {
            return;
        }
    }
}
