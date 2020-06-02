package org.screamingsandals.bedwars.commands;

import static org.screamingsandals.lib.gamecore.language.GameLanguage.mpr;

public class CommandsLanguage extends org.screamingsandals.lib.commands.common.language.CommandLanguage {

    public CommandsLanguage() {
        super();
        load();
    }

    private void load() {
        put(Key.NO_PERMISSIONS, mpr("commands.errors.no-permissions").get());
        put(Key.COMMAND_DOES_NOT_EXISTS, mpr("commands.errors.command-does-not-exists").get());
        put(Key.SOMETHINGS_FUCKED, mpr("commands.errors.somethings-fucked").get());
        put(Key.NOT_FOR_CONSOLE, mpr("commands.errors.not-for-console").get());
    }
}
