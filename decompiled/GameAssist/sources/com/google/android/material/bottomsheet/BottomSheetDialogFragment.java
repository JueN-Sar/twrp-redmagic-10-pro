package com.google.android.material.bottomsheet;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* loaded from: classes.dex */
public class BottomSheetDialogFragment extends AppCompatDialogFragment {
    private boolean y0;

    private class BottomSheetDismissCallback extends BottomSheetBehavior.BottomSheetCallback {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ BottomSheetDialogFragment f14067a;

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void b(View view, float f2) {
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void c(View view, int i2) {
            if (i2 == 5) {
                this.f14067a.s2();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s2() {
        if (this.y0) {
            super.e2();
        } else {
            super.d2();
        }
    }

    @Override // androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog j2(Bundle bundle) {
        return new BottomSheetDialog(z(), h2());
    }
}
