
package vn.edu.fpt.solitaire.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.ui.statistics.StatisticsActivity;

/**
 * Dialog for deleting all high scores
 */

public class DialogHighScoreDelete extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.statistics_button_delete_text)
                .setPositiveButton(R.string.game_confirm, (dialog, id) -> {
                    StatisticsActivity statistics = (StatisticsActivity) getActivity();
                    statistics.deleteHighScores();
                })
                .setNegativeButton(R.string.game_cancel, (dialog, id) -> {
                    // User cancelled the dialog
                });

        return builder.create();
    }
}