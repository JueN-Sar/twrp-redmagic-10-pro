package com.zte.mifavor.androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SwitchPreference;
import com.zte.extres.R;
import com.zte.mifavor.utils.Utils;

/* loaded from: classes2.dex */
public class SwitchPreferenceZTE extends SwitchPreference {
    private final boolean a0;
    private Context b0;
    private String c0;
    private boolean d0;
    private Drawable e0;
    private int f0;
    private boolean g0;
    private View h0;
    private View i0;
    private boolean j0;
    private TextView k0;

    public SwitchPreferenceZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.a0 = false;
        this.c0 = "";
        this.d0 = true;
        this.e0 = null;
        this.f0 = -2;
        this.g0 = false;
        this.h0 = null;
        this.i0 = null;
        this.j0 = false;
        this.k0 = null;
        N0(context, attributeSet);
    }

    private void N0(Context context, AttributeSet attributeSet) {
        this.b0 = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceZTE);
        this.c0 = obtainStyledAttributes.getString(R.styleable.PreferenceZTE_myTag);
        this.d0 = obtainStyledAttributes.getBoolean(R.styleable.PreferenceZTE_isCard, true);
        this.e0 = obtainStyledAttributes.getDrawable(R.styleable.PreferenceZTE_itemBackground);
        this.f0 = obtainStyledAttributes.getInt(R.styleable.PreferenceZTE_number, -2);
        this.j0 = obtainStyledAttributes.getBoolean(R.styleable.PreferenceZTE_customClickable, false);
        obtainStyledAttributes.recycle();
    }

    public boolean L0() {
        return this.d0;
    }

    public int M0() {
        return this.f0;
    }

    public boolean O0() {
        if (this.j0) {
            if (t() != null || p() != null || x() != null) {
                if (!this.g0) {
                    return true;
                }
                View view = this.h0;
                if (view != null && view.getVisibility() == 0) {
                    return true;
                }
            }
        } else if (t() != null || p() != null || x() != null) {
            return true;
        }
        return false;
    }

    @Override // androidx.preference.SwitchPreference, androidx.preference.Preference
    public void R(PreferenceViewHolder preferenceViewHolder) {
        View view;
        View view2;
        super.R(preferenceViewHolder);
        this.k0 = (TextView) preferenceViewHolder.N(android.R.id.title);
        View N = preferenceViewHolder.N(android.R.id.widget_frame);
        if (N != null) {
            ViewGroup.LayoutParams layoutParams = N.getLayoutParams();
            layoutParams.height = -1;
            N.setLayoutParams(layoutParams);
        }
        View N2 = preferenceViewHolder.N(R.id.entrance_indicator);
        this.h0 = N2;
        if (N2 != null) {
            N2.setVisibility(O0() ? 0 : 8);
        }
        View view3 = this.h0;
        if (view3 != null && this.g0) {
            view3.setVisibility(8);
        }
        View N3 = preferenceViewHolder.N(android.R.id.switch_widget);
        this.i0 = N3;
        if (N3 != null && this.j0 && (this.g0 || (view2 = this.h0) == null || view2.getVisibility() != 0)) {
            this.i0.setClickable(false);
        }
        View view4 = this.h0;
        if (view4 == null || view4.getVisibility() == 0 || (view = (View) this.i0.getParent()) == null || !(view instanceof LinearLayout)) {
            return;
        }
        view.setPadding(0, 0, 0, 0);
    }

    @Override // androidx.preference.TwoStatePreference, androidx.preference.Preference
    protected void S() {
        boolean O0 = O0();
        Log.i("Z#SwitchPreferenceZTE", "onClick...  key=" + u() + ", hasEntrance=" + O0 + ", mIsHideEntranceIndicator=" + this.g0 + ", mCustomClickable=" + this.j0);
        if (O0) {
            return;
        }
        super.S();
    }

    @Override // androidx.preference.SwitchPreference, androidx.preference.Preference
    protected void b0(View view) {
        super.b0(view);
        Utils.a(this.b0, R.raw.switch_icon);
    }

    public SwitchPreferenceZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a0 = false;
        this.c0 = "";
        this.d0 = true;
        this.e0 = null;
        this.f0 = -2;
        this.g0 = false;
        this.h0 = null;
        this.i0 = null;
        this.j0 = false;
        this.k0 = null;
        N0(context, attributeSet);
    }

    public SwitchPreferenceZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.switchPreferenceStyleMFS);
    }
}
