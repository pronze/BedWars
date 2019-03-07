package misat11.bw.api;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface Game {
	public String getName();
	
	public GameStatus getStatus();
	
	// Activate and deactivate arena
	
	public void start();
	
	public void stop();
	
	default boolean isActivated() {
		return getStatus() != GameStatus.DISABLED;
	}
	
	// PLAYER MANAGEMENT
	
	public void joinToGame(Player player);
	
	public void leaveFromGame(Player player);
	
	public void selectPlayerTeam(Player player, Team team);
	
	// INGAME
	
	public World getGameWorld();
	
	public Location getPos1();
	
	public Location getPos2();
	
	public Location getSpectatorSpawn();
	
	public int getGameTime();
	
	public int getMinPlayers();
	
	public int getMaxPlayers();
	
	public int countConnectedPlayers();
	
	public List<Player> getConnectedPlayers();
	
	public List<GameStore> getGameStores();
	
	public int countGameStores();
	
	public List<Team> getAvailableTeams();
	
	public int countAvailableTeams();
	
	public List<RunningTeam> getRunningTeams();
	
	public int countRunningTeams();
	
	public RunningTeam getTeamOfPlayer(Player player);
	
	public boolean isPlayerInAnyTeam(Player player);
	
	public boolean isPlayerInTeam(Player player, RunningTeam team);
	
	public boolean isLocationInArena(Location location);
	
	public boolean isBlockAddedDuringGame(Location location);
	
	// LOBBY
	
	public World getLobbyWorld();
	
	public Location getLobbySpawn();
	
	public int getLobbyCountdown();
	
	public int countTeamChests();
	
	public int countTeamChests(RunningTeam team);
	
	public RunningTeam getTeamOfChest(Location location);
	
	public RunningTeam getTeamOfChest(Block block);
}