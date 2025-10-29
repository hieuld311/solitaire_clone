
package vn.edu.fpt.solitaire.games;

import java.util.ArrayList;

import vn.edu.fpt.solitaire.classes.Card;

import static vn.edu.fpt.solitaire.SharedData.*;
import static vn.edu.fpt.solitaire.helper.Preferences.*;

/**
 * Vegas game! It's like Klondike, but with some changes and different scoring.
 */

public class Vegas extends Klondike {

    private int betAmount;
    private int winAmount;

    public Vegas() {
        //Attention!!
        //Vegas also calls the constructor of Klondike, don't forget it!

        disableBonus();
        setPointsInDollar();
        loadData();

        whichGame = 2;

        setNumberOfRecycles(PREF_KEY_VEGAS_NUMBER_OF_RECYCLES, DEFAULT_VEGAS_NUMBER_OF_RECYCLES);
    }

    @Override
    public void dealCards() {
        super.dealCards();

        prefs.saveVegasBetAmountOld();
        prefs.saveVegasWinAmountOld();
        loadData();

        boolean saveMoneyEnabled = prefs.getSavedVegasSaveMoneyEnabled();
        long money = 0;

        if (saveMoneyEnabled) {
            money = prefs.getSavedVegasMoney();
            prefs.saveVegasOldScore(money);
            timer.setStartTime(System.currentTimeMillis() - prefs.getSavedVegasTime() * 1000);
        }

        if (!stopUiUpdates) {
            scores.update(money - betAmount);
        }
    }

    public int addPointsToScore(ArrayList<Card> cards, int[] originIDs, int[] destinationIDs, boolean isUndoMovement) {
        int originID = originIDs[0];
        int destinationID = destinationIDs[0];

        //relevant for deal3 options, because cards on the waste move first and checking only
        //the first id wouldn't be enough
        for (int i = 0; i < originIDs.length; i++) {
            if (originIDs[i] >= 11 && originIDs[i] <= 13 && destinationIDs[i] >= 7 && destinationIDs[i] <= 10) {//stock to foundation
                return winAmount;
            }
        }

        if (originID < 7 && destinationID >= 7 && destinationID <= 10) {                             //from tableau to foundation
            return winAmount;
        }

        if (originID >= 7 && originID <= 10 && destinationID < 7) {                                  //from foundation to tableau
            return -2 * winAmount;
        }

        return 0;
    }

    @Override
    public boolean processScore(long currentScore) {
        boolean saveMoneyEnabled = prefs.getSavedVegasSaveMoneyEnabled();
        boolean resetMoney = prefs.getSavedVegasResetMoney();

        //return true, to let the  addNewScore() save a possible score.
        return !saveMoneyEnabled || resetMoney;
    }

    @Override
    public boolean saveRecentScore() {
        return prefs.getSavedVegasResetMoney();
    }

    private void loadData() {
        betAmount = prefs.getSavedVegasBetAmountOld();
        winAmount = prefs.getSavedVegasWinAmountOld();

        setHintCosts(winAmount);
        setUndoCosts(winAmount);
    }

    @Override
    public void onGameEnd() {
        boolean saveMoneyEnabled = prefs.getSavedVegasSaveMoneyEnabled();
        boolean resetMoney = prefs.getSavedVegasResetMoney();

        if (saveMoneyEnabled) {
            prefs.saveVegasMoney(scores.getScore());
            prefs.saveVegasTime(timer.getCurrentTime());
        }

        if (!gameLogic.hasWon() && scores.getScore() > (saveMoneyEnabled ? prefs.getSavedVegasOldScore() : 0)) {
            gameLogic.incrementNumberWonGames();
        }

        if (resetMoney) {
            prefs.saveVegasMoney(DEFAULT_VEGAS_MONEY);
            prefs.saveVegasTime(0);
            prefs.saveVegasResetMoney(false);
        }
    }
}
