
package vn.edu.fpt.solitaire.ui.manual;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.edu.fpt.solitaire.R;

import static vn.edu.fpt.solitaire.SharedData.*;

import androidx.fragment.app.Fragment;

/**
 * Just show a textView for the user interface page
 */

public class ManualUserInterface extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manual_user_interface, container, false);

        TextView textView1 = view.findViewById(R.id.text_view_manual_ui_1);
        TextView textView2 = view.findViewById(R.id.text_view_manual_ui_2);

        //get the strings for the enumerated text part (with bullet characters)
        CharSequence strings1[] = new CharSequence[]{
                getText(R.string.manual_ui_text_part_2), getText(R.string.manual_ui_text_part_3),
                getText(R.string.manual_ui_text_part_4), getText(R.string.manual_ui_text_part_5)
        };

        CharSequence strings2[] = new CharSequence[]{
                getText(R.string.manual_ui_text_part_7), getText(R.string.manual_ui_text_part_8),
                getText(R.string.manual_ui_text_part_9)
        };

        //set up the textViews
        textView1.setText(createBulletParagraph(strings1));
        textView2.setText(createBulletParagraph(strings2));

        return view;
    }
}
