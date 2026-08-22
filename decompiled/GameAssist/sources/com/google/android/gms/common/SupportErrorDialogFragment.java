package com.google.android.gms.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes.dex */
public class SupportErrorDialogFragment extends DialogFragment {
    private Dialog A0;
    private Dialog y0;
    private DialogInterface.OnCancelListener z0;

    public static SupportErrorDialogFragment r2(Dialog dialog, DialogInterface.OnCancelListener onCancelListener) {
        SupportErrorDialogFragment supportErrorDialogFragment = new SupportErrorDialogFragment();
        Dialog dialog2 = (Dialog) Preconditions.j(dialog, "Cannot display null dialog");
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        supportErrorDialogFragment.y0 = dialog2;
        if (onCancelListener != null) {
            supportErrorDialogFragment.z0 = onCancelListener;
        }
        return supportErrorDialogFragment;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog j2(Bundle bundle) {
        Dialog dialog = this.y0;
        if (dialog != null) {
            return dialog;
        }
        o2(false);
        if (this.A0 == null) {
            this.A0 = new AlertDialog.Builder((Context) Preconditions.i(z())).create();
        }
        return this.A0;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.z0;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public void q2(FragmentManager fragmentManager, String str) {
        super.q2(fragmentManager, str);
    }
}
