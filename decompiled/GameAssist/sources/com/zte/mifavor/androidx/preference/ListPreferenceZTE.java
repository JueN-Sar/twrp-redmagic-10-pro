package com.zte.mifavor.androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceViewHolder;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class ListPreferenceZTE extends ListPreference {
    private CharSequence d0;
    private Context e0;
    private TextView f0;
    private TextView g0;
    private int h0;
    private boolean i0;
    private String j0;
    private boolean k0;
    private Drawable l0;
    private int m0;

    public ListPreferenceZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.h0 = -1;
        this.i0 = true;
        this.j0 = "";
        this.k0 = true;
        this.l0 = null;
        this.m0 = -2;
        Q0(context, attributeSet);
    }

    private void Q0(Context context, AttributeSet attributeSet) {
        this.e0 = context;
        this.d0 = K0();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceZTE);
        this.j0 = obtainStyledAttributes.getString(R.styleable.PreferenceZTE_myTag);
        this.k0 = obtainStyledAttributes.getBoolean(R.styleable.PreferenceZTE_isCard, true);
        this.l0 = obtainStyledAttributes.getDrawable(R.styleable.PreferenceZTE_itemBackground);
        this.m0 = obtainStyledAttributes.getInt(R.styleable.PreferenceZTE_number, -2);
        obtainStyledAttributes.recycle();
        Log.i("ListPreferenceZTEx", "init Prefrence Status XPG#, key=" + u() + ", mStatus=" + ((Object) this.d0) + ", mMyTag=" + this.j0 + ", mIsCard=" + this.k0 + ", mNumber=" + this.m0);
    }

    public boolean N0() {
        return this.k0;
    }

    public int O0() {
        return this.m0;
    }

    public CharSequence P0() {
        return this.d0;
    }

    @Override // androidx.preference.Preference
    public void R(PreferenceViewHolder preferenceViewHolder) {
        super.R(preferenceViewHolder);
        this.f0 = (TextView) preferenceViewHolder.N(android.R.id.summary);
        TextView textView = (TextView) preferenceViewHolder.N(R.id.status);
        this.g0 = textView;
        if (textView == null) {
            Log.w("ListPreferenceZTEx", "onBindViewHolder statusView = " + this.g0);
            return;
        }
        CharSequence P0 = P0();
        if (TextUtils.isEmpty(P0)) {
            this.g0.setVisibility(8);
            return;
        }
        this.g0.setText(P0);
        this.g0.setVisibility(0);
        this.g0.setEnabled(this.i0);
        if (-1 != this.h0) {
            ViewGroup.LayoutParams layoutParams = this.g0.getLayoutParams();
            layoutParams.width = this.h0;
            this.g0.setLayoutParams(layoutParams);
            Log.d("ListPreferenceZTEx", "onBindViewHolder the width of statusView = " + this.g0.getLayoutParams().width);
        }
    }

    public ListPreferenceZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.h0 = -1;
        this.i0 = true;
        this.j0 = "";
        this.k0 = true;
        this.l0 = null;
        this.m0 = -2;
        Q0(context, attributeSet);
    }

    public ListPreferenceZTE(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.dialogPreferenceStyle);
        this.h0 = -1;
        this.i0 = true;
        this.j0 = "";
        this.k0 = true;
        this.l0 = null;
        this.m0 = -2;
        Q0(context, attributeSet);
    }
}
