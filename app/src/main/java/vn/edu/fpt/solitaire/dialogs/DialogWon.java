
package vn.edu.fpt.solitaire.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CustomDialogFragment;
import vn.edu.fpt.solitaire.ui.GameManager;

import static vn.edu.fpt.solitaire.SharedData.*;

import androidx.annotation.NonNull;

/**
 * dialog which is shown after winning a game. It shows options to start a new game, or to return
 * to the main menu. It also shows the current score.
 */

public class DialogWon extends CustomDialogFragment {

    private static String KEY_SCORE = "PREF_KEY_SCORE";
    private static String KEY_BONUS = "BONUS";
    private static String KEY_TOTAL = "TOTAL";

    private long score, bonus, total;

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedState) {
        final GameManager gameManager = (GameManager) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_won, null);

        builder.setCustomTitle(view)
                .setItems(R.array.won_menu, (dialog, which) -> {
                    // "which" argument contains index of selected item
                    switch (which) {
                        case 0:
                            gameLogic.newGame();
                            break;
                        case 1:
                            gameLogic.redeal();
                            break;
                        case 2:
                            if (gameManager.hasLoaded) {
                                timer.save();
                                gameLogic.setWonAndReloaded();
                                gameLogic.save();
                            }

                            gameManager.finish();
                            break;
                    }
                })
                .setNegativeButton(R.string.game_cancel, (dialog, id) -> {
                    //just cancel
                });

        LinearLayout layoutScores = view.findViewById(R.id.dialog_won_layout_scores);

        //only show the calculation of the score if bonus is enabled
        if (currentGame.isBonusEnabled()) {
            layoutScores.setVisibility(View.VISIBLE);
            TextView text1 = view.findViewById(R.id.dialog_won_text1);
            TextView text2 = view.findViewById(R.id.dialog_won_text2);
            TextView text3 = view.findViewById(R.id.dialog_won_text3);

            score = (savedState != null && savedState.containsKey(KEY_SCORE))
                    ? savedState.getLong(KEY_SCORE)
                    : scores.getPreBonus();
            bonus = (savedState != null && savedState.containsKey(KEY_BONUS))
                    ? savedState.getLong(KEY_BONUS)
                    : scores.getBonus();
            total = (savedState != null && savedState.containsKey(KEY_TOTAL))
                    ? savedState.getLong(KEY_TOTAL)
                    : scores.getScore();

            text1.setText(String.format(Locale.getDefault(), getContext()
                    .getString(R.string.dialog_win_score), score));
            text2.setText(String.format(Locale.getDefault(), getContext()
                    .getString(R.string.dialog_win_bonus), bonus));
            text3.setText(String.format(Locale.getDefault(), getContext()
                    .getString(R.string.dialog_win_total), total));
        } else {
            layoutScores.setVisibility(View.GONE);
        }

        return applyFlags(builder.create());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_SCORE, score);
        outState.putLong(KEY_BONUS, bonus);
        outState.putLong(KEY_TOTAL, total);
    }
}