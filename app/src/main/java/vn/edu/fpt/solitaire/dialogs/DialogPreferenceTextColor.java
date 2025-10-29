

package vn.edu.fpt.solitaire.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CustomDialogPreference;
import yuku.ambilwarna.AmbilWarnaDialog;

import static vn.edu.fpt.solitaire.SharedData.prefs;

/**
 * Dialog for changing the background color. It uses a custom layout, so I can dynamically update
 * the widget icon of the preference. The user can choose between 6 pre defined colors or set a custom
 * color. The custom color chooser uses this library: https://github.com/yukuku/ambilwarna
 * <p>
 * To distinguish between the pre defined and custom colors, I use another entry in the sharedPref.
 * I also planned to add a "Add background from gallery" option, but it would require the
 * permission to the external storage, and i wanted my app to use no permissions.
 */

public class DialogPreferenceTextColor extends CustomDialogPreference implements View.OnClickListener {

    final int colorBlack = 0xff000000;
    final int colorWhite = 0xffffffff;

    int colorValue;

    private ArrayList<LinearLayout> linearLayouts;
    private Context context;
    private ImageView image;

    public DialogPreferenceTextColor(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.dialog_text_color);
        setDialogIcon(null);
        this.context = context;
    }

    @Override
    protected void onBindDialogView(View view) {
        colorValue = prefs.getSavedTextColor();

        linearLayouts = new ArrayList<>();
        linearLayouts.add(view.findViewById(R.id.dialogBackgroundColorBlack));
        linearLayouts.add(view.findViewById(R.id.dialogBackgroundColorWhite));


        for (LinearLayout linearLayout : linearLayouts) {
            linearLayout.setOnClickListener(this);
        }

        super.onBindDialogView(view);
    }


    @SuppressWarnings("SuspiciousMethodCalls")
    public void onClick(View view) {
        if (view == ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE)) {
            AmbilWarnaDialog dialog = new AmbilWarnaDialog(context, colorValue, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    prefs.saveTextColor(color);
                    updateSummary();
                    getDialog().dismiss();
                }

                @Override
                public void onCancel(AmbilWarnaDialog dialog) {
                    // cancel was selected by the user
                }
            });
            dialog.show();
        } else if (view == ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_NEGATIVE)) {
            getDialog().dismiss();
        } else {
            int selectedColor = linearLayouts.indexOf(view) + 1;

            switch (selectedColor) {
                case 1:
                default:
                    colorValue = colorBlack;
                    break;
                case 2:
                    colorValue = colorWhite;
            }

            prefs.saveTextColor(colorValue);

            updateSummary();
            getDialog().dismiss();
        }
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);

        ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(this);
        ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(this);
    }

    /*
     * Get the layout from the preference, so I can get the imageView from the widgetLayout
     */
    @Override
    protected View onCreateView(ViewGroup parent) {
        View view = super.onCreateView(parent);

        image = view.findViewById(R.id.widget_layout_color_imageView);
        updateSummary();

        return view;
    }

    /**
     * Gets the saved data and updates the summary according to it
     */
    public void updateSummary() {
        int color = prefs.getSavedTextColor();

        //this forces redrawing of the color preview
        setSummary("");

        switch (color) {
            case colorBlack:
                setSummary(getContext().getString(R.string.black));
                break;
            case colorWhite:
                setSummary(getContext().getString(R.string.white));
                break;
            default:
                //show as hex string, but without the opacity part at the beginning
                setSummary(String.format("#%06X", (0xFFFFFF & color)));
                break;
        }

        if (image != null) {
            image.setImageResource(0);
            image.setBackgroundColor(color);
        }

    }
}
