package com.zte.mifavor.androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceViewHolder;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class ListPreferenceZTEX extends ListPreference {
    private static boolean q0 = P0();
    private final boolean d0;
    private CharSequence e0;
    private Context f0;
    private TextView g0;
    private TextView h0;
    private int i0;
    private int j0;
    private int k0;
    private boolean l0;
    private String m0;
    private boolean n0;
    private Drawable o0;
    private int p0;

    public ListPreferenceZTEX(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.d0 = false;
        this.i0 = -1;
        this.j0 = -1;
        this.k0 = -1;
        this.l0 = true;
        this.m0 = "";
        this.n0 = true;
        this.o0 = null;
        this.p0 = -2;
        R0(context, attributeSet);
    }

    public static boolean P0() {
        boolean z = false;
        try {
            z = SystemProperties.getBoolean("ro.vendor.feature.summary_default_botttom", false);
            Log.d("ListPreferenceZTEx", "get Properties Is Summary Right out. getBoolean summary_default_botttom, isBottom=" + z);
            return !z;
        } catch (Exception e2) {
            Log.w("ListPreferenceZTEx", "get Properties Is Summary Right error, e", e2);
            Log.d("ListPreferenceZTEx", "get Properties Is Summary Right out. isBottom = " + z);
            return !z;
        }
    }

    private void R0(Context context, AttributeSet attributeSet) {
        this.f0 = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceZTE);
        this.e0 = obtainStyledAttributes.getString(R.styleable.PreferenceZTE_status);
        this.m0 = obtainStyledAttributes.getString(R.styleable.PreferenceZTE_myTag);
        this.n0 = obtainStyledAttributes.getBoolean(R.styleable.PreferenceZTE_isCard, true);
        this.o0 = obtainStyledAttributes.getDrawable(R.styleable.PreferenceZTE_itemBackground);
        this.p0 = obtainStyledAttributes.getInt(R.styleable.PreferenceZTE_number, -2);
        obtainStyledAttributes.recycle();
        Log.i("ListPreferenceZTEx", "init Prefrence Status XPG#, key=" + u() + ", mStatus=" + ((Object) this.e0) + ", mMyTag=" + this.m0 + ", mIsCard=" + this.n0 + ", mNumber=" + this.p0);
    }

    private static boolean S0(Context context, AttributeSet attributeSet) {
        Log.d("ListPreferenceZTEx", "Is Summary Right in. mIsSummaryRight=" + q0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceZTE, 0, 0);
        q0 = obtainStyledAttributes.getBoolean(R.styleable.PreferenceZTE_isSummaryRight, P0());
        obtainStyledAttributes.recycle();
        Log.d("ListPreferenceZTEx", "Is Summary Right out. mIsSummaryRight=" + q0);
        return q0;
    }

    public boolean N0() {
        return this.n0;
    }

    public int O0() {
        return this.p0;
    }

    public CharSequence Q0() {
        return this.e0;
    }

    @Override // androidx.preference.Preference
    public void R(PreferenceViewHolder preferenceViewHolder) {
        int i2;
        super.R(preferenceViewHolder);
        this.g0 = (TextView) preferenceViewHolder.N(android.R.id.summary);
        int i3 = this.j0;
        if (-1 != i3) {
            V0(i3);
        }
        int i4 = this.j0;
        if (-1 != i4 && -1 != (i2 = this.k0)) {
            U0(i4, i2);
        }
        if (q0) {
            this.h0 = (TextView) preferenceViewHolder.N(R.id.status);
        } else {
            LinearLayout linearLayout = (LinearLayout) preferenceViewHolder.N(android.R.id.widget_frame);
            if (linearLayout != null) {
                this.h0 = (TextView) linearLayout.findViewById(R.id.status);
            } else {
                Log.d("ListPreferenceZTEx", "onBindViewHolder widgetView is null.");
            }
        }
        if (this.h0 == null) {
            Log.w("ListPreferenceZTEx", "onBindViewHolder mStatusView = " + this.h0);
            return;
        }
        CharSequence Q0 = Q0();
        if (TextUtils.isEmpty(Q0)) {
            this.h0.setVisibility(8);
            return;
        }
        this.h0.setText(Q0);
        this.h0.setVisibility(0);
        this.h0.setEnabled(this.l0);
        int i5 = this.i0;
        if (-1 != i5) {
            T0(i5);
        }
    }

    public void T0(int i2) {
        TextView textView = this.h0;
        if (textView == null) {
            this.i0 = i2;
            return;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = i2;
        this.h0.setLayoutParams(layoutParams);
    }

    public void U0(int i2, int i3) {
        TextView textView = this.g0;
        if (textView == null) {
            this.j0 = i2;
            this.k0 = i3;
        } else {
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            layoutParams.width = i2;
            layoutParams.height = i3;
            this.g0.setLayoutParams(layoutParams);
        }
    }

    public void V0(int i2) {
        TextView textView = this.g0;
        if (textView == null) {
            this.j0 = i2;
            return;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = i2;
        this.g0.setLayoutParams(layoutParams);
    }

    public ListPreferenceZTEX(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.d0 = false;
        this.i0 = -1;
        this.j0 = -1;
        this.k0 = -1;
        this.l0 = true;
        this.m0 = "";
        this.n0 = true;
        this.o0 = null;
        this.p0 = -2;
        R0(context, attributeSet);
    }

    public ListPreferenceZTEX(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, S0(context, attributeSet) ? R.attr.dialogPreferenceStyleX : R.attr.dialogPreferenceStyle);
        this.d0 = false;
        this.i0 = -1;
        this.j0 = -1;
        this.k0 = -1;
        this.l0 = true;
        this.m0 = "";
        this.n0 = true;
        this.o0 = null;
        this.p0 = -2;
        R0(context, attributeSet);
        this.f0 = context;
    }
}
