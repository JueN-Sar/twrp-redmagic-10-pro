package com.zte.mifavor.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import com.zte.extres.R;
import com.zte.mifavor.utils.SinkUtils;
import com.zte.mifavor.widget.AlertController;

/* loaded from: classes2.dex */
public class AlertDialog extends Dialog implements DialogInterface {
    public static final int LAYOUT_HINT_NONE = 0;
    public static final int LAYOUT_HINT_SIDE = 1;
    private static final String TAG = "Z#AlertDialog";
    public static final int THEME_DEVICE_DEFAULT_DARK = 4;
    public static final int THEME_DEVICE_DEFAULT_LIGHT = 5;
    public static final int THEME_HOLO_DARK = 2;
    public static final int THEME_HOLO_LIGHT = 3;
    public static final int THEME_TRADITIONAL = 1;
    private AlertController mAlert;
    private boolean mIsServiceDialog;
    private int mNegativeTxtColor;
    private int mRecommendTxtColor;

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final AlertController.AlertParams f17590a;

        /* renamed from: b, reason: collision with root package name */
        private int f17591b;

        public Builder(Context context) {
            this(context, AlertDialog.resolveDialogTheme(context, 0));
        }

        public AlertDialog a() {
            AlertDialog alertDialog = new AlertDialog(this.f17590a.f17558a, this.f17591b, false);
            this.f17590a.a(alertDialog.mAlert);
            alertDialog.setCancelable(this.f17590a.f17575r);
            if (this.f17590a.f17575r) {
                alertDialog.setCanceledOnTouchOutside(true);
            }
            alertDialog.setOnCancelListener(this.f17590a.f17576s);
            alertDialog.setOnDismissListener(this.f17590a.t);
            DialogInterface.OnKeyListener onKeyListener = this.f17590a.u;
            if (onKeyListener != null) {
                alertDialog.setOnKeyListener(onKeyListener);
            }
            return alertDialog;
        }

        public Builder b(boolean z) {
            this.f17590a.f17575r = z;
            return this;
        }

        public Builder c(boolean z) {
            Log.d(AlertDialog.TAG, "set Is Show Title, isShow=" + z);
            this.f17590a.W = z;
            return this;
        }

        public Builder d(int i2) {
            AlertController.AlertParams alertParams = this.f17590a;
            alertParams.f17565h = alertParams.f17558a.getText(i2);
            return this;
        }

        public Builder e(CharSequence charSequence) {
            this.f17590a.f17565h = charSequence;
            return this;
        }

        public Builder f(int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f17590a;
            alertParams.f17569l = alertParams.f17558a.getText(i2);
            this.f17590a.f17570m = onClickListener;
            return this;
        }

        public Builder g(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f17590a;
            alertParams.f17569l = charSequence;
            alertParams.f17570m = onClickListener;
            return this;
        }

        public Builder h(DialogInterface.OnDismissListener onDismissListener) {
            this.f17590a.t = onDismissListener;
            return this;
        }

        public Builder i(int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f17590a;
            alertParams.f17567j = alertParams.f17558a.getText(i2);
            this.f17590a.f17568k = onClickListener;
            return this;
        }

        public Builder j(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f17590a;
            alertParams.f17567j = charSequence;
            alertParams.f17568k = onClickListener;
            return this;
        }

        public Builder k(CharSequence[] charSequenceArr, int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f17590a;
            alertParams.v = charSequenceArr;
            alertParams.x = onClickListener;
            alertParams.I = i2;
            alertParams.H = true;
            return this;
        }

        public Builder l(int i2) {
            AlertController.AlertParams alertParams = this.f17590a;
            alertParams.f17563f = alertParams.f17558a.getText(i2);
            return this;
        }

        public Builder m(CharSequence charSequence) {
            this.f17590a.f17563f = charSequence;
            return this;
        }

        public Builder n(View view) {
            AlertController.AlertParams alertParams = this.f17590a;
            alertParams.z = view;
            alertParams.y = 0;
            alertParams.E = false;
            return this;
        }

        public AlertDialog o() {
            AlertDialog a2 = a();
            Log.d(AlertDialog.TAG, "show, P=" + this.f17590a + ", mIsRecommendTxt=" + this.f17590a.T + ", mNegativeTxtColor=" + this.f17590a.a0 + ", mIsRecommendBackground=" + this.f17590a.U + ", mIsNegativeBackground=" + this.f17590a.Z + ", mIsServiceDialog=" + this.f17590a.S);
            AlertController.AlertParams alertParams = this.f17590a;
            if (alertParams != null && alertParams.T) {
                a2.setRecommendButtonTextColor(alertParams.V);
            }
            AlertController.AlertParams alertParams2 = this.f17590a;
            if (alertParams2 != null && alertParams2.Y) {
                a2.setNegativeButtonTextColor(alertParams2.a0);
            }
            AlertController.AlertParams alertParams3 = this.f17590a;
            if (alertParams3 != null && alertParams3.U) {
                a2.setRecommendButtonBackground();
            }
            AlertController.AlertParams alertParams4 = this.f17590a;
            if (alertParams4 != null && alertParams4.Z) {
                a2.setNegativeButtonBackground();
            }
            AlertController.AlertParams alertParams5 = this.f17590a;
            if (alertParams5 != null && alertParams5.W) {
                a2.setIsShowTitle();
            }
            AlertController.AlertParams alertParams6 = this.f17590a;
            if (alertParams6 != null && alertParams6.X) {
                a2.closeSyncGravity();
            }
            AlertController.AlertParams alertParams7 = this.f17590a;
            if (alertParams7 != null && alertParams7.S) {
                a2.setServiceDialog();
            }
            a2.show();
            return a2;
        }

