
package vn.edu.fpt.solitaire.handler;

import android.os.Handler;
import android.os.Message;

import java.util.Locale;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.ui.GameManager;

import static vn.edu.fpt.solitaire.SharedData.*;

/**
 * Handler to update the current time and show it
 */

public class HandlerTimer extends Handler {

    private GameManager gm;

    public HandlerTimer(GameManager gm) {
        this.gm = gm;
    }

    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        //is always called at least once a game started, because this gets executed before the
        //won variable in gameLogic was loaded
        if (timer.isRunning() && !gameLogic.hasWon()) {
            timer.setCurrentTime((System.currentTimeMillis() - timer.getStartTime()) / 1000);
            timer.handlerTimer.sendEmptyMessageDelayed(0, 1000);
        }

        if (prefs.getSavedHideTime()) {
            gm.mainTextViewTime.setText("");
        } else {

            if (stopUiUpdates) {
                return;
            }

            Long time = timer.getCurrentTime();

            gm.mainTextViewTime.setText(String.format(Locale.getDefault(),
                    "%s: %02d:%02d:%02d", gm.getString(R.string.game_time),
                    time / 3600, (time % 3600) / 60, (time % 60)));  //in hours:minutes:seconds format
        }
    }
}