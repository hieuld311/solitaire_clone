
package vn.edu.fpt.solitaire.helper;

import android.os.Bundle;

import java.util.ArrayList;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.Card;
import vn.edu.fpt.solitaire.classes.CardAndStack;
import vn.edu.fpt.solitaire.classes.HelperCardMovement;
import vn.edu.fpt.solitaire.classes.Stack;
import vn.edu.fpt.solitaire.ui.GameManager;

import static vn.edu.fpt.solitaire.SharedData.*;

/**
 * Shows hints. It has a handler which shows up to MAX_NUMBER_OF_HINTS hints.
 * The hint function tests the tableau and stock if a card can be moved. If so,
 * the hint animation will be started and the card will be marked, so it won't be shown again
 * as a hint
 */

public class Hint extends HelperCardMovement {

    private static final int MAX_NUMBER_OF_HINTS = 3;                       //max number of hints which are shown when pressing the button

    private int counter = 0;                                                //counter to know how many hints were shown
    private boolean showedFirstHint = false;
    private ArrayList<Card> visited = new ArrayList<>(MAX_NUMBER_OF_HINTS); //array for already shown cards in hint

    public Hint(GameManager gm) {
        super(gm, "HINT");
    }

    @Override
    public void start() {
        showedFirstHint = false;
        visited.clear();
        counter = 0;

        super.start();
    }

    @Override
    protected void saveState(Bundle bundle) {
        bundle.putInt("BUNDLE_HINT_COUNTER", counter);

        ArrayList<Integer> list = new ArrayList<>(visited.size());

        for (Card card : visited) {
            list.add(card.getId());
        }

        bundle.putIntegerArrayList("BUNDLE_HINT_VISITED", list);
    }

    @Override
    protected void loadState(Bundle bundle) {
        counter = bundle.getInt("BUNDLE_HINT_COUNTER");
        ArrayList<Integer> list = bundle.getIntegerArrayList("BUNDLE_HINT_VISITED");

        visited.clear();

        if (list != null) {
            for (Integer i : list) {
                visited.add(cards[i]);
            }
        }
    }

    /**
     * moves a card with the hint animation. It will also be marked as visited, so the card
     * won't be used in the next step. It gets one card and the stack destination, but it
     * also adds all cards above.
     */
    private void makeHint(Card card, Stack destination) {
        Stack origin = card.getStack();
        int index = origin.getIndexOfCard(card);
        ArrayList<Card> currentCards = new ArrayList<>();

        if (counter == 0 && !prefs.getDisableHintCosts()) {
            scores.update(-currentGame.getHintCosts());
        }

        visited.add(card);

        for (int i = index; i < origin.getSize(); i++) {
            currentCards.add(origin.getCard(i));
        }

        for (int i = 0; i < currentCards.size(); i++) {
            animate.cardHint(currentCards.get(i), i, destination);
        }
    }

    @Override
    protected boolean stopCondition() {
        return counter >= Hint.MAX_NUMBER_OF_HINTS;
    }

    protected void moveCard() {

        CardAndStack cardAndStack = currentGame.hintTest(visited);

        if (cardAndStack == null) {
            if (!showedFirstHint) {
                showToast(gm.getString(R.string.dialog_no_hint_available), gm);
            }

            stop();
        } else {
            if (!showedFirstHint) {
                sounds.playSound(Sounds.names.HINT);
                showedFirstHint = true;

                int amount = prefs.getSavedTotalHintsShown() + 1;
                prefs.saveTotalHintsShown(amount);
            }

            makeHint(cardAndStack.getCard(), cardAndStack.getStack());
            counter++;
            nextIteration();
        }
    }
}