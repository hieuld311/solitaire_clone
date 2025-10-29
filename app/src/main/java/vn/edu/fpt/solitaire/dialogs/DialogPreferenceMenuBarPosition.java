
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

public class DialogPreferenceMenuBarPosition extends CustomDialogPreference {

    RadioButton top, bottom, left, right;

    private static String BOTTOM = "bottom";
    private static String RIGHT = "right";

    public DialogPreferenceMenuBarPosition(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.dialog_settings_menu_bar_position);
        setDialogIcon(null);
    }

    @Override
    protected void onBindDialogView(View view) {
        top = view.findViewById(R.id.dialog_button_portrait_top);
        bottom = view.findViewById(R.id.dialog_button_portrait_bottom);
        left = view.findViewById(R.id.dialog_button_landscape_left);
        right = view.findViewById(R.id.dialog_button_landscape_right);

        if (prefs.getSavedMenuBarPosPortrait().equals(BOTTOM)) {
            bottom.setChecked(true);
        } else {
            top.setChecked(true);
        }

        if (prefs.getSavedMenuBarPosLandscape().equals(RIGHT)) {
            right.setChecked(true);
        } else {
            left.setChecked(true);
        }

        super.onBindDialogView(view);
    }


    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {
            String TOP = "top";
            prefs.saveMenuBarPosPortrait(bottom.isChecked() ? BOTTOM : TOP);
            String LEFT = "left";
            prefs.saveMenuBarPosLandscape(right.isChecked() ? RIGHT : LEFT);
        }
    }
}
