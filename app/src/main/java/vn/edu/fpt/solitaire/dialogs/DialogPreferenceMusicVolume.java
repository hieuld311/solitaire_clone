
package vn.edu.fpt.solitaire.dialogs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CustomDialogPreference;

import static vn.edu.fpt.solitaire.SharedData.*;


/**
 * custom dialog to set the background music volume. it can be set from 0 (off) to 100%.
 */

public class DialogPreferenceMusicVolume
        extends CustomDialogPreference implements SeekBar.OnSeekBarChangeListener {

    private SeekBar mSeekBar;
    private TextView mTextView;

    public DialogPreferenceMusicVolume(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.dialog_background_volume);
        setDialogIcon(null);
    }

    @Override
    protected void onBindDialogView(View view) {
        mTextView = view.findViewById(R.id.textView);
        mSeekBar = view.findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(this);

        int volume = prefs.getSavedBackgroundVolume();
        mSeekBar.setProgress(volume);
        setProgressText(volume);

        super.onBindDialogView(view);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        setProgressText(i);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        // When the user selects "OK", persist the new value
        if (positiveResult) {
            prefs.saveBackgroundVolume(mSeekBar.getProgress());
        }
    }

    private void setProgressText(int value) {
        mTextView.setText(String.format(Locale.getDefault(), "%s %%", value));
    }
}
