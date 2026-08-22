package com.google.android.gms.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes.dex */
public class ErrorDialogFragment extends DialogFragment {

    /* renamed from: c, reason: collision with root package name */
    private Dialog f10489c;

    /* renamed from: h, reason: collision with root package name */
    private DialogInterface.OnCancelListener f10490h;

    /* renamed from: i, reason: collision with root package name */
    private Dialog f10491i;

    public static ErrorDialogFragment a(Dialog dialog, DialogInterface.OnCancelListener onCancelListener) {
        ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
        Dialog dialog2 = (Dialog) Preconditions.j(dialog, "Cannot display null dialog");
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        errorDialogFragment.f10489c = dialog2;
        if (onCancelListener != null) {
            errorDialogFragment.f10490h = onCancelListener;
        }
        return errorDialogFragment;
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.f10490h;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = this.f10489c;
        if (dialog != null) {
            return dialog;
        }
        setShowsDialog(false);
        if (this.f10491i == null) {
            this.f10491i = new AlertDialog.Builder((Context) Preconditions.i(getActivity())).create();
        }
        return this.f10491i;
    }

    @Override // android.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
    }
}
