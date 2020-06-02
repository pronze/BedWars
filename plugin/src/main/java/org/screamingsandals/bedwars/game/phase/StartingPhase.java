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
        System.out.println(runTime);
    }

    @Override
    public void tick() {
        super.tick();

        final var scoreboardManager = gameFrame.getScoreboardManager();
        final var playersInGame = gameFrame.getPlayersInGame();
        final var playersInGameCount = playersInGame.size();

        if (playersInGameCount < gameFrame.getMinPlayers()) {
            gameFrame.setActiveState(GameState.WAITING);

            scoreboardManager.hideAll();
            playersInGame.forEach(gamePlayer -> scoreboardManager.show(gamePlayer, GameState.WAITING));
            reset();
            return;
        }

        if (firstTick) {
            scoreboardManager.hideAll();
            playersInGame.forEach(gamePlayer -> scoreboardManager.show(gamePlayer, GameState.PRE_GAME_COUNTDOWN));
        }

        if (countRemainingTime() == 0) {
            gameFrame.moveAllToTeamSpawns();
            gameFrame.setActiveState(GameState.IN_GAME);
        }
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
