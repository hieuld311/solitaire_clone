
package vn.edu.fpt.solitaire.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CustomDialogFragment;

import static vn.edu.fpt.solitaire.SharedData.ensureMovability;

import androidx.annotation.NonNull;

/**
 * Dialog to show while the EnsureMovability asyncTask is running. It shows a spinning wheel
 * and also has a cancel button.
 */

public class DialogEnsureMovability extends CustomDialogFragment implements View.OnClickListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_ensure_movability, null);

        Button cancelButton = view.findViewById(R.id.dialog_ensure_movability_cancel);
        cancelButton.setOnClickListener(this);

        builder.setView(view);
        return applyFlags(builder.create());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setCancelable(false);
    }

    @Override
    public void onClick(View view) {
        ensureMovability.stop();
    }
}
