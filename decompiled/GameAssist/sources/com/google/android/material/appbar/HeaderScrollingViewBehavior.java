package com.google.android.material.appbar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.card.MaterialCardView;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.List;

/* loaded from: classes.dex */
abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View> {

    /* renamed from: j, reason: collision with root package name */
    final Rect f13886j;

    /* renamed from: k, reason: collision with root package name */
    final Rect f13887k;

    /* renamed from: l, reason: collision with root package name */
    private int f13888l;

    /* renamed from: m, reason: collision with root package name */
    private int f13889m;

    public HeaderScrollingViewBehavior() {
        this.f13886j = new Rect();
        this.f13887k = new Rect();
        this.f13888l = 0;
    }

    private static int S(int i2) {
        return i2 == 0 ? MaterialCardView.CHECKED_ICON_GRAVITY_TOP_START : i2;
    }

    @Override // com.google.android.material.appbar.ViewOffsetBehavior
    protected void K(CoordinatorLayout coordinatorLayout, View view, int i2) {
        View M = M(coordinatorLayout.v(view));
        if (M == null) {
            super.K(coordinatorLayout, view, i2);
            this.f13888l = 0;
            return;
        }
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
        Rect rect = this.f13886j;
        rect.set(coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, M.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, ((coordinatorLayout.getHeight() + M.getBottom()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        WindowInsetsCompat lastWindowInsets = coordinatorLayout.getLastWindowInsets();
        if (lastWindowInsets != null && ViewCompat.s(coordinatorLayout) && !ViewCompat.s(view)) {
            rect.left += lastWindowInsets.j();
            rect.right -= lastWindowInsets.k();
        }
        Rect rect2 = this.f13887k;
        GravityCompat.a(S(layoutParams.f2582c), view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i2);
        int N = N(M);
        view.layout(rect2.left, rect2.top - N, rect2.right, rect2.bottom - N);
        this.f13888l = rect2.top - M.getBottom();
    }

    abstract View M(List list);

    final int N(View view) {
        if (this.f13889m == 0) {
            return 0;
        }
        float O = O(view);
        int i2 = this.f13889m;
        return MathUtils.b((int) (O * i2), 0, i2);
    }

    float O(View view) {
        return 1.0f;
    }

    public final int P() {
        return this.f13889m;
    }

    int Q(View view) {
        return view.getMeasuredHeight();
    }

    final int R() {
        return this.f13888l;
    }

    public final void T(int i2) {
        this.f13889m = i2;
    }

    protected boolean U() {
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean r(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4, int i5) {
        View M;
        WindowInsetsCompat lastWindowInsets;
        int i6 = view.getLayoutParams().height;
        if ((i6 != -1 && i6 != -2) || (M = M(coordinatorLayout.v(view))) == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i4);
        if (size <= 0) {
            size = coordinatorLayout.getHeight();
        } else if (ViewCompat.s(M) && (lastWindowInsets = coordinatorLayout.getLastWindowInsets()) != null) {
            size += lastWindowInsets.l() + lastWindowInsets.i();
        }
        int Q = size + Q(M);
        int measuredHeight = M.getMeasuredHeight();
        if (U()) {
            view.setTranslationY(-measuredHeight);
        } else {
            view.setTranslationY(0.0f);
            Q -= measuredHeight;
        }
        coordinatorLayout.N(view, i2, i3, View.MeasureSpec.makeMeasureSpec(Q, i6 == -1 ? WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME : Integer.MIN_VALUE), i5);
        return true;
    }

    public HeaderScrollingViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f13886j = new Rect();
        this.f13887k = new Rect();
        this.f13888l = 0;
    }
}
