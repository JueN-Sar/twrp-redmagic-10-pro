package androidx.preference;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ComponentCallbacks2;
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
import androidx.preference.DialogPreference;

/* loaded from: classes.dex */
public abstract class PreferenceDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    /* renamed from: c, reason: collision with root package name */
    private DialogPreference f4677c;

    /* renamed from: h, reason: collision with root package name */
    private CharSequence f4678h;

    /* renamed from: i, reason: collision with root package name */
    private CharSequence f4679i;

    /* renamed from: j, reason: collision with root package name */
    private CharSequence f4680j;

    /* renamed from: k, reason: collision with root package name */
    private CharSequence f4681k;

    /* renamed from: l, reason: collision with root package name */
    private int f4682l;

    /* renamed from: m, reason: collision with root package name */
    private BitmapDrawable f4683m;

    /* renamed from: n, reason: collision with root package name */
    private int f4684n;

    private void g(Dialog dialog) {
        dialog.getWindow().setSoftInputMode(5);
    }

    public DialogPreference a() {
        if (this.f4677c == null) {
            this.f4677c = (DialogPreference) ((DialogPreference.TargetFragment) getTargetFragment()).f(getArguments().getString("key"));
        }
        return this.f4677c;
    }

    protected boolean b() {
        return false;
    }

    protected void c(View view) {
        int i2;
        View findViewById = view.findViewById(android.R.id.message);
        if (findViewById != null) {
            CharSequence charSequence = this.f4681k;
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

    protected View d(Context context) {
        int i2 = this.f4682l;
        if (i2 == 0) {
            return null;
        }
        return LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
    }

    public abstract void e(boolean z);

    protected void f(AlertDialog.Builder builder) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        this.f4684n = i2;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ComponentCallbacks2 targetFragment = getTargetFragment();
        if (!(targetFragment instanceof DialogPreference.TargetFragment)) {
            throw new IllegalStateException("Target fragment must implement TargetFragment interface");
        }
        DialogPreference.TargetFragment targetFragment2 = (DialogPreference.TargetFragment) targetFragment;
        String string = getArguments().getString("key");
        if (bundle != null) {
            this.f4678h = bundle.getCharSequence("PreferenceDialogFragment.title");
            this.f4679i = bundle.getCharSequence("PreferenceDialogFragment.positiveText");
            this.f4680j = bundle.getCharSequence("PreferenceDialogFragment.negativeText");
            this.f4681k = bundle.getCharSequence("PreferenceDialogFragment.message");
            this.f4682l = bundle.getInt("PreferenceDialogFragment.layout", 0);
            Bitmap bitmap = (Bitmap) bundle.getParcelable("PreferenceDialogFragment.icon");
            if (bitmap != null) {
                this.f4683m = new BitmapDrawable(getResources(), bitmap);
                return;
            }
            return;
        }
        DialogPreference dialogPreference = (DialogPreference) targetFragment2.f(string);
        this.f4677c = dialogPreference;
        this.f4678h = dialogPreference.D0();
        this.f4679i = this.f4677c.F0();
        this.f4680j = this.f4677c.E0();
        this.f4681k = this.f4677c.C0();
        this.f4682l = this.f4677c.B0();
        Drawable A0 = this.f4677c.A0();
        if (A0 == null || (A0 instanceof BitmapDrawable)) {
            this.f4683m = (BitmapDrawable) A0;
            return;
        }
        Bitmap createBitmap = Bitmap.createBitmap(A0.getIntrinsicWidth(), A0.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        A0.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        A0.draw(canvas);
        this.f4683m = new BitmapDrawable(getResources(), createBitmap);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Activity activity = getActivity();
        this.f4684n = -2;
        AlertDialog.Builder negativeButton = new AlertDialog.Builder(activity).setTitle(this.f4678h).setIcon(this.f4683m).setPositiveButton(this.f4679i, this).setNegativeButton(this.f4680j, this);
        View d2 = d(activity);
        if (d2 != null) {
            c(d2);
            negativeButton.setView(d2);
        } else {
            negativeButton.setMessage(this.f4681k);
        }
        f(negativeButton);
        AlertDialog create = negativeButton.create();
        if (b()) {
            g(create);
        }
        return create;
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        e(this.f4684n == -1);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("PreferenceDialogFragment.title", this.f4678h);
        bundle.putCharSequence("PreferenceDialogFragment.positiveText", this.f4679i);
        bundle.putCharSequence("PreferenceDialogFragment.negativeText", this.f4680j);
        bundle.putCharSequence("PreferenceDialogFragment.message", this.f4681k);
        bundle.putInt("PreferenceDialogFragment.layout", this.f4682l);
        BitmapDrawable bitmapDrawable = this.f4683m;
        if (bitmapDrawable != null) {
            bundle.putParcelable("PreferenceDialogFragment.icon", bitmapDrawable.getBitmap());
        }
    }
}
