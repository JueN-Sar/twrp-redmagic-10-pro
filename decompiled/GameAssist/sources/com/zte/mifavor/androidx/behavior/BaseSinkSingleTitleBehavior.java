package com.zte.mifavor.androidx.behavior;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.appbar.AppBarLayout;
import com.zte.extres.R;
import com.zte.mifavor.utils.SinkUtils;

/* loaded from: classes2.dex */
public class BaseSinkSingleTitleBehavior extends BaseSinkTitleBehavior {

    /* renamed from: k, reason: collision with root package name */
    private int f17100k;

    /* renamed from: l, reason: collision with root package name */
    private int f17101l;

    /* renamed from: m, reason: collision with root package name */
    private int f17102m;

    /* renamed from: n, reason: collision with root package name */
    private int f17103n;

    /* renamed from: o, reason: collision with root package name */
    private float f17104o;

    /* renamed from: p, reason: collision with root package name */
    private float f17105p;

    /* renamed from: q, reason: collision with root package name */
    private float f17106q;

    /* renamed from: r, reason: collision with root package name */
    private boolean f17107r;

    /* renamed from: s, reason: collision with root package name */
    private int f17108s;
    private boolean t;
    private String u;

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean m(CoordinatorLayout coordinatorLayout, View view, View view2) {
        float f2;
        boolean z;
        boolean z2;
        int i2;
        if (this.f17104o <= 1.0f) {
            return false;
        }
        AppBarLayout appBarLayout = (AppBarLayout) view2;
        if (this.f17100k <= 0) {
            int totalScrollRange = appBarLayout.getTotalScrollRange();
            this.f17100k = totalScrollRange;
            this.f17108s = totalScrollRange + this.f17101l;
        }
        boolean z3 = this.f17100k > 0;
        if (!z3) {
            View childAt = appBarLayout.getChildAt(0);
            AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) childAt.getLayoutParams();
            childAt.getMeasuredHeight();
            int i3 = ((LinearLayout.LayoutParams) layoutParams).topMargin;
            int c2 = layoutParams.c();
            ViewCompat.s(childAt);
            if ((c2 & 2) != 0) {
                ViewCompat.w(childAt);
            }
        }
        int top = appBarLayout.getTop();
        if (z3) {
            f2 = (r9 + top) / this.f17100k;
        } else {
            f2 = 0.0f;
        }
        TextView textView = (TextView) view;
        int i4 = this.f17111i;
        if (Math.abs(top) == this.f17100k) {
            textView.setSingleLine();
            textView.setEllipsize(TextUtils.TruncateAt.END);
            i4 = Math.max(this.f17111i - this.f17109c, 0);
        } else if (Math.abs(top) < this.f17100k && textView.isSingleLine()) {
            textView.setSingleLine(false);
            textView.setMaxLines(3);
            textView.setEllipsize(TextUtils.TruncateAt.END);
        }
        if (Math.abs(top) == this.f17100k) {
            textView.setTextAppearance(R.style.mfvc_appbar_primary_font);
            int i5 = this.f17110h;
            if (i5 != 0) {
                textView.setTextColor(i5);
            }
        } else if (top == 0) {
            textView.setTextAppearance(R.style.mfvc_appbar_sink_primary_font);
            int i6 = this.f17110h;
            if (i6 != 0) {
                textView.setTextColor(i6);
            }
        }
        int i7 = this.f17103n;
        float f3 = ((i7 - r6) * f2) + this.f17102m;
        textView.setTextSize(0, f3);
        int b2 = SinkUtils.b(f3);
        if (SinkUtils.a(this.u, f3) > this.f17111i) {
            b2 = (b2 * 3) + 6;
        }
        CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) textView.getLayoutParams();
        if (((ViewGroup.MarginLayoutParams) layoutParams2).height != b2) {
            ((ViewGroup.MarginLayoutParams) layoutParams2).height = b2;
            z = true;
        } else {
            z = false;
        }
        if (((ViewGroup.MarginLayoutParams) layoutParams2).width != i4) {
            ((ViewGroup.MarginLayoutParams) layoutParams2).width = i4;
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || z2) {
            textView.setLayoutParams(layoutParams2);
            textView.invalidate();
        }
        boolean z4 = ViewCompat.v(textView) == 1;
        float f4 = this.f17105p * f2;
        if (!z4) {
            f4 = -f4;
        }
        textView.setTranslationX(f4);
        float f5 = (this.f17104o * f2) + this.f17106q;
        int bottom = appBarLayout.getBottom();
        if (top != 0 || bottom <= (i2 = this.f17108s)) {
            textView.setTranslationY(f5);
        } else {
            textView.setTranslationY(f5 + ((bottom - i2) * 0.2f));
        }
        if (this.t) {
            textView.setAlpha(f2 >= 0.2f ? f2 : 0.0f);
        } else if (!this.f17107r) {
            if (Math.abs(top) == this.f17100k) {
                textView.setVisibility(4);
            } else {
                textView.setVisibility(0);
            }
        }
        ViewCompat.Z(textView);
        return true;
    }
}
