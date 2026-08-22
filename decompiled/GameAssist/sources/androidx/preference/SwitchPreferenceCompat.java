package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.res.TypedArrayUtils;

/* loaded from: classes.dex */
public class SwitchPreferenceCompat extends TwoStatePreference {
    private final Listener X;
    private CharSequence Y;
    private CharSequence Z;

    private class Listener implements CompoundButton.OnCheckedChangeListener {
        Listener() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (SwitchPreferenceCompat.this.c(Boolean.valueOf(z))) {
                SwitchPreferenceCompat.this.B0(z);
            } else {
                compoundButton.setChecked(!z);
            }
        }
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.X = new Listener();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SwitchPreferenceCompat, i2, i3);
        E0(TypedArrayUtils.o(obtainStyledAttributes, R.styleable.SwitchPreferenceCompat_summaryOn, R.styleable.SwitchPreferenceCompat_android_summaryOn));
        D0(TypedArrayUtils.o(obtainStyledAttributes, R.styleable.SwitchPreferenceCompat_summaryOff, R.styleable.SwitchPreferenceCompat_android_summaryOff));
        I0(TypedArrayUtils.o(obtainStyledAttributes, R.styleable.SwitchPreferenceCompat_switchTextOn, R.styleable.SwitchPreferenceCompat_android_switchTextOn));
        H0(TypedArrayUtils.o(obtainStyledAttributes, R.styleable.SwitchPreferenceCompat_switchTextOff, R.styleable.SwitchPreferenceCompat_android_switchTextOff));
        C0(TypedArrayUtils.b(obtainStyledAttributes, R.styleable.SwitchPreferenceCompat_disableDependentsState, R.styleable.SwitchPreferenceCompat_android_disableDependentsState, false));
        obtainStyledAttributes.recycle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void J0(View view) {
        boolean z = view instanceof SwitchCompat;
        if (z) {
            ((SwitchCompat) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.U);
        }
        if (z) {
            SwitchCompat switchCompat = (SwitchCompat) view;
            switchCompat.setTextOn(this.Y);
            switchCompat.setTextOff(this.Z);
            switchCompat.setOnCheckedChangeListener(this.X);
        }
    }

    private void K0(View view) {
        if (((AccessibilityManager) l().getSystemService("accessibility")).isEnabled()) {
            J0(view.findViewById(R.id.switchWidget));
            F0(view.findViewById(android.R.id.summary));
        }
    }

    public void H0(CharSequence charSequence) {
        this.Z = charSequence;
        N();
    }

    public void I0(CharSequence charSequence) {
        this.Y = charSequence;
        N();
    }

    @Override // androidx.preference.Preference
    public void R(PreferenceViewHolder preferenceViewHolder) {
        super.R(preferenceViewHolder);
        J0(preferenceViewHolder.N(R.id.switchWidget));
        G0(preferenceViewHolder);
    }

    @Override // androidx.preference.Preference
    protected void b0(View view) {
        super.b0(view);
        K0(view);
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.switchPreferenceCompatStyle);
    }
}
