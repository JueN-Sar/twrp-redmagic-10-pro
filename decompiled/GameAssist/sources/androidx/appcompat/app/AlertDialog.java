package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.R;
import androidx.appcompat.app.AlertController;

/* loaded from: classes.dex */
public class AlertDialog extends AppCompatDialog implements DialogInterface {

    /* renamed from: l, reason: collision with root package name */
    final AlertController f218l;

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final AlertController.AlertParams f219a;

        /* renamed from: b, reason: collision with root package name */
        private final int f220b;

        public Builder(Context context) {
            this(context, AlertDialog.m(context, 0));
        }

        public AlertDialog a() {
            AlertDialog alertDialog = new AlertDialog(this.f219a.f186a, this.f220b);
            this.f219a.a(alertDialog.f218l);
            alertDialog.setCancelable(this.f219a.f203r);
            if (this.f219a.f203r) {
                alertDialog.setCanceledOnTouchOutside(true);
            }
            alertDialog.setOnCancelListener(this.f219a.f204s);
            alertDialog.setOnDismissListener(this.f219a.t);
            DialogInterface.OnKeyListener onKeyListener = this.f219a.u;
            if (onKeyListener != null) {
                alertDialog.setOnKeyListener(onKeyListener);
            }
            return alertDialog;
        }

        public Context b() {
            return this.f219a.f186a;
        }

        public Builder c(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f219a;
            alertParams.w = listAdapter;
            alertParams.x = onClickListener;
            return this;
        }

        public Builder d(View view) {
            this.f219a.f192g = view;
            return this;
        }

        public Builder e(Drawable drawable) {
            this.f219a.f189d = drawable;
            return this;
        }

        public Builder f(CharSequence charSequence) {
            this.f219a.f193h = charSequence;
            return this;
        }

        public Builder g(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.AlertParams alertParams = this.f219a;
            alertParams.v = charSequenceArr;
            alertParams.J = onMultiChoiceClickListener;
            alertParams.F = zArr;
            alertParams.G = true;
            return this;
        }

        public Builder h(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f219a;
            alertParams.f197l = charSequence;
            alertParams.f199n = onClickListener;
            return this;
        }

        public Builder i(DialogInterface.OnKeyListener onKeyListener) {
            this.f219a.u = onKeyListener;
            return this;
        }

        public Builder j(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f219a;
            alertParams.f194i = charSequence;
            alertParams.f196k = onClickListener;
            return this;
        }

        public Builder k(ListAdapter listAdapter, int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f219a;
            alertParams.w = listAdapter;
            alertParams.x = onClickListener;
            alertParams.I = i2;
            alertParams.H = true;
            return this;
        }

        public Builder l(CharSequence[] charSequenceArr, int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f219a;
            alertParams.v = charSequenceArr;
            alertParams.x = onClickListener;
            alertParams.I = i2;
            alertParams.H = true;
            return this;
        }

        public Builder m(CharSequence charSequence) {
            this.f219a.f191f = charSequence;
            return this;
        }

        public Builder n(View view) {
            AlertController.AlertParams alertParams = this.f219a;
            alertParams.z = view;
            alertParams.y = 0;
            alertParams.E = false;
            return this;
        }

        public Builder(Context context, int i2) {
            this.f219a = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.m(context, i2)));
            this.f220b = i2;
        }
    }

    protected AlertDialog(Context context, int i2) {
        super(context, m(context, i2));
        this.f218l = new AlertController(getContext(), this, getWindow());
    }

    static int m(Context context, int i2) {
        if (((i2 >>> 24) & 255) >= 1) {
            return i2;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView l() {
        return this.f218l.d();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f218l.e();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (this.f218l.g(i2, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (this.f218l.h(i2, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f218l.q(charSequence);
    }
}
