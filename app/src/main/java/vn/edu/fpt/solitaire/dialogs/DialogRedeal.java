
package vn.edu.fpt.solitaire.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;


import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CustomDialogFragment;

import static vn.edu.fpt.solitaire.SharedData.*;

import androidx.annotation.NonNull;

/**
 * Little confirmation dialog for redealing the current game
 */

public class DialogRedeal extends CustomDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_redeal_title)
                .setMessage(R.string.dialog_redeal_text)
                .setPositiveButton(R.string.game_confirm, (dialog, id) -> gameLogic.redeal())
                .setNegativeButton(R.string.game_cancel, (dialog, id) -> {
                    // User cancelled the dialog
                });

        return applyFlags(builder.create());
    }
}