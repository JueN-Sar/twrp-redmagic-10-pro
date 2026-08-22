package com.zte.mifavor.androidx.behavior;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class PrimaryGroupTitleBehavior extends BaseSinkGroupTitleBehavior {
    private boolean y;
    private boolean z;

    @Override // com.zte.mifavor.androidx.behavior.BaseSinkGroupTitleBehavior
    protected boolean K(CoordinatorLayout coordinatorLayout, View view, View view2, float f2, int i2, int i3, float f3, int i4) {
        boolean z;
        boolean z2;
        float f4;
        int i5;
        TextView textView = (TextView) view;
        int i6 = this.f17111i;
        if (Math.abs(i2) == this.f17091k) {
            textView.setSingleLine();
            textView.setEllipsize(TextUtils.TruncateAt.END);
            i6 = Math.max(this.f17111i - this.f17109c, 0);
        } else if (textView.isSingleLine()) {
            textView.setSingleLine(false);
            textView.setMaxLines(2);
            textView.setEllipsize(TextUtils.TruncateAt.END);
        }
        if (Math.abs(i2) == this.f17091k) {
            textView.setTextAppearance(R.style.mfvc_appbar_primary_font);
            int i7 = this.f17110h;
            if (i7 != 0) {
                textView.setTextColor(i7);
            }
        } else if (i2 == 0) {
            textView.setTextAppearance(R.style.mfvc_appbar_sink_primary_font);
            int i8 = this.f17110h;
            if (i8 != 0) {
                textView.setTextColor(i8);
            }
        }
        int i9 = this.f17095o;
        float f5 = ((i9 - r12) * f2) + this.f17094n;
        textView.setTextSize(0, f5);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) textView.getLayoutParams();
        if (((ViewGroup.MarginLayoutParams) layoutParams).height != i4) {
            ((ViewGroup.MarginLayoutParams) layoutParams).height = i4;
            z = true;
        } else {
            z = false;
        }
        if (((ViewGroup.MarginLayoutParams) layoutParams).width != i6) {
            ((ViewGroup.MarginLayoutParams) layoutParams).width = i6;
            z2 = true;
        } else {
            z2 = false;
        }
        Log.d("BS#PGroupTitleBehavior", "onCustomDependentViewChanged, setLayoutParams height=" + ((ViewGroup.MarginLayoutParams) layoutParams).height + ", width=" + ((ViewGroup.MarginLayoutParams) layoutParams).width + ", widthChanged=" + z2 + ", heightChanged=" + z + ", fontSize=" + f5);
        if (z || z2) {
            textView.setLayoutParams(layoutParams);
        }
        boolean z3 = ViewCompat.v(textView) == 1;
        float f6 = this.t * f2;
        if (!z3) {
            f6 = -f6;
        }
        textView.setTranslationX(f6);
        float f7 = this.f17091k + i2;
        if (Math.abs(i2) == this.f17091k) {
            f4 = this.u;
        } else {
            f4 = (((f7 + this.f17092l) - this.f17093m) - (i4 + this.f17098r)) / 2.0f;
            float f8 = this.u;
            if (f4 <= f8) {
                f4 = f8;
            }
        }
        if (i2 != 0 || i3 <= (i5 = this.w)) {
            textView.setTranslationY(f4);
        } else {
            textView.setTranslationY(f4 + ((i3 - i5) * 0.2f));
        }
        if (this.z) {
            if (f2 < 0.2f) {
                f2 = 0.0f;
            }
            textView.setAlpha(f2);
        } else if (!this.y) {
            if (Math.abs(i2) == this.f17091k) {
                textView.setVisibility(4);
            } else {
                textView.setVisibility(0);
            }
        }
        ViewCompat.Z(textView);
        return true;
    }
}
