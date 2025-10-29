
package vn.edu.fpt.solitaire.helper;

import android.view.View;
import android.widget.RelativeLayout;

import vn.edu.fpt.solitaire.classes.Card;
import vn.edu.fpt.solitaire.classes.Stack;
import vn.edu.fpt.solitaire.ui.GameManager;

import static android.view.View.GONE;

/**
 * Sets a semi transparent rectangle behind the card to highlight it, so the user knows which card
 * he tapped.
 */

public class CardHighlight {

    int padding, width, height;
    private boolean moveStarted;

    /**
     * Sets the size of the highlighting and moves it behin the cards.
     *
     * @param card The card on the stack to highlight
     */
    public void set(GameManager gm, Card card) {
        Stack stack = card.getStack();

        padding = (int) (Card.width * 0.25);
        width = Card.width + padding;
        height = (int) (stack.getTopCard().getY() + Card.height - card.getY() + padding);

        gm.highlight.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
        gm.highlight.setX(card.getX() - padding / 2);
        gm.highlight.setY(card.getY() - padding / 2);
        gm.highlight.setVisibility(View.VISIBLE);
        gm.highlight.bringToFront();

        for (int i = card.getIndexOnStack(); i < stack.getSize(); i++) {
            stack.getCard(i).bringToFront();
        }

        moveStarted = false;
    }

    /**
     * For drag and drop movements: Updates the position of the view behind the card.
     * It needs to update the height of the highlighted area, because the MovingCard.move() method
     * changes the offset of the cards.
     *
     * @param card The card to highlight
     */
    public void move(GameManager gm, Card card) {
        if (!moveStarted) {
            moveStarted = true;

            height = (int) (card.getStack().getTopCard().getY() + Card.height - card.getY() + padding);
            gm.highlight.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
        }

        gm.highlight.setX(card.getX() - padding / 2);
        gm.highlight.setY(card.getY() - padding / 2);
    }

    public void hide(GameManager gm) {
        gm.highlight.setVisibility(GONE);
    }
}
