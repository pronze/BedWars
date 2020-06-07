package org.screamingsandals.bedwars.game.phase;

import org.screamingsandals.lib.gamecore.core.GameFrame;
import org.screamingsandals.lib.gamecore.core.GameState;
import org.screamingsandals.lib.gamecore.core.cycle.GameCycle;
import org.screamingsandals.lib.gamecore.core.phase.GamePhase;

public class StartingPhase extends GamePhase {

    public StartingPhase(GameCycle gameCycle, int runTime) {
        super(gameCycle, runTime);
    }

    @Override
    public void prepare(GameFrame gameFrame) {
        gameFrame.getPlaceholderParser().add("%timeToStart%", runTime);
    }

    @Override
    public boolean preTick() {
        final var playersInGame = gameFrame.getPlayersInGame();
        final var playersInGameCount = playersInGame.size();

        if (playersInGameCount < gameFrame.getMinPlayers()) {
            gameCycle.switchPhase(GameState.WAITING);
            reset();
            return true;
        }

        if (countRemainingTime() == 0) {
            gameFrame.moveAllToTeamSpawns();
            gameCycle.switchPhase(GameState.IN_GAME);
        }

        return true;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void reset() {
        super.reset();
        updateStartTime(runTime);
    }

    @Override
    public void updatePlaceholders() {
        updateStartTime(countRemainingTime());
    }

    private void updateStartTime(int starTime) {
        gameFrame.getPlaceholderParser().add("%timeToStart%", starTime);
    }
}
