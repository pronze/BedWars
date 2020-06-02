package org.screamingsandals.bedwars.game.phase;

import org.screamingsandals.lib.gamecore.core.GameFrame;
import org.screamingsandals.lib.gamecore.core.GameState;
import org.screamingsandals.lib.gamecore.core.cycle.GameCycle;
import org.screamingsandals.lib.gamecore.core.phase.GamePhase;

public class RestartPhase extends GamePhase {

    public RestartPhase(GameCycle gameCycle) {
        super(gameCycle);
    }

    @Override
    public void prepare(GameFrame gameFrame) {

    }

    @Override
    public void tick() {
        super.tick();

        gameFrame.getGameWorld().regenerate();
        gameFrame.setActiveState(GameState.WAITING);
    }
}
