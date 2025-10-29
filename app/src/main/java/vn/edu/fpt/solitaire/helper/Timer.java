
package vn.edu.fpt.solitaire.helper;

import vn.edu.fpt.solitaire.handler.HandlerTimer;
import vn.edu.fpt.solitaire.ui.GameManager;

import static vn.edu.fpt.solitaire.SharedData.*;
import static vn.edu.fpt.solitaire.helper.Preferences.*;

/**
 * Handles the timer, updates, saves and load the current time of playing.
 * I thought about just incrementing a counter every second using a handler, but it could be
 * not precise enough (?) so I just go the bit more complex way using the System.currentTimeMillis().
 */

public class Timer {

    public HandlerTimer handlerTimer; //handler to show the current time

    private long currentTime;         //current system time, will be "frozen" if a game has been won
    private long startTime;           //time where the game was started
    private boolean running;          //indicates if the timer currently runs
    private long winningTime;

    public Timer(GameManager gm) {
        handlerTimer = new HandlerTimer(gm);
    }

    /**
     * Returns the current playing time. If a winning time was saved, show this instead.
     * <p>
     * The time is in seconds!! not milliseconds!
     *
     * @return The time to show on the screen
     */
    public long getCurrentTime() {
        return winningTime != 0 ? winningTime : currentTime;
    }

    //sets the time in seconds!
    public void setCurrentTime(long time) {
        currentTime = time;
    }

    /**
     * Save all necessary data to retrieve the played time on the next load.
     */
    public void save() {
        if (stopUiUpdates) {
            return;
        }

        running = false;
        if (!gameLogic.hasWon()) {
            prefs.saveEndTime(System.currentTimeMillis());
            prefs.saveStartTime(startTime);
        } else {
            prefs.saveWinningTime(winningTime);
        }
    }

    /**
     * Load the time, but subtract the time where the game was paused. Also load the winning time,
     * if there is one. The default is Zero, which is counted as no winning time
     */
    public void load() {
        running = true;

        startTime = prefs.getSavedStartTime() + System.currentTimeMillis() - prefs.getSavedEndTime();

        winningTime = prefs.getSavedWinningTime();

        handlerTimer.sendEmptyMessage(0);
    }

    /**
     * Reset all the data, so it will be shown as 0 seconds again.
     */
    public void reset() {
        running = true;

        prefs.saveStartTime(System.currentTimeMillis());
        prefs.saveEndTime(System.currentTimeMillis());
        prefs.saveWinningTime(DEFAULT_WINNING_TIME);

        winningTime = 0;

        startTime = System.currentTimeMillis();
        handlerTimer.sendEmptyMessage(0);
    }

    public boolean isRunning() {
        return running;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setWinningTime() {
        winningTime = currentTime;
    }

    public void setStartTime(long time) {
        startTime = time;
    }
}