        public Builder(Context context, int i2) {
            this.f17590a = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, i2)));
            this.f17591b = i2;
        }
    }

    protected AlertDialog(Context context) {
        this(context, resolveDialogTheme(context, 0), true);
    }

    static int resolveDialogTheme(Context context, int i2) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogThemeMfv, typedValue, true);
        return typedValue.resourceId;
    }

    public void closeSyncGravity() {
        Log.d(TAG, "close Sync Gravity in...");
        AlertController alertController = this.mAlert;
        if (alertController != null) {
            alertController.v();
        }
    }

    public Button getButton(int i2) {
        return this.mAlert.x(i2);
    }

    public android.widget.ListView getListView() {
        return this.mAlert.z();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAlert.A();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (this.mAlert.B(i2, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (this.mAlert.C(i2, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    public void setButton(int i2, CharSequence charSequence, Message message) {
        this.mAlert.G(i2, charSequence, null, message);
    }

    @Deprecated
    public void setButton2(CharSequence charSequence, Message message) {
        setButton(-2, charSequence, message);
    }

    @Deprecated
    public void setButton3(CharSequence charSequence, Message message) {
        setButton(-3, charSequence, message);
    }

    void setButtonPanelLayoutHint(int i2) {
        this.mAlert.H(i2);
    }

    public void setCustomTitle(View view) {
        this.mAlert.I(view);
    }

    public void setIcon(int i2) {
        this.mAlert.J(i2);
    }

    public void setIconAttribute(int i2) {
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(i2, typedValue, true);
        this.mAlert.J(typedValue.resourceId);
    }

    public void setInverseBackgroundForced(boolean z) {
        this.mAlert.M(z);
    }

    public void setIsShowTitle() {
        Log.d(TAG, "set Is Show Title in ...");
        AlertController alertController = this.mAlert;
        if (alertController != null) {
            alertController.N();
        }
    }

    public void setMessage(CharSequence charSequence) {
        this.mAlert.O(charSequence);
    }

    public void setNegativeButtonBackground() {
        Log.d(TAG, "set Negative Button Background in ...");
        AlertController alertController = this.mAlert;
        if (alertController != null) {
            alertController.P();
        }
    }

    public void setNegativeButtonTextColor(int i2) {
        Log.d(TAG, "set Negative Button Text Color in. mAlert=" + this.mAlert + ", color=" + i2);
        AlertController alertController = this.mAlert;
        if (alertController != null) {
            alertController.Q(i2);
        }
    }

    public void setRecommendButtonBackground() {
        Log.d(TAG, "set Recommend Button Background in ...");
        AlertController alertController = this.mAlert;
        if (alertController != null) {
            alertController.R();
        }
    }

    public void setRecommendButtonTextColor(int i2) {
        Log.d(TAG, "set Recommend Button Text Color in ... mAlert = " + this.mAlert + ", color=" + i2);
        AlertController alertController = this.mAlert;
        if (alertController != null) {
            alertController.S(i2);
        }
    }

    public void setServiceDialog() {
        Log.d(TAG, "set Service Dialog in ...");
        AlertController alertController = this.mAlert;
        if (alertController != null) {
            alertController.T();
        }
    }

    public void setSubTitle(CharSequence charSequence) {
        this.mAlert.U(charSequence);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mAlert.V(charSequence);
    }

    public void setTitleColor(int i2) {
        this.mAlert.W(i2);
    }

    public void setView(View view) {
        this.mAlert.Y(view);
    }

    public void setupTitle() {
        this.mAlert.e0();
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        Log.d(TAG, "show out.");
    }

    protected AlertDialog(Context context, int i2) {
        this(context, i2, true);
    }

    public void setButton(int i2, CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        this.mAlert.G(i2, charSequence, onClickListener, null);
    }

    @Deprecated
    public void setButton2(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        setButton(-2, charSequence, onClickListener);
    }

    @Deprecated
    public void setButton3(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        setButton(-3, charSequence, onClickListener);
    }

    public void setIcon(Drawable drawable) {
        this.mAlert.K(drawable);
    }

    public void setView(View view, int i2, int i3, int i4, int i5) {
        this.mAlert.Z(view, i2, i3, i4, i5);
    }

    AlertDialog(Context context, int i2, boolean z) {
        super(context, resolveDialogTheme(context, i2));
        this.mRecommendTxtColor = 0;
        this.mNegativeTxtColor = 0;
        this.mIsServiceDialog = false;
        getWindow().alwaysReadCloseOnTouchAttr();
        this.mAlert = new AlertController(getContext(), this, getWindow());
        if (SinkUtils.c(context.getResources())) {
            Log.d(TAG, "AlertDialog out. is Landscape.");
        } else {
            Log.d(TAG, "AlertDialog out. is Portrait.");
        }
    }

    @Deprecated
    public void setButton(CharSequence charSequence, Message message) {
        setButton(-1, charSequence, message);
    }

    @Deprecated
    public void setButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        setButton(-1, charSequence, onClickListener);
    }
}
