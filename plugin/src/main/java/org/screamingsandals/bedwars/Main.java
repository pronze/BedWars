package org.screamingsandals.bedwars;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.screamingsandals.bedwars.api.Permissions;
import org.screamingsandals.bedwars.commands.BedWarsCommand;
import org.screamingsandals.bedwars.commands.CommandsLanguage;
import org.screamingsandals.bedwars.config.MainConfig;
import org.screamingsandals.bedwars.config.VisualsConfig;
import org.screamingsandals.bedwars.game.Game;
import org.screamingsandals.bedwars.listeners.PlayerCoreListener;
import org.screamingsandals.lib.commands.Commands;
import org.screamingsandals.lib.config.ConfigAdapter;
import org.screamingsandals.lib.debug.Debug;
import org.screamingsandals.lib.gamecore.GameCore;
import org.screamingsandals.lib.gamecore.core.GameManager;
import org.screamingsandals.lib.gamecore.core.config.GameConfig;
import org.screamingsandals.lib.gamecore.core.config.GameValue;
import org.screamingsandals.lib.gamecore.core.cycle.GameCycleType;
import org.screamingsandals.lib.gamecore.exceptions.GameCoreException;
import org.screamingsandals.lib.gamecore.language.GameLanguage;

import java.io.File;
import java.util.Collection;

public class Main extends JavaPlugin {
    private static Main instance;
    private MainConfig mainConfig;
    private VisualsConfig visualsConfig;
    @Getter
    private GameCore gameCore;
    private GameManager<Game> gameManager;
    @Getter
    private GameLanguage language;
    private Commands commands;

    @Getter
    private File shopFile;
    @Getter
    private File upgradesFile;
    @Getter
    private boolean bungee;

    @Override
    @SuppressWarnings("unchecked")
    public void onEnable() {
        Debug.setFallbackName("[SBedWars] ");
        instance = this;
        try {
            mainConfig = new MainConfig(ConfigAdapter.createFile(getDataFolder(), "config.yml"));
            mainConfig.load();
            bungee = mainConfig.getBoolean(MainConfig.ConfigPaths.BUNGEE_ENABLED);

            visualsConfig = new VisualsConfig(ConfigAdapter.createFile(getDataFolder(), "visuals.yml"));
            visualsConfig.load();

            var shopFileName = "shop.yml";
            var upgradesFileName = "upgrades.yml";

            if (mainConfig.getBoolean(MainConfig.ConfigPaths.GROOVY)) {
                shopFileName = "shop.groovy";
                upgradesFileName = "upgrades.groovy";
            }

            shopFile = checkIfExistsOrCopyDefault(getDataFolder(), shopFileName);
            upgradesFile = checkIfExistsOrCopyDefault(getDataFolder(), upgradesFileName);

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        language = new GameLanguage(this, mainConfig.getString("language"), mainConfig.getString("prefix"));

        Debug.init(getName());
        Debug.setDebug(mainConfig.getBoolean("debug"));

        commands = new Commands(this);
        commands.load();
        commands.setCommandLanguage(new CommandsLanguage());

        //Make sure that we destroy existing instance of the  core, fucking reloads
        if (gameCore != null) {
            gameCore.destroy();
            Debug.info("GameCore already exists, destroying and loading new!");
        }

        gameCore = new GameCore(this, BedWarsCommand.COMMAND_NAME, Permissions.ADMIN_COMMAND, mainConfig.getBoolean(MainConfig.ConfigPaths.VERBOSE));
        gameCore.setVisualsConfig(visualsConfig);

        try {
            commands.loadScreamingCommands(gameCore.getClass());
        } catch (Throwable ignored) {
        }

        try {
            gameCore.load(new File(getDataFolder(), "games"), Game.class, getGameType());
        } catch (GameCoreException e) {
            Debug.info("This is some way of fuck up.. Please report that to our GitHub or Discord!", true);
            e.printStackTrace();
            return;
        }

        gameManager = (GameManager<Game>) GameCore.getGameManager();
        loadDefaultConfigForGames();
        gameManager.loadGames();

        //Beware of plugin reloading..
        final Collection<Player> onlinePlayers = (Collection<Player>) Bukkit.getOnlinePlayers();
        if (onlinePlayers.size() > 0) {
            onlinePlayers.forEach(player -> GameCore.getPlayerManager().registerPlayer(player));
            System.out.println(GameCore.getPlayerManager().getRegisteredPlayers());
        }

        registerListeners();

        Debug.info("&e------------ &aEverything is loaded! :) &e------------");
    }

    @Override
    public void onDisable() {
        //TODO: use paper's getServer().isStopping(); to see if we are reloading
        gameCore.destroy();
        commands.destroy();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerCoreListener(), this);
    }

    private File checkIfExistsOrCopyDefault(File folder, String fileName) {
        final var file = new File(folder, fileName);
        if (file.exists()) {
            return file;
        } else {
            saveResource(fileName, false);
        }

        return file;
    }

    private GameCycleType getGameType() {
        if (bungee) {
            if (mainConfig.getBoolean(MainConfig.ConfigPaths.BUNGEE_MULTI_GAME_MODE)) {
                return GameCycleType.MULTI_GAME_BUNGEE;
            }
            return GameCycleType.SINGLE_GAME_BUNGEE;
        } else {
            return GameCycleType.MULTI_GAME;
        }
    }

    private void loadDefaultConfigForGames() {
        final var gameConfig = gameManager.getGameConfig();
        //Not configurable by user
        gameConfig.registerValue(GameConfig.ValueHolder.get(
                GameConfig.DefaultKeys.TEAMS_ENABLED, true, GameValue.DEFAULT));
        gameConfig.registerValue(GameConfig.ValueHolder.get(
                GameConfig.DefaultKeys.STORES_ENABLED, true, GameValue.DEFAULT));

        //Loaded from config
        gameConfig.registerValue(GameConfig.ValueHolder.get(
                GameConfig.DefaultKeys.START_TIME, mainConfig.getInt(MainConfig.ConfigPaths.GAME_DEFAULT_START_TIME), GameValue.SHARED));
        gameConfig.registerValue(GameConfig.ValueHolder.get(
                GameConfig.DefaultKeys.GAME_TIME, mainConfig.getInt(MainConfig.ConfigPaths.GAME_DEFAULT_GAME_TIME), GameValue.SHARED));
        gameConfig.registerValue(GameConfig.ValueHolder.get(
                GameConfig.DefaultKeys.DEATHMATCH_TIME, mainConfig.getInt(MainConfig.ConfigPaths.GAME_DEFAULT_DEATHMATCH_TIME), GameValue.SHARED));
        gameConfig.registerValue(GameConfig.ValueHolder.get(
                GameConfig.DefaultKeys.END_GAME_TIME, mainConfig.getInt(MainConfig.ConfigPaths.GAME_DEFAULT_END_TIME), GameValue.SHARED));
    }

    public static Main getInstance() {
        return instance;
    }

    public static GameManager<Game> getGameManager() {
        return instance.gameManager;
    }

    public static MainConfig getMainConfig() {
        return instance.mainConfig;
    }

    public static VisualsConfig getVisualsConfig() {
        return instance.visualsConfig;
    }
}
