package com.zte.mifavor.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.SystemProperties;
import android.preference.ListPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class ListPreferenceZTEX extends ListPreference {

    /* renamed from: p, reason: collision with root package name */
    private static boolean f17319p = c();

    /* renamed from: c, reason: collision with root package name */
    private CharSequence f17320c;

    /* renamed from: h, reason: collision with root package name */
    private Context f17321h;

    /* renamed from: i, reason: collision with root package name */
    private TextView f17322i;

    /* renamed from: j, reason: collision with root package name */
    private TextView f17323j;

    /* renamed from: k, reason: collision with root package name */
    private int f17324k;

    /* renamed from: l, reason: collision with root package name */
    private int f17325l;

    /* renamed from: m, reason: collision with root package name */
    private int f17326m;

    /* renamed from: n, reason: collision with root package name */
    private boolean f17327n;

    /* renamed from: o, reason: collision with root package name */
    private CharSequence f17328o;

    public ListPreferenceZTEX(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.f17324k = -1;
        this.f17325l = -1;
        this.f17326m = -1;
        this.f17327n = true;
        this.f17328o = "";
        e(context, attributeSet);
    }

    public static boolean c() {
        boolean z = false;
        try {
            z = SystemProperties.getBoolean("ro.vendor.feature.summary_default_botttom", false);
            Log.d("ListPreferenceZTE", "get Properties Is Summary Right out. getBoolean summary_default_botttom, isBottom=" + z);
            return !z;
        } catch (Exception e2) {
            Log.w("ListPreferenceZTE", "get Properties Is Summary Right error, e", e2);
            Log.d("ListPreferenceZTE", "get Properties Is Summary Right out. isBottom = " + z);
            return !z;
        }
    }

    private void e(Context context, AttributeSet attributeSet) {
        this.f17321h = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceZTE);
        this.f17320c = obtainStyledAttributes.getString(R.styleable.PreferenceZTE_status);
        this.f17328o = obtainStyledAttributes.getString(R.styleable.PreferenceZTE_myTag);
        obtainStyledAttributes.recycle();
    }

    private static boolean f(Context context, AttributeSet attributeSet) {
        Log.d("ListPreferenceZTE", "Is Summary Right in. mIsSummaryRight=" + f17319p);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceZTE, 0, 0);
        f17319p = obtainStyledAttributes.getBoolean(R.styleable.PreferenceZTE_isSummaryRight, c());
        obtainStyledAttributes.recycle();
        Log.d("ListPreferenceZTE", "Is Summary Right out. mIsSummaryRight=" + f17319p);
        return f17319p;
    }

    public CharSequence d() {
        return this.f17320c;
    }

    public void h(int i2) {
        TextView textView = this.f17323j;
        if (textView == null) {
            this.f17324k = i2;
            return;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = i2;
        this.f17323j.setLayoutParams(layoutParams);
    }

    public void j(int i2, int i3) {
        TextView textView = this.f17322i;
        if (textView == null) {
            this.f17325l = i2;
            this.f17326m = i3;
        } else {
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            layoutParams.width = i2;
            layoutParams.height = i3;
            this.f17322i.setLayoutParams(layoutParams);
        }
    }

    public void l(int i2) {
        TextView textView = this.f17322i;
        if (textView == null) {
            this.f17325l = i2;
            return;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = i2;
        this.f17322i.setLayoutParams(layoutParams);
    }

    @Override // android.preference.Preference
    protected void onBindView(View view) {
        int i2;
        super.onBindView(view);
        this.f17322i = (TextView) view.findViewById(android.R.id.summary);
        int i3 = this.f17325l;
        if (-1 != i3) {
            l(i3);
        }
        int i4 = this.f17325l;
        if (-1 != i4 && -1 != (i2 = this.f17326m)) {
            j(i4, i2);
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(android.R.id.widget_frame);
        if (f17319p) {
            this.f17323j = (TextView) view.findViewById(R.id.status);
        } else if (linearLayout != null) {
            this.f17323j = (TextView) linearLayout.findViewById(R.id.status);
        }
        if (this.f17323j != null) {
            CharSequence d2 = d();
            if (TextUtils.isEmpty(d2)) {
                this.f17323j.setVisibility(8);
                return;
            }
            this.f17323j.setText(d2);
            this.f17323j.setVisibility(0);
            this.f17323j.setEnabled(this.f17327n);
            int i5 = this.f17324k;
            if (-1 != i5) {
                h(i5);
            }
        }
    }

    @Override // android.preference.Preference
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        TextView textView = this.f17323j;
        if (textView != null) {
            textView.setEnabled(z);
        }
        this.f17327n = z;
        Log.d("ListPreferenceZTE", "setEnabled out. enabled = " + z + ", mStatusView = " + this.f17323j);
    }

    @Override // android.preference.ListPreference, android.preference.Preference
    public void setSummary(CharSequence charSequence) {
        TextView textView;
        if (!TextUtils.isEmpty(charSequence) && (textView = this.f17322i) != null) {
            textView.setVisibility(0);
        }
        super.setSummary(charSequence);
    }

    @Override // android.preference.Preference
    public void setSummary(int i2) {
        TextView textView;
        String string = this.f17321h.getString(i2);
        if (string != null && string.length() > 0 && (textView = this.f17322i) != null) {
            textView.setVisibility(0);
        }
        super.setSummary(i2);
    }

    public ListPreferenceZTEX(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f17324k = -1;
        this.f17325l = -1;
        this.f17326m = -1;
        this.f17327n = true;
        this.f17328o = "";
        e(context, attributeSet);
    }

    public ListPreferenceZTEX(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, f(context, attributeSet) ? R.attr.dialogPreferenceStyleX : android.R.attr.dialogPreferenceStyle);
        this.f17324k = -1;
        this.f17325l = -1;
        this.f17326m = -1;
        this.f17327n = true;
        this.f17328o = "";
        e(context, attributeSet);
    }
}
