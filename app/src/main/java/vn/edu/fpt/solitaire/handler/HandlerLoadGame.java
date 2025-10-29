
package vn.edu.fpt.solitaire.handler;

import android.os.Handler;
import android.os.Message;

import static vn.edu.fpt.solitaire.SharedData.*;

/**
 * load the game data in a handler which waits a bit, so the initial card deal looks smoother
 */

public class HandlerLoadGame extends Handler {

    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        gameLogic.load(false);
    }
}
