package com.zte.mifavor.androidx.behavior;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.zte.mifavor.utils.SinkUtils;

/* loaded from: classes2.dex */
public abstract class BaseSinkGroupTitleBehavior extends BaseSinkTitleBehavior {

    /* renamed from: k, reason: collision with root package name */
    protected int f17091k;

    /* renamed from: l, reason: collision with root package name */
    protected int f17092l;

    /* renamed from: m, reason: collision with root package name */
    protected int f17093m;

    /* renamed from: n, reason: collision with root package name */
    protected int f17094n;

    /* renamed from: o, reason: collision with root package name */
    protected int f17095o;

    /* renamed from: p, reason: collision with root package name */
    protected int f17096p;

    /* renamed from: q, reason: collision with root package name */
    protected int f17097q;

    /* renamed from: r, reason: collision with root package name */
    protected int f17098r;

    /* renamed from: s, reason: collision with root package name */
    protected float f17099s;
    protected float t;
    protected float u;
    protected float v;
    protected int w;
    protected String x;

    protected abstract boolean K(CoordinatorLayout coordinatorLayout, View view, View view2, float f2, int i2, int i3, float f3, int i4);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean m(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (this.f17099s <= 1.0f) {
            return false;
        }
        AppBarLayout appBarLayout = (AppBarLayout) view2;
        if (this.f17091k <= 0) {
            int totalScrollRange = appBarLayout.getTotalScrollRange();
            this.f17091k = totalScrollRange;
            this.w = totalScrollRange + this.f17092l;
        }
        if (this.f17091k <= 0) {
            return K(coordinatorLayout, view, view2, 0.0f, 0, 0, this.f17094n, this.f17096p);
        }
        int top = appBarLayout.getTop();
        float f2 = (r1 + top) / this.f17091k;
        int bottom = appBarLayout.getBottom();
        int i2 = this.f17095o;
        float f3 = ((i2 - r1) * f2) + this.f17094n;
        int b2 = SinkUtils.b(f3);
        if (SinkUtils.a(this.x, f3) > this.f17111i) {
            b2 = (b2 * 2) + 6;
        }
        return K(coordinatorLayout, view, view2, f2, top, bottom, f3, b2);
    }
}
