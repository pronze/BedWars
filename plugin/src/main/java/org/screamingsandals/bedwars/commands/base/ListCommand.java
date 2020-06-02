package org.screamingsandals.bedwars.commands.base;

import org.bukkit.command.CommandSender;
import org.screamingsandals.bedwars.api.Permissions;
import org.screamingsandals.bedwars.commands.BedWarsCommand;
import org.screamingsandals.lib.commands.common.RegisterCommand;
import org.screamingsandals.lib.commands.common.SubCommandBuilder;
import org.screamingsandals.lib.commands.common.interfaces.ScreamingCommand;
import org.screamingsandals.lib.gamecore.GameCore;
import org.screamingsandals.lib.gamecore.utils.GameUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.screamingsandals.lib.gamecore.language.GameLanguage.mpr;

@RegisterCommand(subCommand = true)
public class ListCommand implements ScreamingCommand {

    @Override
    public void register() {
        SubCommandBuilder.bukkitSubCommand().createSubCommand(BedWarsCommand.COMMAND_NAME, "list", Permissions.BASE_COMMAND_LIST, Collections.emptyList())
                .handleSubPlayerCommand(this::handle)
                .handleSubConsoleCommand(this::handle);
    }

    private void handle(CommandSender commandSender, List<String> args) {
        final var gameManager = GameCore.getGameManager();
        final Collection<String> names = gameManager.getRegisteredGamesNames();
        final int size = names.size();
        if (size > 0) {
            mpr("commands.base.list.arenas-list-header")
                    .replace("%count%", size)
                    .send(commandSender);
            names.forEach(name -> {
                final var registeredGame = gameManager.getRegisteredGame(name);
                if (registeredGame.isEmpty()) {
                    return;
                }

                final var game = registeredGame.get();

                mpr("commands.base.list.arenas-list-text")
                        .game(game)
                        .replace("%gameState%", GameUtils.getTranslatedGameState(game.getActiveState()))
                        .send(commandSender);
            });

            mpr("commands.base.list.arenas-list-footer")
                    .send(commandSender);

        } else {
            mpr("commands.base.list.no_arenas_found").send(commandSender);
        }
    }
}
