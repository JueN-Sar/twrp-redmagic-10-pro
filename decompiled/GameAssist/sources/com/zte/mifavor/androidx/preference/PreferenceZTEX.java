package com.zte.mifavor.androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.SystemProperties;
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
public class PreferenceZTEX extends Preference {
    private static boolean o0 = E0();
    private final boolean S;
    private CharSequence T;
    private Context U;
    private TextView V;
    private TextView W;
    private TextView X;
    private View Y;
    private boolean Z;
    private int a0;
    private int b0;
    private int c0;
    private int d0;
    private int e0;
    private int f0;
    private boolean g0;
    private String h0;
    private boolean i0;
    private Drawable j0;
    private boolean k0;
    private int l0;
    private boolean m0;
    private LinearLayout n0;

    public PreferenceZTEX(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.S = false;
        this.X = null;
        this.Z = true;
        this.a0 = -1;
        this.b0 = -1;
        this.c0 = -1;
        this.d0 = -1;
        this.e0 = -1;
        this.f0 = -1;
        this.g0 = true;
        this.h0 = "";
        this.i0 = true;
        this.j0 = null;
        this.k0 = true;
        this.l0 = -2;
        this.m0 = false;
        this.n0 = null;
        H0(context, attributeSet);
    }

    public static boolean E0() {
        boolean z = false;
        try {
            z = SystemProperties.getBoolean("ro.vendor.feature.summary_default_botttom", false);
            Log.d("Z#PreferenceZTEXX", "get Properties Is Summary Right out. getBoolean summary_default_botttom, isBottom=" + z);
            return !z;
        } catch (Exception e2) {
            Log.w("Z#PreferenceZTEXX", "get Properties Is Summary Right error, e", e2);
            Log.d("Z#PreferenceZTEXX", "get Properties Is Summary Right out. isBottom = " + z);
            return !z;
        }
    }

    private void H0(Context context, AttributeSet attributeSet) {
        this.U = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceZTE);
        this.T = obtainStyledAttributes.getString(R.styleable.PreferenceZTE_status);
        this.h0 = obtainStyledAttributes.getString(R.styleable.PreferenceZTE_myTag);
        this.i0 = obtainStyledAttributes.getBoolean(R.styleable.PreferenceZTE_isCard, true);
        this.j0 = obtainStyledAttributes.getDrawable(R.styleable.PreferenceZTE_itemBackground);
        this.l0 = obtainStyledAttributes.getInt(R.styleable.PreferenceZTE_number, -2);
        this.k0 = obtainStyledAttributes.getBoolean(R.styleable.PreferenceZTE_specifyHeight, true);
        obtainStyledAttributes.recycle();
    }

    private static boolean I0(Context context, AttributeSet attributeSet) {
        Log.d("Z#PreferenceZTEXX", "Is Summary Right in. mIsSummaryRight=" + o0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceZTE, 0, 0);
        o0 = obtainStyledAttributes.getBoolean(R.styleable.PreferenceZTE_isSummaryRight, E0());
        obtainStyledAttributes.recycle();
        Log.d("Z#PreferenceZTEXX", "Is Summary Right out. mIsSummaryRight=" + o0);
        return o0;
    }

    public boolean A0() {
        return this.i0;
    }

    public Drawable B0() {
        return this.j0;
    }

    public String C0() {
        return this.h0;
    }

    public int D0() {
        return this.l0;
    }

    public boolean F0() {
        return this.k0;
    }

    public CharSequence G0() {
        return this.T;
    }

    public void J0(int i2) {
        TextView textView = this.W;
        if (textView == null) {
            this.a0 = i2;
            return;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = i2;
        this.W.setLayoutParams(layoutParams);
    }

    public void K0(int i2, int i3) {
        Log.w("Z#PreferenceZTEXX", "set Summary Horizontal Padding in. paddingTop=" + i2 + ", paddingBottom=" + i3 + ", mSummaryView=" + this.V);
        TextView textView = this.V;
        if (textView != null) {
            textView.setPadding(0, i2, 0, i3);
        }
        this.d0 = i2;
        this.e0 = i3;
    }

    public void L0(int i2) {
        Log.w("Z#PreferenceZTEXX", "set Summary Max Width in. maxWidth=" + i2 + ", mSummaryView=" + this.V);
        TextView textView = this.V;
        if (textView != null) {
            textView.setMaxWidth(i2);
        }
        this.c0 = i2;
    }

    public void M0(int i2, int i3) {
        TextView textView = this.V;
        if (textView == null) {
            this.b0 = i2;
            this.f0 = i3;
        } else {
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            layoutParams.width = i2;
            layoutParams.height = i3;
            this.V.setLayoutParams(layoutParams);
        }
    }

    public void N0(int i2) {
        TextView textView = this.V;
        if (textView == null) {
            this.b0 = i2;
            return;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = i2;
        this.V.setLayoutParams(layoutParams);
    }

    @Override // androidx.preference.Preference
    public void R(PreferenceViewHolder preferenceViewHolder) {
        TextView textView;
        int i2;
        int i3;
        super.R(preferenceViewHolder);
        this.n0 = (LinearLayout) preferenceViewHolder.N(R.id.common_preference_layout);
        this.X = (TextView) preferenceViewHolder.N(android.R.id.title);
        this.V = (TextView) preferenceViewHolder.N(android.R.id.summary);
        int i4 = this.b0;
        if (-1 != i4) {
            N0(i4);
        }
        int i5 = this.b0;
        if (-1 != i5 && -1 != (i3 = this.f0)) {
            M0(i5, i3);
        }
        int i6 = this.d0;
        if (-1 != i6 && -1 != (i2 = this.e0)) {
            K0(i6, i2);
        }
        int i7 = this.c0;
        if (-1 != i7) {
            L0(i7);
        }
        if (this.m0 && (textView = this.V) != null) {
            textView.setFontFeatureSettings("tnum");
        }
        LinearLayout linearLayout = (LinearLayout) preferenceViewHolder.N(android.R.id.widget_frame);
        if (linearLayout != null) {
            View findViewById = linearLayout.findViewById(R.id.indicator);
            this.Y = findViewById;
            if (findViewById != null) {
                if (this.Z) {
                    findViewById.setVisibility(0);
                } else {
                    findViewById.setVisibility(8);
                }
            }
        }
        if (o0) {
            this.W = (TextView) preferenceViewHolder.N(R.id.status);
        } else if (linearLayout != null) {
            this.W = (TextView) linearLayout.findViewById(R.id.status);
        }
        if (this.W != null) {
            CharSequence G0 = G0();
            if (TextUtils.isEmpty(G0)) {
                this.W.setVisibility(8);
                return;
            }
            this.W.setText(G0);
            this.W.setVisibility(0);
            this.W.setEnabled(this.g0);
            int i8 = this.a0;
            if (-1 != i8) {
                J0(i8);
            }
        }
    }

    public PreferenceZTEX(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.S = false;
        this.X = null;
        this.Z = true;
        this.a0 = -1;
        this.b0 = -1;
        this.c0 = -1;
        this.d0 = -1;
        this.e0 = -1;
        this.f0 = -1;
        this.g0 = true;
        this.h0 = "";
        this.i0 = true;
        this.j0 = null;
        this.k0 = true;
        this.l0 = -2;
        this.m0 = false;
        this.n0 = null;
        H0(context, attributeSet);
    }

    public PreferenceZTEX(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, I0(context, attributeSet) ? R.attr.preferenceStyleMFSX : R.attr.preferenceStyleMFS);
    }
}
