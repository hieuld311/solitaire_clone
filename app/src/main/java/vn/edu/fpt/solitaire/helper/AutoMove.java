
package vn.edu.fpt.solitaire.helper;

import android.os.Bundle;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CardAndStack;
import vn.edu.fpt.solitaire.classes.HelperCardMovement;
import vn.edu.fpt.solitaire.games.Pyramid;
import vn.edu.fpt.solitaire.ui.GameManager;

import static vn.edu.fpt.solitaire.SharedData.*;

/**
 * if the last card on the tableau is flipped up, the auto complete can be run. it simply test
 * every card from the tableau and the stock if they can be placed on the foundation.
 * it continues until the last card was moved to the foundation. after that,
 * the win animation will be started
 */

public class AutoMove extends HelperCardMovement {

    private boolean testAfterMove = false;
    private boolean movedFirstCard = false;
    private boolean mainStackAlreadyFlipped = false;

    public AutoMove(GameManager gm) {
        super(gm, "AUTO_MOVE");
    }

    @Override
    public void start() {
        movedFirstCard = false;
        testAfterMove = false;
        mainStackAlreadyFlipped = false;

        super.start();
    }

    @Override
    protected void saveState(Bundle bundle) {
    }

    @Override
    protected void loadState(Bundle bundle) {
    }

    @Override
    protected boolean stopCondition() {
        return gameLogic.hasWon() || currentGame.winTest();
    }

    @Override
    protected void moveCard() {

        if (testAfterMove) {
            currentGame.testAfterMove();
            testAfterMove = false;
            nextIteration();
        } else {
            CardAndStack cardAndStack = currentGame.hintTest();

            if (cardAndStack != null) {
                mainStackAlreadyFlipped = false;
                movedFirstCard = true;
                movingCards.reset();

                //needed because in Pyramid, I save in cardTest() if cards need to move to the waste stack
                //TODO manage this in another way
                if (currentGame instanceof Pyramid) {
                    currentGame.cardTest(cardAndStack.getStack(), cardAndStack.getCard());
                }

                movingCards.add(cardAndStack.getCard(), 0, 0);
                movingCards.moveToDestination(cardAndStack.getStack());

                testAfterMove = true;
                nextIteration();
            } else if (prefs.getImproveAutoMove() && currentGame.hasMainStack()) {
                switch (currentGame.mainStackTouch()) {
                    case 0:
                        stop();
                    case 1:
                        testAfterMove = true;
                        nextIteration();
                        break;
                    case 2:
                        if (mainStackAlreadyFlipped) {
                            stop();
                        } else {
                            mainStackAlreadyFlipped = true;
                            testAfterMove = true;
                            nextIteration();
                        }
                        break;
                }
            } else {
                if (!movedFirstCard) {
                    showToast(gm.getString(R.string.dialog_no_movement_possible), gm);
                }

                stop();
            }
        }
    }
}
