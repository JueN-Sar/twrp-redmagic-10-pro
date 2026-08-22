package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.core.content.res.TypedArrayUtils;

/* loaded from: classes.dex */
public class SwitchPreference extends TwoStatePreference {
    private final Listener X;
    private CharSequence Y;
    private CharSequence Z;

    private class Listener implements CompoundButton.OnCheckedChangeListener {
        Listener() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (SwitchPreference.this.c(Boolean.valueOf(z))) {
                SwitchPreference.this.B0(z);
            } else {
                compoundButton.setChecked(!z);
            }
        }
    }

    public SwitchPreference(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.X = new Listener();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SwitchPreference, i2, i3);
        E0(TypedArrayUtils.o(obtainStyledAttributes, R.styleable.SwitchPreference_summaryOn, R.styleable.SwitchPreference_android_summaryOn));
        D0(TypedArrayUtils.o(obtainStyledAttributes, R.styleable.SwitchPreference_summaryOff, R.styleable.SwitchPreference_android_summaryOff));
        I0(TypedArrayUtils.o(obtainStyledAttributes, R.styleable.SwitchPreference_switchTextOn, R.styleable.SwitchPreference_android_switchTextOn));
        H0(TypedArrayUtils.o(obtainStyledAttributes, R.styleable.SwitchPreference_switchTextOff, R.styleable.SwitchPreference_android_switchTextOff));
        C0(TypedArrayUtils.b(obtainStyledAttributes, R.styleable.SwitchPreference_disableDependentsState, R.styleable.SwitchPreference_android_disableDependentsState, false));
        obtainStyledAttributes.recycle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void J0(View view) {
        boolean z = view instanceof Switch;
        if (z) {
            ((Switch) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.U);
        }
        if (z) {
            Switch r4 = (Switch) view;
            r4.setTextOn(this.Y);
            r4.setTextOff(this.Z);
            r4.setOnCheckedChangeListener(this.X);
        }
    }

    private void K0(View view) {
        if (((AccessibilityManager) l().getSystemService("accessibility")).isEnabled()) {
            J0(view.findViewById(android.R.id.switch_widget));
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
        J0(preferenceViewHolder.N(android.R.id.switch_widget));
        G0(preferenceViewHolder);
    }

    @Override // androidx.preference.Preference
    protected void b0(View view) {
        super.b0(view);
        K0(view);
    }

    public SwitchPreference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public SwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.a(context, R.attr.switchPreferenceStyle, android.R.attr.switchPreferenceStyle));
    }
}
