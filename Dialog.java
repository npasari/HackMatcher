//Nayan Pasari
//111868106
package com.example.hackmatcher;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Dialog extends AppCompatDialogFragment {

    private EditText ed1, ed2;
    private DialogListener listener;

    /*
    Creates a Dialog with an 'ok' and 'cancel' button.
     */
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialoglayout, null);
        builder.setView(view)
                .setTitle("Help!")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String iss = ed1.getText().toString();
                        String sol = ed2.getText().toString();
                        listener.applyTexts(iss, sol);
                    }
                });

        ed1 = view.findViewById(R.id.issue);
        ed2 = view.findViewById(R.id.solve);

        return builder.create();
    }

    /*
    Checks if the Dialog box works/is attached properly and hence its placed in a try-catch block.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogListener");
        }
    }

    public interface DialogListener {
        void applyTexts(String issue, String solve);
    }

}
