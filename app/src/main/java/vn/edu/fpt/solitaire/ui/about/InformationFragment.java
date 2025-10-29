
package vn.edu.fpt.solitaire.ui.about;

import android.os.Bundle;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DateFormat;

import vn.edu.fpt.solitaire.BuildConfig;
import vn.edu.fpt.solitaire.R;

import static vn.edu.fpt.solitaire.SharedData.stringFormat;

import androidx.fragment.app.Fragment;

/**
 * Shows some info about my app
 */

public class InformationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_tab1, container, false);

//        TableLayout table_further_contributors = view.findViewById(R.id.about_table_further_contributors);
//        TableLayout table_translators = view.findViewById(R.id.about_table_translators);
//
//        TextView textViewBuildDate = view.findViewById(R.id.aboutTextViewBuild);       //build date
//        TextView textViewAppVersion = view.findViewById(R.id.aboutTextViewVersion);    //app version
//        TextView textViewGitHubLink = view.findViewById(R.id.aboutTextViewGitHubLink); //link for the gitHub repo
//        TextView textViewLicenseLink = view.findViewById(R.id.aboutTextViewLicenseLink);
//
//        String buildDate = DateFormat.getDateInstance().format(BuildConfig.TIMESTAMP); //get the build date in locale time format
//
//        //update the textViews
//        textViewAppVersion.setText(stringFormat(BuildConfig.VERSION_NAME));
//        textViewBuildDate.setText(stringFormat(buildDate));
//
//        //enable the hyperlink clicks
//        TextView[] textViews = new TextView[]{textViewGitHubLink, textViewLicenseLink};
//
//        for (TextView textView : textViews) {
//            textView.setMovementMethod(LinkMovementMethod.getInstance());
//        }
//
//        //enable hyperlinks in "Translations"
//        for (int i = 0; i < table_translators.getChildCount(); i++) {
//            TableRow row = (TableRow) table_translators.getChildAt(i);
//
//            //first entry is language title, no need for hyperlinking that
//            for (int j = 1; j < row.getChildCount(); j++) {
//                TextView text = (TextView) row.getChildAt(j);
//                text.setMovementMethod(LinkMovementMethod.getInstance());
//            }
//        }
//
//        //enable hyperlinks in "Further contributors"
//        for (int i = 0; i < table_further_contributors.getChildCount(); i++) {
//            TableRow row = (TableRow) table_further_contributors.getChildAt(i);
//
//            for (int j = 0; j < row.getChildCount(); j++) {
//                TextView text = (TextView) row.getChildAt(j);
//                text.setMovementMethod(LinkMovementMethod.getInstance());
//            }
//        }

        return view;
    }
}