package org.screamingsandals.bedwars.game.phase;

import org.screamingsandals.bedwars.Main;
import org.screamingsandals.bedwars.config.ConfigPaths;
import org.screamingsandals.lib.gamecore.core.GameFrame;
import org.screamingsandals.lib.gamecore.core.GameState;
import org.screamingsandals.lib.gamecore.core.cycle.GameCycle;
import org.screamingsandals.lib.gamecore.core.phase.GamePhase;

public class WaitingPhase extends GamePhase {

    //Infinity phase
    public WaitingPhase(GameCycle gameCycle) {
        super(gameCycle);
    }

    @Override
    public void prepare(GameFrame gameFrame) {
    }

    @Override
    public boolean preTick() {
        final var playersInGame = gameFrame.getPlayersInGame();
        final var playersCount = playersInGame.size();

        if (playersCount >= gameFrame.getMinPlayers()) {
            final var mainConfig = Main.getMainConfig();
            if (mainConfig.getBoolean(ConfigPaths.GAME_MECHANICS_AUTO_START_ENABLED)
                    && mainConfig.getBoolean(ConfigPaths.GAME_MECHANICS_AUTO_START_SORT_PLAYERS_TO_TEAMS)) {
                gameFrame.sortPlayersToTeams();
            }

            gameCycle.switchPhase(GameState.PRE_GAME_COUNTDOWN);
        }
        return true;
    }

    @Override
    public void tick() {
        super.tick();
    }

}
