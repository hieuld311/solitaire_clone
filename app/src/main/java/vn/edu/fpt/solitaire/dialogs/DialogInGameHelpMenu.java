
package vn.edu.fpt.solitaire.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CustomDialogFragment;
import vn.edu.fpt.solitaire.ui.GameManager;
import vn.edu.fpt.solitaire.ui.manual.Manual;

import static vn.edu.fpt.solitaire.SharedData.*;

import androidx.annotation.NonNull;

/**
 * dialog to handle new games or returning to main menu( in that case, cancel the current activity)
 */

public class DialogInGameHelpMenu extends CustomDialogFragment {

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final GameManager gameManager = (GameManager) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.settings_support)
                .setItems(R.array.help_menu, (dialog, which) -> {
                    // "which" argument contains index of selected item
                    switch (which) {
                        case 0:
                            if (!gameLogic.hasWon()) {
                                hint.start();
                            }
                            break;
                        case 1:
                            if (!gameLogic.hasWon()) {
                                autoMove.start();
                            }
                            break;
                        case 2:
                            if (!gameLogic.hasWon()) {
                                if (currentGame.hintTest() == null) {
                                    if (prefs.getShowDialogMixCards()) {
                                        prefs.putShowDialogMixCards(false);
                                        DialogMixCards dialogMixCards = new DialogMixCards();
                                        dialogMixCards.show(getFragmentManager(), "MIX_DIALOG");
                                    } else {
                                        currentGame.mixCards();
                                    }
                                } else {
                                    showToast(getString(R.string.dialog_mix_cards_not_available), getActivity());
                                }
                            }
                            break;
                        case 3:
                            Intent intent = new Intent(gameManager, Manual.class);
                            intent.putExtra(GAME, lg.getSharedPrefName());
                            startActivity(intent);
                            break;
                    }
                })
                .setNegativeButton(R.string.game_cancel, (dialog, id) -> {
                    //just cancel
                });

        return applyFlags(builder.create());
    }
}