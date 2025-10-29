
package vn.edu.fpt.solitaire.dialogs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Spinner;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CustomDialogPreference;

import static vn.edu.fpt.solitaire.SharedData.*;

/**
 * dialog for changing the rows shown in the menu. It uses different values for portrait and landscape
 */

public class DialogPreferenceMenuRows extends CustomDialogPreference {

    Spinner spinnerPortrait, spinnerLandscape;

    public DialogPreferenceMenuRows(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.dialog_settings_menu_columns);
        setDialogIcon(null);
    }

    @Override
    protected void onBindDialogView(View view) {
        spinnerPortrait = view.findViewById(R.id.dialogSettingsMenuColumnsPortrait);
        spinnerLandscape = view.findViewById(R.id.dialogSettingsMenuColumnsLandscape);

        //minus 1 because the values are 1 to 10, indexes are from 0 to 9
        spinnerPortrait.setSelection(prefs.getSavedMenuColumnsPortrait() - 1);
        spinnerLandscape.setSelection(prefs.getSavedMenuColumnsLandscape() - 1);

        super.onBindDialogView(view);
    }


    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {
            prefs.saveMenuColumnsPortrait(spinnerPortrait.getSelectedItem().toString());
            prefs.saveMenuColumnsLandscape(spinnerLandscape.getSelectedItem().toString());
        }
    }
}
