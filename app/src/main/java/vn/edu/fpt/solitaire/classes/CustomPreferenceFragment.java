package vn.edu.fpt.solitaire.classes;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.preference.PreferenceFragment;

import static vn.edu.fpt.solitaire.SharedData.reinitializeData;

/**
 * Custom PreferenceFragment, to override onAttach. If the app got killed within a
 * PreferenceFragment and restarted, the data has to be reinitialized
 */

public class CustomPreferenceFragment extends PreferenceFragment {

    @Override
    public void onAttach(Context context) {
        reinitializeData(context);
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
