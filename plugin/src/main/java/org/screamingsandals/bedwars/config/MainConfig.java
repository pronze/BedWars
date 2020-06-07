package org.screamingsandals.bedwars.config;

import org.screamingsandals.lib.config.DefaultConfigBuilder;
import org.screamingsandals.lib.config.SpigotConfigAdapter;

import java.io.File;

public class MainConfig extends SpigotConfigAdapter {

    public MainConfig(File configFile) {
        super(configFile);
    }

    @Override
    public void load() {
        super.load();
        loadDefaults();
    }

    public void loadDefaults() {
        DefaultConfigBuilder.start(this)
                .put(ConfigPaths.DEBUG, true) //for now, true.
                .put(ConfigPaths.PREFIX, "&7[&bS&c&lBed&f&lWars&7]&r") //big news, whoosh
                .put(ConfigPaths.LANG, "en")
                .put(ConfigPaths.VERBOSE, true)
                .put(ConfigPaths.GROOVY, false)
                .put(ConfigPaths.BUNGEE_ENABLED, false)
                .put(ConfigPaths.BUNGEE_MULTI_GAME_MODE, false)
                .put(ConfigPaths.BUNGEE_LOBBY_SERVER, "your_hub_server_name_here")
                .put(ConfigPaths.BUNGEE_RESTART_ON_GAME_END, false)
                .put(ConfigPaths.BUNGEE_REGENERATE_ON_GAME_END, true)
                .put(ConfigPaths.GAME_DEFAULT_SPECTATORS_ENABLED, true)
                .put(ConfigPaths.GAME_DEFAULT_START_TIME, 30)
                .put(ConfigPaths.GAME_DEFAULT_GAME_TIME, 3600)
                .put(ConfigPaths.GAME_DEFAULT_DEATHMATCH_TIME, 600)
                .put(ConfigPaths.GAME_DEFAULT_END_TIME, 5)
                .put(ConfigPaths.GAME_MECHANICS_AUTO_START_ENABLED, true)
                .put(ConfigPaths.GAME_MECHANICS_AUTO_START_SORT_PLAYERS_TO_TEAMS, true)
                .end();
    }
}
