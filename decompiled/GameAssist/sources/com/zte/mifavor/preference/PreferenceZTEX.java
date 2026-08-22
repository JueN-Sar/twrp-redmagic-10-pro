package com.zte.mifavor.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.SystemProperties;
import android.preference.Preference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class PreferenceZTEX extends Preference {
    private static boolean x = c();

    /* renamed from: c, reason: collision with root package name */
    private CharSequence f17339c;

    /* renamed from: h, reason: collision with root package name */
    private Context f17340h;

    /* renamed from: i, reason: collision with root package name */
    private TextView f17341i;

    /* renamed from: j, reason: collision with root package name */
    private TextView f17342j;

    /* renamed from: k, reason: collision with root package name */
    private TextView f17343k;

    /* renamed from: l, reason: collision with root package name */
    private View f17344l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f17345m;

    /* renamed from: n, reason: collision with root package name */
    private int f17346n;

    /* renamed from: o, reason: collision with root package name */
    private int f17347o;

    /* renamed from: p, reason: collision with root package name */
    private int f17348p;

    /* renamed from: q, reason: collision with root package name */
    private int f17349q;

    /* renamed from: r, reason: collision with root package name */
    private int f17350r;

    /* renamed from: s, reason: collision with root package name */
    private int f17351s;
    private boolean t;
    private CharSequence u;
    private boolean v;
    private LinearLayout w;

    public PreferenceZTEX(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.f17343k = null;
        this.f17345m = true;
        this.f17346n = -1;
        this.f17347o = -1;
        this.f17348p = -1;
        this.f17349q = -1;
        this.f17350r = -1;
        this.f17351s = -1;
        this.t = true;
        this.u = "";
        this.v = false;
        this.w = null;
        e(context, attributeSet);
    }

    public static boolean c() {
        boolean z = false;
        try {
            z = SystemProperties.getBoolean("ro.vendor.feature.summary_default_botttom", false);
            Log.d("Z#PreferenceZTEX", "get Properties Is Summary Right out. getBoolean summary_default_botttom, isBottom=" + z);
            return !z;
        } catch (Exception e2) {
            Log.w("Z#PreferenceZTEX", "get Properties Is Summary Right error, e", e2);
            Log.d("Z#PreferenceZTEX", "get Properties Is Summary Right out. isBottom = " + z);
            return !z;
        }
    }

    private void e(Context context, AttributeSet attributeSet) {
        this.f17340h = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceZTE);
        this.f17339c = obtainStyledAttributes.getString(R.styleable.PreferenceZTE_status);
        this.u = obtainStyledAttributes.getString(R.styleable.PreferenceZTE_myTag);
        obtainStyledAttributes.recycle();
    }

    private static boolean f(Context context, AttributeSet attributeSet) {
        Log.d("Z#PreferenceZTEX", "Is Summary Right in. mIsSummaryRight=" + x);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceZTE, 0, 0);
        x = obtainStyledAttributes.getBoolean(R.styleable.PreferenceZTE_isSummaryRight, c());
        obtainStyledAttributes.recycle();
        Log.d("Z#PreferenceZTEX", "Is Summary Right out. mIsSummaryRight=" + x);
        return x;
    }

    public CharSequence d() {
        return this.f17339c;
    }

    public void h(int i2) {
        TextView textView = this.f17342j;
        if (textView == null) {
            this.f17346n = i2;
            return;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = i2;
        this.f17342j.setLayoutParams(layoutParams);
    }

    public void j(int i2, int i3) {
        Log.w("Z#PreferenceZTEX", "set Summary Horizontal Padding in. paddingTop=" + i2 + ", paddingBottom=" + i3 + ", mSummaryView=" + this.f17341i);
        TextView textView = this.f17341i;
        if (textView != null) {
            textView.setPadding(0, i2, 0, i3);
        }
        this.f17349q = i2;
        this.f17350r = i3;
    }

    public void l(int i2) {
        Log.w("Z#PreferenceZTEX", "set Summary Max Width in. maxWidth=" + i2 + ", mSummaryView=" + this.f17341i);
        TextView textView = this.f17341i;
        if (textView != null) {
            textView.setMaxWidth(i2);
        }
        this.f17348p = i2;
    }

    public void n(int i2, int i3) {
        TextView textView = this.f17341i;
        if (textView == null) {
            this.f17347o = i2;
            this.f17351s = i3;
        } else {
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            layoutParams.width = i2;
            layoutParams.height = i3;
            this.f17341i.setLayoutParams(layoutParams);
        }
    }

    public void o(int i2) {
        TextView textView = this.f17341i;
        if (textView == null) {
            this.f17347o = i2;
            return;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = i2;
        this.f17341i.setLayoutParams(layoutParams);
    }

    @Override // android.preference.Preference
    protected void onBindView(View view) {
        TextView textView;
        int i2;
        int i3;
        super.onBindView(view);
        this.w = (LinearLayout) view.findViewById(R.id.common_preference_layout);
        this.f17343k = (TextView) view.findViewById(android.R.id.title);
        this.f17341i = (TextView) view.findViewById(android.R.id.summary);
        int i4 = this.f17347o;
        if (-1 != i4) {
            o(i4);
        }
        int i5 = this.f17347o;
        if (-1 != i5 && -1 != (i3 = this.f17351s)) {
            n(i5, i3);
        }
        int i6 = this.f17349q;
        if (-1 != i6 && -1 != (i2 = this.f17350r)) {
            j(i6, i2);
        }
        int i7 = this.f17348p;
        if (-1 != i7) {
            l(i7);
        }
        if (this.v && (textView = this.f17341i) != null) {
            textView.setFontFeatureSettings("tnum");
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(android.R.id.widget_frame);
        if (x) {
            this.f17342j = (TextView) view.findViewById(R.id.status);
        } else if (linearLayout != null) {
            this.f17342j = (TextView) linearLayout.findViewById(R.id.status);
        }
        if (this.f17342j != null) {
            CharSequence d2 = d();
            if (TextUtils.isEmpty(d2)) {
                this.f17342j.setVisibility(8);
            } else {
                this.f17342j.setText(d2);
                this.f17342j.setVisibility(0);
                this.f17342j.setEnabled(this.t);
                int i8 = this.f17346n;
                if (-1 != i8) {
                    h(i8);
                }
            }
        }
        if (linearLayout != null) {
            View findViewById = linearLayout.findViewById(R.id.indicator);
            this.f17344l = findViewById;
            if (findViewById != null) {
                if (this.f17345m) {
                    findViewById.setVisibility(0);
                } else {
                    findViewById.setVisibility(8);
                }
            }
        }
    }

    @Override // android.preference.Preference
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        TextView textView = this.f17342j;
        if (textView != null) {
            textView.setEnabled(z);
        }
        TextView textView2 = this.f17343k;
        if (textView2 != null) {
            textView2.setAlpha(z ? 1.0f : 0.26f);
        }
        this.t = z;
    }

    @Override // android.preference.Preference
    public void setSummary(CharSequence charSequence) {
        TextView textView;
        if (!TextUtils.isEmpty(charSequence) && (textView = this.f17341i) != null) {
            textView.setVisibility(0);
        }
        super.setSummary(charSequence);
    }

    @Override // android.preference.Preference
    public void setSummary(int i2) {
        TextView textView;
        String string = this.f17340h.getString(i2);
        if (string != null && string.length() > 0 && (textView = this.f17341i) != null) {
            textView.setVisibility(0);
        }
        super.setSummary(i2);
    }

    public PreferenceZTEX(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f17343k = null;
        this.f17345m = true;
        this.f17346n = -1;
        this.f17347o = -1;
        this.f17348p = -1;
        this.f17349q = -1;
        this.f17350r = -1;
        this.f17351s = -1;
        this.t = true;
        this.u = "";
        this.v = false;
        this.w = null;
        e(context, attributeSet);
    }

    public PreferenceZTEX(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f(context, attributeSet) ? R.attr.preferenceStyleZTEX : R.attr.preferenceStyleZTE);
        e(context, attributeSet);
    }
}
