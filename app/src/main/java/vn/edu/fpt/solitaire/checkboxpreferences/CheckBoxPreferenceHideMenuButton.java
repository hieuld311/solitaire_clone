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

public class CheckBoxPreferenceHideMenuButton extends CustomCheckBoxPreference {

    public CheckBoxPreferenceHideMenuButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CheckBoxPreferenceHideMenuButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CheckBoxPreferenceHideMenuButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckBoxPreferenceHideMenuButton(Context context) {
        super(context);
    }

    @Override
    protected void onClick() {
        boolean value = !isChecked();
        prefs.putHideMenuButton(value);
        setChecked(value);
    }

    public void update() {
        setChecked(prefs.getHideMenuButton());
    }
}
