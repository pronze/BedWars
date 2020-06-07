package org.screamingsandals.bedwars.game.phase;

import org.screamingsandals.lib.gamecore.core.GameFrame;
import org.screamingsandals.lib.gamecore.core.GameState;
import org.screamingsandals.lib.gamecore.core.cycle.GameCycle;
import org.screamingsandals.lib.gamecore.core.phase.GamePhase;

public class InGamePhase extends GamePhase {

    public InGamePhase(GameCycle gameCycle, int runTime) {
        super(gameCycle, runTime);
    }

    @Override
    public void prepare(GameFrame gameFrame) {
        updateRemainingTime(countRemainingTime());
    }

    @Override
    public boolean preTick() {
        if (gameFrame.getPlayersInGame().size() < gameFrame.getMinPlayers()) {
            gameCycle.kickAllPlayers();

            gameCycle.switchPhase(GameState.RESTART);
            return true;
        }
        return true;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void updatePlaceholders() {
        updateRemainingTime(countRemainingTime());
    }

    private void updateRemainingTime(int time) {
        gameFrame.getPlaceholderParser().add("%remainingTime%", time);
    }
}
