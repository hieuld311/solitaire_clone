
package vn.edu.fpt.solitaire.ui.manual;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import vn.edu.fpt.solitaire.R;

/**
 * Feedback contains just text and two buttons
 */

public class ManualFeedback extends Fragment implements View.OnClickListener {

    Button buttonGoogle, buttonGitHub;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_manual_feedback, container, false);

        buttonGoogle = view.findViewById(R.id.manual_feedback_button_google_play);
        buttonGitHub = view.findViewById(R.id.manual_feedback_button_github);

        buttonGoogle.setOnClickListener(this);
        buttonGitHub.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
    }
}
