package org.screamingsandals.bedwars.game.phase;

import org.screamingsandals.lib.gamecore.core.GameFrame;
import org.screamingsandals.lib.gamecore.core.cycle.GameCycle;
import org.screamingsandals.lib.gamecore.core.phase.GamePhase;

public class InGamePhase extends GamePhase {
    private int remainingTime;

    public InGamePhase(GameCycle gameCycle, int runTime) {
        super(gameCycle, runTime);
    }

    @Override
    public void prepare(GameFrame gameFrame) {
        updateRemainingTime(runTime);
    }

    @Override
    public void tick() {
        super.tick();

        if (gameFrame.getPlayersInGame().size() < gameFrame.getMinPlayers()) {
            //TODO: destroy
        }
    }

    @Override
    public void updatePlaceholders() {
        updateRemainingTime(countRemainingTime());
    }

    private void updateRemainingTime(int time) {
        gameFrame.getPlaceholderParser().add("%remainingTime%", time);
    }
}
