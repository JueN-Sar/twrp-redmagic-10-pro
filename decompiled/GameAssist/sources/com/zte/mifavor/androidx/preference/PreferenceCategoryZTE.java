package com.zte.mifavor.androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.preference.PreferenceCategory;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class PreferenceCategoryZTE extends PreferenceCategory {
    private Context b0;
    private String c0;
    private boolean d0;
    private boolean e0;
    private boolean f0;
    private final boolean g0;

    public PreferenceCategoryZTE(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c0 = "";
        this.d0 = true;
        this.e0 = false;
        this.f0 = false;
        this.g0 = false;
        K0(context, attributeSet, false, false);
    }

    private void K0(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        this.b0 = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceZTE);
        this.c0 = obtainStyledAttributes.getString(R.styleable.PreferenceZTE_myTag);
        this.d0 = obtainStyledAttributes.getBoolean(R.styleable.PreferenceZTE_isCard, true);
        if (z || z2) {
            this.e0 = z;
            this.f0 = z2;
        } else {
            this.e0 = obtainStyledAttributes.getBoolean(R.styleable.PreferenceZTE_allowDividerAboveZTE, false);
            this.f0 = obtainStyledAttributes.getBoolean(R.styleable.PreferenceZTE_allowDividerBelowZTE, false);
        }
        obtainStyledAttributes.recycle();
    }

    public boolean I0() {
        return this.d0;
    }

    public String J0() {
        return this.c0;
    }

    public boolean L0() {
        return this.e0;
    }

    public boolean M0() {
        return this.f0;
    }

    public PreferenceCategoryZTE(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public PreferenceCategoryZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.c0 = "";
        this.d0 = true;
        this.e0 = false;
        this.f0 = false;
        this.g0 = false;
        K0(context, attributeSet, false, false);
    }
}
