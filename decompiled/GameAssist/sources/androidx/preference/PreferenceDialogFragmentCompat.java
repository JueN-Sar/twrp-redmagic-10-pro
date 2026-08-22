package androidx.preference;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCaller;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.DialogPreference;

/* loaded from: classes.dex */
public abstract class PreferenceDialogFragmentCompat extends DialogFragment implements DialogInterface.OnClickListener {
    private CharSequence A0;
    private CharSequence B0;
    private CharSequence C0;
    private int D0;
    private BitmapDrawable E0;
    private int F0;
    private DialogPreference y0;
    private CharSequence z0;

    private void x2(Dialog dialog) {
        dialog.getWindow().setSoftInputMode(5);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void X0(Bundle bundle) {
        super.X0(bundle);
        bundle.putCharSequence("PreferenceDialogFragment.title", this.z0);
        bundle.putCharSequence("PreferenceDialogFragment.positiveText", this.A0);
        bundle.putCharSequence("PreferenceDialogFragment.negativeText", this.B0);
        bundle.putCharSequence("PreferenceDialogFragment.message", this.C0);
        bundle.putInt("PreferenceDialogFragment.layout", this.D0);
        BitmapDrawable bitmapDrawable = this.E0;
        if (bitmapDrawable != null) {
            bundle.putParcelable("PreferenceDialogFragment.icon", bitmapDrawable.getBitmap());
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog j2(Bundle bundle) {
        FragmentActivity t = t();
        this.F0 = -2;
        AlertDialog.Builder h2 = new AlertDialog.Builder(t).m(this.z0).e(this.E0).j(this.A0, this).h(this.B0, this);
        View u2 = u2(t);
        if (u2 != null) {
            t2(u2);
            h2.n(u2);
        } else {
            h2.f(this.C0);
        }
        w2(h2);
        AlertDialog a2 = h2.a();
        if (s2()) {
            x2(a2);
        }
        return a2;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        this.F0 = i2;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityResultCaller d0 = d0();
        if (!(d0 instanceof DialogPreference.TargetFragment)) {
            throw new IllegalStateException("Target fragment must implement TargetFragment interface");
        }
        DialogPreference.TargetFragment targetFragment = (DialogPreference.TargetFragment) d0;
        String string = x().getString("key");
        if (bundle != null) {
            this.z0 = bundle.getCharSequence("PreferenceDialogFragment.title");
            this.A0 = bundle.getCharSequence("PreferenceDialogFragment.positiveText");
            this.B0 = bundle.getCharSequence("PreferenceDialogFragment.negativeText");
            this.C0 = bundle.getCharSequence("PreferenceDialogFragment.message");
            this.D0 = bundle.getInt("PreferenceDialogFragment.layout", 0);
            Bitmap bitmap = (Bitmap) bundle.getParcelable("PreferenceDialogFragment.icon");
            if (bitmap != null) {
                this.E0 = new BitmapDrawable(U(), bitmap);
                return;
            }
            return;
        }
        DialogPreference dialogPreference = (DialogPreference) targetFragment.f(string);
        this.y0 = dialogPreference;
        this.z0 = dialogPreference.D0();
        this.A0 = this.y0.F0();
        this.B0 = this.y0.E0();
        this.C0 = this.y0.C0();
        this.D0 = this.y0.B0();
        Drawable A0 = this.y0.A0();
        if (A0 == null || (A0 instanceof BitmapDrawable)) {
            this.E0 = (BitmapDrawable) A0;
            return;
        }
        Bitmap createBitmap = Bitmap.createBitmap(A0.getIntrinsicWidth(), A0.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        A0.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        A0.draw(canvas);
        this.E0 = new BitmapDrawable(U(), createBitmap);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        v2(this.F0 == -1);
    }

    public DialogPreference r2() {
        if (this.y0 == null) {
            this.y0 = (DialogPreference) ((DialogPreference.TargetFragment) d0()).f(x().getString("key"));
        }
        return this.y0;
    }

    protected boolean s2() {
        return false;
    }

    protected void t2(View view) {
        int i2;
        View findViewById = view.findViewById(android.R.id.message);
        if (findViewById != null) {
            CharSequence charSequence = this.C0;
            if (TextUtils.isEmpty(charSequence)) {
                i2 = 8;
            } else {
                if (findViewById instanceof TextView) {
                    ((TextView) findViewById).setText(charSequence);
                }
                i2 = 0;
            }
            if (findViewById.getVisibility() != i2) {
                findViewById.setVisibility(i2);
            }
        }
    }

    protected View u2(Context context) {
        int i2 = this.D0;
        if (i2 == 0) {
            return null;
        }
        return LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
    }

    public abstract void v2(boolean z);

    protected void w2(AlertDialog.Builder builder) {
    }
}
