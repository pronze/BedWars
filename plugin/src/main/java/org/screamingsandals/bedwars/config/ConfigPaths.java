package org.screamingsandals.bedwars.config;

public interface ConfigPaths {
    String DEBUG = "debug";
    String PREFIX = "prefix";
    String LANG = "language";
    String VERBOSE = "verbose-to-admins";
    String GROOVY = "use-groovy-for-shops";

    String BUNGEE_ENABLED = "bungeecord.enabled";
    String BUNGEE_MULTI_GAME_MODE = "bungeecord.multi-game-mode";
    String BUNGEE_LOBBY_SERVER = "bungeecord.lobby-server-name";
    String BUNGEE_REGENERATE_ON_GAME_END = "bungeecord.regenerate-map-after-game-end";
    String BUNGEE_RESTART_ON_GAME_END = "bungeecord.restart-server-after-game-end";

    String GAME_DEFAULT_SPECTATORS_ENABLED = "game.shared-values.enabled.spectators";
    String GAME_DEFAULT_START_TIME = "game.shared-values.time.starting";
    String GAME_DEFAULT_GAME_TIME = "game.shared-values.time.game";
    String GAME_DEFAULT_DEATHMATCH_TIME = "game.shared-values.time.deathmatch";
    String GAME_DEFAULT_END_TIME = "game.shared-values.time.ending";

    String GAME_MECHANICS_AUTO_START_ENABLED = "game.mechanics.auto-start.enabled";
    String GAME_MECHANICS_AUTO_START_SORT_PLAYERS_TO_TEAMS = "game.mechanics.auto-start.sort-players-to-teams-when-enough-joined";
    String GAME_MECHANICS_AUTO_START_FORCE_SORT_PLAYERS = "game.mechanics.auto-start.force-sort-players-to-teams"; //do we need this?

    interface GUI {
        String CUSTOM_ENABLED = "enabled";
    }
}
