package com.zte.mifavor.preference;

import android.R;
import android.content.Context;
import android.preference.SwitchPreference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/* loaded from: classes2.dex */
public class SwitchPreferenceZTE extends SwitchPreference {

    /* renamed from: c, reason: collision with root package name */
    private View f17352c;

    /* renamed from: h, reason: collision with root package name */
    private boolean f17353h;

    /* renamed from: i, reason: collision with root package name */
    private View f17354i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f17355j;

    /* renamed from: k, reason: collision with root package name */
    private TextView f17356k;

    public SwitchPreferenceZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.f17352c = null;
        this.f17353h = false;
        this.f17354i = null;
        this.f17355j = false;
        this.f17356k = null;
    }

    public boolean c() {
        if (this.f17355j) {
            if (getIntent() != null || getFragment() != null || getOnPreferenceClickListener() != null) {
                if (!this.f17353h) {
                    return true;
                }
                View view = this.f17354i;
                if (view != null && view.getVisibility() == 0) {
                    return true;
                }
            }
        } else if (getIntent() != null || getFragment() != null || getOnPreferenceClickListener() != null) {
            return true;
        }
        return false;
    }

    @Override // android.preference.SwitchPreference, android.preference.Preference
    protected void onBindView(View view) {
        View view2;
        View view3;
        super.onBindView(view);
        this.f17356k = (TextView) view.findViewById(R.id.title);
        View findViewById = view.findViewById(R.id.widget_frame);
        if (findViewById != null) {
            ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
            layoutParams.height = -1;
            findViewById.setLayoutParams(layoutParams);
        }
        View findViewById2 = view.findViewById(com.zte.extres.R.id.entrance_indicator);
        this.f17354i = findViewById2;
        if (findViewById2 != null) {
            findViewById2.setVisibility(c() ? 0 : 8);
        }
        View view4 = this.f17354i;
        if (view4 != null && this.f17353h) {
            view4.setVisibility(8);
        }
        View findViewById3 = view.findViewById(R.id.switch_widget);
        this.f17352c = findViewById3;
        if (findViewById3 != null && this.f17355j && (this.f17353h || (view3 = this.f17354i) == null || view3.getVisibility() != 0)) {
            this.f17352c.setClickable(false);
        }
        View view5 = this.f17354i;
        if (view5 == null || view5.getVisibility() == 0 || (view2 = (View) this.f17352c.getParent()) == null || !(view2 instanceof LinearLayout)) {
            return;
        }
        view2.setPadding(0, 0, 0, 0);
    }

    @Override // android.preference.TwoStatePreference, android.preference.Preference
    protected void onClick() {
        if (c()) {
            return;
        }
        super.onClick();
    }

    @Override // android.preference.Preference
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        TextView textView = this.f17356k;
        if (textView != null) {
            textView.setAlpha(z ? 1.0f : 0.26f);
        }
    }

    public SwitchPreferenceZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f17352c = null;
        this.f17353h = false;
        this.f17354i = null;
        this.f17355j = false;
        this.f17356k = null;
    }

    public SwitchPreferenceZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.zte.extres.R.attr.switchPreferenceStyleZTE);
    }
}
