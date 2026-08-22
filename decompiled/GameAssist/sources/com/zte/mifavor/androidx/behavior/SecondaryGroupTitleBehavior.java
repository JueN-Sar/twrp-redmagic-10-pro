package com.zte.mifavor.androidx.behavior;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

/* loaded from: classes2.dex */
public class SecondaryGroupTitleBehavior extends BaseSinkGroupTitleBehavior {
    private boolean y;
    private boolean z;

    @Override // com.zte.mifavor.androidx.behavior.BaseSinkGroupTitleBehavior
    protected boolean K(CoordinatorLayout coordinatorLayout, View view, View view2, float f2, int i2, int i3, float f3, int i4) {
        boolean z;
        boolean z2;
        float f4;
        int i5;
        TextView textView = (TextView) view;
        int i6 = this.f17112j;
        int i7 = this.f17098r;
        if (Math.abs(i2) == this.f17091k) {
            textView.setSingleLine();
            textView.setEllipsize(TextUtils.TruncateAt.END);
            i6 = Math.max(this.f17112j - this.f17109c, 0);
            i7 = this.f17097q;
        } else if (textView.isSingleLine()) {
            textView.setSingleLine(false);
            textView.setMaxLines(2);
            textView.setEllipsize(TextUtils.TruncateAt.END);
        }
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) textView.getLayoutParams();
        if (((ViewGroup.MarginLayoutParams) layoutParams).height != i7) {
            ((ViewGroup.MarginLayoutParams) layoutParams).height = i7;
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
        Log.d("BS#SGroupTitleBehavior", "onCustomDependentViewChanged, setLayoutParams height=" + ((ViewGroup.MarginLayoutParams) layoutParams).height + ", width=" + ((ViewGroup.MarginLayoutParams) layoutParams).width + ", widthChanged=" + z2 + ", heightChanged=" + z);
        if (z || z2) {
            textView.setLayoutParams(layoutParams);
        }
        boolean z3 = ViewCompat.v(textView) == 1;
        float f5 = this.t * f2;
        if (!z3) {
            f5 = -f5;
        }
        textView.setTranslationX(f5);
        float f6 = this.f17091k + i2;
        if (Math.abs(i2) == this.f17091k) {
            f4 = this.v;
        } else {
            f4 = (((f6 + this.f17092l) - this.f17093m) + (i4 - this.f17098r)) / 2.0f;
            float f7 = this.v;
            if (f4 <= f7) {
                f4 = f7;
            }
        }
        if (i2 != 0 || i3 <= (i5 = this.w)) {
            textView.setTranslationY(f4);
        } else {
            textView.setTranslationY(f4 + ((i3 - i5) * 0.2f));
        }
        if (!this.z && this.y) {
            textView.setVisibility(0);
        } else if (f2 < 0.5f) {
            textView.setVisibility(4);
        } else {
            textView.setAlpha((f2 - 0.5f) * 2.0f);
            textView.setVisibility(0);
        }
        ViewCompat.Z(textView);
        return true;
    }
}
