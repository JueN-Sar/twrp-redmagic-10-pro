package androidx.appcompat.app;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

/* loaded from: classes.dex */
public class AppCompatDialogFragment extends DialogFragment {
    @Override // androidx.fragment.app.DialogFragment
    public Dialog j2(Bundle bundle) {
        return new AppCompatDialog(z(), h2());
    }

    @Override // androidx.fragment.app.DialogFragment
    public void p2(Dialog dialog, int i2) {
        if (!(dialog instanceof AppCompatDialog)) {
            super.p2(dialog, i2);
            return;
        }
        AppCompatDialog appCompatDialog = (AppCompatDialog) dialog;
        if (i2 != 1 && i2 != 2) {
            if (i2 != 3) {
                return;
            } else {
                dialog.getWindow().addFlags(24);
            }
        }
        appCompatDialog.k(1);
    }
}
