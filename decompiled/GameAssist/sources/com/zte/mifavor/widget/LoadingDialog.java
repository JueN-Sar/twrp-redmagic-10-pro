package com.zte.mifavor.widget;

import android.content.DialogInterface;
import android.util.Log;

/* loaded from: classes2.dex */
public class LoadingDialog extends AlertDialog {

    /* renamed from: h, reason: collision with root package name */
    private static LoadingDialog f17672h;

    /* renamed from: c, reason: collision with root package name */
    private OnTaskCompletedListener f17673c;

    /* renamed from: com.zte.mifavor.widget.LoadingDialog$1, reason: invalid class name */
    class AnonymousClass1 implements DialogInterface.OnClickListener {
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
        }
    }

    public interface OnTaskCompletedListener {
        void a();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        super.cancel();
        Log.d("Z#LoadingDialog", "cancel out. mLoadingDialog=" + f17672h + ", listener=" + this.f17673c);
        OnTaskCompletedListener onTaskCompletedListener = this.f17673c;
        if (onTaskCompletedListener != null) {
            onTaskCompletedListener.a();
        }
        this.f17673c = null;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        Log.d("Z#LoadingDialog", "dismiss out. mLoadingDialog=" + f17672h + ", listener=" + this.f17673c);
    }
}
