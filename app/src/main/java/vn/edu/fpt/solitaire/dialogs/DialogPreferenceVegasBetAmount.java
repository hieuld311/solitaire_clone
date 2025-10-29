
package vn.edu.fpt.solitaire.dialogs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CustomDialogPreference;

import static vn.edu.fpt.solitaire.SharedData.*;

/*
 * custom dialog to set the bet and win amount in Vegas
 */

public class DialogPreferenceVegasBetAmount extends CustomDialogPreference {

    private EditText input1, input2;

    public DialogPreferenceVegasBetAmount(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.dialog_vegas_bet_amount);
        setDialogIcon(null);
    }

    @Override
    protected void onBindDialogView(View view) {
        input1 = view.findViewById(R.id.settings_vegas_bet_amount_input_1);
        input2 = view.findViewById(R.id.settings_vegas_bet_amount_input_2);

        input1.setText(stringFormat(Integer.toString(prefs.getSavedVegasBetAmount())));
        input2.setText(stringFormat(Integer.toString(prefs.getSavedVegasWinAmount())));

        super.onBindDialogView(view);
    }


    @Override
    protected void onDialogClosed(boolean positiveResult) {
        // When the user selects "OK", persist the new value
        if (positiveResult) {

            try {
                prefs.saveVegasBetAmount(Integer.parseInt(input1.getText().toString()));
                prefs.saveVegasWinAmount(Integer.parseInt(input2.getText().toString()));
            } catch (Exception e) {
                showToast(getContext().getString(R.string.settings_number_input_error), getContext());
            }
        }
    }

}
