
package vn.edu.fpt.solitaire.dialogs;

import android.content.Context;
import android.util.AttributeSet;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CustomDialogPreference;

import static vn.edu.fpt.solitaire.SharedData.*;

/**
 * custom dialog to set the background music volume. it can be set from 0 (off) to 100%.
 */

public class DialogPreferenceVegasResetMoney extends CustomDialogPreference {

    public DialogPreferenceVegasResetMoney(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.dialog_settings_vegas_reset_money);
        setDialogIcon(null);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        // When the user selects "OK", persist the new value
        if (positiveResult) {
            prefs.saveVegasResetMoney(true);
        }
    }
}
