
package vn.edu.fpt.solitaire.dialogs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CustomDialogPreference;

import static vn.edu.fpt.solitaire.SharedData.*;

/*
 * custom dialog to set the maximum amount of undos
 */

public class DialogPreferenceMaxNumberUndos extends CustomDialogPreference {

    private EditText input;

    public DialogPreferenceMaxNumberUndos(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.dialog_max_number_undos);
        setDialogIcon(null);
    }

    @Override
    protected void onBindDialogView(View view) {
        input = view.findViewById(R.id.settings_max_number_undos_input);

        input.setText(stringFormat(Integer.toString(prefs.getSavedMaxNumberUndos())));


        super.onBindDialogView(view);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        // When the user selects "OK", persist the new value
        if (positiveResult) {
            try {
                //Saving zero would cause force closes, so just catch it here
                if (Integer.parseInt(input.getText().toString()) < 1) {
                    showToast(getContext().getString(R.string.settings_number_input_error), getContext());
                    return;
                }

                prefs.saveMaxNumberUndos(Integer.parseInt(input.getText().toString()));
            } catch (Exception e) {
                showToast(getContext().getString(R.string.settings_number_input_error), getContext());
            }
        }
    }
}
