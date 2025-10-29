
package vn.edu.fpt.solitaire.handler;

import android.os.Handler;
import android.os.Message;

import static vn.edu.fpt.solitaire.SharedData.*;

/**
 * Check here if the application is closed. If the activityCounter reaches zero, no activity
 * is in the foreground so stop the background music. But try stopping some milliseconds delayed,
 * because otherwise the music would stop/restart between the activities
 */

public class HandlerStopBackgroundMusic extends Handler {


    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        if (activityCounter == 0) {
            backgroundSound.pausePlaying();
        }
    }
}