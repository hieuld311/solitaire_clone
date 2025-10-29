
package vn.edu.fpt.solitaire.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;


import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CustomDialogFragment;
import vn.edu.fpt.solitaire.ui.GameManager;

import static vn.edu.fpt.solitaire.SharedData.*;

import androidx.annotation.NonNull;

/**
 * dialog to handle new games or returning to main menu( in that case, cancel the current activity)
 */

public class DialogInGameMenu extends CustomDialogFragment {

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final GameManager gameManager = (GameManager) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(lg.getGameName())
                .setItems(R.array.restart_menu, (dialog, which) -> {
                    // "which" argument contains index of selected item
                    switch (which) {
                        case 0:
                            if (prefs.getShowDialogNewGame()) {
                                prefs.putShowDialogNewGame(false);
                                DialogStartNewGame dialogStartNewGame = new DialogStartNewGame();
                                dialogStartNewGame.show(getFragmentManager(), "START_NEW_GAME_DIALOG");
                            } else {
                                gameLogic.newGame();
                            }
                            break;
                        case 1:
                            if (prefs.getShowDialogRedeal()) {
                                prefs.putShowDialogRedeal(false);
                                DialogRedeal dialogRedeal = new DialogRedeal();
                                dialogRedeal.show(getFragmentManager(), "REDEAL_DIALOG");
                            } else {
                                gameLogic.redeal();
                            }
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

        return applyFlags(builder.create());
    }
}
