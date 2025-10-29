package vn.edu.fpt.solitaire.classes;

import static vn.edu.fpt.solitaire.SharedData.*;

/**
 * Just a little class to return a stack and a card at once from a method
 */

public class CardAndStack {
    private int cardID;
    private int stackID;

    public CardAndStack(Card card, Stack stack) {
        cardID = card.getId();
        stackID = stack.getId();
    }

    public CardAndStack(int cardID, int stackID) {
        this.cardID = cardID;
        this.stackID = stackID;
    }

    public int getCardId() {
        return cardID;
    }

    public int getStackId() {
        return stackID;
    }

    public Card getCard() {
        return cards[cardID];
    }

    public Stack getStack() {
        return stacks[stackID];
    }
}
