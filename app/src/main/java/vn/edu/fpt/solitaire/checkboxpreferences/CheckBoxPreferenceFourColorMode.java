package vn.edu.fpt.solitaire.checkboxpreferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import vn.edu.fpt.solitaire.classes.CustomCheckBoxPreference;

import static vn.edu.fpt.solitaire.SharedData.prefs;

/**
 * Custom Checkbox preferences so I can handle the saved data in the SharedPref by myself.
 * This is needed so I can set up some settings on a per game basis.
 */

public class CheckBoxPreferenceFourColorMode extends CustomCheckBoxPreference {

    public CheckBoxPreferenceFourColorMode(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CheckBoxPreferenceFourColorMode(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CheckBoxPreferenceFourColorMode(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckBoxPreferenceFourColorMode(Context context) {
        super(context);
    }

    @Override
    protected void onClick() {
        boolean value = !isChecked();
        prefs.putFourColorMode(value);
        setChecked(value);
    }

    public void update() {
        setChecked(prefs.getSavedFourColorMode());
    }
}
