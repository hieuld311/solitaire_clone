
package vn.edu.fpt.solitaire.classes;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import vn.edu.fpt.solitaire.ui.GameManager;

import static vn.edu.fpt.solitaire.SharedData.animate;

import androidx.annotation.CallSuper;


/**
 * New super class to generalise some work. The helper functions like auto complete, show hint
 * and auto move should stop when the activity gets paused and then restart when it's resumed.
 * Also screen orientation changes have to be handled by saving to bundles if the functions were
 * running and then reading the bundles after the recreation.
 * <p>
 * This is completely handled by this super class, so overriding classes don't have to worry
 * about that. (But they still need to  be added to the onPause(), onResume(), onSaveInstanceState()
 * and onCreate() methods of GameManager.java)
 */

public abstract class HelperCardMovement {

    private String bundleName;
    private int timeDelta = 100;                     //in ms
    private HelperCardMovementHandler handler;

    private boolean running = false;

    private boolean paused = false;

    protected GameManager gm;

    public HelperCardMovement(GameManager gm, String bundleName) {
        this.gm = gm;
        this.bundleName = bundleName;
        handler = new HelperCardMovementHandler(this);
    }

    @CallSuper
    public void start() {
        running = true;
        handler.sendMessage(0);
    }

    @CallSuper
    public void stop() {
        running = false;
    }

    public void pause() {
        if (isRunning()) {
            paused = true;
            running = false;
        }
    }

    public void saveInstanceState(Bundle bundle) {
        if (running || paused) {
            bundle.putBoolean("BUNDLE_" + bundleName, true);
            saveState(bundle);
        }
    }

    public void loadInstanceState(Bundle bundle) {
        if (bundle.containsKey("BUNDLE_" + bundleName)) {
            loadState(bundle);

            running = true;
            handler.sendMessage(0);
        }
    }

    /**
     * Is nearly the same as start(), but it does not reinitialize data! (if setup up in the
     * overriding class) So child classes SHOULD NOT override this one!
     */
    public void resume() {
        if (paused) {
            paused = false;
            running = true;
            handler.sendMessage(0);
        }
    }

    protected abstract void moveCard();

    protected void saveState(Bundle bundle) {
        //empty by default
    }

    protected void loadState(Bundle bundle) {
        //empty by default
    }

    @CallSuper
    protected void nextIteration() {
        handler.sendMessage(timeDelta);
    }

    @CallSuper
    protected void nextIteration(int customTimeDelta) {
        timeDelta = customTimeDelta;
        handler.sendMessage(timeDelta);
    }

    public boolean isRunning() {
        return running;
    }

    protected boolean haltCondition() {
        return animate.cardIsAnimating();
    }

    protected boolean stopCondition() {
        return false;
    }

    private static class HelperCardMovementHandler extends Handler {

        private HelperCardMovement base;

        public HelperCardMovementHandler(HelperCardMovement helperCardMovement) {
            this.base = helperCardMovement;
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (base.stopCondition()) {
                base.running = false;
                return;
            }

            if (base.running) {
                if (base.haltCondition()) {
                    sendMessage(base.timeDelta);
                } else {
                    base.moveCard();
/*
                    if (isRunning()) {
                        sendEmptyMessageDelayed(0, timeDelta);
                    }
*/
                }
            }
        }

        protected void sendMessage(int timeDelta) {
            sendEmptyMessageDelayed(0, timeDelta);
        }
    }
}
