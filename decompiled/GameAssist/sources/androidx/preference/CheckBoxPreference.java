package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.core.content.res.TypedArrayUtils;

/* loaded from: classes.dex */
public class CheckBoxPreference extends TwoStatePreference {
    private final Listener X;

    private class Listener implements CompoundButton.OnCheckedChangeListener {
        Listener() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (CheckBoxPreference.this.c(Boolean.valueOf(z))) {
                CheckBoxPreference.this.B0(z);
            } else {
                compoundButton.setChecked(!z);
            }
        }
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void H0(View view) {
        boolean z = view instanceof CompoundButton;
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.U);
        }
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(this.X);
        }
    }

    private void I0(View view) {
        if (((AccessibilityManager) l().getSystemService("accessibility")).isEnabled()) {
            H0(view.findViewById(android.R.id.checkbox));
            F0(view.findViewById(android.R.id.summary));
        }
    }

    @Override // androidx.preference.Preference
    public void R(PreferenceViewHolder preferenceViewHolder) {
        super.R(preferenceViewHolder);
        H0(preferenceViewHolder.N(android.R.id.checkbox));
        G0(preferenceViewHolder);
    }

    @Override // androidx.preference.Preference
    protected void b0(View view) {
        super.b0(view);
        I0(view);
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.X = new Listener();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CheckBoxPreference, i2, i3);
        E0(TypedArrayUtils.o(obtainStyledAttributes, R.styleable.CheckBoxPreference_summaryOn, R.styleable.CheckBoxPreference_android_summaryOn));
        D0(TypedArrayUtils.o(obtainStyledAttributes, R.styleable.CheckBoxPreference_summaryOff, R.styleable.CheckBoxPreference_android_summaryOff));
        C0(TypedArrayUtils.b(obtainStyledAttributes, R.styleable.CheckBoxPreference_disableDependentsState, R.styleable.CheckBoxPreference_android_disableDependentsState, false));
        obtainStyledAttributes.recycle();
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.a(context, R.attr.checkBoxPreferenceStyle, android.R.attr.checkBoxPreferenceStyle));
    }
}
