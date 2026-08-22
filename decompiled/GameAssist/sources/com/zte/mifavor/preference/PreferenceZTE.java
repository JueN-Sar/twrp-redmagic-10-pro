package com.zte.mifavor.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class PreferenceZTE extends Preference {

    /* renamed from: c, reason: collision with root package name */
    private CharSequence f17330c;

    /* renamed from: h, reason: collision with root package name */
    private Context f17331h;

    /* renamed from: i, reason: collision with root package name */
    private TextView f17332i;

    /* renamed from: j, reason: collision with root package name */
    private TextView f17333j;

    /* renamed from: k, reason: collision with root package name */
    private TextView f17334k;

    /* renamed from: l, reason: collision with root package name */
    private View f17335l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f17336m;

    /* renamed from: n, reason: collision with root package name */
    private int f17337n;

    /* renamed from: o, reason: collision with root package name */
    private boolean f17338o;

    public PreferenceZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.f17334k = null;
        this.f17336m = true;
        this.f17337n = -1;
        this.f17338o = true;
        d(context, attributeSet);
    }

    private void d(Context context, AttributeSet attributeSet) {
        this.f17331h = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceZTE);
        this.f17330c = obtainStyledAttributes.getString(R.styleable.PreferenceZTE_status);
        obtainStyledAttributes.recycle();
    }

    public CharSequence c() {
        return this.f17330c;
    }

    @Override // android.preference.Preference
    protected void onBindView(View view) {
        super.onBindView(view);
        this.f17334k = (TextView) view.findViewById(android.R.id.title);
        this.f17332i = (TextView) view.findViewById(android.R.id.summary);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(android.R.id.widget_frame);
        if (linearLayout != null) {
            TextView textView = (TextView) linearLayout.findViewById(R.id.status);
            this.f17333j = textView;
            if (textView != null) {
                CharSequence c2 = c();
                if (TextUtils.isEmpty(c2)) {
                    this.f17333j.setVisibility(8);
                } else {
                    this.f17333j.setText(c2);
                    this.f17333j.setVisibility(0);
                    this.f17333j.setEnabled(this.f17338o);
                    if (-1 != this.f17337n) {
                        ViewGroup.LayoutParams layoutParams = this.f17333j.getLayoutParams();
                        layoutParams.width = this.f17337n;
                        this.f17333j.setLayoutParams(layoutParams);
                    }
                }
            }
            View findViewById = linearLayout.findViewById(R.id.indicator);
            this.f17335l = findViewById;
            if (findViewById != null) {
                if (this.f17336m) {
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
        TextView textView = this.f17333j;
        if (textView != null) {
            textView.setEnabled(z);
        }
        TextView textView2 = this.f17334k;
        if (textView2 != null) {
            textView2.setAlpha(z ? 1.0f : 0.26f);
        }
        this.f17338o = z;
    }

    @Override // android.preference.Preference
    public void setSummary(CharSequence charSequence) {
        TextView textView;
        if (!TextUtils.isEmpty(charSequence) && (textView = this.f17332i) != null) {
            textView.setVisibility(0);
        }
        super.setSummary(charSequence);
    }

    @Override // android.preference.Preference
    public void setSummary(int i2) {
        TextView textView;
        String string = this.f17331h.getString(i2);
        if (string != null && string.length() > 0 && (textView = this.f17332i) != null) {
            textView.setVisibility(0);
        }
        super.setSummary(i2);
    }

    public PreferenceZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f17334k = null;
        this.f17336m = true;
        this.f17337n = -1;
        this.f17338o = true;
        d(context, attributeSet);
    }

    public PreferenceZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.preferenceStyleZTE);
        d(context, attributeSet);
    }
}
