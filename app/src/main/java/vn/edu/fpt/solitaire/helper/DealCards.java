
package vn.edu.fpt.solitaire.helper;

import android.os.Bundle;

import vn.edu.fpt.solitaire.classes.HelperCardMovement;
import vn.edu.fpt.solitaire.ui.GameManager;

import static vn.edu.fpt.solitaire.SharedData.*;

public class DealCards extends HelperCardMovement {

    private int phase = 1;

    public DealCards(GameManager gm) {
        super(gm, "DEAL_CARDS");
    }

    public void start() {
        phase = 1;
        super.start();
    }

    @Override
    protected void saveState(Bundle bundle) {
        bundle.putInt("BUNDLE_DEAL_CARDS_PHASE", phase);
    }

    @Override
    protected void loadState(Bundle bundle) {
        phase = bundle.getInt("BUNDLE_DEAL_CARDS_PHASE");
    }

    @Override
    protected void moveCard() {
        switch (phase) {
            case 1:
                currentGame.dealNewGame();
                sounds.playSound(Sounds.names.DEAL_CARDS);
                phase = 2;
                nextIteration();
                break;
            case 2:
            default:
                handlerTestAfterMove.sendNow();
                stop();
                break;
        }
    }
}
