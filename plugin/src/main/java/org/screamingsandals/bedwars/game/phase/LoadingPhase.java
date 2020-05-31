package org.screamingsandals.bedwars.game.phase;

import org.screamingsandals.lib.gamecore.core.GameFrame;
import org.screamingsandals.lib.gamecore.core.GameState;
import org.screamingsandals.lib.gamecore.core.cycle.GameCycle;
import org.screamingsandals.lib.gamecore.core.phase.GamePhase;

public class LoadingPhase extends GamePhase {

    //One tick long phase
    public LoadingPhase(GameCycle gameCycle) {
        super(gameCycle);
        oneTick = true;
    }

    @Override
    public void prepare(GameFrame gameFrame) {
        getGameFrame().setActiveState(GameState.WAITING);
    }

    @Override
    public void tick() {
        super.tick();
    }
}
