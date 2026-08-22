package com.zte.mifavor.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.ListPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class ListPreferenceZTE extends ListPreference {

    /* renamed from: c, reason: collision with root package name */
    private CharSequence f17313c;

    /* renamed from: h, reason: collision with root package name */
    private Context f17314h;

    /* renamed from: i, reason: collision with root package name */
    private TextView f17315i;

    /* renamed from: j, reason: collision with root package name */
    private TextView f17316j;

    /* renamed from: k, reason: collision with root package name */
    private int f17317k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f17318l;

    public ListPreferenceZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.f17317k = -1;
        this.f17318l = true;
        d(context, attributeSet);
    }

    private void d(Context context, AttributeSet attributeSet) {
        this.f17314h = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceZTE);
        this.f17313c = obtainStyledAttributes.getString(R.styleable.PreferenceZTE_status);
        obtainStyledAttributes.recycle();
    }

    public CharSequence c() {
        return this.f17313c;
    }

    @Override // android.preference.Preference
    protected void onBindView(View view) {
        super.onBindView(view);
        this.f17315i = (TextView) view.findViewById(android.R.id.summary);
        TextView textView = (TextView) view.findViewById(R.id.status);
        this.f17316j = textView;
        if (textView == null) {
            Log.w("ListPreferenceZTE", "onBindView statusView = " + this.f17316j);
            return;
        }
        CharSequence c2 = c();
        if (TextUtils.isEmpty(c2)) {
            this.f17316j.setVisibility(8);
            return;
        }
        this.f17316j.setText(c2);
        this.f17316j.setVisibility(0);
        this.f17316j.setEnabled(this.f17318l);
        if (-1 != this.f17317k) {
            ViewGroup.LayoutParams layoutParams = this.f17316j.getLayoutParams();
            layoutParams.width = this.f17317k;
            this.f17316j.setLayoutParams(layoutParams);
            Log.d("ListPreferenceZTE", "onBindView the width of statusView = " + this.f17316j.getLayoutParams().width);
        }
    }

    @Override // android.preference.Preference
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        TextView textView = this.f17316j;
        if (textView != null) {
            textView.setEnabled(z);
        }
        this.f17318l = z;
    }

    @Override // android.preference.ListPreference, android.preference.Preference
    public void setSummary(CharSequence charSequence) {
        TextView textView;
        if (!TextUtils.isEmpty(charSequence) && (textView = this.f17315i) != null) {
            textView.setVisibility(0);
        }
        super.setSummary(charSequence);
    }

    @Override // android.preference.Preference
    public void setSummary(int i2) {
        TextView textView;
        String string = this.f17314h.getString(i2);
        if (string != null && string.length() > 0 && (textView = this.f17315i) != null) {
            textView.setVisibility(0);
        }
        super.setSummary(i2);
    }

    public ListPreferenceZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f17317k = -1;
        this.f17318l = true;
        d(context, attributeSet);
    }

    public ListPreferenceZTE(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, android.R.attr.dialogPreferenceStyle);
        this.f17317k = -1;
        this.f17318l = true;
        d(context, attributeSet);
    }
}
