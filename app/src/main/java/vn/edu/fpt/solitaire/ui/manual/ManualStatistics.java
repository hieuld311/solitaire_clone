
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
 * Just show a textView for the menu page.
 */

public class ManualStatistics extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manual_statistics, container, false);

        TextView textView = view.findViewById(R.id.manual_statistics_list);

        //get the strings for the enumerated text part (with bullet characters)
        CharSequence strings[] = new CharSequence[]{
                getText(R.string.manual_statistics_part_2), getText(R.string.manual_statistics_part_3),
                getText(R.string.manual_statistics_part_4), getText(R.string.manual_statistics_part_5),
                getText(R.string.manual_statistics_part_6)
        };

        //set up the textView
        textView.setText(createBulletParagraph(strings));

        return view;
    }
}
