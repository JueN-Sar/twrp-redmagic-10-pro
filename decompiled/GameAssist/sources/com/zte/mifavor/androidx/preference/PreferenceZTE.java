package com.zte.mifavor.androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class PreferenceZTE extends Preference {
    private final boolean S;
    private CharSequence T;
    private Context U;
    private TextView V;
    private TextView W;
    private TextView X;
    private View Y;
    private boolean Z;
    private int a0;
    private boolean b0;
    private String c0;
    private boolean d0;
    private Drawable e0;
    private boolean f0;
    private int g0;

    public PreferenceZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.S = false;
        this.X = null;
        this.Z = true;
        this.a0 = -1;
        this.b0 = true;
        this.c0 = "";
        this.d0 = true;
        this.e0 = null;
        this.f0 = true;
        this.g0 = -2;
        G0(context, attributeSet);
    }

    private void G0(Context context, AttributeSet attributeSet) {
        this.U = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceZTE);
        this.T = obtainStyledAttributes.getString(R.styleable.PreferenceZTE_status);
        this.c0 = obtainStyledAttributes.getString(R.styleable.PreferenceZTE_myTag);
        this.d0 = obtainStyledAttributes.getBoolean(R.styleable.PreferenceZTE_isCard, true);
        this.e0 = obtainStyledAttributes.getDrawable(R.styleable.PreferenceZTE_itemBackground);
        this.g0 = obtainStyledAttributes.getInt(R.styleable.PreferenceZTE_number, -2);
        this.f0 = obtainStyledAttributes.getBoolean(R.styleable.PreferenceZTE_specifyHeight, true);
        obtainStyledAttributes.recycle();
    }

    public boolean A0() {
        return this.d0;
    }

    public Drawable B0() {
        return this.e0;
    }

    public String C0() {
        return this.c0;
    }

    public int D0() {
        return this.g0;
    }

    public boolean E0() {
        return this.f0;
    }

    public CharSequence F0() {
        return this.T;
    }

    @Override // androidx.preference.Preference
    public void R(PreferenceViewHolder preferenceViewHolder) {
        super.R(preferenceViewHolder);
        this.X = (TextView) preferenceViewHolder.N(android.R.id.title);
        this.V = (TextView) preferenceViewHolder.N(android.R.id.summary);
        LinearLayout linearLayout = (LinearLayout) preferenceViewHolder.N(android.R.id.widget_frame);
        if (linearLayout != null) {
            TextView textView = (TextView) linearLayout.findViewById(R.id.status);
            this.W = textView;
            if (textView != null) {
                CharSequence F0 = F0();
                if (TextUtils.isEmpty(F0)) {
                    this.W.setVisibility(8);
                } else {
                    this.W.setText(F0);
                    this.W.setVisibility(0);
                    this.W.setEnabled(this.b0);
                    if (-1 != this.a0) {
                        ViewGroup.LayoutParams layoutParams = this.W.getLayoutParams();
                        layoutParams.width = this.a0;
                        this.W.setLayoutParams(layoutParams);
                    }
                }
            }
            View findViewById = linearLayout.findViewById(R.id.indicator);
            this.Y = findViewById;
            if (findViewById != null) {
                if (this.Z) {
                    findViewById.setVisibility(0);
                    return;
                } else {
                    findViewById.setVisibility(8);
                    return;
                }
            }
            Log.w("PreferenceZTEx", "onBindViewHolder indicator = " + this.W);
        }
    }

    public PreferenceZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.S = false;
        this.X = null;
        this.Z = true;
        this.a0 = -1;
        this.b0 = true;
        this.c0 = "";
        this.d0 = true;
        this.e0 = null;
        this.f0 = true;
        this.g0 = -2;
        G0(context, attributeSet);
    }

    public PreferenceZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.preferenceStyleMFS);
        G0(context, attributeSet);
    }
}
