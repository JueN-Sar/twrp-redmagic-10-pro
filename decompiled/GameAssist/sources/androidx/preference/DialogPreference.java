package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;

/* loaded from: classes.dex */
public abstract class DialogPreference extends Preference {
    private CharSequence S;
    private CharSequence T;
    private Drawable U;
    private CharSequence V;
    private CharSequence W;
    private int X;

    public interface TargetFragment {
        Preference f(CharSequence charSequence);
    }

    public DialogPreference(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DialogPreference, i2, i3);
        String o2 = TypedArrayUtils.o(obtainStyledAttributes, R.styleable.DialogPreference_dialogTitle, R.styleable.DialogPreference_android_dialogTitle);
        this.S = o2;
        if (o2 == null) {
            this.S = G();
        }
        this.T = TypedArrayUtils.o(obtainStyledAttributes, R.styleable.DialogPreference_dialogMessage, R.styleable.DialogPreference_android_dialogMessage);
        this.U = TypedArrayUtils.c(obtainStyledAttributes, R.styleable.DialogPreference_dialogIcon, R.styleable.DialogPreference_android_dialogIcon);
        this.V = TypedArrayUtils.o(obtainStyledAttributes, R.styleable.DialogPreference_positiveButtonText, R.styleable.DialogPreference_android_positiveButtonText);
        this.W = TypedArrayUtils.o(obtainStyledAttributes, R.styleable.DialogPreference_negativeButtonText, R.styleable.DialogPreference_android_negativeButtonText);
        this.X = TypedArrayUtils.n(obtainStyledAttributes, R.styleable.DialogPreference_dialogLayout, R.styleable.DialogPreference_android_dialogLayout, 0);
        obtainStyledAttributes.recycle();
    }

    public Drawable A0() {
        return this.U;
    }

    public int B0() {
        return this.X;
    }

    public CharSequence C0() {
        return this.T;
    }

    public CharSequence D0() {
        return this.S;
    }

    public CharSequence E0() {
        return this.W;
    }

    public CharSequence F0() {
        return this.V;
    }

    @Override // androidx.preference.Preference
    protected void S() {
        E().o(this);
    }

    public DialogPreference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public DialogPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.a(context, R.attr.dialogPreferenceStyle, android.R.attr.dialogPreferenceStyle));
    }
}
