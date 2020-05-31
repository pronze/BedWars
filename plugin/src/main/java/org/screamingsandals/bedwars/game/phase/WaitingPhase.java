package org.screamingsandals.bedwars.game.phase;

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
    public void tick() {
        super.tick();

        final var playersInGame = gameFrame.getPlayersInGame().size();
        if (playersInGame >= gameFrame.getMinPlayers()) {
            finished = true;

            gameFrame.setActiveState(GameState.PRE_GAME_COUNTDOWN);
        }
    }
}
