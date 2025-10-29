
package vn.edu.fpt.solitaire.dialogs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CustomDialogPreference;

import static vn.edu.fpt.solitaire.SharedData.prefs;

/**
 * dialog for changing the rows shown in the menu. It uses different values for portrait and landscape
 */

public class DialogPreferenceGameLayoutMargins extends CustomDialogPreference {

    RadioButton[] portrait = new RadioButton[4];
    RadioButton[] landscape = new RadioButton[4];


    public DialogPreferenceGameLayoutMargins(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.dialog_settings_game_layout_margins);
        setDialogIcon(null);
    }

    @Override
    protected void onBindDialogView(View view) {

        portrait[0] = view.findViewById(R.id.dialog_button_portrait_none);
        portrait[1] = view.findViewById(R.id.dialog_button_portrait_small);
        portrait[2] = view.findViewById(R.id.dialog_button_portrait_medium);
        portrait[3] = view.findViewById(R.id.dialog_button_portrait_large);

        landscape[0] = view.findViewById(R.id.dialog_button_landscape_none);
        landscape[1] = view.findViewById(R.id.dialog_button_landscape_small);
        landscape[2] = view.findViewById(R.id.dialog_button_landscape_medium);
        landscape[3] = view.findViewById(R.id.dialog_button_landscape_large);

        portrait[prefs.getSavedGameLayoutMarginsPortrait()].setChecked(true);
        landscape[prefs.getSavedGameLayoutMarginsLandscape()].setChecked(true);

        super.onBindDialogView(view);
    }


    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {
            int portraitValue = 0, landscapeValue = 0;

            for (int i = 0; i < 4; i++) {
                if (portrait[i].isChecked()) {
                    portraitValue = i;
                }

                if (landscape[i].isChecked()) {
                    landscapeValue = i;
                }
            }

            prefs.saveGameLayoutMarginsPortrait(portraitValue);
            prefs.saveGameLayoutMarginsLandscape(landscapeValue);
        }
    }
}
