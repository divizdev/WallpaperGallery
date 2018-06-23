package ru.divizdev.wallpapergallery.presentation.detail.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import ru.divizdev.wallpapergallery.R;

public class DialogConfirm extends DialogFragment {

    private INoticeDialogListener _listener;


    public interface INoticeDialogListener {
        void onDialogConfirm();
        void onDialogCancel();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title_confirm_dialog)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        _listener.onDialogConfirm();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        _listener.onDialogCancel();
                        dialogInterface.dismiss();
                    }
                })
                .create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof INoticeDialogListener){
            _listener = (INoticeDialogListener) context;

        }else {
            throw new RuntimeException(context.toString()
                    + " must implement INoticeDialogListener");
        }
    }
}
